### 路由(Router)
```yaml
zuul:
  routes:
    # 转发服务id
    api-example-serviceid:
      path: /eureka-consumer/**
      service-id: eureka-consumer
    # 转发网址
    api-example-website:
      path: /baidu/**
      service-id: https://www.baidu.com/
    # 默认自动配置的转发规则：地址转换为服务id，类似api-example-serviceid
```
### 过滤器(Filter)
Filter 的生命周期有 4 个，分别是 “PRE”、“ROUTING”、“POST” 和 “ERROR”，整个生命周期可以用下图来表示
![Filter 的生命周期](https://src.windmt.com/img/006tNc79ly1fqmg1wtyhdj30pl0fqdgt.jpg)
- **PRE**：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
- **ROUTING**：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用 Apache HttpClient 或 Netfilx Ribbon 请求微服务。
- **POST**：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的 HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
- **ERROR**：在其他阶段发生错误时执行该过滤器。 除了默认的过滤器类型，Zuul 还允许我们创建自定义的过滤器类型。例如，我们可以定制一种 STATIC 类型的过滤器，直接在 Zuul 中生成响应，而不将请求转发到后端的微服务。