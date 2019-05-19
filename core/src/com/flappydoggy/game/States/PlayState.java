package com.flappydoggy.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappydoggy.game.FlappyDoggy;
import com.flappydoggy.game.sprites.Dog;
import com.flappydoggy.game.sprites.Tube;

public class PlayState extends State {

    //The distance between the emerging pipe width
    private static final int TUBE_SPACING = 125;
    //Will be in the game 4 sets of pipes
    private static final int TUBE_COUNT = 4;
    //Y offset
    private static final int GROUND_Y_OFFSET = -30;

    private Dog dog;
    private Texture bg;
    private Vector2 bgPos1, bgPos2;

    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        dog = new Dog(50, 300);
        //Center the camera for the dog
        camera.setToOrtho(false, FlappyDoggy.WIDTH / 2, FlappyDoggy.HEIGTH / 2);
        bg = new Texture("bg.png");
        bgPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, 0);
        bgPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + bg.getWidth(), 0);

        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);



        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i* (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
             dog.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateBg();
        updateGround();
        dog.update(dt);
        //Binding the camera to the dog's position
        camera.position.x = dog.getPostition().x + 80;

        //Pipe movement (But the camera will move, and pipes will be created and disappear)
        for(int i = 0; i< tubes.size; i++){
            Tube tube = tubes.get(i);
            if(camera.position.x - (camera.viewportWidth / 2 ) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            //Dog collision with pipe
            if(tube.collides(dog.getBounds()))
                gsm.set(new GameOver(gsm));
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Set the projection matrix for the camera
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, bgPos1.x, bgPos1.y);
        sb.draw(bg, bgPos2.x, bgPos2.y);

        sb.draw(dog.getDog(), dog.getPostition().x, dog.getPostition().y);
        for(Tube tube:tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        dog.dispose();
        ground.dispose();
        for(Tube tube : tubes)
            tube.dispose();
        System.out.println("PlayState Disposed");
    }

    private void updateGround(){
        if(camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

    private void updateBg(){
        if(camera.position.x - (camera.viewportWidth / 2) > bgPos1.x + bg.getWidth())
            bgPos1.add(bg.getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth / 2) > bgPos2.x + bg.getWidth())
            bgPos2.add(bg.getWidth() * 2, 0);
    }


}
