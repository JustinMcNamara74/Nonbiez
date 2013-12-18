package com.NonbieSoft.nonbiez.components;

import com.NonbieSoft.engine.IComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


/*
 * The SpriteComponent just stores a texture as a sprite
 * for drawing.
 */
public class SpriteComponent implements IComponent {
	public Sprite sprite;
	public Batch batch;
	public Texture texture;
	
	public SpriteComponent(String fileName, Batch batch) {
		this.batch = batch;
		
		texture = new Texture(Gdx.files.internal(fileName));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setSize(64f, 64f);
		sprite.setPosition(50f, 50f);
	}
	
	// Added render method to make things easier
	public void render() {
		sprite.draw(batch);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}
}
