package com.zombie.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zombie.plugin.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 05.12.13
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class Creature extends AliveObject implements ICharacter {
    public static final float SIZE = 1f;
    //public static final float SPEED = 2f;

    public Creature(Vector2 pos, AliveObjectProperties properties) {
        super(properties);
        this.setX(pos.x);
        this.setY(pos.y);
        //TODO: set zombie id
        //здесь создается зомби, надо серверу команду отправить
        /*id = 1;
        health = 50;
        stamina = 50;
        accuracy = 50;
        visibility = 5;
        speed = 1;*/
        /*Weapon weapon = new Weapon();
        weapon.setDamage(7);
        weapon.setDistance(1);
        setWeapon(weapon);*/
    }

    @Override
    public void doMove() {}

}
