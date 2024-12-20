package com.envoy.illiad.handler;

import com.envoy.illiad.utils.NettyUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

public class IdleStateEventHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(IdleStateEventHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            handleIdleState(ctx, (IdleStateEvent) evt);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    private void handleIdleState(ChannelHandlerContext ctx, IdleStateEvent event) {
        if (LOG.isInfoEnabled()) {
            SocketAddress remoteAddress = ctx.channel().remoteAddress();
            LOG.info(remoteAddress + " idle timeout: " + event.state());
        }
        NettyUtils.closeAfterFlush(ctx);
    }

}
