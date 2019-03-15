package org.java.learn.nio.model;

import com.google.protobuf.ProtocolMessageEnum;

public interface BaseEnum <T extends ProtocolMessageEnum> {
    T toProto();
    int getNumber();
}
