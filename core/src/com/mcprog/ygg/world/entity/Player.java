package com.mcprog.ygg.world.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ygg.lib.Assets;

public class Player extends EntityAnimated {

	
	
	
	
	
	public static final int LEFT_BLOCK = 	8;
	public static final int RIGHT_BLOCK = 	9;
	public static final int UP_BLOCK = 		10;
	public static final int DOWN_BLOCK = 	11;
	
	public Player(World world, Vector2 position) {
		super(world, position);
		entityAnimations = Assets.loadAnimations("player.png");
		
		
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.6f, 1f);
		body.setFixedRotation(true);
		body.createFixture(shape, 1);
		
		shape.dispose();
		
		body.setUserData("player");
	}
	
	public void update () {
		handleInput();
	}
	
	public void handleInput () {
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			if (Gdx.input.isKeyPressed(Keys.A)) {
				direction = LEFT_BLOCK;
			}
			else if (Gdx.input.isKeyPressed(Keys.D)) {
				direction = RIGHT_BLOCK;
			}
			else if (Gdx.input.isKeyPressed(Keys.W)) {
				direction = UP_BLOCK;
			}
			else if (Gdx.input.isKeyPressed(Keys.S)) {
				direction = DOWN_BLOCK;
			}
			body.setLinearVelocity(Vector2.Zero);
		}
		else if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			if (Gdx.input.isKeyPressed(Keys.A)) {
				body.setLinearVelocity(-5, 0);
				direction = LEFT;
			}
			if (Gdx.input.isKeyPressed(Keys.D)) {
				body.setLinearVelocity(5, 0);
				direction = RIGHT;
			}
			if (Gdx.input.isKeyPressed(Keys.W)) {
				body.setLinearVelocity(0, 5);
				direction = UP;
			}
			if (Gdx.input.isKeyPressed(Keys.S)) {
				body.setLinearVelocity(0, -5);
				direction = DOWN;
			}
		}
		if (!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.D)) {
			body.setLinearVelocity(Vector2.Zero);
			if (direction != LEFT_IDLE && direction != RIGHT_IDLE && direction != UP_IDLE && direction != DOWN_IDLE && !Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				switch (direction) {
				case LEFT:
					direction = LEFT_IDLE;
					break;
				case LEFT_BLOCK:
					direction = LEFT_IDLE;
					break;
				case RIGHT:
					direction = RIGHT_IDLE;
					break;
				case RIGHT_BLOCK:
					direction = RIGHT_IDLE;
					break;
				case UP:
					direction = UP_IDLE;
					break;
				case UP_BLOCK:
					direction = UP_IDLE;
					break;
				case DOWN:
					direction = DOWN_IDLE;
					break;
				case DOWN_BLOCK:
					direction = DOWN_IDLE;
					break;
				default:
					break;
				}
			}
			
		}
	}
	
	

}
