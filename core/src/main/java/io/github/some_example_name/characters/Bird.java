package io.github.some_example_name.characters;

import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    int x, y;
    int width, height;
    int speed;
    int jumpHeight;
    final int maxHeightOfJump = 100;
    boolean jump;
    int frameCounter = 0;

    Texture[] framesArray = new Texture[]{
        new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png"),
    };

    public Bird(int x, int y, int speed, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        frameCounter = 0;
    }

    public boolean isInField(){
        if(y + height <= 0) return false;
        if(y > SCR_HEIGHT) return false;
        return true;
    }

    public void onClick(){
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void fly(){
        if (y >= jumpHeight) {
            jump = false;
        }

        if (jump) {
            y += speed;
        } else {
            y -= speed;
        }
    }

    public void draw(Batch batch){
        int frameMultiplier = 5;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

    public void dispose(){
        for (Texture texture : framesArray){
            texture.dispose();
        }
    }

    public void setY(int y) {
        this.y = y;
    }
}
