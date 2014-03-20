package com.zombie.interconnection;

import com.zombie.util.Callback;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Наталья on 02.03.14.
 */
public class Client {

    public static final int COMMAND_AUTHENTICATE = 1;
    public static final int COMMAND_REGISTER = 2;
    public static EventLoopGroup group;
    public static Channel channel;
    private static URI uri;
    private static int requestId = 0;
    private static Map<Integer, JSONObject> requests = new HashMap<Integer, JSONObject>(100);

    private static Map<Integer, Callback<Boolean>> callbacks = new HashMap<Integer, Callback<Boolean>>();

    public static void init(URI uri) {
        Client.uri = uri;
        channel();
    }

    private static Channel channel() {
        if (channel == null) {
            if (group != null)
                throw new RuntimeException("group must be null");
            group = new NioEventLoopGroup();
            try {

                final String protocol = uri.getScheme();
                int defaultPort = 80;

                final WebSocketClientHandler handler = new WebSocketClientHandler(WebSocketClientHandshakerFactory.newHandshaker(
                        uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));

                Bootstrap bootstrap = new Bootstrap()
                        .group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline()
                                        .addLast("http-codec", new HttpClientCodec())
                                        .addLast("aggregator", new HttpObjectAggregator(65536))
                                        .addLast("ws-handler", handler);
//                                        .addLast("http-codec", new HttpClientCodec())
//                                        .addLast("aggregator", new HttpObjectAggregator(65536))
//                                .addLast(new HttpResponseEncoder())
//                                        .addLast(new WebSocketClientProtocolHandler(uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders(), 0));
//                                .addLast("commandHandler", new CommandHandler());
                            }
                        });

                channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
                handler.handshakeFuture().sync();

                WebSocketFrame frame = new PingWebSocketFrame(Unpooled.copiedBuffer(new byte[]{8, 1, 8, 1}));
                channel.writeAndFlush(frame);

            } catch (InterruptedException e) {
                //System.out.print(e.getMessage());
                throw new RuntimeException(e);
            } finally {
                //group.shutdownGracefully();
            }
        }
        return channel;
    }


    public static void authenticate(String login, String password, final Callback<Boolean> callback) {

        if (channel == null) {
            try {
                Client.init(new URI("ws://127.0.0.1:900/websocket"));
            } catch (URISyntaxException e) {
                System.out.print(e.getMessage());
            }
        }
        callbacks.put(COMMAND_AUTHENTICATE, callback);
        sendRequest(COMMAND_AUTHENTICATE, json(
                "username", login,
                "password", password));
    }

    private static Callback<Boolean> findCallback(int id) {
        return callbacks.get(id);
    }

    public static void onReceivemessage(String message) {
        JSONParser parser = new JSONParser();
        int id = -15;
        JSONObject jsonObj = null;

        try {
            Object obj = parser.parse(message);
            jsonObj = (JSONObject) obj;
            id = (Integer)jsonObj.get("id");
            System.out.println(id);
        }
        catch(org.json.simple.parser.ParseException e) {
            System.out.print(e.getMessage());
        }
        switch (id){
            case 1:
                onAuthenticate(jsonObj);
                break;
            case 2:
                break;
        }

    }

    public static void onAuthenticate(JSONObject obj) {
        JSONObject nested = (JSONObject) obj.get("parameters");
        findCallback(COMMAND_AUTHENTICATE).callback(nested.isEmpty());

    }

    public static void registration(String login, String password) {

        sendRequest(COMMAND_REGISTER, json(
                "username", login,
                "password", password));
    }

    private static void sendRequest(int commandType, JSONObject obj) {

        JSONObject resultJson = json(
                "id", commandType,
                //"requestId", ++requestId,
                "parameters", obj
        );

        channel().writeAndFlush(new TextWebSocketFrame(resultJson.toString()));
        requests.put(requestId, obj);
    }

    public static JSONObject json(Object... params) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < params.length; i += 2)
            json.put(params[i], params[i + 1]);

        return json;
    }


}
