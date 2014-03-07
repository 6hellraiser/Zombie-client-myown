package com.zombie;

/**
 * Created by Наталья on 02.03.14.
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

public class ClientHandler extends ChannelInboundMessageHandlerAdapter<String> {
    @Override
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }
}
