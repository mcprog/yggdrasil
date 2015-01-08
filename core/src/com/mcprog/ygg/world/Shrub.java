package com.mcprog.ygg.world;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ygg.world.entity.EntityAnimated;

public class Shrub extends Entity {

	public Shrub(World world, Vector2 position) {
		super(world, position);
		bodyDef.type = BodyType.StaticBody;
		CircleShape shape = new CircleShape();
		shape.setRadius(.7f);
		initBody();
		body.createFixture(shape, 1);
		shape.dispose();
		
	}

}
