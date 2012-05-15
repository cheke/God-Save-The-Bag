package com.estudio.cheke.game.gstb;
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
import com.estudio.cheke.game.gstb.CanvasSurfaceView.Renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

/**
 * An extremely simple renderer based on the CanvasSurfaceView drawing
 * framework.  Simply draws a list of sprites to a canvas every frame.
 */
public class SimpleCanvasRenderer extends Cache implements Renderer  {
	public void drawFrame(Canvas canvas) {
		if(loadImages){
			if (!GstbActivity.pause) {
				canvas.drawRGB(rgb[0], rgb[1], rgb[2]);
				Paint paint=new Paint();
				paint.setFlags(1);
				int efimero;
				if(LevelNum==1){
					paint.setColor(Color.YELLOW);
					canvas.drawCircle(starX, starY, 15*hpor, paint);
					if(SunLines){
						paint.setStrokeWidth(5*hpor);
						efimero=SunLine.length;
						for(int l=0;l<efimero;l++){
							canvas.drawLine(SunLine[l].left,SunLine[l].top ,SunLine[l].right ,SunLine[l].bottom , paint);
						}
					}
				}
				if(LevelNum==4){
					paint.setColor(Color.YELLOW);
					canvas.drawCircle(starX, starY, 15*hpor, paint);
					if(SunLines){
						paint.setStrokeWidth(5*hpor);
						efimero=SunLine.length;
						for(int l=0;l<efimero;l++){
							canvas.drawLine(SunLine[l].left,SunLine[l].top ,SunLine[l].right ,SunLine[l].bottom , paint);
						}
					}
				}
				if(Stars){
					paint.setColor(Color.WHITE);
					canvas.drawCircle(2*wpor, 9*hpor, hpor, paint);
					canvas.drawCircle(12*wpor, 89*hpor, hpor, paint);
					canvas.drawCircle(15*wpor, 97*hpor, hpor, paint);
					canvas.drawCircle(21*wpor, 25*hpor, hpor, paint);
					canvas.drawCircle(26*wpor, 45*hpor, hpor, paint);
					canvas.drawCircle(29*wpor, 21*hpor, hpor, paint);
					canvas.drawCircle(50*wpor, 94*hpor, hpor, paint);
					canvas.drawCircle(56*wpor, 28*hpor, hpor, paint);
					canvas.drawCircle(59*wpor, 83*hpor, hpor, paint);
					canvas.drawCircle(72*wpor, 38*hpor, hpor, paint);
					canvas.drawCircle(79*wpor, 34*hpor, hpor, paint);
					canvas.drawCircle(85*wpor, 45*hpor, hpor, paint);
					canvas.drawCircle(85*wpor, 34*hpor, hpor, paint);
					canvas.drawCircle(86*wpor, 95*hpor, hpor, paint);
					canvas.drawCircle(89*wpor, 89*hpor, hpor, paint);
					canvas.drawCircle(95*wpor, 45*hpor, hpor, paint);
				}
				if(LevelNum!=1&&LevelNum!=4){
					moon.draw(canvas, starX, starY);
				}
				paint.setColor(Color.BLACK);
				efimero=nubes.length;
				for(int n=0;n<efimero;n++){
					if(nubes[n]!=null){
						nubes[n].draw(canvas);
					}
				}
				if(LevelNum==1){
					efimero=mountain.length;
					for(int m=0;m<efimero;m++){
						if(mountain[m]!=null){
							mountain[m].draw(canvas);
						}
					}
					efimero=arboles.length;
					for(int a=0;a<efimero;a++){
						if(arboles[a]!=null){
							arboles[a].draw(canvas);
						}
					}
					canvas.drawRect(0, height-(15*hpor)+bagUp, width, height, paint);//Suelo
				}
				if(mar!=null){
					if(LevelNum==3){
						mar.draw(canvas);
					}
				}
				if(LevelNum==4){
					efimero=builds.length;
					for(int b=0;b<efimero;b++){
						if(builds[b]!=null){
							builds[b].draw(canvas);
						}
					}
					efimero=arboles.length;
					for(int a=0;a<efimero;a++){
						if(arboles[a]!=null){
							arboles[a].draw(canvas);
						}
					}
				}
				if(LevelNum>2){
					canvas.drawRect(0, height-(15*hpor)+SueloUp, width, height, paint);//Suelo
				}
				efimero=pajaro.length;
				for(int p=0;p<efimero;p++){
					if(pajaro[p]!=null){
						pajaro[p].draw(canvas);
					}
				}
				canvas.drawCircle(width-(10*wpor),10*wpor, 5*wpor, paint);//Circulo nivel
				canvas.drawRect(0, 0, width, 4*hpor, paint);//barra superior
				paint.setColor(Color.GREEN);//cambio de color de barra leveldoned
				canvas.drawRect(0, 0, barraNivelX, 4*hpor, paint);//barra leveldoned
				paint.setTextSize(10*wpor);//num nivel size
				canvas.drawText(""+LevelNum, width-(12.5f*wpor), 13.5f*wpor, paint);//num nivel
				canvasArray.draw(canvas);//bolsa
				control.draw(canvas);
				if(menuFin){
					Rect dst=new Rect();
					paint.setTextSize(hpor*15);
					Typeface typeface=Typeface.create(Typeface.SERIF, Typeface.BOLD);
					paint.setTypeface(typeface);
					paint.setColor(Color.BLACK);
					canvas.drawText("THE END", 0, hpor*15, paint);//title
					dst.set(menuleft, (menutop*3)+menuY, menuleft+menuX, (menutop*3)+menuY*2);
					canvas.drawPicture(menu[1], dst);
				}
			}else{//pause true
				Paint paint=new Paint();
				paint.setFlags(1);
				canvas.drawRGB(224, 218, 230);
				paint.setColor(Color.BLACK);
				int efimero;
				efimero=nubes.length;
				for(int n=0;n<efimero;n++){
					if(nubes[n]!=null){
						nubes[n].draw(canvas);
					}
				}
				efimero=mountain.length;
				for(int m=0;m<efimero;m++){
					if(mountain[m]!=null){
						mountain[m].draw(canvas);
					}
				}
				efimero=arboles.length;
				for(int a=0;a<efimero;a++){
					if(arboles[a]!=null){
						arboles[a].draw(canvas);
					}
				}
				canvas.drawRect(0, height-(15*hpor)+bagUp, width, height, paint);//Suelo
				if(menu[0]!=null){
					menuleft=(width-menuX)/2;
					menutop=(height-(menuY*2))/4;
					paint.setTextSize(hpor*15);
					Typeface typeface=Typeface.create(Typeface.SERIF, Typeface.BOLD);
					paint.setTypeface(typeface);
					canvas.drawText("God Save The Bag", 0, hpor*15, paint);//title
					Rect dst=new Rect();
					dst.set(menuleft, menutop*2, menuleft+menuX, menutop*2+menuY);
					canvas.drawPicture(menu[0], dst);
					dst.set(menuleft, (menutop*3)+menuY, menuleft+menuX, (menutop*3)+menuY*2);
					canvas.drawPicture(menu[1], dst);
				}
			}
		}
	}
	public void sizeChanged(int width, int height) {}
}