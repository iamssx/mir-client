package com.mmorpg.mir.client.framework.net.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

public class BaseFrameDecode extends ByteToMessageDecoder {

    private static final byte[] FRAME_MAGIC = {9, 5, 2, 7};
    private static final byte[] OLD_FRAME_MAGIC = {5, 1, 2, 0};
    private static final int FRAME_BODY_SIZE = 4;
    /**
     * 1024*1024
     */
    private static final int MAX_BODY_SIZE = 1024 * 1024;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < FRAME_MAGIC.length + FRAME_BODY_SIZE) {
            return;
        }
        in.markReaderIndex();
        byte[] receiveMagic = new byte[FRAME_MAGIC.length];
        in.readBytes(receiveMagic);
        if (!matchMagic(receiveMagic)) {
            in.resetReaderIndex();
            return;
        }
        int bodySize = in.readInt();
        if (in.readableBytes() < bodySize) {
            in.resetReaderIndex();
            return;
        }
        ByteBuf buffer = ctx.alloc().buffer(bodySize);
        in.readBytes(buffer);
        out.add(new Packet(buffer));
    }


    private boolean matchMagic(byte[] recvMagic) {
        return Arrays.equals(FRAME_MAGIC, recvMagic);
    }

}
