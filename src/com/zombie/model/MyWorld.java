package com.zombie.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import io.netty.buffer.AbstractByteBuf;
/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class MyWorld {
    private Array<Brick> bricks = new Array<Brick>();

    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private Creature creature;

    private Man man;

    private Map map;

    public int width;
    public int height;

    //public World world;

    public Array<Brick> getBricks() {
        return bricks;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }



    public Creature getCreature() {
        return creature;
    }

    public Man getMan() {
        return man;
    }

    public Map getMap() {
        return map;
    }

    public MyWorld() {
        width = 8;
        height = 8;
        createWorld();
    }

    public void createWorld() {
        creature = new Creature(new Vector2(10,2));
        man = new Man(new Vector2(2,10), Team.Red);
        resources.add(new Resource(new Vector2(2,7),ResourceType.Food));
        map = new Map(16, 12);
        /*bricks.add(new Brick(new Vector2(0, 0)));
        bricks.add(new Brick(new Vector2(1, 2)));
        bricks.add(new Brick(new Vector2(2, 0)));
        bricks.add(new Brick(new Vector2(3, 2)));
        bricks.add(new Brick(new Vector2(4, 6)));
        bricks.add(new Brick(new Vector2(5, 0)));
        bricks.add(new Brick(new Vector2(6, 1)));
        bricks.add(new Brick(new Vector2(7, 1)));*/

    }
}
