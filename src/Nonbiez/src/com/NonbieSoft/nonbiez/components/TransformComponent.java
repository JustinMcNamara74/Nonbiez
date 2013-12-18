package com.NonbieSoft.nonbiez.components;

import com.NonbieSoft.engine.IComponent;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements IComponent {
	public Vector2 position;
	public Vector2 scale;
	public float angle;
	
	public TransformComponent() {
		position = new Vector2(0f, 0f);
		scale = new Vector2(1f, 1f);
		angle = 0f;
	}
	
	public void move(float x, float y) {
		position.add(x, y);
	}
}
