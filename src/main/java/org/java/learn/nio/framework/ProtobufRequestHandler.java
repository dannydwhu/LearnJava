package org.java.learn.nio.framework;

import io.netty.channel.*;
import io.netty.util.Attribute;
import io.netty.util.ReferenceCountUtil;
import org.java.learn.nio.model.BasePackage;
import org.java.learn.nio.model.BaseRequest;
import org.java.learn.nio.util.JsonUtil;

public class ProtobufRequestHandler extends SimpleChannelInboundHandler<BasePackage> {

//
////    @Override
//    protected void messageReceived1(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("messageReceived1 read");
//        try {
//            if (msg instanceof BasePackage) {
//                BasePackage basePackage = (BasePackage) msg;
//                System.out.println("read message " + basePackage.getBody());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            ctx.close();
//        }
//    }
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("channel read");
//        boolean release = true;
//        try {
//            if (acceptInboundMessage(msg)) {
//                @SuppressWarnings("unchecked")
//                BasePackage imsg = (BasePackage) msg;
//                messageReceived1(ctx, imsg);
//            } else {
//                release = false;
//                ctx.fireChannelRead(msg);
//            }
//        } finally {
//            if (true) {
//                ReferenceCountUtil.release(msg);
//            }
//        }
//    }
//
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, BasePackage basePackage) throws Exception {
//        System.out.println("messageReceived read");
//        try {
//                System.out.println("read message " + basePackage.getBody());
//        } catch (Exception e) {
//            e.printStackTrace();
//            ctx.close();
//        }
//    }
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("channel read " );
//        try {
//            if (msg instanceof BasePackage) {
//                BasePackage basePackage = (BasePackage) msg;
//                System.out.println("got read " + basePackage);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            ctx.close();
//        }
//
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BasePackage basePackage) throws Exception {
        System.out.println("channel read " );
        try {
            switch(basePackage.getType()){
                case REQUEST:
                    BaseRequest baseRequest = (BaseRequest) basePackage.getBody();
                    System.out.println("got read " + JsonUtil.toJsonString(baseRequest));

                    baseRequest.setUri("/ok");
                    ctx.channel().writeAndFlush(basePackage);
//                    f.sync();
                    System.out.println("server say");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ctx.close();
        }
    }
}
