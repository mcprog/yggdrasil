package com.mcprog.ygg;

import com.badlogic.gdx.Game;
import com.mcprog.ygg.screens.DeathScreen;
import com.mcprog.ygg.screens.GameScreen;

public class Yggdrasil extends Game {
	
	public GameScreen vanaheim;
	public DeathScreen deathScreen;
	
	
	@Override
	public void create () {
		vanaheim = new GameScreen(this);
		deathScreen = new DeathScreen(this);
		setScreen(vanaheim);
	}
}
