package com.flappydoggy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappydoggy.game.States.GameStateManager;
import com.flappydoggy.game.States.MenuState;

public class FlappyDoggy extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGTH = 800;

	public static final String TITLE = "Flappy Doggy";

	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		//Background music
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		//Background music loop
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		//Will create a new menu screen and place it on top of the stack.
		gsm.push(new MenuState(gsm));


	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		//Updates the top item on the stack (Screen that is visible to the user). Gdx.graphics.getDeltaTime () returns the elapsed time between the last and the current frame in seconds
		gsm.update(Gdx.graphics.getDeltaTime());
		//Draws the top screen on the stack
		gsm.render(batch);
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();

	}
}
