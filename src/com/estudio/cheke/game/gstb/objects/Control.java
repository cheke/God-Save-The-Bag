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
import android.graphics.Picture;
import android.graphics.Rect;

public class Control {
	Picture control;
	int tercio;
	Rect dst=new Rect();;
	public void draw(Canvas canvas){
		canvas.drawPicture(control, dst);
	}
	public void setPicture(Picture pic){
		control=null;
		control=pic;
		int box_dimension=(int) (Cache.wpor*33);
		tercio=box_dimension/3;
		int y = (int) (Cache.height-(box_dimension+(Cache.hpor*5)));
		int x = (int) (Cache.hpor*5);
		dst.set(x, y, x+box_dimension, y+box_dimension);
	}
	public void touched(int x, int y){
		int movMin=10;
		int mov=movMin;
		boolean Xup=false;
		if(x>dst.left&&x<dst.right){
			if(y>dst.top&&y<dst.bottom){
				boolean mover=false;
				if(x<dst.left+tercio){//Primera columna
					if(y>dst.top+tercio&&y<dst.bottom-tercio){//atras
						Xup=true;
						mov=-movMin;
						mover=true;
					}
				}else if(x<dst.left+(tercio*2)){//Segunda columna
					if(y>dst.top&&y<dst.top+tercio){//arriba
						Xup=false;
						mov=-movMin;
						mover=true;
					}else if(y>dst.bottom-tercio&&y<dst.bottom){//abajo
						Xup=false;
						mover=true;
					}
				}else{//Tercera columna
					if(y>dst.top+tercio&&y<dst.bottom-tercio){//adelante
						Xup=true;
						mover=true;
					}
				}
				if(mover){
					Cache.canvasArray.moveBolsa(mov, Xup);
				}
			}
		}
	}
}