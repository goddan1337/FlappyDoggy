package com.flappydoggy.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    //Frame array
    private Array<TextureRegion> frames;
    //Display duration 1 frame
    private float maxFrameTime;
    //The current frame display time
    private float currentFrameTime;
    //The number of frames of animation
    private int frameCount;
    //A single frame of animation
    private int frame;

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        //Frame width = width of region of textures / number of frames
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i* frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        currentFrameTime +=dt;
        //If the display duration of the current frame is more than the maximum, increase the frame number, and so on in a circle
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }
    }

    //Method to get current frame of animation
    public TextureRegion getFrame(){
        return  frames.get(frame);
    }

}
