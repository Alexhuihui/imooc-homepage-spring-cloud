package com.imooc.homepage.repository;

import com.imooc.homepage.entity.HomepageUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 汪永晖
 */
public interface HomepageUserRepository extends JpaRepository<HomepageUser, Long> {

    /**
     * <h2>通过用户名寻找数据记录</h2>
     * @param username 用户名
     * @return User 信息
     */
    HomepageUser findByUsername(String username);
}
