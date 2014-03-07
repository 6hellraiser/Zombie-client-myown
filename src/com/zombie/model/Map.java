package com.zombie.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.01.14
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class Map {

    private Integer height;
    private Integer width;

    public Map(Integer w, Integer h) {
        width = w;
        height = h;
        water.add(new Vector2(1,4));
        water.add(new Vector2(1,2));
        water.add(new Vector2(1,3));
        water.add(new Vector2(2,2));
        water.add(new Vector2(2,3));
        buildings.add(new Building(new Vector2(0, 0), Team.Yellow));
        buildings.add(new Building(new Vector2(1, 5), BuildingType.Hospital));
        buildings.add(new Building(new Vector2(6, 5), BuildingType.Police));
        buildings.add(new Building(new Vector2(7, 10), Team.Red));
        buildings.add(new Building(new Vector2(13, 1), Team.Green));
    }

    public Integer getHeight() {
        return  height;
    }

    public Integer getWidth() {
        return width;
    }

    private ArrayList<Vector2> water = new ArrayList<Vector2>();
    private ArrayList<Building> buildings = new ArrayList<Building>();

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public boolean isWater(Vector2 pos) {
        if (water.contains(pos))
            return true;
        else
            return false;
    }

    public ArrayList<Vector2> getWater() {
        return water;
    }
}
