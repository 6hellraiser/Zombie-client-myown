package com.zombie.interconnection;

import com.zombie.model.Island;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Наталья on 02.03.14.
 */
public class Client {

    private static int command_id;

    static final String ERROR = "error";
    static final String HELLO = "hello";
    static final String LOGIN = "login";
    static final String REGISTER = "register";
    static final String GET_ISLANDS = "get_islands";
    static final String CREATE_ISLAND = "create_island";
    static final String JOIN_ISLAND = "join_island";
    static final String START_GAME = "start_game";
    static final String FINISH_GAME = "finish_game";
    static final String GET_ENTITY = "get_entity";
    static final String GET_VISIBLE_ENTITIES = "get_visible_entities";
    static final String MOVE = "move";
    static final String TAKE = "take";
    static final String DROP = "drop";
    static final String GET_BASE = "get_base";
    static final String USE = "use";
    static final String ATTACK = "attack";

    public static EventLoopGroup group;
    public static Channel channel;
    public static ArrayList<Island> worlds = new ArrayList<Island>();
    private static URI uri;
    private static int TEST_VAR = 4;
    private static Map<String, Callback<Object>> callbacks = new HashMap<String, Callback<Object>>();

    //when game starts
    public static Long base_x;
    public static Long base_y;
    public static Long team_id;
    public static ArrayList<Long> men_id = new ArrayList<Long>();
    public static ArrayList<Long> entities_id = new ArrayList<Long>();

    //when moves
    public static Float move_x;
    public static Float move_y;

    //when attacks
    public static Integer damage;

    //when gets entity
    public static String type;
    public static Long getEntity_x;
    public static Long getEntity_y;
    //array resources
    public static boolean alive;
    public static Long speed;
    public static Long accuracy;
    public static Long health;
    public static Long stamina;
    public static Long hunger;
    public static Long thirst;
    public static Long team;
    //json backpack
    //json weapon
    public static Long weapon_Position_x;
    public static Long weapon_Position_y;
    public static Long weapon_Weight;
    public static Long weapon_Damage;
    public static Long weapon_Range;
    public static Long weapon_AmmoPerAttack;
    public static Long weapon_delay;
    public static boolean weapon_used;
    public static Long weapon_id;

    public static Long weight;
    public static boolean used;
    public static Long id;


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
                            }
                        });

                channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
                handler.handshakeFuture().sync();

                // WebSocketFrame frame = new PingWebSocketFrame(Unpooled.copiedBuffer(new byte[]{8, 1, 8, 1}));
                // channel.writeAndFlush(frame);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                //group.shutdownGracefully();
            }
        }
        return channel;
    }

    public static void authenticate(String login, String password, final Callback<Object> callback) {

        if (channel == null) {
            try {
                Client.init(new URI("ws://127.0.0.1:900/websocket"));
            } catch (URISyntaxException e) {
                System.out.print(e.getMessage());
            }
        }
        callbacks.put(LOGIN, callback);
        sendRequest(LOGIN, json(
                "username", login,
                "password", password));
    }

    private static Callback<Object> findCallback(String id) {
        return callbacks.get(id);
    }

    public static void onReceivemessage(String message) {
        JSONParser parser = new JSONParser();
        String id = null;
        JSONObject jsonObj = null;

        try {
            Object obj = parser.parse(message);
            jsonObj = (JSONObject) obj;
            id = (String) jsonObj.get("name");
            System.out.println(id);
        } catch (org.json.simple.parser.ParseException e) {
            System.out.print(e.getMessage());
        }

        if (id.equals(LOGIN)) {
            onAuthenticate(jsonObj);

        } else if (id.equals(REGISTER)) {
            //onAuthenticate(jsonObj);

        } else if (id.equals(GET_ISLANDS)) {
            onGetIslands(jsonObj);

        } else if (id.equals(CREATE_ISLAND)) {
            onCreateIsland(jsonObj);

        } else if (id.equals(ERROR)) {
            onGetError(jsonObj);
        } else if (id.equals(JOIN_ISLAND)) {
           // onJoinIsland(jsonObj);
        } else if (id.equals(START_GAME)) {
            onStartgame(jsonObj);
        } else if (id.equals(GET_ENTITY)) {
            onGetentity(jsonObj);
        } else if (id.equals(MOVE)) {
            onMove(jsonObj);
        } else if (id.equals(ATTACK)) {
            onAttack(jsonObj);
        }


    }

    private static void onGetError(JSONObject jsonObj) {
        //понять,к какой команде ошибка относится
    }

    private static void onGetIslands(JSONObject jsonObj) {
        JSONObject nested = (JSONObject) jsonObj.get("parameters");
        JSONArray islands = (JSONArray) nested.get("islands");
        if (!islands.isEmpty()) {
            worlds.clear();
            for (int i = 0; i < islands.size(); i++) {
                JSONObject island = (JSONObject) islands.get(i);
                Island isl = new Island((Long) island.get("current_players"), (Long) island.get("island_id"), (Long) island.get("max_players"));
                //worlds[i] = isl;
                worlds.add(isl);
                //worlds[i].setIsland_id((Long)island.get("island_id"));
            }
            findCallback(GET_ISLANDS).callback(true);
        } else
            findCallback(GET_ISLANDS).callback(false);
    }

    public static void onAuthenticate(JSONObject obj) {
        JSONObject nested = (JSONObject) obj.get("parameters");
        findCallback(LOGIN).callback(nested.isEmpty());

    }

    public static void registration(String login, String password) {
        sendRequest(REGISTER, json(
                "username", login,
                "password", password));
    }

    public static void createIsland(final Callback<Object> callback, int players) {
        callbacks.put(CREATE_ISLAND, callback);
        sendRequest(CREATE_ISLAND, json("players", players));
    }

    public static void onCreateIsland(JSONObject obj) {
        JSONObject nested = (JSONObject) obj.get("parameters");
        findCallback(CREATE_ISLAND).callback(nested.isEmpty());
    }

    public static void getIslands(final Callback<Object> callback) {

        callbacks.put(GET_ISLANDS, callback);
        sendRequest(GET_ISLANDS, json());
    }

    public static void joinIsland(int island_id) {
     //   callbacks.put(JOIN_ISLAND, callback);
        sendRequest(JOIN_ISLAND, json("island_id", island_id));
    }

    public static void onJoinIsland(JSONObject obj) {
        JSONObject nested = (JSONObject) obj.get("parameters");
        findCallback(JOIN_ISLAND).callback(nested.isEmpty());
    }

    public static void startGame(final Callback<Object> callback) {
        callbacks.put(START_GAME, callback);
    }

    public static void onStartgame(JSONObject obj) {
        JSONObject nested = (JSONObject) obj.get("parameters");
        JSONObject base = (JSONObject) nested.get("base");
        base_x = (Long) base.get("x");
        base_y = (Long) base.get("y");
        entities_id = (ArrayList<Long>) nested.get("entities");
        men_id = (ArrayList<Long>) nested.get("men");
        team_id = (Long) nested.get("team");
        findCallback(START_GAME).callback(true);
    }

    public static void getEntity(final Callback<Object> callback, Long id) {
        callbacks.put(GET_ENTITY, callback);
        sendRequest(GET_ENTITY, json("id", id));
    }

    public static void onGetentity(JSONObject obj) {
       // Entity e = new Building();
        JSONObject nested = (JSONObject) obj.get("parameters");
        JSONObject entity = (JSONObject)nested.get("entity");
        type = (String) nested.get("type");
        JSONObject position = (JSONObject) entity.get("position");
        getEntity_x = (Long) position.get("x");
        getEntity_y = (Long) position.get("y");
        if (type.equals("base")) {
            //TODO есть еще ресурсы, которые сейчас пусты

        } else if (type.equals("man")) {
            alive = (Boolean) entity.get("alive");
            speed = (Long) entity.get("speed");
            accuracy = (Long) entity.get("accuracy");
            health = (Long) entity.get("health");
            stamina = (Long) entity.get("stamina");
            hunger = (Long) entity.get("hunger");
            thirst = (Long) entity.get("thirst");
            team = (Long) entity.get("team");
            JSONObject weapon = (JSONObject) entity.get("weapon");
            if (weapon != null) {
                JSONObject weapon_position = (JSONObject) weapon.get("position");
                weapon_Position_x = (Long) weapon_position.get("x");
                weapon_Position_y = (Long) weapon_position.get("y");
                weapon_Weight = (Long) weapon.get("weight");
                weapon_Damage = (Long) weapon.get("damage");
                weapon_Range = (Long) weapon.get("range");
                weapon_AmmoPerAttack = (Long) weapon.get("ammoPerAttack");
                weapon_delay = (Long) weapon.get("delay");
                weapon_used = (Boolean) weapon.get("used");
                weapon_id = (Long) weapon.get("id");
            }

        } else if (type.equals("building")) {
            //e = new Building();
           // e.type = "building";
            //e.x = (int)(long)getEntity_x;
            //e.y = (int)(long)getEntity_y;

        } else if (type.equals("zombie")) {
            alive = (Boolean) entity.get("alive");
            speed = (Long) entity.get("speed");
            accuracy = (Long) entity.get("accuracy");
            health = (Long) entity.get("health");
            stamina = (Long) entity.get("stamina");
            JSONObject weapon = (JSONObject) entity.get("weapon");
            JSONObject weapon_position = (JSONObject) weapon.get("position");
            weapon_Position_x = (Long) weapon_position.get("x");
            weapon_Position_y = (Long) weapon_position.get("y");
            weapon_Weight = (Long) weapon.get("weight");
            weapon_Damage = (Long) weapon.get("damage");
            weapon_Range = (Long) weapon.get("range");
            weapon_AmmoPerAttack = (Long) weapon.get("ammoPerAttack");
            weapon_delay = (Long) weapon.get("delay");
            weapon_used = (Boolean) weapon.get("used");
            weapon_id = (Long) weapon.get("id");

        } else if (type.equals("water") || type.equals("medicines") || type.equals("food")) {
            weight = (Long) entity.get("weight");
            id = (Long) entity.get("id");
            used = (Boolean) entity.get("used");

        }
        findCallback(GET_ENTITY).callback(nested);
    }

    public static void move(final Callback<Object> callback, Integer man_id, Float x, Float y) {
        callbacks.put(MOVE, callback);
        sendRequest(MOVE, json("man_id", man_id,
                                "x", x,
                                "y", y));
    }

    public static void onMove(JSONObject obj) {
        JSONObject nested = (JSONObject) obj.get("parameters");
        move_x = (Float) nested.get("x");
        move_y = (Float) nested.get("y");
        findCallback(MOVE).callback(true);
    }

    public static void attack(final Callback<Object> callback, Integer man_id, Integer id) {
        callbacks.put(ATTACK, callback);

    }

    public static void onAttack(JSONObject obj) {
        JSONObject nested = (JSONObject) obj.get("parameters");
        damage = (Integer) nested.get("damage");
        findCallback(ATTACK).callback(true);
    }

    private static void sendRequest(String commandType, JSONObject obj) {

        JSONObject resultJson = json(
                "name", commandType,
                "id", command_id++,
                "parameters", obj
        );

        channel().writeAndFlush(new TextWebSocketFrame(resultJson.toString()));
    }

    public static JSONObject json(Object... params) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < params.length; i += 2)
            json.put(params[i], params[i + 1]);

        return json;
    }


}
