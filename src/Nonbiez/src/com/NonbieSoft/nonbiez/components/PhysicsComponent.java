package com.NonbieSoft.nonbiez.components;

import com.NonbieSoft.engine.*;
import com.badlogic.gdx.math.Vector2;

/*
 * The PhysicsComponent enables velocity and acceleration for
 * an Entity.
 */
public class PhysicsComponent implements IComponent {
	public Vector2 velocity;
	public Vector2 accel;
	
	public PhysicsComponent() {
		velocity = new Vector2();
		accel = new Vector2();
	}
	
	public void step(float dt) {
		velocity.add(accel.x*dt, accel.y*dt);
	}

	@Override
	public void dispose() {

	}
}
