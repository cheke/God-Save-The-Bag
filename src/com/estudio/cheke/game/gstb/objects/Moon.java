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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;

public class Moon {
	private Picture moon=new Picture();
	private int width, height;
	Rect dst=new Rect();
	public Moon(){}
	public void makeMoon(){
		new Thread(new Runnable() {
			public void run() {
				if(Cache.hpor>0f){
					Picture newPicture = new Picture();
					Canvas canvas;
					int minRadius=(int) (20*Cache.hpor);
					width=height=minRadius*2;
					Bitmap bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_4444);
					canvas = new Canvas(bitmap);
					Paint paint=new Paint();
					paint.setFlags(1);
					paint.setColor(Color.LTGRAY);
					canvas=newPicture.beginRecording(width, height);
					canvas.drawCircle(minRadius, minRadius, minRadius, paint);
					paint.setColor(Color.DKGRAY);
					canvas.drawCircle(22*Cache.hpor, 29*Cache.hpor, 2*Cache.hpor, paint);
					canvas.drawCircle(10*Cache.hpor, 18*Cache.hpor, 5*Cache.hpor, paint);
					canvas.drawCircle(25*Cache.hpor, 15*Cache.hpor, 3*Cache.hpor, paint);
					canvas.drawCircle(17*Cache.hpor, 32*Cache.hpor, 2*Cache.hpor, paint);
					paint.setColor(Color.GRAY);
					canvas.drawCircle(32*Cache.hpor, 23*Cache.hpor, 6*Cache.hpor, paint);
					canvas.drawCircle(11*Cache.hpor, 18*Cache.hpor, 3*Cache.hpor, paint);
					canvas.drawCircle(15*Cache.hpor, 25*Cache.hpor, 4*Cache.hpor, paint);
					canvas.drawCircle(23*Cache.hpor, 8*Cache.hpor, 3*Cache.hpor, paint);
					canvas.drawCircle(31*Cache.hpor, 31*Cache.hpor, 3*Cache.hpor, paint);
					newPicture.endRecording();
					bitmap=null;
					moon=newPicture;
				}
			}
		}).start();
	}
	public void draw(Canvas canvas,int starX, int starY){
		dst.set(starX, starY, starX+width, starY+height);
		canvas.drawPicture(moon, dst);
	}
}