package com.zombie.model;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 12.12.13
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
/*public class Man extends com.zombie.plugin.AbstractCharacter {
    //ICharacter strategy;
    public static final float SIZE = 1f;
    public static final float SPEED = 3f;
    Vector2 position = new Vector2();
    Vector2 velocity = new Vector2();
    Rectangle bounds = new Rectangle();

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    private boolean dead = false;
    private Team team;

    public Team getTeam() {
        return team;
    }

    public Man(Vector2 pos, Team t, IWorld w) {
        super(w);
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
        team = t;
        //strategy = s;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void action() {
        //strategy.DoMove();
        this.doMove();
    }
    /*public void move(float delta) {
        position.add(velocity.mul(delta));
    }*/
/*}  */
