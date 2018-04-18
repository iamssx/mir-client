package com.mmorpg.mir.client.framework.net.method.utils;

import com.google.common.base.Preconditions;
import com.mmorpg.mir.client.framework.net.method.parameter.MethodParameterResolver;
import io.netty.buffer.ByteBuf;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Map;

@Component
public final class MethodParameterResolverUtils implements ApplicationContextAware {

    private static Map<Class<?>, MethodParameterResolver> methodParamterResolverMap;

    public static Object resolve(Parameter parameter, ByteBuf byteBuf) {
        Class<?> type = parameter.getType();
        MethodParameterResolver resolver = methodParamterResolverMap.get(type);
        Preconditions.checkNotNull(resolver, "can not find resolver of type:" + type);
        return resolver.resolve(parameter, byteBuf);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, MethodParameterResolver> beansMap = applicationContext.getBeansOfType(MethodParameterResolver.class);

    }
}
