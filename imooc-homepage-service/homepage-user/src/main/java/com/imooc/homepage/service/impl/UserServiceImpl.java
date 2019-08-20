package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.client.CourseClient;
import com.imooc.homepage.entity.HomepageUser;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.repository.HomepageUserCourseRepository;
import com.imooc.homepage.repository.HomepageUserRepository;
import com.imooc.homepage.service.IUserService;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1>用户相关服务实现</h1>
 *
 * @author 汪永晖
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final HomepageUserRepository homepageUserRepository;

    private final HomepageUserCourseRepository homepageUserCourseRepository;

    private final CourseClient courseClient;

    @Autowired
    public UserServiceImpl(HomepageUserRepository homepageUserRepository, HomepageUserCourseRepository homepageUserCourseRepository, CourseClient courseClient) {
        this.homepageUserRepository = homepageUserRepository;
        this.homepageUserCourseRepository = homepageUserCourseRepository;
        this.courseClient = courseClient;
    }


    /**
     * <h2>创建用户</h2>
     *
     * @param request 创建用户的请求
     * @return 成功创建的用户
     */
    @Override
    public UserInfo createUser(CreateUserRequest request) {

        if (!request.validate()) {
            return UserInfo.invalid();
        }

        HomepageUser oldUser = homepageUserRepository.findByUsername(request.getUsername());

        if (null != oldUser) {
            return UserInfo.invalid();
        }

        HomepageUser newUser = homepageUserRepository.save(
                new HomepageUser(request.getUsername(), request.getEmail())
        );

        return new UserInfo(newUser.getId(), newUser.getUsername(), newUser.getEmail());
    }

    /**
     * <h2>根据 id 获取用户信息</h2>
     *
     * @param id 用户的主键
     * @return 所要查询的用户信息
     */
    @Override
    public UserInfo getUserInfo(long id) {

        Optional<HomepageUser> user = homepageUserRepository.findById(id);
        if (!user.isPresent()) {
            return UserInfo.invalid();
        }

        HomepageUser curUser = user.get();

        return new UserInfo(curUser.getId(), curUser.getUsername(), curUser.getEmail());
    }

    /**
     * <h2>获取用户和课程信息</h2>
     *
     * @param id
     * @return
     */
    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {

        Optional<HomepageUser> user = homepageUserRepository.findById(id);
        if (!user.isPresent()) {
            return UserCourseInfo.invalid();
        }

        HomepageUser homepageUser = user.get();
        UserInfo userInfo = new UserInfo(
                homepageUser.getId(),
                homepageUser.getUsername(),
                homepageUser.getEmail()
        );

        List<HomepageUserCourse> userCourses =
                homepageUserCourseRepository.findAllByUserId(id);

        if (CollectionUtils.isEmpty(userCourses)) {
            return new UserCourseInfo(userInfo, Collections.emptyList());
        }

        // 通过 Feign 访问 course 服务获取 courseInfos
        List<CourseInfo> courseInfoList = courseClient.getCourseInfos(
                new CourseInfosRequest(userCourses.stream()
                        .map(HomepageUserCourse::getCourseId)
                        .collect(Collectors.toList()))
        );

        return new UserCourseInfo(userInfo, courseInfoList);
    }
}
