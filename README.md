# SpringCloudStudy
> SpringCloud-Finchley研究测试
## 模块解释
### eureka-server 注册中心
- [eureka-server](eureka-server)
- 集群启动
  - `java -jar target/eureka-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer1`
  - `java -jar target/eureka-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer2`
  - `java -jar target/eureka-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer3`
---
### eureka-producer 生产者
- [eureka-producer](eureka-producer)
- 集群启动
    - ` java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --config.producer.instance=1 --server.port=8001`
    - ` java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --config.producer.instance=2 --server.port=8002`
---
### eureka-consumer 消费者
> 测试三种web服务客户端（LoadBalancerClient、Ribbon、Feign）
- [eureka-consumer](eureka-consumer)
---
### hystrix 服务容错保护
> 使用feign加入hystrix<br/>
> 通过服务降级、资源隔离、断路器方式来防止服务不可用
- [eureka-consumer-feign-hystrix](eureka-consumer-feign-hystrix)
---
### Hystrix 监控面板
> 只测试了单节点监控，集群需要加入Turbine，未测试
-  [hystrix-dashboard](hystrix-dashboard)
---
### config-server-git 配置中心（Git 版与动态刷新）
> 使用配置中心远程读取github上的配置文件
- [config-server-git](config-server-git)
- 构建了 config-server，连接到 Git 仓库
- 在 Git 上创建了一个 config-repo 目录，用来存储配置信息
- ` java -jar target/config-server-git-0.0.1-SNAPSHOT.jar --server.port=12000`
- ` java -jar target/config-server-git-0.0.1-SNAPSHOT.jar --server.port=12001`
---
### config-client 配置中心客户端（Git 版与动态刷新）
- [config-client](config-client)
- 构建了 config-client，来获取 Git 中的配置信息
- 在 config-client 中开启了 Refresh，动态刷新配置信息
---

---
## 教程
### 1. 服务提供与调用 Eureka1
> 集成了
- 流程
    1. 启动注册中心
    2. 服务提供者生产服务并注册到服务中心中
    3. 消费者从服务中心中获取服务并执行
- 启动
    1. 集群启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 集群启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 集群启动消费者 [eureka-consumer 消费者](#eureka-consumer-消费者)
    4. 访问消费者-LoadBalancer <http://localhost:9000/LBCHello/?name=jason>
    5. 访问消费者-Ribbon <http://localhost:9000/RibbonHello/?name=jason>
    6. 访问消费者-LoadBalancer <http://localhost:9000/FeignHello/?name=jason>

### 2. 服务容错保护 Hystrix
- 启动
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 启动Hystrix [hystrix 服务容错保护](#hystrix-服务容错保护)
    4. 访问 <http://localhost:9003/FeignHystrixHello/?name=jason>,确认正常
    5. 手动停止eureka-producer，访问<http://localhost:9003/FeignHystrixHello/?name=jason>，确认已经熔断
    6. 启动eureka-producer，再次访问<http://localhost:9003/FeignHystrixHello/?name=jason>，发现正常
- 总结
    1. 通过使用 Hystrix，我们能方便的防止雪崩效应，同时使系统具有自动降级和自动恢复服务的效果。
- 监控面板
    1. 启动监控面板[Hystrix 监控面板](#hystrix-监控面板)
    2. 访问<http://localhost:11000/hystrix>
    3. 启动注册中心[eureka-server 注册中心](#eureka-server-注册中心)、生产者[eureka-producer 生产者](#eureka-producer-生产者)、服务容错启动Hystrix [hystrix 服务容错保护](#hystrix-服务容错保护)
    4. 在步骤2的界面上输入<http://localhost:9003/actuator/hystrix.stream>，点击Monitor Stream
    5. 可以通过手动停止生产者进行测试

### 3. 配置中心（Git 版与动态刷新)
- 启动
  1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
  2. 启动配置中心[config-server-git 配置中心（Git 版与动态刷新）](#config-server-git-配置中心git-版与动态刷新)
  3. 查看是否读取到数据，访问<http://localhost:12000/config-client/dev/master>
  4. 启动客户端来读取数据[config-client 配置中心客户端（Git 版与动态刷新）](#config-client-配置中心客户端git-版与动态刷新)
  5. 访问<http://localhost:13000/info>来查看数据
  6. 当数据刷新后手动刷新：post执行 /actuator/refresh 刷新变量值`curl -X POST http://localhost:13000/actuator/refresh`
  7. 需要高可用的话，启动多个配置中心就可以了，代码已经支持

### 4. 配置中心(消息总线)
### 6. 服务提供与调用 Eureka1
### 7. 服务提供与调用 Eureka1
### 8. 服务提供与调用 Eureka1
### 9. 服务提供与调用 Eureka1


