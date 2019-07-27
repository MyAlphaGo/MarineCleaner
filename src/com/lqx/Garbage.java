/**
 * com.lqx.FightPlane.java
 */
package com.lqx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 垃圾类继承清洁员类
 * 
 *
 */
public class Garbage extends Cleaner{
	/**
	 * 定义一个垃圾子弹集合
	 */
	public List<FightBullet> bullet_list = new ArrayList<FightBullet>();
	/**
	 * 实例化一个随机数发生器，随机产生清洁员上下移动距离
	 */
	Random ram = new Random();
	/**
	 * 重写构造函数
	 */
	public Garbage(){
		plimg = tool.getImage(Garbage.class.getResource("/images/r1.png"));
	}
	/**
	 * 重写构造函数
	 * @param _f_x 清洁员的宽度
	 * @param _f_y 清洁员的高度
	 * @param _f_Localx 清洁员的x坐标
	 * @param _f_Localy 清洁员的 y坐标
	 * @param _img 子弹的图片
	 */
	private int fullblood = 0;
	private int i = 0;
	public Garbage(int _f_Localx, int _f_Localy){
		if(Level.level%2 == 0) {
			i = new Random().nextInt(5)+5;
		}
		else {
			i = new Random().nextInt(5);
		}
		f_x = fp_x[i];
		f_y = fp_y[i];
		f_Localx = _f_Localx;
		f_Localy = _f_Localy;
		inity = _f_Localy;
		plimg = fp_imgs[i];
		//设置子弹的移动速度
		setBaseMove(Level.FPMoveSpeed);
		//设置垃圾的默认体力值
		this.blood = i+1 ;
		fullblood = i+1;
		ymove.start();
	}
	//工具包
	private Toolkit tool  = Toolkit.getDefaultToolkit();
	//以类为路径找相对路径(反射法)寻找背景
	public Image[] fp_imgs = {
		tool.getImage(Garbage.class.getResource("/images/r1_1.png")),
		tool.getImage(Garbage.class.getResource("/images/r2_1.png")),
		tool.getImage(Garbage.class.getResource("/images/r3_1.png")),
		tool.getImage(Garbage.class.getResource("/images/r4_1.png")),
		tool.getImage(Garbage.class.getResource("/images/r5_1.png")),
		
		tool.getImage(Garbage.class.getResource("/images/r1_2.png")),
		tool.getImage(Garbage.class.getResource("/images/r2_2.png")),
		tool.getImage(Garbage.class.getResource("/images/r3_2.png")),
		tool.getImage(Garbage.class.getResource("/images/r4_2.png")),
		tool.getImage(Garbage.class.getResource("/images/r5_2.png"))
	} ;
	public int []fp_x ={53,34,29,42,57,40,40,40,40,40};
	public int []fp_y ={51,49,17,15,30,13,27,19,27,19};
	/**
	 * 绘制垃圾
	 */
	public void paint(Graphics g){
		//绘制垃圾
		g.drawImage(plimg, f_Localx, f_Localy, null );
		//设置画笔颜色
		g.setColor(Color.red);
		//移动垃圾
		Move();
	}
	public int y_move = 0;
	public void setY_move(int yMove) {
		y_move = yMove;
	}
	public int inity = this.f_Localy;
	private static int counter = 0;
	private class ymovesleep extends Thread{

		@Override
		public void run() {
			while(true){
				try {
					ymovesleep.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++ counter;
				if(counter % 10 ==0){
					setY_move(3 - new Random().nextInt(7));
				}
			}
		}		
	}
	private ymovesleep ymove = new ymovesleep();
	public void Move(){
		f_Localx -= BaseMove;
		f_Localy -= y_move;
		if(f_Localy < (GameFrame.TitleWidth+f_y) ) {
			f_Localy = GameFrame.TitleWidth +f_y ;
		}
		if(f_Localy > GameFrame.height - 2*f_y) {
			f_Localy = GameFrame.height - 2*f_y;
		}
		if(f_Localx + f_x< 0){
			Plane_live = false;
		}
	}
}

