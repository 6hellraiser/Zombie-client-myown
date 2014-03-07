package com.zombie;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class ZombieApplication {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Zombie Apocalypse";
        cfg.resizable = true;
        cfg.fullscreen = false;
        cfg.width = 800;
        cfg.height = 600;
        new LwjglApplication(new ZombieGame(), cfg);
    }

}
