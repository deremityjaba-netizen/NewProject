package io.github.some_example_name;

import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;
import static io.github.some_example_name.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {
    boolean isPointReceived = false;
    int x, gapY;
    final int width = 200;
    final int height = 700;
    int padding = 100;
    int gapHeight = 400;
    int distanceBetweenTubes;
    int speed = 5;
    Texture textureUpperTube;
    Texture textureDownTube;
    Random r = new Random();


    public Tube(int tubeCount, int tubeIdx) {
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);

        gapY = gapHeight / 2 + padding + r.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
    }

    public boolean isHit(Bird bird){
        if(bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;
        if(bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <=x)
            return true;

        return false;
    }

    public boolean needAddPoint(Bird bird){

        return bird.x > x + width && !isPointReceived;
    }
    public void setPointReceived(){
        isPointReceived = true;
    }

    void draw(Batch batch){
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }

    void move(){
        x -= speed;
        if (x < -width){

            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + r.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }

    }

    void dispose(){
        textureUpperTube.dispose();
        textureDownTube.dispose();
    }

}
