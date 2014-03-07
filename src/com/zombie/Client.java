package com.zombie;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.json.simple.JSONObject;

import io.netty.channel.Channel;

/**
 * Created by Наталья on 02.03.14.
 */
public class Client {

   /* private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }*/

    public static void run(JSONObject obj) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            Channel channel = bootstrap.connect("127.0.0.1", 1025).sync().channel();
           // while (true) {
                channel.write("Hello!");
            //}
        }
        catch (InterruptedException e) {
            System.out.print(e.getMessage());
        }
        finally {
            group.shutdownGracefully();
        }
    }

}
