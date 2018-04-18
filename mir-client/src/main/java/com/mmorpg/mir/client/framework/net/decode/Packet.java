package com.mmorpg.mir.client.framework.net.decode;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class Packet {

    private final ByteBuf buf;
    private final short protocol;

    public Packet(ByteBuf buf) {
        this.buf = buf;
        protocol = buf.readShort();
    }
}
