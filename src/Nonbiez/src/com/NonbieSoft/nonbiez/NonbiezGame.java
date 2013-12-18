package com.NonbieSoft.nonbiez;

import com.NonbieSoft.engine.*;
import com.NonbieSoft.nonbiez.components.*;
import com.NonbieSoft.nonbiez.systems.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NonbiezGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private EntityManager em;
	
	// System declarations go here!
	private PhysicsSystem physicsSystem;
	private RenderSystem renderSystem;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		em = new EntityManager();
		
		physicsSystem = new PhysicsSystem(em);
		renderSystem = new RenderSystem(em, batch);
		
		spawnPlayer();
	}
	
	public void spawnPlayer() {
		Entity player = em.createEntity("Player1", "player");
		
		// Give this Entity position, scale, and rotation properties
		player.addComponent(new TransformComponent());
		
		// Give this Entity a sprite to render itself with
		SpriteComponent spr = (SpriteComponent)player.addComponent(new SpriteComponent("data/evolutio.png"));
		spr.sprite.setRotation(-90f);
		
		// Make this Entity physics-enabled!
		PhysicsComponent phys = new PhysicsComponent();
		player.addComponent(phys);
		
		// Give it some gravity
		phys.accel.set(0.0f, -98.10f);
		
		// Now that the entity has a PhysicsComponent, the PhysicsSystem
		// will pick it up and start working it.
	}

	@Override
	public void dispose() {
		batch.dispose();
		em.dispose();
	}

	@Override
	public void render() {		
		
		physicsSystem.update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		renderSystem.update(Gdx.graphics.getDeltaTime());
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
