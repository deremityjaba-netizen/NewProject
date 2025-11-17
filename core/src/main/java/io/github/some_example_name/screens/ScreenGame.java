package io.github.some_example_name.screens;

import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;
import static io.github.some_example_name.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.characters.Tube;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;

public class ScreenGame implements Screen {

    boolean isGameOver;
    MyGdxGame myGdxGame;
    Bird bird;
    Tube[] tubes;
    PointCounter pointCounter;
    MovingBackground movingBackground;

    final int pointCounterMarginTop = 60;
    final int getPointCounterMarginRight = 400;
    int tubeCount = 3;
    int gamePoints;


    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        initTubes();
        bird = new Bird(20,SCR_HEIGHT / 2, 6, 200, 150);
        pointCounter = new PointCounter(SCR_WIDTH - getPointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
        movingBackground = new MovingBackground("background/game_bg.png");

    }



    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
        bird.setY(SCR_HEIGHT / 2);
        initTubes();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        for(Tube tube : tubes){

            tube.move();

            if(tube.isHit(bird)){
                System.out.println("Попадание");
                isGameOver = true;
            } else if (tube.needAddPoint(bird)) {
                gamePoints += 1;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }

        }
        movingBackground.move();
        bird.fly();
        if(!bird.isInField()){
            System.out.println("ВЕРНИТЕСЬ НА ПОЛЕ");
            isGameOver = true;


        }

        if(isGameOver) {
            myGdxGame.screenRestart.gamePoints = gamePoints;
            myGdxGame.setScreen(myGdxGame.screenRestart);
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        movingBackground.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
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
        for (int i = 0; i < tubeCount; i++){
            tubes[i].dispose();
        }
        bird.dispose();
        movingBackground.dispose();
    }
    public void initTubes(){
        tubes = new Tube[tubeCount];
        for(int i = 0; i < tubeCount; i++){
            tubes[i] = new Tube(tubeCount, i);
        }
    }
}
