package com.zombie;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zombie.interconnection.Client;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class ZombieApplication {

    private static LwjglApplication application;

    public static void main(String[] args) throws URISyntaxException {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Zombie Apocalypse";
        cfg.resizable = true;
        cfg.fullscreen = false;
        cfg.width = 800;
        cfg.height = 600;
        Client.init(new URI("ws://127.0.0.1:900/websocket"));
        application = new LwjglApplication(new ZombieGame(), cfg);


    }

    public static void postRunnable(Runnable runnable) {
        application.postRunnable(runnable);
    }
}
