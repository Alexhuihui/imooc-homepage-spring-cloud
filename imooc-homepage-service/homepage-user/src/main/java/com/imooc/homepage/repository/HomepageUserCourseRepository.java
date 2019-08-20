package com.imooc.homepage.repository;

import com.imooc.homepage.entity.HomepageUserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>HomepageUserCourse 数据表访问接口定义</h1>
 * @author 汪永晖
 */
public interface HomepageUserCourseRepository extends JpaRepository<HomepageUserCourse, Long> {

    /**
     * <h2>通过用户 id 寻找数据记录</h2>
     * @param userId 用户 id
     * @return 该用户 id 下的所有课程
     */
    List<HomepageUserCourse> findAllByUserId(Long userId);
}
