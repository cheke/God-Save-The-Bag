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
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.estudio.cheke.game.gstb.Cache;

public class CanvasSprite extends Object{
	private int currentFrame=0; //current frame being played
	private int waitDelay=15; // delay before the next frame
	public int velocityX; 
	public CanvasSprite() {}
	public void draw(Canvas canvas) {
		Rect dst=new Rect();
		dst.set(x, y, x+width, y+height);
		canvas.drawPicture(Cache.mPictures[currentFrame], dst);
		update();
	}
	public void update(){//updates the frame counter to the next frame
		if(waitDelay==0){//if done waiting
			//set current frame back to the first because looping is possible
			if(currentFrame == Cache.mPictures.length-1){
				currentFrame=0;
			}else{	
				currentFrame++; //go to next frame
				waitDelay = 15; //set delaytime for the next frame
			}
		}else{
			waitDelay--; //wait for delay to expire
		}
	}
	public void setSize(RectF r){
		setSizeBasic(r);
		x=250;
		y=canvasheight/2;
		if(width!=Cache.wpor*10){
			float multipler=(Cache.wpor*10)/width;
			width*=multipler;
			height*=multipler;
		}
	}
	public void moveBolsa(int mov,boolean x){
		if(x){
			velocityX+=mov;
		}else{
			velocityY+=mov;
		}
	}
	public void moveX(float timeDeltaSeconds){
		if(velocityX!=0){
			if(x<Cache.wpor*10){
				Velocity.Speed=0;
			}else if(x<Cache.wpor*20){
				Velocity.Speed=1;
			}else if(x<Cache.wpor*40){
				Velocity.Speed=2;
			}else if(x<Cache.wpor*60){
				Velocity.Speed=3;
			}else if(x<Cache.wpor*80){
				Velocity.Speed=4;
			}
		}
		if(velocityX!=0&&(int) (x + (velocityX * timeDeltaSeconds))+width<canvaswidth-100&&(int) (x + (velocityX * timeDeltaSeconds))>25){ 
			x=x!=1000?(int) (x + (velocityX * timeDeltaSeconds)):x;
		}else{
			velocityX=0;
		}
	}
	public void move(float timeDeltaSeconds){ 
		if((int) (y + (velocityY * timeDeltaSeconds))+height+50<=Cache.height&&(int) (y + (velocityY * timeDeltaSeconds))>25){
			//if((int) (y + (velocityY * timeDeltaSeconds))>25){	
			y=y!=1000?(int) (y + (velocityY * timeDeltaSeconds)):y;
		}else{
			velocityY=0;
		}
	}
	// Apply Gravity.
	public void gravity(float timeDeltaSeconds){
		velocityY += SPEED_OF_GRAVITY * timeDeltaSeconds;
	}
}