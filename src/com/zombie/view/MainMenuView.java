package com.zombie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zombie.ZombieGame;
import com.zombie.screens.MainScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

/**
 * Created with IntelliJ IDEA.
 * User: 123
 * Date: 19.12.13
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuView extends Table {

    private Label title;
    private TextField login;
    private TextField password;
    private TextButton btnLogin;
    private TextButton btnExit;
    private MainMenuStage stage;
    private Image background;
    Texture texture = new Texture(Gdx.files.internal("assets/pictures/zombie.png"));


    public MainMenuView(MainMenuStage g) {
        stage = g;

        btnLogin = new TextButton("Login", MainScreen.regularSkin);
        btnLogin.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.6f);
        btnLogin.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.getLoginView();
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnLogin);

        btnExit = new TextButton("Exit", MainScreen.regularSkin);
        btnExit.setPosition(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() * 0.4f);
        btnExit.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        }) ;
        addActor(btnExit);
        /*BitmapFont buttonFont = new BitmapFont();
        buttonFont.setColor(Color.BLACK);

        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
        style.checked = new TextureRegionDrawable(downRegion);
        style.up = new TextureRegionDrawable(upRegion);
        style.down = new TextureRegionDrawable(downRegion);
        style.font = buttonFont;    */


        /*btnLogin = new ImageTextButton("Login", style);
        //login.setBounds(this.getWidth()/3, this.getHeight()/3, 30, 30);
        //login.setPosition(this.getWidth() / 3, this.getHeight() / 3);
        btnLogin.setX(this.getWidth() / 3);
        btnLogin.setY(this.getHeight() / 3);
        btnLogin.setSize(10, 10);
        //System.out.print(login.getX() + " " + login.getY());
        btnLogin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (btnLogin.isChecked())
                    stage.gameScreen.setScreen(stage.gameScreen.gameScreen);
                //System.out.print("here we are");
            }
        });

        this.add(btnLogin);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = buttonFont;
        textFieldStyle.fontColor = Color.BLACK;
        login = new TextField("gagaga", textFieldStyle);
        this.add(login);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.font.setColor(Color.GREEN);  */
        title = new Label("Zombie Apocalypse", MainScreen.regularSkin);//labelStyle);
        title.setPosition(Gdx.graphics.getWidth() / 2  - title.getWidth()/2, Gdx.graphics.getHeight() - title.getHeight()*2);
        addActor(title);

        background = new Image(texture);
        float widthStart = Gdx.graphics.getWidth()/2;
        float k = (Gdx.graphics.getWidth())/(2*texture.getWidth());
        float heightStart = Gdx.graphics.getHeight() - texture.getHeight()*k;
        background.setPosition(widthStart, 0);
        background.setBounds(widthStart, 0, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() - heightStart);
        addActor(background);
    }
}
