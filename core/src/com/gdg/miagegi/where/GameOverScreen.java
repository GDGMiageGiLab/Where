package com.gdg.miagegi.where;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameOverScreen implements Screen {

	Where game;
	BitmapFont font;
	OrthographicCamera cam;

	Rectangle rreplay;
	Rectangle rmenu;

	public GameOverScreen(Where game){
		this.game = game;
		font = new BitmapFont(Gdx.files.internal("font/font2.fnt"),Gdx.files.internal("img/font2.png"),false);
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 480, 800);
		rreplay = new Rectangle(71, 350, 398, 90);
		rmenu = new Rectangle(123.5f, 200, 233, 90);
	}
	@Override
	public void render(float delta) {
		draw();
		update();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
		font.dispose();
	}

	public void draw(){
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		game.batch.draw(Assets.gameover, 0, 600);
		if(Score.score > Score.highscore){
			Score.save(Score.score);
			font.draw(game.batch, String.valueOf("Meilleur score !!! : " + Score.score), 60, 570);
		}
		else
			font.draw(game.batch, String.valueOf("Score : " + Score.score), 130, 570);
		game.batch.draw(Assets.replay, rreplay.x, rreplay.y);
		game.batch.draw(Assets.menu, rmenu.x, rmenu.y);
		game.batch.end();
	}

	public void update(){
		if(Gdx.input.isTouched()){
			Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(v);

			if(rmenu.contains(v.x, v.y)){
				game.setScreen(new MenuScreen(game));
				dispose();
			}

			if(rreplay.contains(v.x, v.y)) {
				game.setScreen(new GameScreen(game));
				dispose();

			}
		}
	}

}
