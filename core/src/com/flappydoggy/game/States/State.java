package com.flappydoggy.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    // Camera - a window to the game world
    protected OrthographicCamera camera;
    // For an orthographic camera, but Z is 0
    protected Vector3 mouse;
    // Manage windows or states
    protected GameStateManager gsm;

    //Constructor
    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    // Check for tap or click
    protected abstract void handleInput();

    //Updating pictures at regular intervals
    public abstract void update(float dt);
    //Will draw a screen
    public abstract void render(SpriteBatch sb);
    //Destruction of resources
    public abstract void dispose();

}
