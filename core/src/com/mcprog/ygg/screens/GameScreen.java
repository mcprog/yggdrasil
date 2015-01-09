package com.mcprog.ygg.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ygg.Yggdrasil;
import com.mcprog.ygg.lib.Assets;
import com.mcprog.ygg.world.Shrub;
import com.mcprog.ygg.world.entity.Footman;
import com.mcprog.ygg.world.entity.Player;

public class GameScreen implements Screen, ContactListener {
	
	private Yggdrasil game;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;
	private World world;
	private Player player;
	private Footman footman;
	private float stateTime;
	private Shrub testEntityShrub;
	
	private Vector2[][] tileLocations;
	
	public GameScreen(Yggdrasil gameInstance) {
		Assets.loadBackgrounds();
		tileLocations = new Vector2[3][3];
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				tileLocations[i][j] = new Vector2(Assets.grassBackground.getWidth() / 2 - i * Assets.grassBackground.getWidth(), Assets.grassBackground.getHeight() / 2 - j * Assets.grassBackground.getHeight());
			}
		}
		game = gameInstance;
		batch = new SpriteBatch();
		world = new World(Vector2.Zero, true);
		player = new Player(world, Vector2.Zero);
		footman = new Footman(world, new Vector2(0, 10));
		camera = new OrthographicCamera();
		
		debugRenderer = new Box2DDebugRenderer();
		
		
		testEntityShrub = new Shrub(world, Vector2.Zero);
		
	}
		

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set(player.getPosition(), 0);
		camera.update();
		
		stateTime += delta;
		batch.setProjectionMatrix(camera.combined);
		player.update();
		footman.update(player.getPosition());
		batch.begin();
		
		for (Vector2[] arr : tileLocations) {
			for (Vector2 v : arr) {
				System.out.println(v);
				batch.draw(Assets.grassBackground, v.x, v.y);
			}
		}
//		batch.draw(Assets.grassBackground, -Assets.grassBackground.getWidth() / 2, -Assets.grassBackground.getWidth() /2 );
//		batch.draw(Assets.grassBackground, -Assets.grassBackground.getWidth() * 1.5f, -Assets.grassBackground.getHeight() * .5f);
		player.draw(batch, stateTime);
		footman.draw(batch, stateTime);
		batch.end();
		
		world.step(1/60f, 8, 3);
		debugRenderer.render(world, camera.combined);
		
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 16;
		camera.viewportHeight = height / 16;
		camera.update();
	}

	@Override
	public void show() {
//		game.camera.translate(256, 256);
		world.setContactListener(this);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}


	@Override
	public void beginContact(Contact contact) {
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		
		if (a.getBody().getUserData() != null && b.getBody().getUserData() != null) {
			if ((a.getBody().getUserData().equals("player") && b.getBody().getUserData().equals("footman")) || (a.getBody().getUserData().equals("footman") && b.getBody().getUserData().equals("player"))) {
				game.setScreen(game.deathScreen);
			}
		}
		
	}


	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
