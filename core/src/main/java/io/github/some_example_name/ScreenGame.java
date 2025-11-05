package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {

    boolean isGameOver;
    MyGdxGame myGdxGame;
    Bird bird;
    Tube[] tubes;
    int tubeCount = 3;


    ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        initTubes();
        bird = new Bird(0,0, 7, 250, 200);
    }



    @Override
    public void show() {
        isGameOver = false;
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        for(Tube tube : tubes){
            tube.move();
            if(tube.isHit(bird)){
            System.out.println("Попадание \n" + "60" + "\uD83E\uDD81" + "6" + "\uD83D\uDCA1");
            isGameOver = true;
            }
        }

        bird.fly();
        if(!bird.isInField()){
            System.out.println("ВЕРНИТЕСЬ НА ПОЛЕ БОЯ");
            isGameOver = true;
        }

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
    public void initTubes(){
        tubes = new Tube[tubeCount];
        for(int i = 0; i < tubeCount; i++){
            tubes[i] = new Tube(tubeCount, i);
        }
    }
}
