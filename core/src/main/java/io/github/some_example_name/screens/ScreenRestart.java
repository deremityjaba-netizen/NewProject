package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;
import io.github.some_example_name.components.TextButton;

public class ScreenRestart implements Screen {

    MyGdxGame myGdxGame;
    MovingBackground movingBackground;

    TextButton textButton;

    PointCounter pointCounter;

    int gamePoints;




    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        pointCounter = new PointCounter(750, 530);
        textButton = new TextButton(100, 400, "restart");
        movingBackground = new MovingBackground("background/restart_bg.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(textButton.isHit((int) touch.x, (int) touch.y)){
                myGdxGame.setScreen((myGdxGame.screenGame));
            }

        }



        ScreenUtils.clear(1,0,0,1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        movingBackground.draw(myGdxGame.batch);
        textButton.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);

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
        textButton.dispose();
    }
}
