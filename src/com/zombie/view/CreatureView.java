package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 10.12.13
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public class CreatureView extends Actor {
    private final float size = 1f;

    private Creature model;

    public CreatureView(Creature model) {
        this.model = model;
        currentTexture = textureLeft;
    }

    Texture textureLeft = TextureLibrary.getTextures(null)[0];
    Texture textureRight = TextureLibrary.getTextures(null)[1];
    Texture textureDead = TextureLibrary.getTextures(null)[2];
    Texture currentTexture;

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        //Rectangle rect = model.getBounds();
        float x1 = model.getX() + size;
        float y1 = model.getY() + size;
        if (model.getCurrentHealth() <= 0)
            currentTexture = textureDead;
        else {
            if (model.getxSpeed() < 0)
                currentTexture = textureLeft;
            if (model.getxSpeed() > 0)
                currentTexture = textureRight;
        }
        batch.draw(currentTexture, x1, y1, size, size);
    }

}
