package com.NonbieSoft.nonbiez.systems;

import java.util.Set;

import com.NonbieSoft.engine.*;
import com.NonbieSoft.nonbiez.components.*;

// grab all entities that have pathfinder components
// move entity toward target position at given speed
// 

public class PathFindingSystem extends EntitySystem {
	private Set<Entity> entSet;

	public PathFindingSystem(EntityManager em) {
		super(em);
		
	}

	@Override
	public void update(float dt) {
		entSet = _em.getEntitiesByComponentType(PathFinderComponent.class);

		if (entSet != null) {
			for (Entity ent : entSet) {
				PathFinderComponent pf = ent
						.getComponent(PathFinderComponent.class);
				TransformComponent t = ent
						.getComponent(TransformComponent.class);

				if (pf.targetPosition != null) {

					float d = t.position.dst(pf.targetPosition);

					// pf moves to ward target position
					if (d > pf.walkSpeed * dt) {
						float alpha = (pf.walkSpeed * dt) / d;
						t.position.lerp(pf.targetPosition, alpha);
					}

					else {
						t.position.set(pf.targetPosition);

						pf.targetPosition = null;
					}
					
				}
				
			}
		}

	}

}
