package com.lqx;

import java.awt.Graphics;
import java.awt.Image;

/**
 * ����ӵ���
 * �̳��ӵ��࣬�����ӵ��ƶ�����
 */
public class MyBullet extends Bullet {
	/**
	 * ���캯��
	 */
	public MyBullet(){		
	}	
	/**
	 * ��д���캯��
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
	 * �����ӵ���ͼ��
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