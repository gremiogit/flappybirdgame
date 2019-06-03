package dev.jason.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 52;

    private Texture topTubeTexture, botTubeTexture;
    private Vector2 posTopTube, posBotTube;
    private Random rand;

    private Rectangle boundsTop, boundsBot;

    public Tube(float x){

        topTubeTexture = new Texture("toptube.png");
        botTubeTexture = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - botTubeTexture.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTubeTexture.getWidth(), topTubeTexture.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, botTubeTexture.getWidth(), botTubeTexture.getHeight());

    }

    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - botTubeTexture.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public Texture getTopTubeTexture() {
        return topTubeTexture;
    }

    public Texture getBotTubeTexture() {
        return botTubeTexture;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void dispose(){
        topTubeTexture.dispose();
        botTubeTexture.dispose();
    }

}
