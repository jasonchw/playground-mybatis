package com.playground.mybatis.interceptor;

import com.playground.mybatis.model.AuditAwareModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class AuditAwareInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // implement pre-processing if needed
        preProcessing(invocation);

        Object returnObject = invocation.proceed();

        // implement post-processing if needed
        postProcessing(invocation);

        return returnObject;
    }

    private void preProcessing(Invocation invocation) {
        String statementId = null;
        Object[] args = invocation.getArgs();
        if (args[0] instanceof MappedStatement) {
            MappedStatement mappedStatement = (MappedStatement) args[0];
            statementId = mappedStatement.getId();
        }

        if (args[1] instanceof AuditAwareModel) {
            AuditAwareModel model = (AuditAwareModel) args[1];

            if (StringUtils.contains(statementId, "insert")) {
                model.setCreatedBy("test-user-c");
                model.setUpdatedBy("test-user-c");
                model.setUpdatedUsing("test-system-c");
            } else if (StringUtils.contains(statementId, "update")) {
                model.setUpdatedBy("test-user-u");
                model.setUpdatedUsing("test-system-u");
            }
        }
    }

    private void postProcessing(Invocation invocation) {
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
