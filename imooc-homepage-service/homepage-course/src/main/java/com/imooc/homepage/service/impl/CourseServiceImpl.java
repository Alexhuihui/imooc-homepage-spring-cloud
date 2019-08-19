package com.imooc.homepage.service.impl;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.entity.HomepageCourse;
import com.imooc.homepage.repository.HomepageCourseRepository;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h1>课程服务功能实现</h1>
 *
 * @author 汪永晖
 */
@Slf4j
@Service
public class CourseServiceImpl implements ICourseService {

    private final HomepageCourseRepository homepageCourseRepository;

    @Autowired
    public CourseServiceImpl(HomepageCourseRepository homepageCourseRepository) {
        this.homepageCourseRepository = homepageCourseRepository;
    }

    /**
     * <h2>通过 id 获取课程信息</h2>
     */
    @Override
    public CourseInfo getCourseInfo(Long id) {

        Optional<HomepageCourse> course = homepageCourseRepository.findById(id);

        return buildCourseInfo(course.orElse(HomepageCourse.invalid()));
    }

    /**
     * <h2>通过 ids 获取课程信息</h2>
     */
    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {

        if(CollectionUtils.isEmpty(request.getIds())) {
            return Collections.emptyList();
        }

        List<HomepageCourse> courseList = homepageCourseRepository.findAllById(
                request.getIds());

        return courseList.stream()
                .map(this::buildCourseInfo)
                .collect(Collectors.toList());
    }

    /**
     * <h2>根据数据记录构造对象信息</h2>
     */
    private CourseInfo buildCourseInfo(HomepageCourse course) {

        return CourseInfo.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType() == 0 ? "免费课程" : "实战课程")
                .courseIcon(course.getCourseIcon())
                .courseIntro(course.getCourseIntro())
                .build();
    }
}
