package com.lqx;

import java.awt.Image;
import java.awt.Toolkit;

public class Bullet {
	/**
	 * 子弹位置 x 坐标
	 */
	protected int zd_x = 0 ;
	/**
	 * 子弹位置 y 坐标
	 */
	protected int zd_y = 0;

	protected int zd_width;
	protected int zd_height;
	/**
	 * 定位子弹图片
	 */
	protected  Toolkit tool  = Toolkit.getDefaultToolkit();
	/**
	 * 子弹图片
	 */
	protected Image zd = tool.getImage(MyBullet.class.getResource("/images/missile3.PNG"));
	//子弹的生命
	protected boolean bullet_live = true;
	/**
	 * 子弹飞行速度
	 */
	protected int Zdspeed = 5;
	
	public int getZd_width() {
		return zd_width;
	}
	public void setZd_width(int zdWidth) {
		zd_width = zdWidth;
	}
	public int getZd_height() {
		return zd_height;
	}
	public void setZd_height(int zdHeight) {
		zd_height = zdHeight;
	}
	public int getZdspeed() {
		return Zdspeed;
	}
	public void setZdspeed(int zdspeed) {
		Zdspeed = zdspeed;
	}
	public boolean isBullet_live() {
		return bullet_live;
	}
	public void setBullet_live(boolean bulletLive) {
		bullet_live = bulletLive;
	}
	public Image getZd() {
		return zd;
	}

	public void setZd(Image zd) {
		this.zd = zd;
	}

	public int getZd_x() {
		return zd_x;
	}

	public void setZd_x(int zd_x) {
		this.zd_x = zd_x;
	}

	public int getZd_y() {
		return zd_y;
	}

	public void setZd_y(int zd_y) {
		this.zd_y = zd_y;
	}

}
