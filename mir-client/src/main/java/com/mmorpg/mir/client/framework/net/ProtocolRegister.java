package com.mmorpg.mir.client.framework.net;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class ProtocolRegister {

    //并发读没问题
    protected Map<Short, HandlerPacketMethod> protocolLookup = new HashMap<>();

    public void register(Map<Short, HandlerPacketMethod> map) {
        protocolLookup.putAll(map);
    }

    public HandlerPacketMethod lookup(short protocol) {
        return protocolLookup.get(protocol);
    }
}
