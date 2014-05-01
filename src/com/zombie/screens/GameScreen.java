package com.zombie.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL10;
import com.zombie.PluginFactory;
import com.zombie.model.*;
import com.zombie.plugin.AbstractCharacter;
import com.zombie.plugin.Team;
import com.zombie.plugin.Weapon;
import com.zombie.view.*;
import com.zombie.controller.*;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen, InputProcessor {

    private MyWorld world;
    private WorldController controller;
    private WorldView worldView;

    private int width, height;

    @Override
    public void show() {
        //TODO: not new?..
        setGame();
        Gdx.input.setInputProcessor(this);

        /*Music music = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/music/01.mp3"));
        music.setLooping(true);
        music.play();*/
    }

    private void setGame() {
        if (world == null) {
            System.out.println("CHUPO-MOTING-CHUPS");
            //TODO: tododoododoodoooooooo PARSE THE UNIVERSE
            //
            world = new MyWorld();
            worldView = new WorldView(world);
            controller = new WorldController(world);
        }
    }

    public void setCharacter(AbstractCharacter character) {
        setGame();

        for (int i = 0; i < world.getMap().getTeams().size(); i++) {
            if (world.getMap().getTeams().get(i).getTeam().equals(character.getTeam())) {
                character.setX(world.getMap().getTeams().get(i).getPosition().x);
                character.setY(world.getMap().getTeams().get(i).getPosition().y);
                //TODO послать серверу команду с твоими координатами
                character.setWorld(world);
                Weapon weapon = new Weapon();
                weapon.setDamage(10);
                weapon.setDistance(1);
                character.setWeapon(weapon);
                world.getMen().add(character);
                worldView.addActor(new ManView(character));
                return;
            }
        }
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
                   return true;
    }

    @Override
    public void resize(int width, int height) {
        worldView.setSize(width, height);
        this.width = width;
        this.height = height;
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
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                controller.moveLeft();
                break;
            case Input.Keys.RIGHT:
                controller.moveRight();
                break;
            case Input.Keys.UP:
                controller.moveUp();
                break;
            case Input.Keys.DOWN:
                controller.moveDown();
                break;
            case Input.Keys.Q:
                controller.zoomIn();
                break;
            case Input.Keys.E:
                controller.zoomOut();
                break;
        }
        return true;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        controller.update(delta);
        worldView.draw();
    }
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                controller.stopLeft();
                break;
            case Input.Keys.RIGHT:
                controller.stopRight();
                break;
            case Input.Keys.UP:
                controller.stopUp();
                break;
            case Input.Keys.DOWN:
                controller.stopDown();
                break;
            case Input.Keys.Q:
                controller.stopZoomIn();
                break;
            case Input.Keys.E:
                controller.stopZoomOut();
                break;

        }
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        if (button == Input.Buttons.RIGHT) {
            CameraUtils.setCamera(x, this.height - y);
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}