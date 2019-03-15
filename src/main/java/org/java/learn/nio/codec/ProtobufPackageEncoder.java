package org.java.learn.nio.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.java.learn.proto.NettyProto;

public class ProtobufPackageEncoder extends MessageToByteEncoder<NettyProto.ProtoPackage> {

    public ProtobufPackageEncoder(){
        super(NettyProto.ProtoPackage.class);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyProto.ProtoPackage msg, ByteBuf out) throws Exception {
        System.out.println("ProtobufPackageEncoder encode " );
        byte[] body = msg.toByteArray();
        // version
        out.writeByte(1);
        // flag
        out.writeByte(0);
        out.writeInt(body.length);
        out.writeBytes(body);
    }
}
