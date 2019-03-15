package org.java.learn.nio.model;

import com.google.protobuf.ByteString;
import org.java.learn.nio.codec.ObjectProtobufCodec;
import org.java.learn.proto.NettyProto;

public enum PackageType implements BaseEnum<NettyProto.ProtoPackageType>  {
    REQUEST(NettyProto.ProtoPackageType.REQUEST, BaseRequest.class, NettyProto.ProtoRequest.class);

    private NettyProto.ProtoPackageType pbType;
    private Class bodyType;
    private Class bodyProtoType;

    PackageType(NettyProto.ProtoPackageType pbType,
                        Class bodyType,
                        Class bodyProtoType){
        this.pbType = pbType;
        this.bodyType = bodyType;
        this.bodyProtoType = bodyProtoType;
    }

    @Override
    public NettyProto.ProtoPackageType toProto() {
        return this.pbType;
    }

    @Override
    public int getNumber() {
        return this.pbType.getNumber();
    }

    public static PackageType valueOf(NettyProto.ProtoPackageType pbType){
        for (PackageType type : PackageType.values()) {
            if (type.pbType == pbType) {
                return type;
            }
        }
        return REQUEST;
    }

    public BaseObject decodeFromProto(ByteString body) {
        if (body == null || this.bodyType == null) {
            return null;
        }
        return (BaseObject) ObjectProtobufCodec.getInstance().decode(bodyType, bodyProtoType, body.toByteArray());
    }
}
