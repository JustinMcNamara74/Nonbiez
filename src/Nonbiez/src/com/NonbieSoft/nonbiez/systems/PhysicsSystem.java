package com.NonbieSoft.nonbiez.systems;

import java.util.Set;

import com.NonbieSoft.engine.*;
import com.NonbieSoft.nonbiez.components.*;


// All new Systems need to extend EntitySystem and
// call super(em) in their constructors. This is so
// we can have access to the EntityManager inside the
// update and other methods.

public class PhysicsSystem extends EntitySystem {

	private Set<Entity> entSet;
	
	public PhysicsSystem(EntityManager em) {
		super(em);
	}

	@Override
	public void update(float dt) {
		entSet = _em.getEntitiesByComponentType(PhysicsComponent.class);
		
		if(entSet != null) {
			for(Entity ent : entSet) {
				// Grab the physics and transform components.
				// I use getComponent instead of getComponents because I know there will only
				// be one instance of each. Don't have to deal with a LinkedList and all that.
				PhysicsComponent phys = ent.getComponent(PhysicsComponent.class);
				TransformComponent transform = ent.getComponent(TransformComponent.class);
				
				// Do the simple physics!
				phys.step(dt);
				transform.position.add(phys.velocity.x*dt, phys.velocity.y*dt);
			}
		}
	}

}
