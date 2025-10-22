package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    int birdX = 0;
    int birdY = 0;
    int birdSpeed = 5;
    private SpriteBatch batch;

    Texture birdTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        birdTexture = new Texture("bird0.png");

    }

    @Override
    public void render() {
        birdX += birdSpeed;
        birdY += birdSpeed;

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(birdTexture, birdX, birdY);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        birdTexture.dispose();
    }
}
