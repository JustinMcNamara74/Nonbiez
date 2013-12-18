package com.NonbieSoft.nonbiez;

import com.NonbieSoft.engine.IComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent implements IComponent {
	public Sprite sprite;
	public Batch batch;
	public Texture texture;
	
	public SpriteComponent(String fileName, Batch batch) {
		texture = new Texture(Gdx.files.local(fileName));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
	}
}
