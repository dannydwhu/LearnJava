package org.java.learn.nio.codec;

import com.google.protobuf.GeneratedMessageV3;

public interface Protoable<T extends GeneratedMessageV3> {
    T toProto();
}
