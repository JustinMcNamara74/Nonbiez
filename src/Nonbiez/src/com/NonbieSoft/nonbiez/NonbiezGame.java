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
	
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		em = new EntityManager();
		
		physicsSystem = new PhysicsSystem(em);
	}
	
	public void spawnPlayer() {
		Entity player = em.createEntity("Player1", "player");
		
		// Give this component position, scale, and rotation properties
		player.addComponent(new TransformComponent());
		
		// Give this component a sprite to render itself with
		player.addComponent(new SpriteComponent("data/evolutio.png", batch));
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {		
		
		physicsSystem.update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
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
