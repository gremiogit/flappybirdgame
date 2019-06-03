package dev.jason.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Texture flySheet;

    private Vector3 position;
    private Vector3 velocity;

    private Rectangle bounds;

    private Animation birdAnimation;
    private Sound flap;

    public Bird(int x, int y){
        flySheet = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(flySheet), 3, 0.5f);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bounds = new Rectangle(x, y, flySheet.getWidth() / 3, flySheet.getHeight() / 3);
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if(position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);


    }

    public void jump(){
        velocity.y = 250;
        flap.play(.5f);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public TextureRegion getTexture(){
        return birdAnimation.getFrame();
    }

    public Vector3 getPosition(){
        return position;
    }

    public void dispose(){
        flySheet.dispose();
        flap.dispose();
    }
}
