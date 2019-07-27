package com.lqx;

import java.awt.Graphics;
import java.awt.Image;

public class Feedback {
	//��ը��ʾ������ x y
	private int epd_x;

	private int epd_y;

	//��ը��Ҫ��ͼƬ����
	private Image[] explode_imgs;

	private GameFrame gf;

	//��ը��״̬
	private boolean explode_live;

	//��ըͼƬ��ʾ��˳��
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
	 * ͨ�����ʻ�����ըͼƬ
	 * @param g
	 */
	public void drawEpdImages(Graphics g) {
		//�����ըͼƬ�������һ�ţ�����Ҫ��ʾ��ը�ˣ��޸ı�ը״��ͱ�ը˳��
		if(index == explode_imgs.length) {
			index = 0;
			explode_live = false;
		}
		//�����ը��ɣ����Ƴ���ը
		if(!explode_live) {
			GameFrame.explode_list.remove(this);
			return;
		}
		
		//����ըͼ��
		g.drawImage(explode_imgs[index], epd_x, epd_y, gf);
		//��ըͼ��˳�����
		index++;
	}

}

