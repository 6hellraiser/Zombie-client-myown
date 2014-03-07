package com.zombie.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.01.14
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class Building {
    public static final float SIZE = 2f;
    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public Building(Vector2 pos, BuildingType bt) {
        type = bt;
        position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    public Building(Vector2 pos, Team t) {
        type = BuildingType.House;
        team = t;
        position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    private BuildingType type;
    private Team team = null;

    public BuildingType getBuildingType() {
        return type;
    }

    public Team getTeam() {
        return team;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }
}
