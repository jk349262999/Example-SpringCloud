# SpringCloudStudy
> SpringCloud-Example研究测试
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
- 服务降级未使用
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
- [config-client](config-client-git)
- 构建了 config-client，来获取 Git 中的配置信息
- 在 config-client 中开启了 Refresh，动态刷新配置信息
---
### config-server-bus配置中心（bus动态刷新）
- [config-server-bus](config-server-bus)
- 此实例集成配置中心、git远程资源库、bus
- 通过刷新来控制bus进行消息群体推送
- 可集群启动
- 刷新配置命令
    - **整体**刷新命令`curl -X POST http://localhost:13000/actuator/bus-refresh/`
    - **局部**刷新命令`curl -X POST http://localhost:13000/actuator/bus-refresh/{destination}`
    - > destination 参数来定位要刷新的应用程序,例如/actuator/bus-refresh/config-client-bus:13000,如果要刷新特定的微服务`config-client-bus:**`
---
### config-client-bus配置中心（bus动态刷新）
- [config-client-bus](config-client-bus)
- 此实例集成配置中心、bus
- 可集群
---
### gateway-zuul网关(Zuul)
- [gateway-zuul](gateway-zuul)
- 服务默认转发规则:
  - 通过服务ID转发,转发到 producer 服务的请求规则为：`/producer/**`,转发到 consumer 服务的请求规则为：`/consumer/**`
  - <http://localhost:9000/FeignHello/index?name=jason>转换为<http://localhost:14000/eureka-consumer/FeignHello/index?name=jason>
- 也可以手动配置路由转发规则
---
### 分布式链路跟踪 Sleuth与Zipkin
- [sleuth](sleuth)
- Sleuth
  - 为服务之间调用提供链路追踪,然后存储到Zipkin
  - 提供内容
    - 耗时分析：通过 Sleuth 可以很方便的了解到每个采样请求的耗时，从而分析出哪些服务调用比较耗时
    - 可视化错误：对于程序未捕捉的异常，可以通过集成 Zipkin 服务界面上看到
    - 链路优化：对于调用比较频繁的服务，可以针对这些服务实施一些优化措施
- Zipkin
  - 提供存储、展示数据功能
  - 安装方式
      - java8以上
        - `curl -sSL https://zipkin.io/quickstart.sh | bash -s`
        - `java -jar zipkin.jar`
      - docker
        - docker run -d -p 9411:9411 openzipkin/zipkin
  - 访问<http://localhost:9411>查看监控平台
  - 存储方式使用了In-Memory
---
### 服务网关 Gateway
- [gateway](gateway)
- 路由，配置见[README](gateway/README.md) > routes配置
- 过滤
- 限流
- 集成Hystrix

---
---

## 教程
### 1. 服务提供与调用 Eureka
> 集成了
- 流程
    1. 启动注册中心
    2. 服务提供者生产服务并注册到服务中心中
    3. 消费者从服务中心中获取服务并执行
- 启动
    1. 集群启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 集群启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 集群启动消费者 [eureka-consumer 消费者](#eureka-consumer-消费者)
    4. 访问消费者-LoadBalancer <http://localhost:9000/LBCHello/index?name=jason>
    5. 访问消费者-Ribbon <http://localhost:9000/RibbonHello/index?name=jason>
    6. 访问消费者-LoadBalancer <http://localhost:9000/FeignHello/index?name=jason>

### 2. 服务容错保护 Hystrix
- 启动
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 启动Hystrix [hystrix 服务容错保护](#hystrix-服务容错保护)
    4. 访问 <http://localhost:9003/FeignHystrixHello/index?name=jason>,确认正常
    5. 手动停止eureka-producer，访问<http://localhost:9003/FeignHystrixHello/index?name=jason>，确认已经熔断
    6. 启动eureka-producer，再次访问<http://localhost:9003/FeignHystrixHello/index?name=jason>，发现正常
- 总结
    1. 通过使用 Hystrix，我们能方便的防止雪崩效应，同时使系统具有自动降级和自动恢复服务的效果。
- 监控面板
    1. 启动监控面板[Hystrix 监控面板](#hystrix-监控面板)
    2. 访问<http://localhost:11000/hystrix>
    3. 启动注册中心[eureka-server 注册中心](#eureka-server-注册中心)、生产者[eureka-producer 生产者](#eureka-producer-生产者)、服务容错启动Hystrix [hystrix 服务容错保护](#hystrix-服务容错保护)
    4. 在步骤2的界面上输入<http://localhost:9003/actuator/hystrix.stream>，点击Monitor Stream
    5. 可以通过手动停止生产者进行测试

### 3. 配置中心（Git 版与动态刷新、bus动态刷新)
- 低配版
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动配置中心 [config-server-git 配置中心（Git 版与动态刷新）](#config-server-git-配置中心git-版与动态刷新)
    3. 查看是否读取到数据，访问<http://localhost:12000/config-client/dev/master>
    4. 启动客户端来读取数据[config-client 配置中心客户端（Git 版与动态刷新）](#config-client-配置中心客户端git-版与动态刷新)
    5. 访问<http://localhost:13000/info>来查看数据
    6. 修改git仓库文件config-client-dev的neo的value、然后手动刷新：post执行 /actuator/refresh 刷新变量值`curl -X POST http://localhost:13000/actuator/refresh`
    7. 需要高可用的话，启动多个配置中心服务端就可以了，代码已经支持
- 加入bus
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动配置中心 [config-server-bus配置中心（bus动态刷新）](#config-server-bus配置中心bus动态刷新)
    3. 验证数据<http://localhost:12000/config-client/dev>
    4. 集群启动客户端[config-client-bus配置中心（bus动态刷新）](#config-client-bus配置中心bus动态刷新)
    5. 验证数据<http://localhost:13000/info>,<http://localhost:13001/info>
    6. 修改git仓库文件config-client-dev的neo的value，然后手动整体刷新`curl -X POST http://localhost:12000/actuator/refresh`
    7. 查看数据，发现两个客户端数据都改变了
    8. 需要验证局部刷新直接看[config-server-bus配置中心（bus动态刷新）](#config-server-bus配置中心bus动态刷新)

### 4. 服务网关 Zuul
- 路由(Router)、过滤器(Filter)
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 启动消费者 [eureka-consumer 消费者](#eureka-consumer-消费者)
    4. 启动网关[gateway-zuul网关(Zuul)](#gateway-zuul网关zuul)
    5. 通过网关访问eureka-consumer的服务,<http://localhost:14000/eureka-consumer/FeignHello/index?name=jason>
    6. 返回token is empty,证明过滤器已工作,加入token参数，访问<http://localhost:14000/eureka-consumer/FeignHello/index?name=jason&token=xx>
    7. 测试正常

### 5. 链路追踪 sleuth
- 测试
    1. 下载并启动zipkin服务端,命令见[分布式链路跟踪 Sleuth与Zipkin](#分布式链路跟踪-sleuth与zipkin)
    2. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    3. 启动[trace-a](sleuth/trace-a),[trace-b](sleuth/trace-b)
    4. 访问<http://localhost:15001/trace-a>
    5. 访问<http://localhost:9411/zipkin>查看调用过程

### 6. 网关gateway
- 路由
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 启动消费者 [eureka-consumer 消费者](#eureka-consumer-消费者)
    4. 启动网关[服务网关 Gateway](#服务网关-gateway)
    5. before_route `curl -H 'X-Request-Id:1' localhost:14100`
    6. header_route `curl -H 'X-Request-Id:1' localhost:14100`
    7. cookie_route `curl -H 'Cookie:name=forezp' localhost:14100`
    8. path_router <http://localhost:14100/path_route/s?wd=path_route>
    9. 内部服务路由 <http://localhost:14100/to_client/FeignHello/index?name=jason>
    10. Java 的流式 API 进行路由的定义<http://localhost:14100/java/customer/FeignHello/index?name=jason>
    11. 测试框架自动生成的路由 <http://localhost:14100/eureka-consumer/FeignHello/index?name=jason&token=1>
- 过滤
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 启动消费者 [eureka-consumer 消费者](#eureka-consumer-消费者)
    4. 启动网关[服务网关 Gateway](#服务网关-gateway)
    5. 自定义局部过滤器
        - [TokenFilter.java](gateway/src/main/java/cn/jason/gateway/filter/TokenFilter.java)
        - <http://localhost:14100/fluent/customer/FeignHello/index?name=jason&token=1>
    6. 自定义全局过滤器
        - [TimeCostFilter.java](gateway/src/main/java/cn/jason/gateway/filter/TimeCostFilter.java)
        - 访问任意地址 <http://localhost:14100/path_route/s?wd=path_route> 查看后台输出
    7. 过滤器工厂
        - [PrintParamsGatewayFilterFactory.java](gateway/src/main/java/cn/jason/gateway/filter/PrintParamsGatewayFilterFactory.java)
        - 访问任意地址 <http://localhost:14100/path_route/s?wd=path_route> 查看后台输出
- 限流
    1. 启动注册中心 [eureka-server 注册中心](#eureka-server-注册中心)
    2. 启动生产者 [eureka-producer 生产者](#eureka-producer-生产者)
    3. 启动消费者 [eureka-consumer 消费者](#eureka-consumer-消费者)
    4. 启动网关[服务网关 Gateway](#服务网关-gateway)
    5. 自定义限流 运行[CustomerRouteTest.java](gateway/src/test/java/cn/jason/gateway/router/CustomerRouteTest.java)，查看后台输出
    6. 架构自带限流 访问并不停刷新查看后台输出<http://localhost:14100/limit/customer/FeignHello/index?name=jason&token=1>
