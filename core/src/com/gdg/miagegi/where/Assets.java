package com.gdg.miagegi.where;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	static final int FRAME_COLS = 5;
	static final int FRAME_ROWS = 1;
	public static Texture circleSheets;
	public static Texture play;
	public static Texture retry;
	public static Texture resume;
	public static Texture menu;
	public static Texture title;
	public static Texture pause;
	public static Texture pausebutton;
	public static Animation circleAnimation;
	public static TextureRegion[] circleFrames;
	public static TextureRegion currentCircleFrame;
	public static Texture find;
	public static Texture replay;
	public static Texture gameover;
	public static Texture libgdxlogo;
	
	
	
	public static void load(){
		
		title = new Texture("img/title.png");
		play = new Texture("img/play.png");
		pause = new Texture("img/pause.png");
		resume = new Texture("img/resume.png");
		menu = new Texture("img/menu.png");
		pausebutton = new Texture("img/pausebutton.png");
		circleSheets = new Texture("img/pics.png");
		TextureRegion[][] tmp = TextureRegion.split(circleSheets, circleSheets.getWidth()/FRAME_COLS, circleSheets.getHeight()/FRAME_ROWS);              
        circleFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                circleFrames[index++] = tmp[i][j];
            }
        }
        circleAnimation = new Animation(0.2f, circleFrames);   
        find = new Texture("img/find.png");
        replay = new Texture("img/replay.png");
        gameover = new Texture("img/gameover.png");
        libgdxlogo = new Texture("img/libgdx.png");
		
	}
	
	
		
}
