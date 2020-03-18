### predicate简介

>Predict的源码在org.springframework.cloud.gateway.handler.predicate包中
- ![predicate断言](http://jason_jin.gitee.io/picturebed/upload_images/RoutePredicateFactory.png)

>在上图中，有很多类型的Predicate,  
比如说时间类型的Predicated（AfterRoutePredicateFactory BeforeRoutePredicateFactory BetweenRoutePredicateFactory），  
当只有满足特定时间要求的请求会进入到此predicate中，并交由router处理；  
cookie类型的CookieRoutePredicateFactory，指定的cookie满足正则匹配，才会进入此router;  
以及host、method、path、querparam、remoteaddr类型的predicate，每一种predicate都会对当前的客户端请求进行判断，是否满足当前的要求，如果满足则交给当前请求处理。  
如果有很多个Predicate，并且一个请求满足多个Predicate，则按照配置的**顺序第一个生效**

### routes配置
- java流式 API 进行路由的定义[CustomerRoute.java](src/main/java/cn/jason/gateway/router/CustomerRoute.java)
- 配置文件见[application.yml](src/main/resources/application.yml)

### 过滤器
#### 自带过滤器
![过滤器工厂](http://jason_jin.gitee.io/picturebed/upload_images/GatewayFilterFactories.png)
#### 自定义过滤器
- [TimeCostFilter.java](src/main/java/cn/jason/gateway/filter/TimeCostFilter.java)
- [TokenFilter.java](src/main/java/cn/jason/gateway/filter/TokenFilter.java)
> 实现GlobalFilter，注册成Bean就可以了
#### 自定义过滤器工厂
- [PrintParamsGatewayFilterFactory.java](src/main/java/cn/jason/gateway/filter/PrintParamsGatewayFilterFactory.java)
- 需要配置spring.cloud.gateway.default-filters,实例见[application.yml](src/main/resources/application.yml)
### 限流
- 使用令牌桶算法（Token Bucket）
  - ![令牌桶](http://jason_jin.gitee.io/picturebed/upload_images/TokenBucket.png)
  - 随着时间流逝，系统会按恒定 1/QPS 时间间隔（如果 QPS=100，则间隔是 10ms）往桶里加入 Token（想象和漏洞漏水相反，有个水龙头在不断的加水），如果桶已经满了就不再加了。新请求来临时，会各自拿走一个 Token，如果没有 Token 可拿了就阻塞或者拒绝服务
  - 变量
    - capacity：桶的最大容量，即能装载 Token 的最大数量
    - refillTokens：每次 Token 补充量
    - refillDuration：补充 Token 的时间间隔
- Gateway通过过滤器来实现
  - 自定义限流
    - 使用了简单的Map来存储bucket,通过java配置来注入
    - 配置文件的未研究，理论上应该和filter是一样的
  - 自带限流
    - 需引用reactive Redis,自带限流工厂需使用Redis
    - 需要实现KeyResolver通过KEY来进行判断
    - 配置见下面
```yaml
# 加入到router的key中
- name: RequestRateLimiter
  args:
    # 用于限流的键的解析器的 Bean 对象名字
    key-resolver: '#{@remoteAddrKeyResolver}'
    # 令牌桶每秒填充平均速率
    redis-rate-limiter.replenishRate: 1
    # 令牌桶容量
    redis-rate-limiter.burstCapacity: 5
```

