package com.mmorpg.mir.client.framework.net.decode;

import io.netty.buffer.ByteBuf;

public interface MethodParameterDecode<T> {

    T decode(ByteBuf byteBuf);
}
