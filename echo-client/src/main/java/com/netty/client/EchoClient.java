package com.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 37 on 2017/7/20.
 */
@Component
public class EchoClient {

    @Autowired
    private EchoClientHandler echoClientHandler;

    public void contect() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(eventLoopGroup)
                        .remoteAddress("localhost", 8080)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(echoClientHandler);
                            }
                        });
                ChannelFuture sync = bootstrap.connect().sync();
                sync.channel().closeFuture().sync();
            } finally {
                eventLoopGroup.shutdownGracefully();
            }
        }

    }
}
