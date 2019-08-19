package com.imooc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.CourseInfosRequest;
import com.imooc.homepage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1>课程对外服务接口</h1>
 *
 * @author 汪永晖
 */
@Slf4j
@RestController
public class HomepageCourseController {

    /**
     * 课程服务接口
     */
    private final ICourseService iCourseService;

    @Autowired
    public HomepageCourseController(ICourseService iCourseService) {
        this.iCourseService = iCourseService;
    }

    @GetMapping("/get/course")
    public CourseInfo getCourseInfo(Long id) {
        log.info("<homepage-course>: get course -> {}", id);
        return iCourseService.getCourseInfo(id);
    }

    @PostMapping("/get/courses")
    public List<CourseInfo> getCourseInfos(
            @RequestBody CourseInfosRequest request) {

        log.info("<homepage-course>: get courses -> {}", JSON.toJSONString(request));
        return iCourseService.getCourseInfos(request);
    }

}
