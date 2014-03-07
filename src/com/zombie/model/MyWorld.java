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
    private ArrayList<Creature> creatures = new ArrayList<Creature>();

    private ArrayList<Man> men = new ArrayList<Man>();

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



    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Man> getMen() {
        return men;
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
        Creature creature = new Creature(new Vector2(10,2));
        Man man = new Man(new Vector2(2,10), Team.Red);
        creatures.add(creature);
        men.add(man);
        man = new Man(new Vector2(14, 12), Team.Yellow);
        men.add(man);

        resources.add(new Resource(new Vector2(2,7),ResourceType.Food));
        map = new Map(36, 25);

    }
}
