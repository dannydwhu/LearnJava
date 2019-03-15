package org.java.learn.nio.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.java.learn.nio.model.BasePackage;
import org.java.learn.proto.NettyProto;

import java.util.List;

public class PackageCodec extends MessageToMessageCodec<NettyProto.ProtoPackage, BasePackage> {


    @Override
    protected void encode(ChannelHandlerContext ctx, BasePackage msg, List<Object> out) throws Exception {
        System.out.println("package encode");
        out.add(msg.toProto());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, NettyProto.ProtoPackage msg, List<Object> out) throws Exception {
        System.out.println("package decode");
        BasePackage basePackage = new BasePackage();
        basePackage.initFromProto(msg);

        System.out.println("package decode basePackage " +basePackage);

        out.add(basePackage);
    }
}
