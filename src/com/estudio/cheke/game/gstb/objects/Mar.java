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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;

import com.estudio.cheke.game.gstb.Cache;

public class Mar extends Object{
	private int moveX=0;
	private static int[] filasY=new int[3];
	private static int[] filasX=new int[3];
	private boolean direction=false;
	private int maxX;
	private int olaY=0;
	private int newWidth=0;
	private int width2,height2;
	public Mar(){}
	public void draw(Canvas canvas) {
		Rect dst=new Rect();
		boolean dof1oneTime=false;
		int efimero=filasY.length;
		for(int f=0;f<efimero;f++){
			int movS=filasY[f]+Cache.marUp+Cache.bagUp;
			if(f==1&&!dof1oneTime){
				moveX*=-1;
				dof1oneTime=true;
			}else if(f!=1&&dof1oneTime){
				moveX*=-1;
				dof1oneTime=false;
			}
			dst.set(filasX[f]+moveX, movS, filasX[f]+width+moveX, height+movS);
			canvas.drawPicture(Cache.olas[0], dst);//CHange
		}
		int ola2=y+Cache.bagUp+olaY+Cache.marUp;
		dst.set(x, ola2, x+width2, ola2+height2);
		canvas.drawPicture(Cache.olas[1], dst);//CHange
	}
	public void setSize(RectF r, int obj){
		if(obj==0){
			setSizeBasic(r);
			float porY=canvasheight/2;
			if(height>porY){
				float multipler=height/porY;
				height=(int) (height/multipler);
				width=(int) (width/multipler);
			}
			height=canvasheight-40;
			filasY[0]=(int) (Cache.hpor*66);
			filasY[1]=(int) (Cache.hpor*77);
			filasY[2]=(int) (Cache.hpor*89);
			maxX=(int) (1.5*height);
			filasX[0]=-maxX+-20;
			filasX[1]=-20;
			filasX[2]=-maxX+-20;
		}else{
			height2=(int) r.bottom;
			width2=(int) r.right;
			x=150;
			y=(int) (canvasheight-height2+(Cache.hpor*5));
		}
		newWidth=(int)(width*0.9);
		makeMultiMarPicture();
	}
	public void makeMultiMarPicture(){
		int wW2=((canvaswidth/width)+2);
		Picture newPicture = new Picture();
		Canvas canvas;
		int Width=width+((wW2-1)*newWidth);
		Bitmap bitmap=Bitmap.createBitmap(Width,height, Bitmap.Config.ARGB_4444);
		canvas = new Canvas(bitmap);
		Rect dst=new Rect();
		canvas=newPicture.beginRecording(Width, height);
		for(int c=wW2;c>=0;c--){//make starting for the end for overlap
			if(c==0){
				dst.set(0, 0, width, height);
			}else{
				int left=newWidth*c;
				int right=left+width;
				dst.set(left, 0, right, height);
			}
			canvas.drawPicture(Cache.olas[0], dst);//CHange
		}
		width=Width;
		newPicture.endRecording();
		bitmap=null;
		Cache.olas[0]=newPicture;
	}
	public boolean contacto(int right, int yB, int h){
		boolean touch=false;
		if(right>=x&&right<=x+width2){ 
			int yhB=yB+h;
			int yy=y+Cache.bagUp+olaY+Cache.marUp;
			int yh=yy+height2;
			if( (yhB>=yy&&yhB<=yh) || (yB>=yy&&yhB<=yh) ){
				touch=true;
			}
		}
		return touch;
	}
	public void move(float timeDeltaSeconds){
		moveBasic(timeDeltaSeconds);
		if(x<-150){
			x=(int) (canvasheight*1.5);
		}
		if(moveX<maxX&&!direction){
			moveX++;//=move;
			if(olaY<height2){
				olaY++;
			}
		}else if(!direction){
			direction=true;
		}else if(direction&&moveX>0){
			moveX--;//=move;
			if(olaY>0){
				olaY--;
			}
		}else if(moveX<=maxX){
			direction=false;
		}
	}
}