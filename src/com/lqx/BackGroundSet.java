/**
 * com.lqx.BackGroundSet.java
 */
package com.lqx;
/**
 * ������صİ�
 */
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * @data 2011-5-13
 * @author ��ȫ��
 * @���������ࣨ�������Ʊ������ƶ���������Ϸ������ʾgame over��
 */
public class BackGroundSet {
	/**
	 * �������
	 */
	GameFrame game;
	/**
	 * ��д���캯��
	 * @param game ��Ϸ����ʵ��
	 */
	public BackGroundSet(GameFrame game){
		this.game = game;
	}

	/**
	 * ����ͼƬ��������
	 */
	private Image BackGround1;//����ͼƬ1
	private Image BackGround2;//����ͼƬ2
	private int BackGround1_x = 0;//����ͼƬ1��x�����ʼ��
	private int BackGround2_x = GameFrame.width;//����ͼƬ2��x�����ʼ��
	private int BackGroundSpeed = 2;//�����ƶ����ٶ�
	/**
	 * ���ñ���ͼƬ�����ݹؿ��Ĳ�ͬ�л�����ͼƬ��
	 * @param backGround
	 */
	public void setBackGround() {
		BackGround2 = BackGround1  = back_imgs[Level.level-1];
	}
	/**
	 * ���ط������������ã�
	 * @param backGround
	 */
	public void setBackGround(Image backGround) {
		BackGround2 = BackGround1  = backGround;
	}
	//���߰�
	private Toolkit tool  = Toolkit.getDefaultToolkit();
	//����Ϊ·�������·��(���䷨)Ѱ�ұ���
	/**
	 * ����ͼƬ�����飬���ڴ洢��ͬ�ؿ��ı���ͼƬ
	 */
	public Image[] back_imgs = {
		tool.getImage(StartGame.class.getResource("/images/background1.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background2.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background9.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background4.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background5.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background6.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background7.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background8.jpg")),
		tool.getImage(StartGame.class.getResource("/images/background3.jpg"))
	} ;

	/**
	 * �����ƶ�
	 */
	public void PictureMove(){
		//ÿ���ƶ�һ�����룬��СΪ�ƶ��ٶ�
		BackGround1_x -= BackGroundSpeed;
		//���һ�ű����ƶ�����������ԭ����һ��ʼλ��
		if(BackGround1_x == -GameFrame.width){
			BackGround1_x = GameFrame.width;
		}
		/*
		 *����ͼƬͬʱ�ƶ�����ʽ��ͼƬ1 ����ʽһ�� 
		 */
		BackGround2_x -= BackGroundSpeed;
		if(BackGround2_x == -GameFrame.width){
			BackGround2_x = GameFrame.width;
		}
	}
	/**
	 * ���Ʊ���
	 * @param g ��������
	 */
	public void paint(Graphics g){
		//���ñ��������ݹؿ���ͬ���ı䣩
		setBackGround();
		//�ƶ�����
		PictureMove();	
		//���Ʊ�����������С�趨Ϊ������ӻ���С��
		g.drawImage(BackGround1, BackGround1_x, GameFrame.TitleWidth,GameFrame.width,GameFrame.height - GameFrame.TitleWidth, null );
		g.drawImage(BackGround2, BackGround2_x, GameFrame.TitleWidth,GameFrame.width,GameFrame.height - GameFrame.TitleWidth, null );
		//�����Ϸ�����������ʾ��
		if(game.myMyPlane.isPlane_live() == false){
			Font ft=g.getFont();
			g.setFont(new Font("Dialog",Font.PLAIN,30));
			g.drawString("Game Over!!!", GameFrame.width/2-80, GameFrame.height/2);
			g.setFont(new Font("Dialog",Font.PLAIN,20));
			g.drawString("��R����������", GameFrame.width/2-60, GameFrame.height/2+90);
			g.drawString("��T�����¿�ʼ", GameFrame.width/2-60, GameFrame.height/2+120);
			g.setFont(ft);
		}
	}

}
