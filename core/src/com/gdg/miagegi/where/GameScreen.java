package com.gdg.miagegi.where;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {

	Where game;
	OrthographicCamera cam;
	BitmapFont font;
	BitmapFont font2;

	final int NUMBERCIRCLES = 4;
	Rectangle rresume;
	Rectangle rmenu;
	Rectangle rpause;

	Circle[] circlesTab;
	int speed;
	int difference;
	int numbermoves;
	int numbermaxmoves;
	int mainGameState;
	int gameState;
	int indextarget;
	int indexc1;
	int indexc2;
	float xc1;
	float xc2;
	String textcounter;
	boolean mainGamePause;
	long time;
	Vector3 v;



	public GameScreen(Where game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 480, 800);
		font = new BitmapFont(Gdx.files.internal("font/font2.fnt"),Gdx.files.internal("img/font2.png"),false);
		font2 = new BitmapFont(Gdx.files.internal("font/font4.fnt"),Gdx.files.internal("img/font4.png"),false);

		rresume = new Rectangle(41, 350, 398, 90);
		rmenu = new Rectangle(123.5f, 200, 233, 90);
		rpause = new Rectangle(430, 750, 70, 70);

		circlesTab = new Circle[]{new Circle(30,350), new Circle(140,350), new Circle(250,350), new Circle(360,350)};
		mainGameState = 0;
		gameState = 0;
		Score.score = 0;
		speed = 500;
		difference = 50;
		numbermoves = 1;
		numbermaxmoves = 8;
		textcounter = "";
		mainGamePause = false;

	}
	@Override
	public void render(float delta) {
		switch(mainGameState){
		case 0:
			drawGame();
			updateGame();
			break;
		case 1:
			drawPause();
			updatePause();
			break;
		}

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
		mainGameState = 1;
		mainGamePause = true;
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		font.dispose();
	}

	public void drawGame(){
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		if(gameState == 2){
			game.batch.draw(Assets.find, 300, 520);
			font.draw(game.batch, "Trouve " , 150 , 560);
			font.draw(game.batch, "Touche l'ecran pour" , 50 , 250);
			font.draw(game.batch, "continuer" , 150 , 200);
		}

		if(gameState == 7){
			font2.draw(game.batch, "W.H.E.R.E.?" , 80 , 590);
			font.draw(game.batch, "Touche un cercle" , 80 , 250);
		}

		font2.draw(game.batch, textcounter, 220, 120);
		game.batch.draw(Assets.pausebutton, rpause.x, rpause.y);
		game.batch.draw(Assets.circleAnimation.getKeyFrame(circlesTab[0].stateTime, false), circlesTab[0].position.x, circlesTab[0].position.y);
		game.batch.draw(Assets.circleAnimation.getKeyFrame(circlesTab[1].stateTime, false), circlesTab[1].position.x, circlesTab[1].position.y);
		game.batch.draw(Assets.circleAnimation.getKeyFrame(circlesTab[2].stateTime, false), circlesTab[2].position.x, circlesTab[2].position.y);
		game.batch.draw(Assets.circleAnimation.getKeyFrame(circlesTab[3].stateTime, false), circlesTab[3].position.x, circlesTab[3].position.y);
		font.draw(game.batch, String.valueOf(Score.score) , 5, 795);
		game.batch.end();
	}

	public void updateGame(){

		if(mainGamePause){
			long timetmp = TimeUtils.millis();
			if(timetmp > time + 1000) textcounter = "2";
			if(timetmp > time + 2000) textcounter = "1";
			if(timetmp > time + 3000){
				mainGamePause = false;
				textcounter = "";
			}
		}

		if(gameState == 0 && !mainGamePause){
			indextarget = (int) Math.floor(Math.random()*NUMBERCIRCLES);
			circlesTab[indextarget].target = true;
			gameState = 1;
		}

		if(gameState == 1 && !mainGamePause){
			if(Animation.r(circlesTab[indextarget])){
				gameState = 2;
			}
		}

		if(gameState == 2 && !mainGamePause){
			if(Gdx.input.isTouched()){
				v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(v);
				if(!rpause.contains(v.x, v.y)){
					gameState = 3;
				}
			}
		}

		if(gameState == 3 && !mainGamePause){
			if(Animation.l(circlesTab[indextarget])){
				gameState = 4;
			}
		}

		if(gameState == 4 && !mainGamePause){
			indexc1 = (int) Math.floor(Math.random()*NUMBERCIRCLES);
			indexc2 = (int) Math.floor(Math.random()*NUMBERCIRCLES);
			if(indexc1 != indexc2) {
				gameState = 5;
				xc1 = circlesTab[indexc1].position.x;
				xc2 = circlesTab[indexc2].position.x;
			}
		}

		if(gameState == 5 && !mainGamePause){
			if(xc1 < xc2){
				if(Math.abs(xc1 - xc2) == 110){
					if(Moves.d(circlesTab[indexc1],circlesTab[indexc2],speed,difference)){
						gameState = 6;
					}
				}
				else if(Math.abs(xc1 - xc2) == 220){
					if(Moves.d(circlesTab[indexc1],circlesTab[indexc2],speed*2,difference*2)){
						gameState = 6;
					}
				}
				else{
					if(Moves.d(circlesTab[indexc1],circlesTab[indexc2],speed*3,difference*2)){
						gameState = 6;
					}
				}

			}
			else{
				if(Math.abs(xc1 - xc2) == 110){
					if(Moves.d(circlesTab[indexc2],circlesTab[indexc1],speed,difference)){
						gameState = 6;
					}
				}
				else if(Math.abs(xc1 - xc2) == 220){
					if(Moves.d(circlesTab[indexc2],circlesTab[indexc1],speed*2,difference*2)){
						gameState = 6;
					}
				}
				else{
					if(Moves.d(circlesTab[indexc2],circlesTab[indexc1],speed*3,difference*2)){
						gameState = 6;
					}
				}
			}
		}

		if(gameState == 6 && !mainGamePause){
			if(numbermoves < numbermaxmoves){
				numbermoves++;
				gameState = 4;
			}
			else{
				gameState = 7;
			}
		}

		if(gameState == 7 && !mainGamePause){
			if(Gdx.input.isTouched()){
				v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
				cam.unproject(v);
				for(Circle c : circlesTab){
					if(c.bounds.contains(v.x, v.y)){
						if(c.target){
							Score.score++;
							numbermoves = 1;
							if(speed < 700){
								speed += 10;
							}
							else if(speed >= 700 && speed < 900){
								speed += 10;
								numbermaxmoves = 12;
							}
							else if (speed >= 900 && speed < 1000){
								speed += 5;
								numbermaxmoves = 15;
							}
							else{
								speed += 5;
								numbermaxmoves = 20;
							}

							gameState = 8; 
							break;
						}
						else{
							gameState = 9;
							time = TimeUtils.millis();
							break;
						}
					}
				}
			}
		}





		if(gameState == 8 && !mainGamePause){
			if(Animation.rl(circlesTab[indextarget])){
				gameState = 4;
				Animation.stp = false;
			}
		}

		if(gameState == 9 && !mainGamePause){
			if(Animation.r(circlesTab[indextarget])){
				long timetmp = TimeUtils.millis();
				if(timetmp > time + 1000){
					game.setScreen(new GameOverScreen(game));
				}
			}
		}

		if(Gdx.input.isTouched()){
			v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(v);

			if(rpause.contains(v.x, v.y)){
				if(gameState > 3){
					mainGameState = 1;
					mainGamePause = true;
				}
			}

		}
	}

	public void drawPause(){
		GL20 gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		game.batch.draw(Assets.pause, 0, 550);
		game.batch.draw(Assets.resume, rresume.x, rresume.y);
		game.batch.draw(Assets.menu, rmenu.x, rmenu.y);
		font.draw(game.batch, String.valueOf("SCORE : " + Score.score), 130, 570);

		game.batch.end();
	}

	public void updatePause(){
		if(Gdx.input.isTouched()){
			Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			cam.unproject(v);

			if(rmenu.contains(v.x, v.y)){
				game.setScreen(new MenuScreen(game));
				dispose();
			}

			if(rresume.contains(v.x, v.y)) {
				mainGameState = 0;
				time = TimeUtils.millis();
				textcounter = "3";

			}
		}
	}
}
