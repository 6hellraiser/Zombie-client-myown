package com.zombie.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.zombie.ZombieGame;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 19.12.13
 * Time: 9:51
 * To change this template use File | Settings | File Templates.
 */
public class MainScreen implements Screen {

    public static Skin regularSkin = new Skin(Gdx.files.internal("assets/skins/MainSkin.skin"));

    private ZombieGame gameScreen;
    private MainMenuStage mainMenuStage;

    public MainScreen(ZombieGame g) {
        gameScreen = g;
        mainMenuStage = new MainMenuStage(gameScreen);
        Gdx.input.setInputProcessor(mainMenuStage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mainMenuStage);
    }

    @Override
    public void resize(int width, int height) {
        mainMenuStage.setViewport(width, height, true);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        mainMenuStage.dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        mainMenuStage.act(Gdx.graphics.getDeltaTime());
        mainMenuStage.draw();
    }
}
