# zhoouon-samples

基于 Spring Boot 3 的框架集成示例聚合工程，包含以下模块：

| 模块 | 类型 | 说明 |
| --- | --- | --- |
| zhoouon-dependencies | BOM | 依赖版本管理中心，统一维护平台 BOM、三方依赖版本与项目版本号 |
| zhoouon-starter | 聚合 | 自研 Spring Boot Starter 集合 |
| zhoouon-concurrent | 示例 | 并发编程示例 |
| zhoouon-lock | 示例 | JUC 锁示例 |
| zhoouon-netty | 示例 | Netty/NIO 示例 |
| zhoouon-proxy | 示例 | JDK/CGLIB 动态代理示例 |
| zhoouon-redis | 示例 | Redisson 分布式锁、布隆过滤器、WebSocket 示例 |
| zhoouon-minio | 示例 | MinIO 文件操作示例 |
| zhoouon-shardingsphere | 示例 | ShardingSphere 分库分表 + Seata 示例 |
| zhoouon-seata | 聚合 | Seata AT 模式分布式事务示例 |
| zhoouon-webflux | 示例 | WebFlux/SSE 示例 |
| zhoouon-ai | 示例 | Spring AI / DeepSeek 示例 |

## 工程结构与依赖关系

```text
zhoouon-samples（根 POM：聚合 + 唯一父 POM）
├── zhoouon-dependencies（BOM：版本唯一来源）
│     ├── 平台 BOM  Spring Boot 3.2.4 / Spring Cloud 2023.0.1
│     │            / Spring Cloud Alibaba 2022.0.0.2 / Spring AI 1.0.0
│     ├── 三方依赖版本（hutool、mybatis-plus、druid、redisson 等）
│     └── com.zhoouon 内部组件版本（version = revision）
│
├── zhoouon-starter（聚合，仅负责组织子模块）
│     ├── zhoouon-logging-spring-boot-starter
│     ├── zhoouon-common-spring-boot-starter
│     ├── zhoouon-id-spring-boot-starter
│     ├── zhoouon-datasource-spring-boot-starter ── 依赖 id、common
│     ├── zhoouon-web-spring-boot-starter ────────── 依赖 common
│     ├── zhoouon-openfeign-spring-boot-starter ──── 依赖 common、web
│     ├── zhoouon-core-spring-boot-starter
│     └── zhoouon-rocketmq-spring-boot-starter ───── 依赖 common、core
│
├── 示例模块（依赖自研 starter，间接形成引用关系）
│     ├── zhoouon-logging-spring-boot-starter ← zhoouon-concurrent/lock/minio/
│     │        proxy/redis/shardingsphere/webflux/seata-* 等
│     ├── zhoouon-common-spring-boot-starter ← zhoouon-netty/shardingsphere/webflux
│     └── zhoouon-web-spring-boot-starter ← zhoouon-netty
└── zhoouon-ai（独立示例，仅依赖 Spring Boot / Spring AI，不依赖自研 starter）
```

### 版本管理约定

1. 所有版本号（平台 BOM、三方依赖、`com.zhoouon` 内部组件）统一维护在
   `zhoouon-dependencies` 中；模块 pom 内**不写依赖版本号**。
2. 新增依赖时：先在 `zhoouon-dependencies` 增加 version 属性与 dependencyManagement，
   再在需要使用的模块里只声明 `groupId/artifactId`。
3. 项目版本号由根 POM 中的 `revision` 与 `zhoouon-dependencies` 中的 `revision`
   保持一致（两处定义保证 BOM 可脱离聚合工程独立使用），升级时同步修改。
4. 根 POM 只负责模块聚合、公共构建配置，以及统一导入 `zhoouon-dependencies`。

## 运行准备

1. 安装 JDK 17 与 Maven 3.9+。
2. 将项目导入 IDEA（Maven 会自动聚合所有模块）。
3. 首次构建时先安装 BOM 模块：
   `mvn -f zhoouon-dependencies/pom.xml install`（如 IDE 中未识别，右键其
   `pom.xml` 添加为 Maven 项目）。
4. 从各模块 `sql` 目录获取 SQL 脚本，创建对应数据库。
5. 修改各模块 `src/main/resources` 下的 `application*.yml`，将 Redis、MySQL、
   RocketMQ、Nacos、MinIO 等地址改成实际环境。

## 构建

```bash
mvn -DskipTests compile
```

仓库中的 `zhoouon-concurrent`、`zhoouon-lock`、`zhoouon-proxy` 等模块以教学演示为主，
主要入口是各 Java 类的 `main` 方法。
