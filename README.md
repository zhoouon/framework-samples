# zhoouon-samples

基于 Spring Boot 3 的框架集成示例仓库，包含如下模块：

| 模块 | 说明 |
| --- | --- |
| zhoouon-dependencies | Maven BOM，统一管理三方依赖版本 |
| zhoouon-starter | 自研 Spring Boot Starter 集合 |
| zhoouon-concurrent | 并发编程示例 |
| zhoouon-lock | JUC 锁示例 |
| zhoouon-netty | Netty/NIO 示例 |
| zhoouon-proxy | JDK/CGLIB 动态代理示例 |
| zhoouon-redis | Redisson 分布式锁、布隆过滤器、WebSocket 示例 |
| zhoouon-minio | MinIO 文件操作示例 |
| zhoouon-shardingsphere | ShardingSphere 分库分表 + Seata 示例 |
| zhoouon-seata | Seata AT 模式分布式事务示例 |
| zhoouon-webflux | WebFlux/SSE 示例 |

## 运行准备

1. 安装 JDK 17 与 Maven 3.9+。
2. 将项目导入 IDEA（Maven 会自动聚合所有模块）。
3. 首次构建时先安装 BOM 模块：
   `mvn -f zhoouon-dependencies/pom.xml install`（如果 IDE 中没有该模块，右键其 `pom.xml` 添加为 Maven 项目）。
4. 从 `script` 或各模块的 `sql` 目录获取 SQL 脚本，创建对应数据库。
5. 修改各模块 `src/main/resources` 下的 `application*.yml`，将 Redis、MySQL、RocketMQ、Nacos、MinIO 等地址改成实际环境。

## 构建

```bash
mvn -DskipTests compile
```

仓库中的 `zhoouon-concurrent`、`zhoouon-lock`、`zhoouon-proxy` 等模块以教学演示为主，
主要入口是各 Java 类的 `main` 方法。
