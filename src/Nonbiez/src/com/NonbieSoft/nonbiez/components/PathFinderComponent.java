package com.NonbieSoft.nonbiez.components;

import com.NonbieSoft.engine.IComponent;
import com.badlogic.gdx.math.Vector2;

public class PathFinderComponent implements IComponent{
	public Vector2 targetPosition = null;
	public float walkSpeed = 0;
	
	public PathFinderComponent(float walkSpeed){
		this.walkSpeed = walkSpeed;
		
	}
	

	@Override
	public void dispose() {
		
		
	}

}
