package org.java.learn.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.java.learn.nio.codec.PackageCodec;
import org.java.learn.nio.codec.ProtobufPackageDecoder;
import org.java.learn.nio.codec.ProtobufPackageEncoder;
import org.java.learn.nio.framework.EndPoint;
import org.java.learn.nio.framework.ExceptionHandler;
import org.java.learn.nio.framework.ProtobufRequestHandler;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

public class TcpEndPoint implements EndPoint {

    private int port;

    private ServerBootstrap bootstrap;
    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;

    private Channel serverChannel;

    @Override
    public void start() {
        bootstrap = new ServerBootstrap();

        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
//                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        List<ChannelHandler> handlers = getHandlers();
                        if(null != handlers){
                            for(ChannelHandler handler : handlers){
                                p.addLast(handler);
                            }
                        }
                        p.addLast("handler",new ProtobufRequestHandler());
//                        p.addLast(new ExceptionHandler(){
//
//                            @Override
//                            protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//                            }
//
//                            @Override
//                            protected void handleException(ChannelHandlerContext ctx, Throwable cause) {
//
//                            }
//                        });
                    }
                });

        try {
//            serverChannel = bootstrap.bind(new InetSocketAddress("localhost",getPort())).sync().channel();
            serverChannel = bootstrap.bind(new InetSocketAddress(getPort())).sync()
            .channel();
            serverChannel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<ChannelHandler> getHandlers(){
        return Arrays.asList(
                new ProtobufPackageDecoder(),
                new ProtobufPackageEncoder(),
                new PackageCodec());
    }

    @Override
    public void stop() {
        try {
            if (this.serverChannel != null) {
                this.serverChannel.close().sync();
            }
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }
}
