
## 数动支付结算服务介绍

`xudq-saas-paycenter`是一套基于微服务技术架构的支付结算服务系统，Spring Boot、MyBatis、Docker等核心技术快速搭建系统。

## 业务功能
1. 支持多种支付服务：如收款(充值)、消费、付款[、提现、退款]、转账、调账（长短款）、划拨、撤销、冲正(红冲)及退票；
2. 账户满足支持外部/内部账户分类，支持一主多分(子)账户灵活设计；
3. 支持多渠道支付服务（收款、付款、退款）对账、清分结算、资金划拨、补单、勾销；
4. 支持商户多账期灵活配置扎差、结算；

## 系统模块结构

``` lua
paycore
├── pay-core-api -- 支付结算服务接口
├── pay-core-base -- 支付结算服务基础支持
```

## 技术选型

### 后端技术

| 技术                 | 说明                | 官网                                                         |
| -------------------- | ------------------- | ------------------------------------------------------------ |
| Spring Boot          | 容器+MVC框架        | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot) |
| Spring Security      | 认证和授权框架      | [https://spring.io/projects/spring-security](https://spring.io/projects/spring-security) |
| MyBatis              | ORM框架             | [http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html) |
| MyBatisGenerator     | 数据层代码生成      | [http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html) |
| PageHelper           | MyBatis物理分页插件 | [http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper) |
| RabbitMq             | 消息队列            | [https://www.rabbitmq.com/](https://www.rabbitmq.com/)       |
| Redis                | 分布式缓存          | [https://redis.io/](https://redis.io/)                       | MongoDb              | NoSql数据库         | [https://www.mongodb.com/](https://www.mongodb.com/)         |
| Docker               | 应用容器引擎        | [https://www.docker.com/](https://www.docker.com/)           |
| Druid                | 数据库连接池        | [https://github.com/alibaba/druid](https://github.com/alibaba/druid) |
| OSS                  | 对象存储            | [https://github.com/aliyun/aliyun-oss-java-sdk](https://github.com/aliyun/aliyun-oss-java-sdk) |


## 环境搭建

### 开发环境

工具 | 版本号 | 下载
----|----|----
JDK | 1.8 | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Mysql | 5.8 | https://www.mysql.com/
Redis | 3.2 | https://redis.io/download
RabbitMq | 3.7.14 | http://www.rabbitmq.com/download.html
nginx | 1.10 | http://nginx.org/en/download.html


### 联系方式
Email：2558404588@qq.com
