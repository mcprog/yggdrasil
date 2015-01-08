package com.mcprog.ygg.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Entity {
	
	protected Vector2 position;
	protected World world;
	
	protected Body body;
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	
	public Entity(World world, Vector2 position) {
		this.world = world;
		this.position = position;
		
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(position);
	}
	
	protected void initBody () {
		body = world.createBody(bodyDef);
	}
	
	public Body getBody () {
		return body;
	}
	
	public Vector2 getVelocity () {
		return body.getLinearVelocity();
	}
	
	public Vector2 getPosition () {
		return body.getPosition();
	}

}
