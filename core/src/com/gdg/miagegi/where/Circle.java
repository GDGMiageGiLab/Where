package com.gdg.miagegi.where;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Circle {

	public Vector2 position;
	public Vector2 initposition;
	public Vector2 finalposition;
	public Rectangle bounds;
	float stateTime;
	boolean target;
	
	public Circle(float x, float y){
		position = new Vector2(x, y);
		initposition = new Vector2(x, y);
		finalposition = new Vector2(x, y);
		bounds = new Rectangle(x, y, 100, 100);
		stateTime = 0f;
		target = false;
	}
}
