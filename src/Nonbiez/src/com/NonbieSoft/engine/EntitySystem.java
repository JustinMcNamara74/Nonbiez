package com.NonbieSoft.engine;

abstract public class EntitySystem {
	protected EntityManager _em;
	
	public EntitySystem(EntityManager em) {
		_em = em;
	}
	
	abstract public void update(float dt);
}
