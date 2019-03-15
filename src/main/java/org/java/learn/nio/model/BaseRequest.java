package org.java.learn.nio.model;

import com.google.protobuf.ByteString;
import org.java.learn.nio.util.JsonUtil;
import org.java.learn.proto.NettyProto;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest extends BaseObject<NettyProto.ProtoRequest> {

    private String requestId;
    private String uri;
    private Map<String, Object> params;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public void initFromProto(NettyProto.ProtoRequest pbObject) {
        this.requestId = pbObject.getRequestId();
        this.uri = pbObject.getUri();
        if(null == params){
            this.params = new HashMap<>();
        }
        if(null != pbObject.getBody()){
            this.params.putAll(JsonUtil.parseJsonMap(pbObject.getBody().toStringUtf8()));
        }
    }

    @Override
    public NettyProto.ProtoRequest toProto() {
        NettyProto.ProtoRequest.Builder builder = NettyProto.ProtoRequest.newBuilder();
        builder.setRequestId(this.requestId);
        builder.setUri(this.uri);
        if(null != params && params.size() > 0){
            builder.setBody(ByteString.copyFromUtf8(JsonUtil.toJsonString(params)));
        }

        return builder.build();
    }
}
