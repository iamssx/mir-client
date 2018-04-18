package com.mmorpg.mir.client.framework.net;

import com.mmorpg.mir.client.framework.net.decode.Packet;
import com.mmorpg.mir.client.framework.net.method.utils.MethodParameterResolverUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Data
public class ReflectHandlerPacketMethod implements HandlerPacketMethod {

    private Object targetObj;
    private Method method;
    private Parameter[] parameters;

    @Override
    public Object invoke(Packet packet) throws InvocationTargetException, IllegalAccessException {
        ByteBuf buf = packet.getBuf();
        Object[] parametersValue = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parametersValue[i] = MethodParameterResolverUtils.resolve(parameters[i], buf);
        }
        return method.invoke(targetObj, parameters);
    }

}
