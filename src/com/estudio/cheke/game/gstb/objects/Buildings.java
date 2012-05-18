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
import com.estudio.cheke.game.gstb.Cache;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;

public class Buildings extends Object{
	private Picture Building;
	Rect dst=new Rect();
	public void draw(Canvas canvas){
		//canvas.drawBitmap(Building, x, y, null);
		dst.set(x, y , x+width, y+height);
		canvas.drawPicture(Building, dst);
	}
	public void move(float timeDeltaSeconds){
		moveBasic(timeDeltaSeconds);
		if(x<-width){
			reinit();
		}
	}
	private void reinit(){
		MakeBuild();
		x=(int) (canvaswidth*((2*Math.random())+1));
	}
	public Buildings(){
		MakeBuild();
	}
	private void MakeBuild(){
		int minX=3;
		int minY=3;
		width=(int) (((3*Math.random())+minX)*(Cache.wpor*5));
		height=(int) (((5*Math.random())+minY)*(Cache.hpor*5));
		y=(int) (canvasheight-(15*Cache.hpor)-height);
		x=(int) (canvaswidth*((2*Math.random())+1));
		Paint paint=new Paint();
		paint.setFlags(1);
		int color=(int) ((3*Math.random())+1);
		switch(color){
		case 1:
			paint.setColor(Color.DKGRAY);
			break;
		case 2:
			paint.setColor(0xff333333);
			break;
		case 3:
			paint.setColor(0xff222222);
			break;
		case 4:
			paint.setColor(0xff111111);
			break;
		}
		Building = new Picture();
		//Bitmap build = Bitmap.createBitmap((int)(width),(int)(height), Bitmap.Config.ARGB_4444);
		Canvas canvas = new Canvas();
		canvas=Building.beginRecording(width, height);
		canvas.drawRect(0, 0, width, height, paint);//estructura
		for(int p=0;p<height;p++){//plantas
			if(p==1||p==3||p==5||p==7){
				for(int v=0;v<width;v++){//ventanas
					if(v==1||v==3){
						paint.setColor(Color.BLACK);
						canvas.drawRect(v*(Cache.wpor*5), height-((p+1)*(Cache.hpor*5)), (v+1)*(Cache.wpor*5), height-(p*(Cache.hpor*5)), paint);//ventanas
					}
				}
			}
		}
		Building.endRecording();
		//Building=build;
	}
}