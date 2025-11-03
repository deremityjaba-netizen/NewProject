package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {

    MyGdxGame myGdxGame;
    Bird bird;
    Tube[] tubes;
    int tubeCount = 3;
    ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        initTubes();
        bird = new Bird(0,0, 7, 250, 200);
    }

    public void initTubes(){
        tubes = new Tube[tubeCount];
        for(int i = 0; i < tubeCount; i++){
            tubes[i] = new Tube(tubeCount, i);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        bird.fly();

        for (Tube tube : tubes) tube.move();

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (int i = 0; i < tubeCount; i++){
            tubes[i].dispose();
        }
        bird.dispose();
    }
}
