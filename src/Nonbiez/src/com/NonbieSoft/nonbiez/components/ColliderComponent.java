package com.NonbieSoft.nonbiez.components;

import com.NonbieSoft.engine.IComponent;
import com.badlogic.gdx.math.Rectangle;

public class ColliderComponent implements IComponent {
	public Rectangle bounds;
	
	// hold rectal data
	public ColliderComponent(float offsetX, float offsetY, float width, float height){
		bounds = new Rectangle(offsetX, offsetY, width, height);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
