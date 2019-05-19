package com.flappydoggy.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.sound.midi.Soundbank;

public class Dog {

    private static final int MOVEMENT = 70;
    private static final int GRAVITY = -15;
    private Vector3 postition;
    private Vector3 velosity;
    private Rectangle bounds;
    public Animation dogAnimation;
    private Sound flap;
    private Texture texture;

    public Dog(int x, int y){
        postition = new Vector3(x, y, 0);
        velosity = new Vector3(0,0,0);
        texture = new Texture("doganimation.png");
        dogAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x,y,texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPostition() {
        return postition;
    }

    public TextureRegion getDog() {
        return dogAnimation.getFrame();
    }

    public void update(float dt){
        dogAnimation.update(dt);
        if(postition.y > 0)
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        postition.add(MOVEMENT * dt, velosity.y, 0);

        if(postition.y < 0)
            postition.y = 0;

        velosity.scl(1/dt);

        bounds.setPosition(postition.x, postition.y);
    }

    public void jump(){
        velosity.y = 250;
        flap.play();

    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
