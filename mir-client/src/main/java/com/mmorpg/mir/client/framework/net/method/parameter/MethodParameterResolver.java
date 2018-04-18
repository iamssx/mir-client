package com.mmorpg.mir.client.framework.net.method.parameter;

import io.netty.buffer.ByteBuf;

import java.lang.reflect.Parameter;

@NotThreadSafe
public interface MethodParameterResolver<T> {

    T resolve(Parameter parameter, ByteBuf byteBuf);
}
