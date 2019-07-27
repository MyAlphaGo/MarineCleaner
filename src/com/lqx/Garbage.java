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
 * ������̳����Ա��
 * 
 *
 */
public class Garbage extends Cleaner{
	/**
	 * ����һ�������ӵ�����
	 */
	public List<FightBullet> bullet_list = new ArrayList<FightBullet>();
	/**
	 * ʵ����һ�������������������������Ա�����ƶ�����
	 */
	Random ram = new Random();
	/**
	 * ��д���캯��
	 */
	public Garbage(){
		plimg = tool.getImage(Garbage.class.getResource("/images/r1.png"));
	}
	/**
	 * ��д���캯��
	 * @param _f_x ���Ա�Ŀ��
	 * @param _f_y ���Ա�ĸ߶�
	 * @param _f_Localx ���Ա��x����
	 * @param _f_Localy ���Ա�� y����
	 * @param _img �ӵ���ͼƬ
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
		//�����ӵ����ƶ��ٶ�
		setBaseMove(Level.FPMoveSpeed);
		//����������Ĭ������ֵ
		this.blood = i+1 ;
		fullblood = i+1;
		ymove.start();
	}
	//���߰�
	private Toolkit tool  = Toolkit.getDefaultToolkit();
	//����Ϊ·�������·��(���䷨)Ѱ�ұ���
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
	 * ��������
	 */
	public void paint(Graphics g){
		//��������
		g.drawImage(plimg, f_Localx, f_Localy, null );
		//���û�����ɫ
		g.setColor(Color.red);
		//�ƶ�����
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

