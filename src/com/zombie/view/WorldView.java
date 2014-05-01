package com.zombie.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zombie.model.MyWorld;
import com.zombie.plugin.AbstractCharacter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 10.12.13
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class WorldView extends Stage {

    private MyWorld model;
    //public float CAMERA_WIDTH = 8f;
    //public float CAMERA_HEIGHT = 8f;
    //private double zoomK = 0.02;

    /*public MyWorld getWorld() {
        return model;
    }*/

    //public OrthographicCamera cam;

    private SpriteBatch batch;

    public int width;
    public int height;


    public WorldView(MyWorld model) {
        this.model = model;
        //CAMERA_WIDTH = (float)model.getMap().getWidth();
        //CAMERA_HEIGHT = (float)model.getMap().getHeight();
        //this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        //setCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
        batch = new SpriteBatch();

        MapView mapView = new MapView(model.getMap());
        addActor(mapView);

        for (int i = 0; i < model.getMap().getBuildings().size(); i++) {
            BuildingView bv = new BuildingView(model.getMap().getBuildings().get(i));
            addActor(bv);
        }

        for (int i = 0; i < model.getMap().getTeams().size(); i++) {
            BuildingView bv = new BuildingView(model.getMap().getTeams().get(i));
            addActor(bv);
        }

        ArrayList<AbstractCharacter> men = model.getMen();
        for (int i = 0; i < men.size(); i++)
        {
            ManView m = new ManView(men.get(i));
            addActor(m);
        }

        for (int i = 0; i < model.getCreatures().size(); i++)
        {
            CreatureView cr = new CreatureView(model.getCreatures().get(i));
            addActor(cr);
        }

        for (int i = 0; i < model.getMap().getResources().size(); i++) {
            ResourceView rv = new ResourceView(model.getMap().getResources().get(i));
            addActor(rv);
        }


    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        CameraUtils.ppuX = (float)width / CameraUtils.getCAMERA_WIDTH();
        CameraUtils.ppuY = (float)height / CameraUtils.getCAMERA_HEIGHT();
    }

    /*public void setCamera(float x, float y){
        this.cam.position.set(x, y, 0);
        this.cam.update();
    } */

    ///////////////////////////////////////////???
    /*public void setCamera(int x, int y){
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
    }*/


    @Override
    public void draw() {
        batch.setProjectionMatrix(CameraUtils.cam.combined);
        batch.begin();
        //super.draw();

        for (int i = 0; i < getActors().size; i++) {
            //System.out.println("CLASS: " + getActors().get(i).getClass().getSimpleName());
            getActors().get(i).draw(batch, 10);
        }
        batch.end();
    }
}
