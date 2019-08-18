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

