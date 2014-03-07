package com.zombie.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class World {
    Array<Brick> bricks = new Array<Brick>();
    public Creature player;

    public int width;
    public int height;

    public Array<Brick> getBricks() {
        return bricks;
    }

    public Creature getPlayer() {
        return player;
    }

    public World() {
        width = 8;
        height = 8;
        createWorld();
    }

    public void createWorld() {
        player = new Creature(new Vector2(6,2));
        bricks.add(new Brick(new Vector2(0, 0)));
        bricks.add(new Brick(new Vector2(1, 2)));
        bricks.add(new Brick(new Vector2(2, 0)));
        bricks.add(new Brick(new Vector2(3, 2)));
        bricks.add(new Brick(new Vector2(4, 6)));
        bricks.add(new Brick(new Vector2(5, 0)));
        bricks.add(new Brick(new Vector2(6, 1)));
        bricks.add(new Brick(new Vector2(7, 1)));
    }
}
