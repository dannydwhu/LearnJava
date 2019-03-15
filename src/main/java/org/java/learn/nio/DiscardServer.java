package org.java.learn.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

    NioEventLoop loop;

    private int port;
    public DiscardServer(int port){
        this.port = port;
    }
    
    public void run(){
        
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("port: " + port);
        
        ServerBootstrap b = new ServerBootstrap();
        b = b.group(bossGroup, workerGroup);
        
        b = b.channel(NioServerSocketChannel.class);
        b = b.childHandler(new ChannelInitializer<SocketChannel>(){

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new DiscardServerHandler());
            }
            
        });
        
        b = b.option(ChannelOption.SO_BACKLOG, 128);
        b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
        
        try {
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
        
    }
    
    public static void main(String[] args){
        new DiscardServer(8080).run();
    }
}
