# imooc-homepage-spring-cloud
### 多节点启动Eureka Server
1. mvn clean package -Dmaven.test.skip=true -U
2. java -jar homepage-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=server1
```yaml
spring:
  application:
    name: homepage-eureka
  profiles: server1
server:
  port: 8000
eureka:
  instance:
    hostname: server1
    prefer-ip-address: false
  client:
    service-url:
      defaultZone: http://server2:8001/eureka/,http://server3:8002/eureka/

---
spring:
  application:
    name: homepage-eureka
  profiles: server2
server:
  port: 8001
eureka:
  instance:
    hostname: server2
    prefer-ip-address: false
  client:
    service-url:
      defaultZone: http://server1:8000/eureka/,http://server3:8002/eureka/

---
spring:
  application:
    name: homepage-eureka
  profiles: server3
server:
  port: 8002
eureka:
  instance:
    hostname: server3
    prefer-ip-address: false
  client:
    service-url:
      defaultZone: http://server1:8000/eureka/,http://server2:8001/eureka/
```

### 服务网关
1. 启动类的注解
```java
/**
 *
 * 网关应用程序
 * EnableZuulProxy：标示当前的应用是 Zuul Server
 * SpringCloudApplication：用于简化配置的组合注解
 * @author 汪永晖
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
```
2. 自定义过滤器，记录请求时间
    1. RequestContext ctx = RequestContext.getCurrentContext();
    2. 先获取请求上下文，这是用来在过滤器之间通信用的
    3. ctx.set("startTime", System.currentTimeMillis());
    4. 然后设置key-value
    5. 获取value值打印日志
    
3. 最后的filterOrder应该比FilterConstants.SEND_RESPONSE_FILTER_ORDER小，保证在其返回之前运行

### 公共模块
1. 为了微服务之间的调用能够使用同一个对象，而不是在每个微服务中都定义一遍
2. 本项目的公共模块定义了3个类：课程信息、用户信息和课程信息请求对象

