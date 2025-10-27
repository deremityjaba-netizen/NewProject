package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyGdxGame extends Game {

    SpriteBatch batch;
    public static final int SCR_WIDTH = 1280;
    public static final int SCR_HEIGHT = 720;
    public OrthographicCamera camera;
    ScreenGame screenGame;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        batch = new SpriteBatch();

        screenGame = new ScreenGame(this);
        setScreen(screenGame);
    }



    @Override
    public void dispose() {
        batch.dispose();

    }
}
