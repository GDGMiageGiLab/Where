package com.gdg.miagegi.where;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen implements Screen {

	Where game;
	BitmapFont font;
	BitmapFont font2;
	OrthographicCamera cam;
	
	Rectangle rplay;
	
	public MenuScreen(Where game){
		this.game = game;
		rplay = new Rectangle(106, 300, 268, 90);
		font = new BitmapFont(Gdx.files.internal("font/font2.fnt"),Gdx.files.internal("img/font2.png"),false);
		font2 = new BitmapFont(Gdx.files.internal("font/font3.fnt"),Gdx.files.internal("img/font3.png"),false);
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 480, 800);
		Score.load();
	
	}
	
	@Override
	public void render(float delta) {
		Gdx.input.setCatchBackKey(true);
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
		game.batch.draw(Assets.title, 0, 600);
		game.batch.draw(Assets.play, rplay.x, rplay.y);
		font2.draw(game.batch, "Agnaramon Boris" , 105, 180);
		font2.draw(game.batch, "Powered by " , 100, 130);
		game.batch.draw(Assets.libgdxlogo, 300, 97);
		font2.draw(game.batch, "2014" , 200, 77);
		
		
     	font.draw(game.batch, "Meilleur score : "+ Score.highscore , 80, 560);
		game.batch.end();
	}
	
	public void update(){
		if(Gdx.input.isTouched()){
			Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(v);
			if(rplay.contains(v.x, v.y)){
				game.setScreen(new GameScreen(game));
				dispose();
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.BACK)) Gdx.app.exit();
	}
}
