package org.java.learn.nio.framework;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class ExceptionHandler extends SimpleChannelInboundHandler {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        handleException(ctx, cause);
        ctx.close();
    }

    protected abstract void handleException(ChannelHandlerContext ctx, Throwable cause);
}
