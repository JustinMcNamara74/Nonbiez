package com.NonbieSoft.nonbiez.components;

import com.NonbieSoft.engine.IComponent;
import com.badlogic.gdx.math.Vector2;

/*
 * The TransformComponenet stores position, scale, and
 * rotation information for an entity.
 */
public class TransformComponent implements IComponent {
	public Vector2 position;
	public Vector2 scale;
	public float angle;
	
	public TransformComponent() {
		position = new Vector2(0f, 0f);
		scale = new Vector2(1f, 1f);
		angle = 0f;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
