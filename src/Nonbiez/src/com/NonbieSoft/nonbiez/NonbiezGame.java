package com.NonbieSoft.nonbiez;

import com.NonbieSoft.engine.*;
import com.NonbieSoft.nonbiez.components.*;
import com.NonbieSoft.nonbiez.systems.*;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class NonbiezGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private EntityManager em;
	
	// System declarations go here!
	private PhysicsSystem physicsSystem;
	private RenderSystem renderSystem;
	private PathFindingSystem pathfindingSystem;
	
	private Entity player1;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		
		batch = new SpriteBatch();
		
		em = new EntityManager();
		
		physicsSystem = new PhysicsSystem(em);
		renderSystem = new RenderSystem(em, batch);
		pathfindingSystem = new PathFindingSystem(em);
		
		player1 = spawnPlayer();
		
		
		//testies
		Entity map1 = em.createEntity("quotes", "yep");
		
		map1.addComponent(new TileMapComponent("testmap1.json"));
	}
	
	public Entity spawnPlayer() {
		Entity player = em.createEntity("Player1", "player");
		
		// Give this Entity position, scale, and rotation properties
		player.addComponent(new TransformComponent());
		
		// Give this Entity a sprite to render itself with
		SpriteComponent spr = (SpriteComponent)player.addComponent(new SpriteComponent("data/evolutio.png"));
		spr.sprite.setRotation(-90f);
		
		// Make this Entity physics-enabled!
		PhysicsComponent phys = new PhysicsComponent();
		player.addComponent(phys);
		
		// Add Pathfinding capability
		PathFinderComponent pf = new PathFinderComponent(100);
		player.addComponent(pf);
		pf.targetPosition = new Vector2(50,50);
		
		// Give it some gravity
		// phys.accel.set(0.0f, -98.10f);
		
		// Now that the entity has a PhysicsComponent, the PhysicsSystem
		// will pick it up and start working it.
		
		return player;
	}

	@Override
	public void dispose() {
		batch.dispose();
		em.dispose();
	}

	@Override
	public void render() {		
		
		pathfindingSystem.update(Gdx.graphics.getDeltaTime());
		
		physicsSystem.update(Gdx.graphics.getDeltaTime());
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			Vector3 tempVec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
			
			camera.unproject(tempVec);
			
	        System.out.println(tempVec.x + " " + tempVec.y);
	        
	        PathFinderComponent pf = player1.getComponent(PathFinderComponent.class);
	        pf.targetPosition = new Vector2(tempVec.x, tempVec.y);
	    }
		
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
