package com.zombie.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.model.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.01.14
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class ResourceView extends Actor {
    Resource model;

    public ResourceView(Resource res) {
        model = res;
        texture = TextureLibrary.getResourceTexture(model.getResourceType());
    }

    Texture texture;

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Rectangle rect = model.getBounds();
        float x1 = model.getPosition().x + rect.x;
        float y1 = model.getPosition().y + rect.y;

        batch.draw(texture, x1, y1, rect.width, rect.height);
    }
}
