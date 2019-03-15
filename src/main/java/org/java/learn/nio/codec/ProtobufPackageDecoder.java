package org.java.learn.nio.codec;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.java.learn.proto.NettyProto;

import java.util.List;

public class ProtobufPackageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ProtobufPackageDecoder decode " );

        NettyProto.ProtoPackage request = this.decode(ctx, in);
        if(request != null){
            out.add(request);
//            System.out.println("request " +  request.getBody());
        }
    }

    NettyProto.ProtoPackage decode(ChannelHandlerContext ctx,  ByteBuf in){
        int readableBytes = in.readableBytes();
        if(readableBytes < 1){
            return null;
        }

        int start = in.readerIndex();
        byte version = in.readByte();
        if(version == 1){
            byte flag = in.readByte();

            // read body
            int bodyLength = in.readInt();
            if(bodyLength > 0){

                if (in.readableBytes() < bodyLength) {
                    in.readerIndex(start);
                    return null;
                }

                byte[] body = new byte[bodyLength];
                in.readBytes(body);

                NettyProto.ProtoPackage protoPackage = null;
                try {
                    protoPackage = NettyProto.ProtoPackage.parseFrom(body);
//                    System.out.println("got package" +protoPackage.getType() + " body" +protoPackage.getBody().toStringUtf8());
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                return protoPackage;
            } else {
                System.out.println("body is empty");
            }
        }
        return null;
    }
}
