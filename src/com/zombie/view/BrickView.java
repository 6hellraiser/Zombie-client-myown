package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.model.Brick;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 10.12.13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class BrickView extends Actor {

    private Brick model;

    public BrickView(Brick model) {
        this.model = model;
    }

    Texture texture = new Texture(Gdx.files.internal("assets/pictures/brick.png"));

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

            Rectangle rect =  model.getBounds();
            float x1 =  model.getPosition().x + rect.x;
            float y1 =  model.getPosition().y + rect.y;
            batch.draw(texture, x1, y1, rect.width, rect.height);
    }
}
