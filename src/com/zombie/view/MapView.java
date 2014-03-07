package com.zombie.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.model.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.01.14
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class MapView extends Actor {
    private Map model;

    public MapView(Map m) {
        model = m;
        textureGrass = TextureLibrary.getMapTexture()[0];
        textureWater = TextureLibrary.getMapTexture()[1];
    }

    Texture textureGrass;
    Texture textureWater;

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        /*Rectangle rect =  model.getBounds();
        float x1 =  model.getPosition().x + rect.x;
        float y1 =  model.getPosition().y + rect.y;
        batch.draw(texture, x1, y1, rect.width, rect.height); */
        for (int i = 0; i < model.getWidth(); i++) {
            for (int j = 0; j < model.getHeight(); j++) {
                if (model.isWater(new Vector2(i,j)))
                    batch.draw(textureWater, i, j, 1, 1);
                else
                    batch.draw(textureGrass, i, j, 1, 1);
            }
        }
    }
}
