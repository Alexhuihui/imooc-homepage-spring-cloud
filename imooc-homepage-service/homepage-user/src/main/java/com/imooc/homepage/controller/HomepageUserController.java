package com.imooc.homepage.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.homepage.UserInfo;
import com.imooc.homepage.service.IUserService;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>用户服务对外接口</h1>
 *
 * @author 汪永晖
 */
@Slf4j
@RestController
public class HomepageUserController {

    private final IUserService userService;

    @Autowired
    public HomepageUserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public UserInfo createUser(CreateUserRequest request) {
        log.info("<homepage-user>: create user -> {}",
                JSON.toJSONString(request));

        return userService.createUser(request);
    }

    @GetMapping("/get/user")
    public UserInfo getUserInfo(Long id) {
        log.info("<homepage-user>: get user -> {}", id);

        return userService.getUserInfo(id);
    }

    @GetMapping("/get/user/course")
    public UserCourseInfo getUserCourseInfo (Long id) {
        log.info("<homepage-user>: get user course info -> {}", id);

        return userService.getUserCourseInfo(id);
    }
}
