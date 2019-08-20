package com.imooc.homepage.service;

import com.imooc.homepage.UserInfo;
import com.imooc.homepage.vo.CreateUserRequest;
import com.imooc.homepage.vo.UserCourseInfo;

/**
 * <h1>用户相关服务接口定义</h1>
 *
 * @author 汪永晖
 */
public interface IUserService {

    /**
     * <h2>创建用户</h2>
     *
     * @param request 创建用户的请求
     * @return 成功创建的用户
     */
    UserInfo createUser(CreateUserRequest request);


    /**
     * <h2>根据 id 获取用户信息</h2>
     *
     * @param id 用户的主键
     * @return 所要查询的用户信息
     */
    UserInfo getUserInfo(long id);

    /**
     * <h2>获取用户和课程信息</h2>
     * @param id
     * @return
     */
    UserCourseInfo getUserCourseInfo(Long id);
}
