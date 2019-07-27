package com.lqx;

import java.awt.Graphics;
import java.awt.Image;

public class FightBullet extends Bullet {
	/**
	 * »æÖÆ×Óµ¯µÄÍ¼Ïñ
	 */
	public FightBullet(){
		Zdspeed = 5;
	}	
	public FightBullet(int zd_x, int zd_y, Image zd){
		this.zd_x = zd_x;
		this.zd_y = zd_y;
		this.zd = zd;
		Zdspeed = 5;
	}
	public FightBullet(int zd_x, int zd_y,int zd_width, int zd_height, Image zd){
		this.zd_x = zd_x;
		this.zd_y = zd_y;
		this.zd_width = zd_width;
		this.zd_height = zd_height;
		this.zd = zd;
		Zdspeed = 5;
	}
	public void paint(Graphics g){
			g.drawImage(zd, zd_x, zd_y, null );
			Move();
	}
	public void Move()
	{
		if(zd_x + zd_width >= 0){
			zd_x -= Zdspeed;
		}
		else{
			bullet_live = false;
		}
	}

}
