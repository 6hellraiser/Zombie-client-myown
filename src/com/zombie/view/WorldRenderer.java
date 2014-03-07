package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.zombie.model.*;
/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class WorldRenderer {
    public static float CAMERA_WIDTH = 8f;
    public static float CAMERA_HEIGHT = 8f;

    private World world;
    public OrthographicCamera cam;

    private SpriteBatch batch;


    public int width;
    public int height;
    public float ppuX;
    public float ppuY;

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    public void SetCamera(float x, float y){
        this.cam.position.set(x, y, 0);
        this.cam.update();
    }

    public WorldRenderer(World world) {
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        SetCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        batch = new SpriteBatch();
    }

    public void render() {
        drawBricks();
        drawPlayer();
    }

    private void drawBricks() {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (Brick brick : world.getBricks()) {
            Rectangle rect =  brick.getBounds();
            float x1 =  brick.getPosition().x + rect.x;
            float y1 =  brick.getPosition().y + rect.y;
            Texture texture = new Texture(Gdx.files.internal("assets/pictures/brick.png"));
            batch.draw(texture, x1, y1, rect.width, rect.height);
        }
        batch.end();
    }

    private void drawPlayer() {
        Creature player = world.getPlayer();
        batch.setProjectionMatrix(cam.combined);
        Rectangle rect = player.getBounds();
        float x1 = player.getPosition().x + rect.x;
        float y1 = player.getPosition().y + rect.y;
        Texture texture = new Texture(Gdx.files.internal("assets/pictures/zombie.png"));
        batch.begin();
        batch.draw(texture, x1, y1, rect.width, rect.height);
        batch.end();
    }
}
