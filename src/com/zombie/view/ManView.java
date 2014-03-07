package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.model.Man;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 12.12.13
 * Time: 13:36
 * To change this template use File | Settings | File Templates.
 */
public class ManView extends Actor {
    private Man model;

    public ManView(Man model) {
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

        Rectangle rect = model.getBounds();
        float x1 = model.getPosition().x + rect.x;
        float y1 = model.getPosition().y + rect.y;
        if (model.isDead())
            currentTexture = textureDead;
        else {
            if (model.getVelocity().x < 0)
                currentTexture = textureLeft;
            if (model.getVelocity().x > 0)
                currentTexture = textureRight;
        }
        batch.draw(currentTexture, x1, y1, rect.width, rect.height);
    }
}
