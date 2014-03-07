package com.zombie;

import com.badlogic.gdx.Game;
import com.zombie.screens.GameScreen;
import com.zombie.screens.MainScreen;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class ZombieGame extends Game {

    public GameScreen gameScreen;
    public MainScreen mainScreen;

    @Override
    public void create() {
        gameScreen = new GameScreen();
        mainScreen = new MainScreen(this);
        setScreen(mainScreen);
    }

    @Override
    public void resize(int width, int height) {
        if (width < height)
            height = width;
        else
            width = height;
        super.resize(width, height);
    }
}
