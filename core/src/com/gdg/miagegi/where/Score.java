package com.gdg.miagegi.where;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Score {

	public static int score;
	public static int highscore;
	public final static String file = ".where";

	public static void save (int s) {
		try {
			FileHandle filehandle = Gdx.files.local(file);
			
			filehandle.writeString(String.valueOf(s), false);
			}
		catch (Throwable e) {
		}
	}
	
	public static void load (){
		FileHandle filehandle = Gdx.files.local(file);
		
		if(!filehandle.exists()) {
			save(0);
		}
		
		try {
			
			String[] strings = filehandle.readString().split("\n");

			highscore = Integer.parseInt(strings[0]);
		}
		catch (Throwable e) {
			// :( It's ok we have defaults
		}

	}

}
