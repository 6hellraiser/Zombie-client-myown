package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.zombie.model.Man;
import com.zombie.plugin.AbstractCharacter;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 12.12.13
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class ManView extends Actor {
    private AbstractCharacter model;
    private final float size = 1f;

    public ManView(AbstractCharacter model) {
        this.model = model;
        textureLeft = TextureLibrary.getTextures(model.getTeam())[0];
        textureRight = TextureLibrary.getTextures(model.getTeam())[1];
        textureDead = TextureLibrary.getTextures(model.getTeam())[2];
        currentTexture = textureLeft;
    }

    Texture textureLeft;
    Texture textureRight;
    Texture textureDead;
    Texture currentTexture;

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

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
