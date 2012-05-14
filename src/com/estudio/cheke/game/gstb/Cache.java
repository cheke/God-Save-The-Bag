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

import android.content.res.Resources;
import android.graphics.Picture;
import android.graphics.Rect;

import com.estudio.cheke.game.gstb.objects.*;
import com.estudio.cheke.game.gstb.objects.Object;

public class Cache {
	public static Picture[] mPictures= new Picture[4];
	public static Picture[] mountains= new Picture[3];
	public static Picture[] olas= new Picture[2];
	public static Picture[] menu= new Picture[2];
	public static CanvasSprite canvasArray;
	public static Nube[] nubes= new Nube[7];
	public static Horizonte[] mountain= new Horizonte[20];
	public static Tree[] arboles=new Tree[5];
	public static Pajaro[] pajaro=new Pajaro[2];
	public static Buildings[] builds=new Buildings[20];
	public static Mar mar=new Mar();
	public static Control control=new Control();
	public static float wpor,hpor;
	public static float nivelPor=0;
	public static float starYmax;
	public static float leveldoned=0;
	public static boolean SunLines=false;
	public static boolean Stars=false;
	public static boolean loadImages=false;
	public static boolean menuFin=false;
	public static boolean controlT=false;
	public static Rect[] SunLine=new Rect[4];
	public static int[] SunLineX=new int[4];
	public static int[] SunLineY=new int[4];
	public static int[] rgb=new int[3];
	public static int width,height;
	public static int barraNivelX=0;
	public static int starY,starX,starXmax;
	public static int LevelNum=1;
	public static int countSec=0;
	public static int bagUp=0;
	public static int marUp=0;
	public static int SueloUp=0;
	public static int menuX, menuY,menuleft,menutop;
	public static Resources resource;
	public static void moveSun(){
		if(starYmax==0){
			LevelNum=1;
			starYmax=(float) (height*1.5);
			starX=0;
			bagUp=0;
			starXmax=(int) (wpor*50);
			rgb[0]=224;
			rgb[1]=218;
			rgb[2]=230;
		}
		starY=(int) ((nivelPor*starYmax)/100);
		starX=(int) (((nivelPor*(starXmax)))/100);
		changeColors();
		if(nivelPor<33){
			SunLines=false;
			for(int s=0;s<SunLine.length;s++){
				SunLine[s]=new Rect(0,0,0,0);
			}
			SunLineX[0]=SunLineY[1]=SunLineX[2]=SunLineY[3]=500;
			SunLineY[0]=SunLineX[1]=0;
			SunLineY[2]=SunLineX[3]=250;
		}else if(nivelPor<66){
			makeSunLinesMovement();
			SunLines=true;
			if(nivelPor>=60){
				SunLines=false;
			}
		}else if(nivelPor<100){
			SunLines=false;
		}else{
			leveldoned=0;
			nivelPor=0;
			LevelNum++; 
			SoundManager.playSound(2, true);
		}
	}
	public static void makeSunLinesMovement(){
		int movSunLines=10;
		for(int s=0;s<SunLine.length;s++){
			SunLine[s].set(starX-SunLineX[s], starY-SunLineY[s], starX+SunLineX[s], starY+SunLineY[s]);
			if(starX-SunLineX[s]<0){
				SunLineY[s]-=movSunLines;
			}
			if(starY-SunLineY[s]>(height*1.5)){
				SunLineX[s]-=movSunLines;
			}
			if(starX-SunLineX[s]>width){
				SunLineY[s]+=movSunLines;
			}
			if(starY-SunLineY[s]<0){
				SunLineX[s]+=movSunLines;
			}
		}
	}
	public static void changeColors(){
		if(nivelPor<20){
			if(rgb[0]>126){rgb[0]--;}
			if(rgb[1]>188){rgb[1]--;}
			if(rgb[2]>209){rgb[2]--;}
		}else if(nivelPor<40){
			if(rgb[0]<254){rgb[0]++;}
			if(rgb[1]<225){rgb[1]++;}
			if(rgb[2]>37){rgb[2]--;}
		}else if(nivelPor<60){
			if(rgb[0]>138){rgb[0]--;}
			if(rgb[1]>18){rgb[1]--;}
			if(rgb[2]>1){rgb[2]--;}
		}else if(nivelPor<80){
			Stars=true;
			if(rgb[0]>60){rgb[0]--;}
			if(rgb[1]<60){rgb[1]++;}
			if(rgb[2]<60){rgb[2]++;}
		}else if(nivelPor<100){
			if(rgb[0]>38){rgb[0]--;}
			if(rgb[1]>38){rgb[1]--;}
			if(rgb[2]>38){rgb[2]--;}
		}
	}
	public static void Sky(){
		int bordeLuna=(int) (20*hpor);
		starYmax=(height/2)+bordeLuna;
		starXmax=(width/2)+bordeLuna;
		starY=(int) (((nivelPor*starYmax)/100)-bordeLuna);
		starX=(int) ((((nivelPor*(starXmax)))/100)-bordeLuna);
		if(nubes.length==7){
			Nube[] nube=new Nube[20];
			for(int n=0;n<nubes.length;n++){
				nube[n]=nubes[n];
			}
			for(int n=nubes.length;n<nube.length;n++){
				nube[n]=new Nube(n);
			}
			nubes=nube;
			nube=null;
		}
		if(nivelPor>100){
			leveldoned=0;
			nivelPor=0;
			LevelNum++;
			SoundManager.playSound(3, true);
		}
		SueloUp=bagUp;
	}
	public static void Sea(){
		if(bagUp<=0&&nubes.length==20){
			Nube[] nube=new Nube[7];
			for(int n=0;n<nube.length;n++){
				nube[n]=nubes[n];
			}
			nubes=nube;
			nube=null;
		}
		if(starYmax==height/2){
			starYmax=height+100;
			starXmax=width+100;
		}
		starY=(int) ((height/2)+(nivelPor*((starYmax/2)+100))/100);
		starX=(int) ((width/2)+(nivelPor*((starXmax/2)+100))/100);
		if(nivelPor>90){
			if(nivelPor>100){
				leveldoned=0;
				nivelPor=0;
				LevelNum++;
				SoundManager.playSound(4, true);
			}
		}
	}
	public static void City(){
		amanecer();
		if(starXmax!=width){
			starXmax=width;
			starYmax=height*1.5f;
		}
		if(nivelPor>50){
			float nivelPorX=(nivelPor-50)*2;
			int SunXBasic=(int) (wpor*50);
			starY=(int) (starYmax-((nivelPorX*starYmax)/100));
			starX=(int) ((nivelPorX*(starXmax-SunXBasic))/100)+SunXBasic;
		}
		if(nivelPor>60){
			makeSunLinesMovement();
			SunLines=true;
		}
		if(nivelPor>100){
			menuFin=true;
		}
	}
	public static void amanecer(){
		if(nivelPor<20){
			if(rgb[0]<60){rgb[0]++;}
			if(rgb[1]<60){rgb[1]++;}
			if(rgb[2]<60){rgb[2]++;}
		}else if(nivelPor<40){
		}else if(nivelPor<60){
			Stars=false;
			if(rgb[0]<138){rgb[0]++;}
			if(rgb[1]>18){rgb[1]--;}
			if(rgb[2]>1){rgb[2]--;}
		}else if(nivelPor<80){
			if(rgb[0]<254){rgb[0]++;}
			if(rgb[1]<225){rgb[1]++;}
			if(rgb[2]<37){rgb[2]++;}
		}else if(nivelPor<90){
			if(rgb[0]>126){rgb[0]--;}
			if(rgb[1]>188){rgb[1]--;}
			if(rgb[2]<209){rgb[2]++;}
		}else if(nivelPor<100){
		}
	}
	public static void setDimensions(int w,int h){
		if(w<h){
			int efimero=h;
			h=w;
			w=efimero;
		}
		width=w;
		height=h;
		wpor=width/100;
		hpor=height/100;
		Object.canvasheight=h;
		Object.canvaswidth=w;
	}
	public static void setDefaultAll(){
		SunLines=Stars=loadImages=menuFin=false;
		SunLine=null;
		SunLineX=SunLineY=rgb=null;
		width=height=barraNivelX=starY=starX=starXmax=countSec=bagUp=marUp=SueloUp=menuX=menuY=menuleft=menutop=0;
		LevelNum=1;
		mPictures=mountains=olas=menu=null;
		canvasArray=null;
		nubes=null;
		mountain=null;
		arboles=null;
		pajaro=null;
		builds=null;
		mar=null;
		wpor=hpor=nivelPor=starYmax=leveldoned=0;
		resource=null;
	}
	public static int moveInt(int x,float timeDeltaSeconds,boolean more){
		if(more){
			x=(int) (x + ((10*Cache.wpor)* timeDeltaSeconds));
		}else{
			x=(int) (x - ((10*Cache.wpor)* timeDeltaSeconds));
		}
		return x;
	}
}