package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.zombie.Client;
import com.zombie.model.Team;
import com.zombie.screens.MainScreen;
import org.json.simple.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 12.03.14
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class UserMenuView extends Table {
    private Label title;
    private TextField login; /////////
    private TextField password;/////////
    private TextButton btnStart;
    private TextButton btnNew;
    private SelectBox worldBox;
    private MainMenuStage stage;
    private Label logLabel;//////////
    private Label passLabel;///////////
    //private String log = "";
    //private String pass = "";

    public UserMenuView(MainMenuStage g) {
        //TODO: get world, create character
        stage = g;

        title = new Label("Lalala", MainScreen.regularSkin);
        title.setPosition(Gdx.graphics.getWidth() / 2  - title.getWidth()/2, Gdx.graphics.getHeight() - title.getHeight()*2);
        addActor(title);

        /*logLabel = new Label("Login: ", MainScreen.regularSkin);
        logLabel.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.6f);
        addActor(logLabel);

        login = new TextField("name", MainScreen.regularSkin);
        login.setPosition(4*Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.6f);
        login.setWidth(logLabel.getWidth() * 3);

        addActor(login);

        passLabel = new Label("Password: ", MainScreen.regularSkin);
        passLabel.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.4f);
        addActor(passLabel);

        password = new TextField("password", MainScreen.regularSkin);
        password.setPosition(4*Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.4f);
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        password.setWidth(logLabel.getWidth() * 3);
        addActor(password);*/

        String[] worlds = {"one", "two", "three"};
        //TODO: worlds

        worldBox = new SelectBox(worlds, MainScreen.regularSkin);
        worldBox.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.6f);
        addActor(worldBox);

        btnStart = new TextButton("Start", MainScreen.regularSkin);
        btnStart.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.4f);
        btnStart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                stage.gameScreen.setScreen(stage.gameScreen.gameScreen);
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnStart);

        btnNew = new TextButton("New character", MainScreen.regularSkin);
        btnNew.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.2f);
        btnNew.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.getRegistrationView(null, null);//TODO: log, pass??
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnNew);
    }

}
