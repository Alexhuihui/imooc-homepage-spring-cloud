package com.imooc.homepage.vo;

import com.imooc.homepage.CourseInfo;
import com.imooc.homepage.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * <h1>用户课程信息对象定义</h1>
 * @author 汪永晖
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCouserInfo {

    private UserInfo userInfo;

    private List<CourseInfo> courseInfos;

    public static UserCouserInfo invalid() {

        return new UserCouserInfo(
                UserInfo.invalid(),
                Collections.emptyList()
        );
    }
}
