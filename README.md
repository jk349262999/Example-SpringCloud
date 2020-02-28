# SpringCloudStudy
> SpringCloud-Finchley研究测试
## eureka-server 注册中心
### 集群启动
- `java -jar target/eureka-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer1`
- `java -jar target/eureka-server-0.0.1-SNAPSHOT.jar  --spring.profiles.active=peer2`
## eureka-producer eureka客户端-生产者
### 集群启动
- ` java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --config.producer.instance=1 --server.port=8001`
- ` java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --config.producer.instance=2 --server.port=8002`

## eureka-consumer eureka客户端-消费者
> 测试三种web服务客户端（LoadBalancerClient、Ribbon、Feign）

## eureka-consumer-feign-hystrix hystrix服务容错保护
> 使用feign加入hystrix

## hystrix-dashboard Hystrix 监控面板
> 只测试了单节点监控，集群需要加入Turbine，未测试

## config-server-git 配置中心（Git 版与动态刷新） 
> 使用配置中心远程读取github上的配置文件
- 加入了服务化与高可用
- ` java -jar target/config-server-git-0.0.1-SNAPSHOT.jar --server.port=12000`
- ` java -jar target/config-server-git-0.0.1-SNAPSHOT.jar --server.port=12001`

