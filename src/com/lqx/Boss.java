/**
 * com.lqx.BossPlane.java
 */
package com.lqx;
/**
 * 导入相应的Java包
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Boss类，继承潜水员类
 * 类中设置 Boss 的图片以及发射子弹的图片
 */
public class Boss extends Cleaner {
	/*
	 * 设定不同子弹的宽度标志，
	 * 用于在碰撞检测的时候确定子弹所造成的伤害
	 */
		public static int Boss_width = 100;
		public static int Bu_width0 = 20;
		public static int Bu_width1 = 30;
		public static int Bu_width2 = 63;
		public static int Bu_width3 = 100;
		public static int ms_width3 = 50;
		public static int ms_width2 = 43;
		public static int ms_width1 = 25;
		public static int ms_width0 = 30;
		
		private volatile boolean isInit = false;
		
		private volatile boolean isAddBullet = false;
	/**
	 * 
	 */
	//工具包
	private Toolkit tool  = Toolkit.getDefaultToolkit();
	//以类为路径找相对路径(反射法)寻找背景
	/**
	 * boss 的图片
	 */
	public Image[] boss_imgs = {
		tool.getImage(Boss.class.getResource("/images/boss02.png")),
		tool.getImage(Boss.class.getResource("/images/boss03.png")),
		tool.getImage(Boss.class.getResource("/images/boss02.png")),
		tool.getImage(Boss.class.getResource("/images/boss02.png")),
		tool.getImage(Boss.class.getResource("/images/boss02.png")),
		tool.getImage(Boss.class.getResource("/images/boss02.png")),
		tool.getImage(Boss.class.getResource("/images/boss02.png")),
		tool.getImage(Boss.class.getResource("/images/boss02.png")),
		tool.getImage(Boss.class.getResource("/images/boss02.png"))
	} ;
	public int []boss_x ={150,250,150,150,150,150,150,150,150};
	public int []boss_y ={67,160,67,67,67,67,67,67,67};
	
	public Image[] bu_imgs = {
			/**
			 * 可回收垃圾
			 */
			tool.getImage(Boss.class.getResource("/images/r1_2.png")),
			tool.getImage(Boss.class.getResource("/images/r2_2.png")),
			tool.getImage(Boss.class.getResource("/images/r3_2.png")),
			tool.getImage(Boss.class.getResource("/images/r4_2.png")),
			/**
			 * 不可回收垃圾
			 */
			tool.getImage(Boss.class.getResource("/images/r1_1.png")),
			tool.getImage(Boss.class.getResource("/images/r2_1.png")),
			tool.getImage(Boss.class.getResource("/images/r3_1.png")),
			tool.getImage(Boss.class.getResource("/images/r4_1.png"))
		} ;
	    public int []bu_x ={40,40,40,40,53,34,29,42,100};
	    public int []bu_y ={13,27,19,27,52,50,18,16,100};
		public int zd_x = 0;
		public int zd_y = 0;
	private static final long serialVersionUID = 1L;
	public List<FightBullet> bullet_list = new ArrayList<FightBullet>();
	public Boss(){
		this.f_x = 150;
		this.f_y = 67;
		this.f_Localx = GameFrame.width - f_x;
		this.f_Localy = GameFrame.height /2;
		this.BaseMove = 2;
		this.Plane_live = true;
		this.plimg = tool.getImage(Boss.class.getResource("/images/boss02.png"));
		/*this.zdimg = tool.getImage(MyBullet.class.getResource("/images/MB_ultimate0.png"));*/
		inix = 2 - new Random().nextInt(5);
		iniy = 2 - new Random().nextInt(5);
		inx.start();
		setBlood();
		BossopenFire();
		isInit = true;
		
	}
	public void init() {
		if(!isInit) {
			setBlood();
			BossopenFire();
			isInit = true;
		}
		
	}
	public void paint(Graphics g){
		setBossPlane();
		g.drawImage(plimg, f_Localx, f_Localy, null );
		g.setColor(Color.red);
		g.drawRect(f_Localx,f_Localy, f_x, 5);
		g.fillRect(f_Localx,f_Localy, blood * f_x /(5 * Level.lexelup[Level.level-1]), 5);
		for(int i = 0; i < bullet_list.size(); ++i){
			FightBullet bullet = bullet_list.get(i);
			CollideExamine coexPB = new CollideExamine();
			CollideExamine.fb = bullet;
			coexPB.CollidedPB();
			bullet.paint(g);
			if(bullet.isBullet_live() == false){
				bullet_list.remove(bullet);
			}
		}
		Move();
	}
	public void setBossPlane(){
		this.f_x = boss_x[Level.level-1];
		this.f_y = boss_y[Level.level-1];
		this.plimg = boss_imgs[Level.level-1];
	}
	public void BossopenFire(){
		BossBuThread bsbu = new BossBuThread();
		bsbu.start();
	}	
	private static int counter = 0;
	private class inixsleep extends Thread{

		@Override
		public void run() {
			while(true){
				try {
					inixsleep.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++ counter;
				if(counter % 10 == 0){
					setInix(4 - new Random().nextInt(8));
					setIniy(4 - new Random().nextInt(9));
				}
			}
		}		
	}
	private inixsleep inx = new inixsleep();
	private int inix = 0;
	public void setInix(int inix) {
		this.inix = inix;
	}
	private int iniy = 0;
	public void setIniy(int iniy) {
		this.iniy = iniy;
	}
	public void Move()
	{
		if(f_Localy >= GameFrame.height - f_y) {
			iniy = 0 - iniy;
		}
		else if(f_Localy <= GameFrame.TitleWidth) {
			iniy = 0 - iniy;
		}
		f_Localy -= iniy;
		if(f_Localy <= GameFrame.TitleWidth){
			f_Localy = GameFrame.TitleWidth;
		}
		if(f_Localy >= GameFrame.height - f_y){
			f_Localy = GameFrame.height - f_y;
		}
		f_Localx -= inix;
		if(f_Localx <= 350) {
			f_Localx = 350;
		}
		if(f_Localx >= GameFrame.width - f_x) {
			f_Localx = GameFrame.width - f_x;
		}
	}
	public void addBullet(){
		if(bullet_list.size()>=10) {
			bullet_list.clear();
		}
		setBossBullet();
		FightBullet bullet = new FightBullet(f_Localx, f_Localy + f_y/2,zd_x,zd_y,zdimg);
		bullet.setZdspeed(Level.FBMoveSpeed);
		bullet_list.add(bullet);
	}
	//
	public void setBossBullet(){
		//170-178
		int i = 0;
		if(Level.level%2 == 0) {
			i = new Random().nextInt(4);
		}
		else {
			i = (new Random().nextInt(4))+4;
		}
		
		/*int i = new Random().nextInt(Level.level);*/
		this.zdimg = bu_imgs[i];
		this.zd_x = bu_x[i];
		this.zd_y = bu_y[i];
	}
	private class BossBuThread extends Thread{
		public void run() {
			while(true){
				try {
					BossBuThread.sleep(Level.FPCreateSpeed/2);//Level.FPCreateSpeed/2
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(isAddBullet)
					addBullet();	
			}
		}
	}
	public void setBlood() {
		this.blood = 5 * Level.lexelup[Level.level-1];
	}
	
	public void setIsInit(boolean isinit) {
		this.isInit = isinit;
	}
	
	public void setIsAddBullet(boolean isadd) {
		this.isAddBullet = isadd;
		if(!isadd) {
			bullet_list.clear();
		}
	}
}
