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

public class Nube extends Object{
	private int[] color={Color.WHITE,Color.LTGRAY,0xffeeeeee,0xffdddddd,0xffcccccc};
	private int radius=25;
	private Picture nubeB = null;
	Rect dst=new Rect();
	private final int minCircles=5;
	private int numCircles=5;
	private int[] colors= new int[numCircles*minCircles];
	public Nube(int n){
		reinit(n);
	}
	// Move.
	public void move(float timeDeltaSeconds){
		moveBasicNube(timeDeltaSeconds);
		if(x<-width){
			reinit(3);
		}
	}
	private void reinit(int n){
		x=canvaswidth+(n*250);
		if(Cache.LevelNum!=2){
			y=(int) (y>Cache.hpor*15-Cache.bagUp||y<Cache.hpor*6-Cache.bagUp?Cache.hpor*6-Cache.bagUp:y);
			y=(int) (y+((Cache.hpor*9)*Math.random()));
		}else if(Cache.LevelNum==2){
			y=(int) (y>Cache.hpor*66-Cache.bagUp||y<Cache.hpor*6-Cache.bagUp?Cache.hpor*6-Cache.bagUp:y);
			y=(int) (y+((Cache.hpor*60)*Math.random()));
		}
		radius=(int) (5+(20*Math.random()));
		radius=(int) (radius*Cache.hpor);
		int h=((radius*3)-3+4);
		if(h>Cache.hpor*25){
			y-=h;
		}
		velocityX=(int) ((-100*Math.random())-25);
		for(int c=0;c<colors.length;c++){
			colors[c]=color[(int) ((color.length)*Math.random())];
			for(int f=0;f<=numCircles;f++){
				if((minCircles*f)-1==c){
					colors[c]=color[0];
				}
				if((minCircles*f)-4==c){
					colors[c]=color[0];
				}
			}
		}
		makeBitmap();
	}
	public void makeBitmap() {
		new Thread(new Runnable() {
			public void run() {
				width=(radius*4)+4;
				height=(radius*3)-3+4;
				nubeB = new Picture();
				//nubeB = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
				//Canvas comboImage = new Canvas(nubeB);
				Canvas comboImage = new Canvas();
				comboImage=nubeB.beginRecording(width, height);
				Paint pai=new Paint();
				pai.setFlags(1);
				int c=0;
				for(int x=0;x<2;x++){
					for(int y=0;y<numCircles;y++){
						pai.setColor(colors[c]);
						c++;
						comboImage.drawCircle(2+radius+(radius/2)+(x*radius)+((radius/9)*y), radius-3+2-((radius/9)*y), radius-3-((radius/6)*y),pai);
					}
				}
				for(int x=0;x<3;x++){
					for(int y=0;y<numCircles;y++){
						pai.setColor(colors[c]);
						c++;
						comboImage.drawCircle(2+radius+(x*radius)+((radius/9)*y), (radius*2)-3+2-((radius/9)*y), radius-((radius/6)*y),pai);
					}
				}
				nubeB.endRecording();
			}
		}).start();
	}
	public void draw(Canvas canvas) {
		if(nubeB!=null){
			//canvas.drawBitmap(nubeB, x, y + height+Cache.bagUp, null);
			dst.set(x, y + height+Cache.bagUp, x+width, y + height+Cache.bagUp+height);
			canvas.drawPicture(nubeB, dst);
		}
	}
}
