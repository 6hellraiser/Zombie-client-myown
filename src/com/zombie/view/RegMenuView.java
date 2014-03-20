package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zombie.screens.MainScreen;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 08.03.14
 * Time: 9:52
 * To change this template use File | Settings | File Templates.
 */
public class RegMenuView extends Table {
    private Label title;
    private String login;
    private String password;
    private TextButton btnOk;
    private MainMenuStage stage;
    private Label attackLabel;
    private TextButton mAttackButton;
    private TextButton pAttackButton;
    private Label healthLabel;
    private TextButton mHealthButton;
    private TextButton pHealthButton;
    private Label freeLabel;
    private Integer sum = 100;
    private Integer attack = 50;
    private Integer health = 50;

    public void setLoginAndPassword(String l, String p) {
         login = l;
         password = p;
    }

    public RegMenuView(MainMenuStage g) {
        stage = g;

        title = new Label("Create character", MainScreen.regularSkin);
        title.setPosition(Gdx.graphics.getWidth() / 2  - title.getWidth()/2, Gdx.graphics.getHeight() - title.getHeight()*2);
        addActor(title);

        freeLabel = new Label("Free: ".concat(((Integer)(sum - attack - health)).toString()), MainScreen.regularSkin);
        freeLabel.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.65f);
        addActor(freeLabel);

        attackLabel = new Label("Attack: ".concat(attack.toString()), MainScreen.regularSkin);
        attackLabel.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.55f);
        addActor(attackLabel);

        mAttackButton = new TextButton("-", MainScreen.regularSkin);
        mAttackButton.setPosition(attackLabel.getRight() + 3.0f, attackLabel.getTop() - attackLabel.getHeight());
        mAttackButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (attack > 0)
                    attack--;
                attackLabel.setText("Attack: ".concat(attack.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(mAttackButton);

        pAttackButton = new TextButton("+", MainScreen.regularSkin);
        pAttackButton.setPosition(mAttackButton.getRight() + 3.0f, mAttackButton.getTop() - mAttackButton.getHeight());
        pAttackButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if ((attack + health) < sum)
                    attack++;
                attackLabel.setText("Attack: ".concat(attack.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(pAttackButton);

        healthLabel = new Label("Health: ".concat(attack.toString()), MainScreen.regularSkin);
        healthLabel.setPosition(pAttackButton.getRight() + 50.0f, pAttackButton.getTop() - pAttackButton.getHeight());
        addActor(healthLabel);

        mHealthButton = new TextButton("-", MainScreen.regularSkin);
        mHealthButton.setPosition(healthLabel.getRight() + 3.0f, healthLabel.getTop() - healthLabel.getHeight());
        mHealthButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (health > 0)
                    health--;
                healthLabel.setText("Health: ".concat(health.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(mHealthButton);

        pHealthButton = new TextButton("+", MainScreen.regularSkin);
        pHealthButton.setPosition(mHealthButton.getRight() + 3.0f, mHealthButton.getTop() - mHealthButton.getHeight());
        pHealthButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if ((attack + health) < sum)
                    health++;
                healthLabel.setText("Health: ".concat(health.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(pHealthButton);

        //TODO: team, type

        btnOk = new TextButton("Ok", MainScreen.regularSkin);
        btnOk.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.1f);
        btnOk.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO: send login password and player info
                stage.gameScreen.setScreen(stage.gameScreen.gameScreen);
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnOk);
    }
}