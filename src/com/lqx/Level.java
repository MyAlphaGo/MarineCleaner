package com.lqx;

public class Level {
	public static int level;
	public static int FPCreateSpeed;
	public static int FPMoveSpeed;
	public static int FBMoveSpeed;
	public static int KillFP;
	public static int []lexelup = {5,10,20,35,55,70,85,100,200};
	public Level(){
		level = 1;
		FPCreateSpeed = 3000;
		FPMoveSpeed = 3;
		FBMoveSpeed = 5;
		KillFP=0;
	}
	public void updateLevel(){
		for(int i = level -1; i < 8; ++i){
			if(KillFP == lexelup[i]){
//				level = i + 2;
				level = level + 1;
			}
		}		
		switch(level){
			case 2:{
				FPCreateSpeed = 2000;
				FPMoveSpeed = 3;
				FBMoveSpeed = 5;
				break;
			}
			case 3:{
				FPCreateSpeed = 2000;
				FPMoveSpeed = 3;
				FBMoveSpeed = 6;
				break;
			}
			case 4:{
				FPCreateSpeed = 1500;
				FPMoveSpeed = 3;
				FBMoveSpeed = 6;
				break;
			}
			case 5:{
				FPCreateSpeed = 1000;
				FPMoveSpeed = 4;
				FBMoveSpeed = 7;
				break;
			}
			case 6:{
				FPCreateSpeed = 1000;
				FPMoveSpeed = 6;
				FBMoveSpeed = 10;
				break;
			}
			case 7:{
				FPCreateSpeed = 800;
				FPMoveSpeed = 8;
				FBMoveSpeed = 15;
				break;
			}
			case 8:{
				FPCreateSpeed = 500;
				FPMoveSpeed = 8;
				FBMoveSpeed = 15;
				break;
			}
			case 9:{
				FPCreateSpeed = 300;
				FPMoveSpeed = 10;
				FBMoveSpeed = 20;
				break;
			}
		}
	}
}
