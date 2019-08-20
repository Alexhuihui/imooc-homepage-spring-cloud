package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.Application;
import com.imooc.homepage.entity.HomepageUserCourse;
import com.imooc.homepage.repository.HomepageUserCourseRepository;
import com.imooc.homepage.vo.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <h1>用户服务测试用例</h1>
 *
 * @author 汪永晖
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class HomepageUserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private HomepageUserCourseRepository homepageUserCourseRepository;

    @Test
//    @Transactional
    public void testCreateUser() {

        System.out.println(JSON.toJSONString(
                userService.createUser(new CreateUserRequest(
                        "alex", "2930807240@qq.com"
                ))
        ));
    }

    @Test
    public void testGetUserInfo() {

        System.out.println(JSON.toJSONString(
                userService.getUserInfo(9L)
        ));
    }

    @Test
    public void testCreateHomepageCourse() {

        HomepageUserCourse course1 = new HomepageUserCourse();
        course1.setUserId(9L);
        course1.setCourseId(8L);

        HomepageUserCourse course2 = new HomepageUserCourse();
        course2.setUserId(9L);
        course2.setCourseId(9L);

        System.out.println(homepageUserCourseRepository.saveAll(
                Arrays.asList(course1, course2)
        ).size());
    }
}
