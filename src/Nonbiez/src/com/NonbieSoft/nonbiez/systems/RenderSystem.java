package com.NonbieSoft.nonbiez.systems;

import java.util.Set;

import com.NonbieSoft.engine.Entity;
import com.NonbieSoft.engine.EntityManager;
import com.NonbieSoft.engine.EntitySystem;
import com.NonbieSoft.nonbiez.components.SpriteComponent;
import com.NonbieSoft.nonbiez.components.TransformComponent;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem extends EntitySystem {

	private Set<Entity> entSet;
	private SpriteBatch batch;
	
	public RenderSystem(EntityManager em, SpriteBatch batch) {
		super(em);
		this.batch = batch;
	}
	
	@Override
	public void update(float dt) {
		entSet = _em.getEntitiesByComponentType(SpriteComponent.class);
		
		if(entSet != null) {
			for(Entity ent : entSet) {
				SpriteComponent spr = _em.getComponent(ent, SpriteComponent.class);
				TransformComponent transform = _em.getComponent(ent, TransformComponent.class);
				spr.setDrawPosition(transform.position);
				spr.sprite.draw(batch);
			}
		}
	}

}
