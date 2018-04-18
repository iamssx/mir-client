package com.mmorpg.mir.client.framework.net;

import com.google.common.collect.Maps;
import com.mmorpg.mir.client.InitHandlerStrategy;
import com.mmorpg.mir.client.framework.net.enums.PacketId;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Component
public class DefaultInitHandlerStrategy implements InitHandlerStrategy {

    @Override
    public Map<Short, HandlerPacketMethod> process(Object bean) {
        HashMap<Short, HandlerPacketMethod> tmpMap = Maps.newHashMap();
        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(PacketId.class)) {
                if (method.getModifiers() != Modifier.PUBLIC) {
                    throw new RuntimeException("method:" + method + " must be public!!!");
                }
                PacketId packetId = method.getAnnotation(PacketId.class);
                short protocol = packetId.value();
                Parameter[] parameters = method.getParameters();
                ReflectHandlerPacketMethod packetMethod = new ReflectHandlerPacketMethod();
                packetMethod.setMethod(method);
                packetMethod.setTargetObj(bean);
                packetMethod.setParameters(parameters);
                tmpMap.put(protocol, packetMethod);
            }
        }
        return tmpMap;
    }
}
