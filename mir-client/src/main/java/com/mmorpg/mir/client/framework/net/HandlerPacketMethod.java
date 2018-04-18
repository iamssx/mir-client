package com.mmorpg.mir.client.framework.net;

import com.mmorpg.mir.client.framework.net.decode.Packet;

public interface HandlerPacketMethod {

    /**
     * 复写这方法，可以拦截方法，可以选择在另外的线程执行，或者采用反射或DynamicInvoke的方式调用
     *
     * @return
     * @throws Exception
     */
    Object invoke(Packet packet) throws Exception;

}
