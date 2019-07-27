package com.lqx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartFrame extends Frame implements Runnable {
	private static final long serialVersionUID = 1L;
	public static int width = 700;	//���ڿ��
	public static int height = 450;	//���ڸ߶�
	public static int TitleWidth = 60;	//�����ȣ�maybe
	public static int NoteWidth = 30;
	private static Toolkit tool  = Toolkit.getDefaultToolkit();
	private Image bufferImg;
	private Graphics bufferG;		//����
	private Image img;
	private Image bgimg;
	
	private int fraps=0;
	private int steps=0;
	
	private int planx=-256;
	private int wordx[]={-80,270,350,700};
	private int wordy[]={185,0,530,185};
	private String word[]={"Marine","  ","cleaner",""};
	
	public StartFrame(){
		setSize(width,height);		//���ô��ڴ�С
		setTitle("Marine cleaner");	//���ñ���
		//�����ǵõ���������Ļ�е�λ�ã�tool.getScreenSize().getWidth()�õ���Ļ�Ŀ�ȵ�һ�룩
		int w_width = (int)tool.getScreenSize().getWidth()/2 - (width/2);	
		int w_height = (int)tool.getScreenSize().getHeight()/2 - (height/2);
		setLocation(w_width, w_height);		//���ô�������λ��
		setResizable(false);		//���ô˴����ܷ��û�������С���������ƴ��ڴ�С,û�������Ż���		
		//��Ӵ��ڼ����������ô���ʱ��֪����
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0){
				System.exit(0);
			}
		});
		setVisible(true);			//���ÿؼ��Ŀɼ���

		/*
		 * public Image createImage(int width, int height)����һ������˫����ġ�������Ļ����Ƶ�ͼ��
		 * 
		 * ������ width - ָ���Ŀ�� height - ָ���ĸ߶� ���أ� һ����Ļ��ɻ��Ƶ�ͼ�񣬿�����˫���塣�������ǲ�����ʾ�ģ��򷵻�ֵ����Ϊ
		 * null����� GraphicsEnvironment.isHeadless() ���� true����ʼ�շ������������
		 */
		bufferImg = this.createImage(width, height);	//�õ�����ͼƬ�Ŀ��
		bufferG = bufferImg.getGraphics();				//�û��ʽ�ͼƬ������
		img=tool.getImage(StartGame.class.getResource("/images/01.gif"));
		bgimg=tool.getImage(StartGame.class.getResource("/images/play.png"));
		Thread th=new Thread(this);
		th.start();
	}
	//��ʼ����Ļ���
	public void update(Graphics g){
		bufferG.setColor(Color.black);
		bufferG.fillRect(0, 0, width, height);
		bufferG.drawImage(bgimg, 0, 0, null);
		bufferG.setColor(Color.blue);
		bufferG.setFont(new Font("Calibri",Font.PLAIN,50));
		for(int i=0;i<4;++i){
			bufferG.drawString(word[i], wordx[i], wordy[i]);
		}
		bufferG.drawImage(img, planx, 180, null);
		paint(g); 
	}
	public void paint(Graphics g){
		g.drawImage(bufferImg, 0, 0, this);
	}
	@Override
	public void run() {
		int temp = 16;
		while(true){
			try {
				Thread.sleep(temp);
				fraps++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(steps==0){
				if(wordx[0]<190)
					wordx[0]+=2;
				else
					wordx[0]=190;
				if(wordy[1]<185)
					wordy[1]+=1;
				else
					wordy[1]=185;
				if(wordy[2]>185)
					wordy[2]-=2;
				else
					wordy[2]=185;
				if(wordx[3]>430)
					wordx[3]-=2;
				else
					wordx[3]=430;
				if(fraps==200){
					temp = 10;
					++steps;
					fraps=0;
				}
			}
			if(steps==1){
				planx+=5;
				if(fraps==300){
					++steps;
					fraps=0;
				}
			}
			if(steps==2){
				dispose();
				new TitleFrame();
				break;
			}
			this.repaint();
		}
	}

}
