package com.lqx;

import java.awt.Graphics;
import java.awt.Image;

public class Feedback {
	//爆炸显示的坐标 x y
	private int epd_x;

	private int epd_y;

	//爆炸需要的图片数组
	private Image[] explode_imgs;

	private GameFrame gf;

	//爆炸的状态
	private boolean explode_live;

	//爆炸图片显示的顺序
	int index = 0;

	public Feedback(int x, int y, Image[] explode_imgs, GameFrame sg,
			boolean explode_live) {
		this.epd_x = x;
		this.epd_y = y;
		this.explode_imgs = explode_imgs;
		this.gf = sg;
		this.explode_live = explode_live;
	}

	/**
	 * 通过画笔画出爆炸图片
	 * @param g
	 */
	public void drawEpdImages(Graphics g) {
		//如果爆炸图片画至最后一张，则不需要显示爆炸了，修改爆炸状体和爆炸顺序
		if(index == explode_imgs.length) {
			index = 0;
			explode_live = false;
		}
		//如果爆炸完成，则移除爆炸
		if(!explode_live) {
			GameFrame.explode_list.remove(this);
			return;
		}
		
		//画爆炸图像
		g.drawImage(explode_imgs[index], epd_x, epd_y, gf);
		//爆炸图像顺序递增
		index++;
	}

}

