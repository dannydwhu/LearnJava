package org.java.learn.nio.codec;

import com.google.protobuf.GeneratedMessageV3;

public interface ProtobufCodecObject<T extends GeneratedMessageV3> extends Protoable<T> {
    void initFromProto(T pbObject);
}
