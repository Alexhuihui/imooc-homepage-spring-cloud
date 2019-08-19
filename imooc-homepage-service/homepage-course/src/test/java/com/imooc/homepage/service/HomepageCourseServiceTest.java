package com.imooc.homepage.service;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.Application;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.entity.HomepageCourse;
import com.imooc.homepage.repository.HomepageCourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * <h1>课程服务测试</h1>
 *
 * @author 汪永晖
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HomepageCourseServiceTest {

    @Autowired
    private HomepageCourseRepository homepageCourseRepository;

    @Autowired
    private ICourseService courseService;

//    @Test
    public void testCreateCourseInfo() {

        HomepageCourse course1 = new HomepageCourse(
                "Python入门",
                0,
                "www.picture.com/1",
                "学习Python"
        );

        HomepageCourse course2 = new HomepageCourse(
                "微服务广告系统的设计与实现",
                1,
                "www.picture.com/2",
                "广告系统的设计与实现"
        );

        System.out.println(homepageCourseRepository.saveAll(
                Arrays.asList(course1, course2)
        ).size());

    }

    @Test
    public void testGetCourseInfo() {
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfo(8L)
        ));
    }

    @Test
    public void testGetCourseInfos() {
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfos(
                        new CourseInfosRequest(Arrays.asList(8L, 9L))
                )
        ));
    }
}
