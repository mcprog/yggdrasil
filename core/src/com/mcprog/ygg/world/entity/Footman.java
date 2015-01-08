package com.mcprog.ygg.world.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ygg.lib.Assets;

public class Footman extends EntityAnimated {

	public Footman(World world, Vector2 position) {
		super(world, position);
		entityAnimations = Assets.loadAnimations("spritesheets/footman.png");
		
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.6f, 1f);
		body.setFixedRotation(true);
		body.createFixture(shape, 1);
		
		shape.dispose();
		
		body.setUserData("footman");
	}
	
	public void update (Vector2 playerPosistion) {
		if (getPosition().x < playerPosistion.x - .6f) {
			direction = RIGHT;
			body.setLinearVelocity(3, 0);
		}
		if (getPosition().x > playerPosistion.x + .6f) {
			direction = LEFT;
			body.setLinearVelocity(-3, 0);
		}
		if (getPosition().y < playerPosistion.y - 1) {
			direction = UP;
			body.setLinearVelocity(0, 3);
		}
		if (getPosition().y > playerPosistion.y + 1) {
			direction = DOWN;
			body.setLinearVelocity(0, -3);
		}
	}

}
