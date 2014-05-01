package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.zombie.enums.BuildingType;
import com.zombie.plugin.ResourceType;
import com.zombie.plugin.Team;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 06.01.14
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public class TextureLibrary {

    public static Texture[] getTextures(Team team) {
        Texture[] textures = new Texture[3];
        if (team == null) {
            textures[0] = new Texture(Gdx.files.internal("assets/pictures/people/zombie_l.png"));
            textures[1] = new Texture(Gdx.files.internal("assets/pictures/people/zombie_r.png"));
            textures[2] = new Texture(Gdx.files.internal("assets/pictures/people/zombie_d.png"));
            return textures;
        }
        String t = "r";
        if (team.equals(Team.Red))
            t = "r";
        if (team.equals(Team.Blue))
            t = "b";
        if (team.equals(Team.Yellow))
            t = "y";
        if (team.equals(Team.Green))
            t = "g";
        textures[0] = new Texture(Gdx.files.internal("assets/pictures/people/citizen_".concat(t).concat("_l.png")));
        textures[1] = new Texture(Gdx.files.internal("assets/pictures/people/citizen_".concat(t).concat("_r.png")));
        textures[2] = new Texture(Gdx.files.internal("assets/pictures/people/citizen_".concat(t).concat("_d.png")));
        return textures;
    }

    public static Texture getBuildingTexture(BuildingType type, Team team) {
        Texture texture;
        if (type.equals(BuildingType.House))
        {
            if (team.equals(Team.Red)) {
                texture = new Texture(Gdx.files.internal("assets/pictures/buildings/house_r.png"));
                return texture;
            }
            if (team.equals(Team.Blue)) {
                texture = new Texture(Gdx.files.internal("assets/pictures/buildings/house_b.png"));
                return texture;
            }
            if (team.equals(Team.Yellow)) {
                texture = new Texture(Gdx.files.internal("assets/pictures/buildings/house_y.png"));
                return texture;
            }
            if (team.equals(Team.Green)) {
                texture = new Texture(Gdx.files.internal("assets/pictures/buildings/house_g.png"));
                return texture;
            }
        }
        if (type.equals(BuildingType.Hospital)) {
            texture = new Texture(Gdx.files.internal("assets/pictures/buildings/hospital.png"));
            return texture;
        }
        if (type.equals(BuildingType.Police)) {
            texture = new Texture(Gdx.files.internal("assets/pictures/buildings/police.png"));
            return texture;
        }
        texture = new Texture(Gdx.files.internal("assets/pictures/buildings/house.png"));
        return texture;
    }

    public static Texture[] getMapTexture() {
        Texture[] textures = new Texture[2];
        textures[0] = new Texture(Gdx.files.internal("assets/pictures/map/grass.png"));
        textures[1] = new Texture(Gdx.files.internal("assets/pictures/map/water.png"));
        return textures;
    }

    public static Texture getResourceTexture(ResourceType type) {
        Texture texture;
        if (type.equals(ResourceType.Food))
        {
            texture = new Texture(Gdx.files.internal("assets/pictures/resources/food.png"));
            return texture;
        }
        if (type.equals(ResourceType.Water)) {
            texture = new Texture(Gdx.files.internal("assets/pictures/resources/water.png"));
            return texture;
        }
        if (type.equals(ResourceType.Medicines)) {
            texture = new Texture(Gdx.files.internal("assets/pictures/resources/med.png"));
            return texture;
        }
/*if (type.equals(ResourceType.Ammunition)) {
texture = new Texture(Gdx.files.internal("assets/pictures/resources/ammunition.png"));
return texture;
}
texture = new Texture(Gdx.files.internal("assets/pictures/resources/med.png"));*/
        texture = new Texture(Gdx.files.internal("assets/pictures/map/water.png"));
        return texture;
    }
}
