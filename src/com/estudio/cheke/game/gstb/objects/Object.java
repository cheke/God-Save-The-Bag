package com.estudio.cheke.game.gstb.objects;
/*
 * Created by Estudio Cheke, creative purpose.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.graphics.RectF;

public class Object {
	// Position.
	public int x;
	public int y;
	// Velocity.
	public int velocityX; 
	public int velocityY;
	// Size.
	public int width;
	public int height;
	public static int canvasheight;
	public static int canvaswidth;
	static final float SPEED_OF_GRAVITY = 50.0f;
	
	public void moveBasic(float timeDeltaSeconds){ 
		if(Velocity.VelocityX==0){
			Velocity.VelocityX=Velocity.MinVelocityX;
		}
		x=(int) (x + ((Velocity.VelocityX-(Velocity.Speed*15))* timeDeltaSeconds));
	}
	public void moveBasicNube(float timeDeltaSeconds){ 
		if(velocityX==0){
			velocityX=Velocity.MinVelocityX;
		}
		x=(int) (x + ((velocityX-(Velocity.Speed*15)) * timeDeltaSeconds));
	}
	public void setSizeBasic(RectF r){
		x=(int) r.left;
		y=(int) r.top;
		height=(int) r.bottom;
		width=(int) r.right;
	}
}