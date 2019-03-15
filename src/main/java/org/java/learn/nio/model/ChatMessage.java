package org.java.learn.nio.model;

import org.java.learn.proto.NettyProto;

public class ChatMessage extends BaseObject<NettyProto.ProtoChatMessage> {

    private String text ;
    private String name;
    private Long id ;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void initFromProto(NettyProto.ProtoChatMessage pbObject) {
        this.name = pbObject.getName();
        this.text = pbObject.getText();
        this.id = pbObject.getId();

        System.out.println("chat message proto initialed " );
    }

    @Override
    public NettyProto.ProtoChatMessage toProto() {
        NettyProto.ProtoChatMessage.Builder builder = NettyProto.ProtoChatMessage.newBuilder();
        builder.setId(this.id);
        builder.setName(this.name);
        builder.setText(this.text);
        return builder.build();
    }
}
