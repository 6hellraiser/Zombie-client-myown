package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.zombie.Client;
import com.zombie.model.Team;
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
    private Label accurancyLabel;
    private TextButton mAccurancyButton;
    private TextButton pAccurancyButton;
    private Label freeLabel;
    private Label teamLabel;
    private SelectBox teamBox;
    private Integer sum = 150;
    private Integer attack = 50;
    private Integer health = 50;
    private Integer accurancy = 50;

    public void setLoginAndPassword(String l, String p) {
         login = l;
         password = p;
    }

    public RegMenuView(MainMenuStage g) {
        stage = g;

        title = new Label("Create character", MainScreen.regularSkin);
        title.setPosition(Gdx.graphics.getWidth() / 2  - title.getWidth()/2, Gdx.graphics.getHeight() - title.getHeight()*2);
        addActor(title);

        freeLabel = new Label("Free: ".concat(((Integer)(sum - attack - health - accurancy)).toString()), MainScreen.regularSkin);
        freeLabel.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.65f);
        addActor(freeLabel);

        attackLabel = new Label("Attack: ".concat(attack.toString()), MainScreen.regularSkin);
        attackLabel.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.50f);
        addActor(attackLabel);

        mAttackButton = new TextButton("-", MainScreen.regularSkin);
        mAttackButton.setPosition(attackLabel.getRight() + 3.0f, attackLabel.getTop() - attackLabel.getHeight());
        mAttackButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (attack > 0)
                    attack--;
                attackLabel.setText("Attack: ".concat(attack.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health - accurancy)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(mAttackButton);

        pAttackButton = new TextButton("+", MainScreen.regularSkin);
        pAttackButton.setPosition(mAttackButton.getRight() + 3.0f, mAttackButton.getTop() - mAttackButton.getHeight());
        pAttackButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if ((attack + health + accurancy) < sum)
                    attack++;
                attackLabel.setText("Attack: ".concat(attack.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health - accurancy)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(pAttackButton);

        healthLabel = new Label("Health: ".concat(health.toString()), MainScreen.regularSkin);
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
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health - accurancy)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(mHealthButton);

        pHealthButton = new TextButton("+", MainScreen.regularSkin);
        pHealthButton.setPosition(mHealthButton.getRight() + 3.0f, mHealthButton.getTop() - mHealthButton.getHeight());
        pHealthButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if ((attack + health + accurancy) < sum)
                    health++;
                healthLabel.setText("Health: ".concat(health.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health - accurancy)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(pHealthButton);

        accurancyLabel = new Label("Accuracy: ".concat(accurancy.toString()), MainScreen.regularSkin);
        accurancyLabel.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.35f);//pHealthButton.getTop() - 2*pHealthButton.getHeight());
        addActor(accurancyLabel);

        mAccurancyButton = new TextButton("-", MainScreen.regularSkin);
        mAccurancyButton.setPosition(accurancyLabel.getRight() + 3.0f, accurancyLabel.getTop() - accurancyLabel.getHeight());
        mAccurancyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (accurancy > 0)
                    accurancy--;
                accurancyLabel.setText("Accuracy: ".concat(accurancy.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health - accurancy)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(mAccurancyButton);

        pAccurancyButton = new TextButton("+", MainScreen.regularSkin);
        pAccurancyButton.setPosition(mAccurancyButton.getRight() + 3.0f, mAccurancyButton.getTop() - mAccurancyButton.getHeight());
        pAccurancyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if ((attack + health + accurancy) < sum)
                    accurancy++;
                accurancyLabel.setText("Accuracy: ".concat(accurancy.toString()));
                freeLabel.setText("Free: ".concat(((Integer)(sum - attack - health - accurancy)).toString()));
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(pAccurancyButton);

        //TODO: team, type

        teamLabel = new Label("Team:", MainScreen.regularSkin);
        teamLabel.setPosition(pAccurancyButton.getRight() + 10.0f, pAccurancyButton.getTop() - pAccurancyButton.getHeight());//Gdx.graphics.getHeight() * 0.45f);
        addActor(teamLabel);

        teamBox = new SelectBox(Team.values(), MainScreen.regularSkin);
        teamBox.setPosition(teamLabel.getRight() + 7.0f, teamLabel.getTop() - teamLabel.getHeight());
        addActor(teamBox);

        btnOk = new TextButton("Ok", MainScreen.regularSkin);
        btnOk.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.1f);
        btnOk.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO: send login password and player info
                Integer team = teamBox.getSelectionIndex();
                System.out.print(health.toString() + " " + attack.toString() + " " + team.toString());
                stage.gameScreen.setScreen(stage.gameScreen.gameScreen);
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnOk);
    }
}