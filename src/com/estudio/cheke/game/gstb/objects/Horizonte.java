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

public class Horizonte  extends Object{
	private int currentObj=0;
	private static int objvar=0; 
	public Horizonte(int obj){
		obj=obj>1&&obj<3?1:obj;
		obj=obj>2?2:obj;
		objvar+=obj==2?1:0;
		obj=objvar==2?1:obj;
		objvar=objvar==2?0:objvar;
		currentObj=obj;
	}
	public void draw(Canvas canvas) {
		Rect dst=new Rect();
		dst.set(x, y+Cache.bagUp, x+width, y+height+Cache.bagUp);
		canvas.drawPicture(Cache.mountains[currentObj], dst);//CHange
	}
	public void setSize(RectF r){
		setSizeBasic(r);
		float porY=canvasheight/2;
		if(currentObj!=0){
			porY=canvasheight/3;
		}
		if(height>porY){
			float multipler=height/porY;
			height=(int) (height/multipler);
			width=(int) (width/multipler);
		}
		if(currentObj!=0){
			double random= Math.random();
			height*=random;
			width*=random;
		}
		y=(int) (canvasheight-height-(Cache.hpor*15));
		height=canvasheight-40;
		x+=500*Math.random();
	}
	// Move.
	public void move(float timeDeltaSeconds){ 
		moveBasic(timeDeltaSeconds);
		if(x<-width){
			reinit(2);
		}
	}
	public void reinit(int n){
		x=canvaswidth+(n*250);
	}
}