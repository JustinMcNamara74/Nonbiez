package com.NonbieSoft.nonbiez.systems;

import java.util.Set;

import com.NonbieSoft.engine.*;
import com.NonbieSoft.nonbiez.components.*;


public class PhysicsSystem extends EntitySystem {

	private Set<Entity> entSet;
	
	public PhysicsSystem(EntityManager em) {
		super(em);
	}

	@Override
	public void update(float dt) {
		entSet = _em.getEntitiesByComponentType(PhysicsComponent.class);
		
		for(Entity ent : entSet) {
			
		}
	}

}
