package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zombie.ZombieGame;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 19.12.13
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuStage extends Stage {
    private SpriteBatch batch;
    private MainMenuView mainMenuView;
    private LoginMenuView loginMenuView;
    public float CAMERA_WIDTH = 800f;
    public float CAMERA_HEIGHT = 800f;
    public OrthographicCamera cam;
    private int width, height;

    ZombieGame gameScreen;

    public MainMenuStage(ZombieGame g) {
        gameScreen = g;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        mainMenuView = new MainMenuView(this);
        mainMenuView.setFillParent(true);
        batch = new SpriteBatch();
        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        cam.update();
        addActor(mainMenuView);

        loginMenuView = new LoginMenuView(this);
        loginMenuView.setFillParent(true);
        loginMenuView.setVisible(false);
        addActor(loginMenuView);
    }

    public void getLoginView() {
        mainMenuView.setVisible(false);
        loginMenuView.setVisible(true);
    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
    }

    @Override
    public void draw() {
        batch.setProjectionMatrix(cam.combined);
        /*batch.begin();
        batch.draw(texture, widthStart, 0, width, height);
        for (int i = 0; i < getActors().size; i++) {
            getActors().get(i).draw(batch, 10);
        }
        batch.end(); */
        super.draw();
    }
}
