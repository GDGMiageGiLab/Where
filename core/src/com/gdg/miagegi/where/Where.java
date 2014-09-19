package com.gdg.miagegi.where;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Where extends Game {
	SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		Assets.load();
		setScreen(new MenuScreen(this));
	}
		
}
