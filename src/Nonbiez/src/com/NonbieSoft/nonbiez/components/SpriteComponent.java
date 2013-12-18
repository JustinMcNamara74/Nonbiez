package com.NonbieSoft.nonbiez.components;

import com.NonbieSoft.engine.IComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


/*
 * The SpriteComponent just stores a texture as a sprite
 * for drawing.
 */
public class SpriteComponent implements IComponent {
	public Sprite sprite;
	public Texture texture;

	public SpriteComponent(String fileName) {
		texture = new Texture(Gdx.files.internal(fileName));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
	}
	
	public void setDrawPosition(Vector2 v) {
		sprite.setPosition(v.x - sprite.getWidth()/2, v.y - sprite.getHeight()/2);
	}
	
	@Override
	public void dispose() {
		texture.dispose();
	}
}
