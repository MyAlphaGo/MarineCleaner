package com.lqx;

import java.awt.Graphics;

public class BigRecruit extends Bullet{
	public static int PB_width = 150;
	public BigRecruit(int zd_x,int zd_y){		
		this.zd_width = 150;
		this.zd_height = 154;
		this.zd_x = zd_x;
		this.zd_y = zd_y-zd_height/2;
		this.zd  = tool.getImage(MyCleaner.class.getResource("/images/bm5.gif"));
		Zdspeed = 2;
	}
	public void paint(Graphics g){
		g.drawImage(zd, zd_x, zd_y, null );
		Move();
	}
	public void Move()
	{
		if(zd_x <= GameFrame.width){
			zd_x += 1;
		}
		else{
			bullet_live = false;
		}
	}

}
