package org.java.learn.nio.codec;

public interface ObjectCodec<T> {
    public static enum CodecType {

        Protobuf(ObjectProtobufCodec.getInstance(), "protobuf");

        private ObjectCodec codec;
        private String contentType;

        CodecType(ObjectCodec codec, String contentType){
            this.codec = codec;
            this.contentType = contentType;
        }

        public ObjectCodec getCodec(){
            return codec;
        }

        public static CodecType getType(String contentType){
            for(CodecType type : CodecType.values()){
                if(contentType.equals(type.contentType)){
                    return type;
                }
            }
            return null;
        }

    }

    byte[] encode(T object);

    <P extends T> P decode(Class<P> objectClass, byte[] bytes);

}
