package com.NonbieSoft.engine;

abstract public class EntitySystem {
	
	// Gives access to the EntityManager instance
	protected EntityManager _em;
	
	public EntitySystem(EntityManager em) {
		_em = em;
	}
	
	abstract public void update(float dt);
}
