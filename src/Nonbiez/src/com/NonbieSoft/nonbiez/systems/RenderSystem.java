package com.NonbieSoft.nonbiez.systems;

import java.util.Set;

import com.NonbieSoft.engine.Entity;
import com.NonbieSoft.engine.EntityManager;
import com.NonbieSoft.engine.EntitySystem;
import com.NonbieSoft.nonbiez.components.SpriteComponent;

public class RenderSystem extends EntitySystem {

	private Set<Entity> entSet;
	
	public RenderSystem(EntityManager em) {
		super(em);
	}

	@Override
	public void update(float dt) {
		entSet = _em.getEntitiesByComponentType(SpriteComponent.class);
		
		if(entSet != null) {
			for(Entity ent : entSet) {
				SpriteComponent spr = _em.getComponent(ent, SpriteComponent.class);
				
				spr.render();
			}
		}
	}

}
