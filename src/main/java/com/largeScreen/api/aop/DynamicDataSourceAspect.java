package com.largeScreen.api.aop;

import com.largeScreen.api.annotations.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered {

    @Before("@annotation(com.largeScreen.api.annotations.DataSource)")
    public void beforeSwitchDS(JoinPoint point) {

        Class<?> className = point.getTarget().getClass();

        String methodName = point.getSignature().getName();

        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();

        String dataSource = DataSourceContextHolder.DEFAULT_DS;

        try {
            Method method = className.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(com.largeScreen.api.annotations.DataSource)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
