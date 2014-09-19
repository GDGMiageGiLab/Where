package com.gdg.miagegi.where;

import com.badlogic.gdx.Gdx;

public class Moves {

	public static boolean d(Circle a, Circle b, int speed, int difference){

		boolean d1 = false;

		if(a.finalposition.y < a.initposition.y+difference){
			a.position.y += Math.floor(speed * Gdx.graphics.getDeltaTime());
			a.finalposition.y = a.position.y;
		}

		if(a.finalposition.y >= a.initposition.y+difference && a.finalposition.x < b.initposition.x){
			a.finalposition.y = a.initposition.y+difference; /**/
			a.position.y = a.finalposition.y; /**/
			a.position.x += Math.floor(speed * Gdx.graphics.getDeltaTime());
			a.finalposition.x = a.position.x;
		}

		if(a.finalposition.y >= a.initposition.y+difference && a.finalposition.x >= b.initposition.x && a.position.y > b.initposition.y){
			a.finalposition.x = b.initposition.x;
			a.position.x = a.finalposition.x; /**/
			a.position.y -= Math.floor(speed * Gdx.graphics.getDeltaTime());
		}

		if(a.finalposition.y >= a.initposition.y+difference && a.finalposition.x >= b.initposition.x && a.position.y <= b.initposition.y){
			d1 = true;
		}

		if(b.finalposition.y > b.initposition.y-difference){
			b.position.y -= Math.floor(speed * Gdx.graphics.getDeltaTime());
			b.finalposition.y = b.position.y;
		}

		if(b.finalposition.y <= b.initposition.y-difference && b.finalposition.x > a.initposition.x){
			b.finalposition.y = b.initposition.y-difference; /**/
			b.position.y = b.finalposition.y; /**/
			b.position.x -= Math.floor(speed * Gdx.graphics.getDeltaTime());
			b.finalposition.x = b.position.x;
		}

		if(b.finalposition.y <= b.initposition.y-difference && b.finalposition.x <= a.initposition.x && b.position.y < a.initposition.y){
			b.finalposition.x = a.initposition.x;
			b.position.x = b.finalposition.x; /**/
			b.position.y += Math.floor(speed * Gdx.graphics.getDeltaTime());
		}

		if(b.finalposition.y <= b.initposition.y-difference && b.finalposition.x <= a.initposition.x && b.position.y >= a.initposition.y){
		}

		if(d1){
			
			a.finalposition.x = b.initposition.x;
			a.finalposition.y = b.initposition.y;
			a.bounds.x = a.finalposition.x;
			a.bounds.y = a.finalposition.y;
			
			
			b.finalposition.x = a.initposition.x;
			b.finalposition.y = a.initposition.y;
			b.bounds.x = b.finalposition.x ;
			b.bounds.y = b.finalposition.y;
			
			a.initposition.x = a.finalposition.x;
			a.initposition.y = a.finalposition.y;
			a.position.x = a.finalposition.x;
			a.position.y = a.finalposition.y;
			b.initposition.x = b.finalposition.x;
			b.initposition.y = b.finalposition.y;
			b.position.x = b.finalposition.x;
			b.position.y = b.finalposition.y;
			
			return true;
		}
		else{
			return false;
		}
	}
}
