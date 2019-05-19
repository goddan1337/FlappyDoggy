package com.flappydoggy.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.flappydoggy.game.FlappyDoggy;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyDoggy.WIDTH;
		config.height = FlappyDoggy.HEIGTH;
		config.title = FlappyDoggy.TITLE;
		config.addIcon("dog.png", FileType.Internal);
		new LwjglApplication(new FlappyDoggy(), config);
	}
}