package org.java.learn.nio.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import org.java.learn.nio.concurrent.DefaultPromise;
import org.java.learn.nio.model.BasePackage;
import org.java.learn.nio.model.BaseRequest;
import org.java.learn.nio.model.PackageType;
import org.java.learn.nio.util.JsonUtil;

import java.util.Map;

public class ProtobufResponseHandler extends SimpleChannelInboundHandler<BasePackage> {

    private  Map<String, DefaultPromise> requestMap;

    public ProtobufResponseHandler(Map<String, DefaultPromise> requestMap){
        super();
        this.requestMap = requestMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BasePackage basePackage) throws Exception {
        System.out.println("server response: " + JsonUtil.toJsonString(basePackage));

        if(null != basePackage){
           if( basePackage.getType() == PackageType.REQUEST ) {
               BaseRequest request = (BaseRequest) basePackage.getBody();
               DefaultPromise<BasePackage> promise = requestMap.get(request.getRequestId());
               promise.setValue(basePackage);
           }
        }


    }
}
