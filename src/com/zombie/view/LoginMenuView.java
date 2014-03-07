package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.zombie.Client;
import com.zombie.screens.MainScreen;
import org.json.simple.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 19.12.13
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public class LoginMenuView extends Table {
    private Label title;
    private TextField login;
    private TextField password;
    private TextButton btnLogin;
    private TextButton btnReg;
    private MainMenuStage stage;
    private Label logLabel;
    private Label passLabel;
    private String log = "";
    private String pass = "";

    public LoginMenuView(MainMenuStage g) {
        stage = g;

        title = new Label("Log In", MainScreen.regularSkin);
        title.setPosition(Gdx.graphics.getWidth() / 2  - title.getWidth()/2, Gdx.graphics.getHeight() - title.getHeight()*2);
        addActor(title);

        logLabel = new Label("Login: ", MainScreen.regularSkin);
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
        addActor(password);

        btnLogin = new TextButton("Login", MainScreen.regularSkin);
        btnLogin.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.2f);
        btnLogin.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                log = login.getText();
                pass = password.getText();
                System.out.println(log + " " + pass);  //////////////////////////////////////////////////////////LOGIN
                JSONObject res = Convert(log.toString(), pass.toString());
                Client.run(res);
                stage.gameScreen.setScreen(stage.gameScreen.gameScreen);
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnLogin);

        btnReg = new TextButton("Registration", MainScreen.regularSkin);
        btnReg.setPosition(3 * Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.2f);
        btnReg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                log = login.getText();
                pass = password.getText();
                System.out.println(log + " " + pass);  //////////////////////////////////////////////////////////REGISTRATION
                stage.gameScreen.setScreen(stage.gameScreen.gameScreen);
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnReg);
    }

    private JSONObject Convert(String one, String two) {
        JSONObject resultJson = new JSONObject();
        JSONObject obj = new JSONObject();
        resultJson.put("id",1);
        obj.put("username", one);
        obj.put("password", two);
        resultJson.put("parameters", obj);
        return resultJson;
    }


}
