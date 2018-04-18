package com.mmorpg.mir.client.framework.net.handler;

import com.google.common.base.Preconditions;
import com.mmorpg.mir.client.framework.net.HandlerPacketMethod;
import com.mmorpg.mir.client.framework.net.ProtocolRegister;
import com.mmorpg.mir.client.framework.net.decode.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class DispatcherHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private ProtocolRegister protocolRegister;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = (Packet) msg;
        HandlerPacketMethod packetMethod = protocolRegister.lookup(packet.getProtocol());
        Preconditions.checkNotNull(packetMethod);
        packetMethod.invoke(packet);
    }
}
