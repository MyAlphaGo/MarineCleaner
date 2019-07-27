package com.lqx;

import java.awt.Graphics;
import java.awt.Image;

/**
 * 玩家子弹类
 * 继承子弹类，增加子弹移动方法
 */
public class MyBullet extends Bullet {
	/**
	 * 构造函数
	 */
	public MyBullet(){		
	}	
	/**
	 * 重写构造函数
	 * @param zd_x
	 * @param zd_y
	 * @param zd
	 */
	public MyBullet(int zd_x, int zd_y, Image zd){
		this.zd_x = zd_x;
		this.zd_y = zd_y;
		this.zd = zd;
	}
	public MyBullet(int zd_x, int zd_y, Image zd,int x,int y){
		this.zd_x = zd_x;
		this.zd_y = zd_y;
		this.zd = zd;
		this.zd_width = x;
		this.zd_height = y;
	}
	/**
	 * 绘制子弹的图像
	 */
	public void paint(Graphics g){
			g.drawImage(zd,zd_x, zd_y, null );
			Move();
	}
	public void Move()
	{
		if(zd_x <= GameFrame.width){
			zd_x += Zdspeed;	
		}
		else{
			bullet_live = false;
		}
	}
}