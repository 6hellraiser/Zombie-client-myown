package com.zombie.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.plugin.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.01.14
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class ResourceView extends Actor {
    Resource model;
    private final float size = 1f;

    public ResourceView(Resource res) {
        model = res;
        texture = TextureLibrary.getResourceTexture(model.getResourceType());
    }

    Texture texture;

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float x1 = model.getX() + size;
        float y1 = model.getY() + size;

        batch.draw(texture, x1, y1, size, size);
    }
}
