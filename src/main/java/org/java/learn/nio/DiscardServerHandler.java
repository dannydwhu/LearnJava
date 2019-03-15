package org.java.learn.nio;

import java.util.concurrent.locks.LockSupport;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf in = (ByteBuf) msg;
        try{
            while(in.isReadable()){
                System.out.print((char) in.readByte());
                System.out.flush();
            }
           
        } finally{
            ReferenceCountUtil.release(msg);
        }
        LockSupport.park();
        
        ctx.write("hello: " + msg);
        ctx.flush();
    }
}
