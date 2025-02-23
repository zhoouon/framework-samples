package com.zhoouon.starter.datasource.intecept;

import com.zhoouon.starter.datasource.configuration.SlowSQLProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;

/**
 * Description:
 * 实现数据库慢查询拦截器,可以根据实际情况发送到单独的日志或者消息中
 */
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
@RequiredArgsConstructor
public class SlowSqlInterceptor implements Interceptor {

    private final SlowSQLProperties slowSQLProperties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object target = invocation.getTarget();
        long startTime = System.currentTimeMillis();

        StatementHandler statementHandler = (StatementHandler) target;
        try {
            return invocation.proceed();
        } finally {
            long costTime = System.currentTimeMillis() - startTime;

            if (costTime >= slowSQLProperties.getCost()) {
                BoundSql boundSql = statementHandler.getBoundSql();

                String sql = boundSql.getSql();

                // 20230901 去掉sql中的换行符
                sql = sql.replaceAll("\\n+", " ");

                log.warn("WARN !!!,监测到慢查询SQL:[{}] 执行耗时 {} ms ", sql, costTime);
            }

        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
