package com.zombie.controller;

import com.badlogic.gdx.math.Vector2;
import com.zombie.model.*;
import com.zombie.view.WorldView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public class WorldController {
    enum Keys {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        Q,
        E
    }

    private ArrayList<Creature> creatures;
    /*
    TODO: delete creature
     */
    private Creature creature;
    private WorldView worldView;
    private ArrayList<Vector2> water;
    private ArrayList<Resource> resources;
    private ArrayList<Man> men;
    private com.zombie.model.Map map;

    static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
        keys.put(Keys.Q, false);
        keys.put(Keys.E, false);
    };

    public WorldController(WorldView worldView) {
        this.creatures = worldView.getWorld().getCreatures();
        /*
        TODO: delete
         */
        this.creature = creatures.get(0);
        this.water = worldView.getWorld().getMap().getWater();//getBricks();
        this.men = worldView.getWorld().getMen();
        this.map = worldView.getWorld().getMap();
        this.resources = worldView.getWorld().getResources();
        this.worldView = worldView;
    }

    public void zoomIn() {
        keys.put(Keys.Q, true);
    }

    public void zoomOut() {
        keys.put(Keys.E, true);
    }

    public void stopZoomIn() {
        keys.put(Keys.Q, false);
    }

    public void stopZoomOut() {
        keys.put(Keys.E, false);
    }

    public void moveLeft() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    public void moveRight() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    public void moveUp() {
        keys.get(keys.put(Keys.UP, true));
    }

    public void moveDown() {
        keys.get(keys.put(Keys.DOWN, true));
    }

    public void stopLeft() {
        keys.get(keys.put(Keys.LEFT, false));
    }
    public void stopRight() {
        keys.get(keys.put(Keys.RIGHT, false));
    }
    public void stopUp() {
        keys.get(keys.put(Keys.UP, false));
    }
    public void stopDown() {
        keys.get(keys.put(Keys.DOWN, false));
    }

    public void update(float delta) {
        processInput();
        creature.move(delta); /////////////////////////////
    }

    public void resetWay(){
        stopRight();
        stopLeft();
        stopDown();
        stopUp();
    }

    private void checkPath() {

        if (creature.getPosition().x <= 0)
            stopLeft();
        if (creature.getPosition().y <= 0)
            stopDown();

        if (creature.getPosition().x >= worldView.CAMERA_WIDTH - creature.SIZE)
            stopRight();
        if (creature.getPosition().y >= worldView.CAMERA_HEIGHT - creature.SIZE)
            stopUp();

        for (int i = 0; i < water.size(); i++) {

            if ((creature.getPosition().x >= water.get(i).x - creature.SIZE)
                    && (creature.getPosition().x <= water.get(i).x + creature.SIZE)
                    && (creature.getPosition().y >= water.get(i).y - creature.SIZE)
                    && (creature.getPosition().y <= water.get(i).y + creature.SIZE)
                    )  {
                if (creature.getPosition().x <= water.get(i).x)
                    stopRight();
                if (creature.getPosition().x >= water.get(i).x)
                    stopLeft();
                if (creature.getPosition().y <= water.get(i).y)
                    stopUp();
                if (creature.getPosition().y >= water.get(i).y)
                    stopDown();
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////
        /*for (int i = 0; i < resources.size(); i++) {
            if ((creature.getPosition().x >= resources.get(i).getPosition().x - creature.SIZE)
                    && (creature.getPosition().x <= resources.get(i).getPosition().x + creature.SIZE)
                    && (creature.getPosition().y >= resources.get(i).getPosition().y - creature.SIZE)
                    && (creature.getPosition().y <= resources.get(i).getPosition().y + creature.SIZE)
                    )  {

            }
        }*/

        for (int i = 0; i < men.size(); i++)
        {
            Man man = men.get(i);
            if ((creature.getPosition().x >= man.getPosition().x - man.SIZE/2)
                    && (creature.getPosition().x <= man.getPosition().x + man.SIZE/2)
                    && (creature.getPosition().y >= man.getPosition().y - man.SIZE/2)
                    && (creature.getPosition().y <= man.getPosition().y + man.SIZE/2)
                    ) {
                man.setDead(true);
            }
        }
    }

    private void processInput() {

        checkPath();

        if (keys.get(Keys.LEFT))
            creature.getVelocity().x = -Creature.SPEED;

        if (keys.get(Keys.RIGHT))
            creature.getVelocity().x = Creature.SPEED;

        if (keys.get(Keys.UP))
            creature.getVelocity().y = Creature.SPEED;


        if (keys.get(Keys.DOWN))
            creature.getVelocity().y = -Creature.SPEED;

        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) || (!keys.get(Keys.LEFT) && (!keys.get(Keys.RIGHT))))
            creature.getVelocity().x = 0;
        if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) || (!keys.get(Keys.UP) && (!keys.get(Keys.DOWN))))
            creature.getVelocity().y = 0;

        if (keys.get(Keys.Q)) {
            worldView.zoomIn();
        }

        if (keys.get(Keys.E)) {
            worldView.zoomOut();
        }
    }
}
