package org.java.learn.nio;

import io.netty.channel.ChannelPromise;
import org.java.learn.nio.client.NettyClient;
import org.java.learn.nio.concurrent.DoneCallback;
import org.java.learn.nio.concurrent.Promise;
import org.java.learn.nio.model.BasePackage;
import org.java.learn.nio.model.BaseRequest;

import java.util.HashMap;
import java.util.Map;

public class TestNettyClient {

    public static void main(String[] args){
        NettyClient client = new NettyClient();
        client.connect("localhost", 81);

        try {

//            ChatMessage chatMessage = new ChatMessage();
//            chatMessage.setName("Danny");
//            chatMessage.setText("test");
//            chatMessage.setId(123L);

            for(int i=0; i<10; i++){
                BaseRequest request = new BaseRequest();
                request.setRequestId(i+"");
                request.setUri("/test");
                Map<String, Object> params = new HashMap<>();
                params.put("1","one");
                request.setParams(params);

                Promise f = client.send(request);
                f.done(new DoneCallback<BasePackage>() {
                    @Override
                    public void onDone(BasePackage basePackage) {
                        System.out.println("on Done callback");
                    }
                });

                BasePackage r = (BasePackage) f.get();
                System.out.println("############ " + r.toString());

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
