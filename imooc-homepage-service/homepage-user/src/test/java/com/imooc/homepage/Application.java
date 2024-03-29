package com.imooc.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <h1>测试用例启动入口</h1>
 *
 * @author 汪永晖
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.imooc.homepage"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
