package com.bootdo.system.controller;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by linla on 2021/10/13.
 */

public class TestController {


    public static void main(String[] args) throws Exception {
           new TestController().run();

    }
    public static void run()throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.channel(NioServerSocketChannel.class);
            b.option(ChannelOption.SO_BACKLOG, 2048);//初始化服务端可连接队

            b.group(bossGroup, workerGroup)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new MyServerHandler());
                            ch.pipeline().addLast(new ExceptionHandler());
                            // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码



                        }
                    }).option(ChannelOption.SO_BACKLOG, 4096)//1
                    .option(ChannelOption.WRITE_SPIN_COUNT, 2048)
                    .option(ChannelOption.SO_KEEPALIVE, true);//用于可能长时间没有数据交流的连接,如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报

            ChannelFuture f = b.bind(8088);
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
