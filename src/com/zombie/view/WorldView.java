package com.zombie.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zombie.model.Man;
import com.zombie.model.MyWorld;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 10.12.13
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class WorldView extends Stage {

    private MyWorld model;
    public float CAMERA_WIDTH = 8f;
    public float CAMERA_HEIGHT = 8f;
    private double zoomK = 0.02;

    public MyWorld getWorld() {
        return model;
    }

    public OrthographicCamera cam;

    private SpriteBatch batch;

    public int width;
    public int height;
    public float ppuX;
    public float ppuY;

    public WorldView(MyWorld model) {
        this.model = model;
        CAMERA_WIDTH = (float)model.getMap().getWidth();
        CAMERA_HEIGHT = (float)model.getMap().getHeight();
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        setCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        batch = new SpriteBatch();


        MapView mapView = new MapView(model.getMap());
        addActor(mapView);

        for (int i = 0; i < model.getBricks().size; i++) {
            BrickView br = new BrickView(model.getBricks().get(i));
            addActor(br);
        }

        for (int i = 0; i < model.getMap().getBuildings().size(); i++) {
            BuildingView bv = new BuildingView(model.getMap().getBuildings().get(i));
            addActor(bv);
        }

        for (int i = 0; i < model.getResources().size(); i++) {
            ResourceView rv = new ResourceView(model.getResources().get(i));
            addActor(rv);
        }
        ManView m = new ManView(model.getMan());
        addActor(m);

        CreatureView cr = new CreatureView(model.getCreature());
        addActor(cr);
    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    public void setCamera(float x, float y){
        this.cam.position.set(x, y, 0);
        this.cam.update();
    }

    ///////////////////////////////////////////???
    public void setCamera(int x, int y){
        float x1 = (float)x/ppuX;
        float y1 = (float)y/ppuY;
        this.cam.position.set(x1, y1, 0);
        this.cam.update();
    }

    public void zoomIn() {
        this.cam.zoom *= (-zoomK+1);
        this.cam.update();
    }

    public void zoomOut() {
        if (this.cam.zoom*(zoomK+1) <= 1) {
            this.cam.zoom *= (zoomK+1);
            this.cam.update();
        }
    }


    @Override
    public void draw() {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        //super.draw();
        for (int i = 0; i < getActors().size; i++) {
            getActors().get(i).draw(batch, 10);
        }
        batch.end();
    }
}
