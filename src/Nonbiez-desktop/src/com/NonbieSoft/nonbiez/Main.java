package com.NonbieSoft.nonbiez;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Nonbiez";
		cfg.useGL20 = false;
		cfg.width = 640;
		cfg.height = 480;
		
		// random change

		new LwjglApplication(new NonbiezGame(), cfg);
	}
}
