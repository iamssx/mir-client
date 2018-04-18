package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by 37 on 2017/7/20.
 */
@Component
public class EchoServer {

    @Autowired
    EchoServerHandler echoServerHandler;

    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup bossEventLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup childEventExecutors = new NioEventLoopGroup();
        try {
            bootstrap.group(bossEventLoopGroup, childEventExecutors)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(8080)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(echoServerHandler);
                        }
                    });
            ChannelFuture sync = bootstrap.bind().sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossEventLoopGroup.shutdownGracefully().sync();
            childEventExecutors.shutdownGracefully().sync();
        }
    }
}
