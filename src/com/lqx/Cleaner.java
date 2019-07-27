package com.lqx;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Cleaner {
	/**
	 * 清洁员长度
	 */
	protected int f_x = 60;
	/**
	 * 清洁员高度
	 */
	protected int f_y = 15; 
	/**
	 * 清洁员位置 x 坐标
	 */
	protected int f_Localx = GameFrame.width / 10;
	/**
	 * 清洁员位置 y 坐标
	 */
	protected boolean Plane_live = true;
	protected int f_Localy = GameFrame.height / 2;
	/**
	 * 一次移动距离
	 */
	protected int BaseMove = 2;
	protected int blood = 3;
	//工具包（获取缺省的工具包）
	protected Toolkit tool  = Toolkit.getDefaultToolkit();
	//以类为路径找相对路径(反射法)
	/**
	 * 清洁员的图像
	 */
	protected Image plimg = null;
	/**
	 * 子弹的图像
	 */
	protected Image zdimg = null;
	
	public int getF_Localx() {
		return f_Localx;
	}


	public void setF_Localx(int f_Localx) {
		this.f_Localx = f_Localx;
	}


	public int getF_Localy() {
		return f_Localy;
	}


	public void setF_Localy(int f_Localy) {
		this.f_Localy = f_Localy;
	}


	public int getBlood() {
		return blood;
	}


	public void cutBlood(int i) {
		blood -= i;
	}
	
	public void overBlood() {
		this.blood = 0;
	}

	public int getF_x() {
		return f_x;
	}


	public void setF_x(int f_x) {
		this.f_x = f_x;
	}


	public int getF_y() {
		return f_y;
	}


	public void setF_y(int f_y) {
		this.f_y = f_y;
	}


	public int getBaseMove() {
		return BaseMove;
	}


	public void setBaseMove(int baseMove) {
		BaseMove = baseMove;
	}


	public Image getZdimg() {
		return zdimg;
	}


	public void setZdimg(Image zdimg) {
		this.zdimg = zdimg;
	}
	
	public boolean isPlane_live() {
		return Plane_live;
	}


	public void setPlane_live(boolean planeLive) {
		Plane_live = planeLive;
	}



	public void paint(Graphics g){
		
	}

}
