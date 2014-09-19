package com.gdg.miagegi.where;

public class Animation {

	static boolean stp = false;
	
	public static boolean r(Circle c){
		boolean b = false;
		if(c.stateTime < 1){
			c.stateTime += 0.05f;
		}
		else b = true;
		return b;
	}
	
	public static boolean l(Circle c){
		boolean b = false;
		if(c.stateTime > 0){
			c.stateTime -= 0.05f;
		}
		else{
			c.stateTime = 0f;
			b = true;
		}
		return b;
	}
	
	public static boolean rl(Circle c){
		boolean bool = false;
		if(!stp){
			if(c.stateTime < 1){
				c.stateTime += 0.05f;
			}
			else stp= true;
		}

		if(stp){
			if(c.stateTime > 0){
				c.stateTime -= 0.05f;
			}
			else {
				bool = true;
				c.stateTime = 0f;
			}
		}
		return bool;
	}
}
