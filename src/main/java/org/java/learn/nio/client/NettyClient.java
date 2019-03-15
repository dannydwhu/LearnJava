package org.java.learn.nio.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.java.learn.nio.codec.PackageCodec;
import org.java.learn.nio.codec.ProtobufPackageDecoder;
import org.java.learn.nio.codec.ProtobufPackageEncoder;
import org.java.learn.nio.concurrent.DefaultPromise;
import org.java.learn.nio.concurrent.Promise;
import org.java.learn.nio.model.BasePackage;
import org.java.learn.nio.model.BaseRequest;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyClient {

    private static final int time_out = 30*1000;
    private Bootstrap bootstrap;
    private EventLoopGroup group;
    private Channel channel;

    private static final Map<String, DefaultPromise> requestMap = new ConcurrentHashMap<>();

    public NettyClient(){
        this(NioSocketChannel.class, time_out, null);
    }

    public NettyClient(Class<? extends Channel> channelClass, int connectTimeout, final SslContext sslContext) {
        bootstrap = new Bootstrap();
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                .option(ChannelOption.TCP_NODELAY, true);
        group = new NioEventLoopGroup(1);
        bootstrap.group(group)
                .channel(channelClass)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        List<ChannelHandler> handlers = getHandlers();
                        if(null != handlers){
                            for(ChannelHandler handler : handlers){
                                p.addLast(handler);
                            }
                        }
                        p.addLast(new ProtobufResponseHandler(requestMap));

                    }
                });


    }

    public List<ChannelHandler> getHandlers(){
        return Arrays.asList(
                new ProtobufPackageDecoder(),
                new ProtobufPackageEncoder(),
                new PackageCodec());
    }

    public ChannelFuture connect(String host, int port){
        try {
            ChannelFuture f = bootstrap.connect(new InetSocketAddress(port)).sync();
            this.channel = f.channel();

            return f;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
//                .addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                if (future.isSuccess()) {
//                    channel = future.channel();
//                    System.out.println("connected to server" );
//                } else {
//                    System.err.println("fail to connect server");
//                }
//            }
//        })
    }

    public Promise send(BaseRequest request){
        BasePackage basePackage = new BasePackage(request);

        DefaultPromise<BasePackage> promise = new DefaultPromise<>();
        requestMap.put(request.getRequestId(),promise);
        this.channel.writeAndFlush(basePackage).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("send success");
                } else {
                    System.out.println("send failed");
                }
            }
        });

        return promise;
    }
}
