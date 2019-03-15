package org.java.learn.nio.model;

import com.google.protobuf.ByteString;
import org.java.learn.nio.codec.ObjectProtobufCodec;
import org.java.learn.proto.NettyProto;

public class BasePackage extends BaseObject<NettyProto.ProtoPackage>{

    private PackageType type;
    private BaseObject body;

    public BasePackage(){}

    public BasePackage(BaseRequest request){
        this.type = PackageType.REQUEST;
        this.body = request;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }

    public BaseObject getBody() {
        return body;
    }

    public void setBody(BaseObject body) {
        this.body = body;
    }

    @Override
    public void initFromProto(NettyProto.ProtoPackage pbObject) {
        this.type = PackageType.valueOf(pbObject.getType());
        System.out.println("type: " + this.type);
        try{
            if(pbObject != null){

                this.body = this.type.decodeFromProto(pbObject.getBody());
                System.out.println("body: " + this.body.getClass());
            } else {
                System.out.println("body is null");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("init From proto completed");
    }
//
//    private BaseObject decodeFromProto(ByteString body) {
//        return ObjectProtobufCodec.getInstance().decode(this.getClass(), body.toByteArray());
//    }

    @Override
    public NettyProto.ProtoPackage toProto() {
        NettyProto.ProtoPackage.Builder builder = NettyProto.ProtoPackage.newBuilder();
        builder.setType(type.toProto());
        if(body != null){
            builder.setBody(body.toProto().toByteString());
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return "BasePackage{" +
                "type='" + type + '\'' +
                ", body=" + body +
                '}';
    }
}
