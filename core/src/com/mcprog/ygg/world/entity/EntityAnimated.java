package com.mcprog.ygg.world.entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ygg.world.Entity;

public class EntityAnimated extends Entity {

	protected Animation[] entityAnimations;
	protected Sprite sprite;
	protected int direction;
	
	public static final int LEFT = 			0;
	public static final int RIGHT = 		1;
	public static final int UP = 			2;
	public static final int DOWN = 			3;
	public static final int LEFT_IDLE = 	4;
	public static final int RIGHT_IDLE = 	5;
	public static final int UP_IDLE = 		6;
	public static final int DOWN_IDLE = 	7;
	
	public EntityAnimated(World world, Vector2 position) {
		super(world, position);
		
		bodyDef.type = BodyType.DynamicBody;
		initBody();
	}
	
	public void draw (SpriteBatch batch, float stateTime) {
		sprite = new Sprite(entityAnimations[getDirection()].getKeyFrame(stateTime, true));
		sprite.setCenter(getPosition().x, getPosition().y);
		sprite.setScale(.125f);
		sprite.draw(batch);
	}
	
	public int getDirection () {
		return direction;
	}
	
	
}
