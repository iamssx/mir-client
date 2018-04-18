package com.mmorpg.mir.client;

import com.mmorpg.mir.client.framework.net.HandlerPacketMethod;

import java.util.Map;

public interface InitHandlerStrategy {

    Map<Short, HandlerPacketMethod> process(Object bean);
}
