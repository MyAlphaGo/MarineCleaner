/**
 * com.lqx.MyPlane.java
 */
package com.lqx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class MyCleaner extends Cleaner{
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	private boolean firezd = false;
	private boolean firebm = false;
	private boolean firePPbm = false;
	public static int expsum = 10;
	public static int PPbm = 1;
	public static int FULLBLOOD = 10;
	public static int bm_width = 25;
	private Butsleep adbu_sleep = new Butsleep();
	public List<MyBullet> bullet_list = new ArrayList<MyBullet>();
	public List<BigRecruit> PB_list = new ArrayList<BigRecruit>();
	public MyCleaner(){
		plimg = tool.getImage(MyCleaner.class.getResource("/images/2.gif"));
		zdimg = tool.getImage(MyBullet.class.getResource("/images/zidan02_1.png"));
		blood = 10;
		PPbm = 1;
		expsum = 10;
		
		f_x = 60;
		f_y = 60;
		
		FULLBLOOD = 10;
		Music.prepare.play();
		adbu_sleep.start();
	}
	public MyCleaner( int _f_x, int _f_y, int _f_Localx, int _f_Localy, Image _img){
		
		f_x = 60;
		f_y = 60;
		f_Localx = _f_Localx;
		f_Localy = _f_Localy;
		plimg = _img;
		blood = 10;
		FULLBLOOD = 10;
		PPbm = 1;
		expsum = 10;
		adbu_sleep.start();
		Music.prepare.play();
	}
	private static int counter = 2;
	private class Butsleep extends Thread{

		@Override
		public void run() {
			while(true){
				try {
					Butsleep.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++ counter;
			}
		}
		
	}
	public void paint(Graphics g){
		if(isPlane_live()){
			MoveMyPlane();
			g.drawImage(plimg, f_Localx, f_Localy, null );
			g.setColor(Color.red);
			g.drawRect(50,GameFrame.TitleWidth - GameFrame.NoteWidth + 8, 200, GameFrame.NoteWidth-16);
			g.fillRect(50,GameFrame.TitleWidth - GameFrame.NoteWidth + 8, blood * 200 / FULLBLOOD , GameFrame.NoteWidth-16);
			for(int i = 0; i < bullet_list.size(); ++i){
				MyBullet bullet = bullet_list.get(i);
				CollideExamine coexBP = new CollideExamine();
				CollideExamine.mb = bullet;
				coexBP.CollidedBP();
				if(bullet.isBullet_live() == false){
					bullet_list.remove(bullet);
				}
				bullet.paint(g);
			}
			for(int i = 0; i < PB_list.size(); ++i){
				BigRecruit bullet = PB_list.get(i);
				CollideExamine coexBP = new CollideExamine();
				CollideExamine.mb = bullet;
				coexBP.CollidedBP();
				if(bullet.isBullet_live() == false){
					PB_list.remove(bullet);
				}
				bullet.paint(g);
			}
		}
		
	}
	public void addBullet(int i){
		if(counter >= 2){
			int zd_w = 13,zd_h = 21;
			if(i == 2) {
				zd_w = 24;
				zd_h = 9;
			}
			MyBullet bullet = new MyBullet(f_Localx + f_x, f_Localy+f_y/2,zdimg,zd_w,zd_h);
			bullet.setZdspeed(5);
			bullet_list.add(bullet);
			Music.fire.play();
			counter = 0;
		}
	}
	public void addBullet2(int i){
		if(expsum >= 1){
			if(counter >= 2){
				int zd_w = 25,zd_h = 40;
				if(i == 2) {
					zd_w = 50;
					zd_h = 17;
				}
				MyBullet bullet = new MyBullet(f_Localx + f_x, f_Localy+f_y/2,zdimg,zd_w,zd_h);
				bullet.setZdspeed(10);
				bullet_list.add(bullet);
				Music.Beam.play();
				counter = 0;
				-- expsum;
			}
		}
		
	}
	public void addBulletPPbm(){
		if(PPbm >= 1){
			if(counter >= 2){
				BigRecruit bullet = new BigRecruit(f_Localx + f_x,f_Localy+f_y/2);
				bullet.setZdspeed(10);
				PB_list.add(bullet);
				Music.Beam.play();
				counter = 0;
				-- PPbm;
			}
		}
		
	}
	public void setBlood(int i) {
		this.blood = i;
	}
	/**
	 * 控制潜水员动作
	 * @param key 键盘按下的键位
	 */
	public void ControlPressed(KeyEvent key){
		int index = key.getKeyCode();
		switch (index){
		
			//按下键盘 w 上升
			case 87:{
				up = true;
				break;
			}
			//按下键盘 s 下降
			case 83:{
				down = true;
				break;
			}
			//按下键盘 a 后退
			case 65:{
				left = true;
				break;
			}
			//按下键盘 d 前进
			case 68:{
				right = true;
				break;
			}
			
			//按下键盘 w 上升
			case 38:{
				up = true;
				break;
			}
			//按下键盘 s 下降
			case 40:{
				down = true;
				break;
			}
			//按下键盘 a 后退
			case 37:{
				left = true;
				break;
			}
			//按下键盘 d 前进
			case 39:{
				right = true;
				break;
			}
		}
		if(key.getKeyCode()==KeyConfig.getKeyConfig().getKeyCode()[0]){
			firezd=true;
			return;
		}
		if(key.getKeyCode()==KeyConfig.getKeyConfig().getKeyCode()[1]){
			firebm=true;
			return;
		}
		if(key.getKeyCode()==KeyConfig.getKeyConfig().getKeyCode()[2]){
			firePPbm=true;
			return;
		}
	}
	public void ControlReleased(KeyEvent key){
		int index = key.getKeyCode();
		switch (index){
			//按下键盘 w 上升
			case KeyEvent.VK_W:{
				up = false;
				break;
			}
			//按下键盘 s 下降
			case KeyEvent.VK_S:{
				down = false;
				break;
			}
			//按下键盘 a 后退
			case KeyEvent.VK_A:{
				left = false;
				break;
			}
			//按下键盘 d 前进
			case KeyEvent.VK_D:{
				right = false;
				break;
			}
			
			//按下键盘 w 上升
			case KeyEvent.VK_UP:{
				up = false;
				break;
			}
			//按下键盘 s 下降
			case KeyEvent.VK_DOWN:{
				down = false;
				break;
			}
			//按下键盘 a 后退
			case KeyEvent.VK_LEFT:{
				left = false;
				break;
			}
			//按下键盘 d 前进
			case KeyEvent.VK_RIGHT:{
				right = false;
				break;
			}
		}
		if(key.getKeyCode()==KeyConfig.getKeyConfig().getKeyCode()[0]){
			firezd=false;
			return;
		}
		if(key.getKeyCode()==KeyConfig.getKeyConfig().getKeyCode()[1]){
			firebm=false;
			return;
		}
		if(key.getKeyCode()==KeyConfig.getKeyConfig().getKeyCode()[2]){
			firePPbm=false;
			return;
		}
	}
	public void MoveMyPlane(){
		if(up == true){
			if (f_Localy >= GameFrame.TitleWidth + BaseMove){
				f_Localy -= BaseMove;
			}
		}
		if(down == true){
			if (f_Localy <= GameFrame.height - f_y - BaseMove){
				f_Localy += BaseMove;						
			}
		}
		if(left == true){
			if (f_Localx >= BaseMove){
				f_Localx -= BaseMove;							
			}
		}
		if(right == true){
			if (f_Localx < GameFrame.width - f_x - BaseMove){
				f_Localx += BaseMove;						
			}
		}
		if(firezd == true || firebm == true){
			if(firezd == true) {
				if(Level.level % 2 == 0) {
						zdimg = tool.getImage(MyBullet.class.getResource("/images/zidan02_1.png"));
						if (bullet_list.size() < Level.level+2){
							addBullet(2);
						}
				}
				else {
						zdimg = tool.getImage(MyBullet.class.getResource("/images/zidan02_2.png"));
						if (bullet_list.size() < Level.level+2){
							addBullet(1);
						}
				}
			}
			else
			if(firebm== true) {
				if(Level.level % 2 == 0) {
						zdimg = tool.getImage(MyBullet.class.getResource("/images/missile_1.PNG"));
						if (bullet_list.size() < Level.level+2){
							addBullet2(2);
						}
				}
				else {
						zdimg = tool.getImage(MyBullet.class.getResource("/images/missile_2.PNG"));
						if (bullet_list.size() < Level.level+2){
							addBullet2(1);
						}
				}
			}
		}
//		if(firebm == true){
//			zdimg = tool.getImage(MyBullet.class.getResource("/images/missile.PNG"));
//			if (bullet_list.size() < Level.level+2){
//				addBullet2();
//			}
//		}
		if(firePPbm == true){
			if (bullet_list.size() < Level.level+2){
				addBulletPPbm();
			}
		}
	}
	public void setFULLBLOOD(int i) {
		FULLBLOOD = i;
		// TODO Auto-generated method stub
		
	}

}
