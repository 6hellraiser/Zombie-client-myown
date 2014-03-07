package com.zombie.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.01.14
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class Resource {
    public static final float SIZE = 1f;
    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public Resource(Vector2 pos, ResourceType rt) {
        type = rt;
        position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    private ResourceType type;

    public ResourceType getResourceType() {
        return type;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }
}
