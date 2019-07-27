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
	public static int width = 700;	//窗口宽度
	public static int height = 450;	//窗口高度
	public static int TitleWidth = 60;	//标题宽度，maybe
	public static int NoteWidth = 30;
	private static Toolkit tool  = Toolkit.getDefaultToolkit();
	private Image bufferImg;
	private Graphics bufferG;		//画笔
	private Image img;
	private Image bgimg;
	
	private int fraps=0;
	private int steps=0;
	
	private int planx=-256;
	private int wordx[]={-80,270,350,700};
	private int wordy[]={185,0,530,185};
	private String word[]={"Marine","  ","cleaner",""};
	
	public StartFrame(){
		setSize(width,height);		//设置窗口大小
		setTitle("Marine cleaner");	//设置标题
		//下面是得到窗口在屏幕中的位置（tool.getScreenSize().getWidth()得到屏幕的宽度的一半）
		int w_width = (int)tool.getScreenSize().getWidth()/2 - (width/2);	
		int w_height = (int)tool.getScreenSize().getHeight()/2 - (height/2);
		setLocation(w_width, w_height);		//设置窗口所在位置
		setResizable(false);		//设置此窗口能否被用户调整大小（就是限制窗口大小,没做比例优化）		
		//添加窗口监听（具体用处暂时不知道）
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0){
				System.exit(0);
			}
		});
		setVisible(true);			//设置控件的可见性

		/*
		 * public Image createImage(int width, int height)创建一幅用于双缓冲的、可在屏幕外绘制的图像。
		 * 
		 * 参数： width - 指定的宽度 height - 指定的高度 返回： 一幅屏幕外可绘制的图像，可用于双缓冲。如果组件是不可显示的，则返回值可能为
		 * null。如果 GraphicsEnvironment.isHeadless() 返回 true，则始终发生这种情况。
		 */
		bufferImg = this.createImage(width, height);	//得到背景图片的宽高
		bufferG = bufferImg.getGraphics();				//用画笔将图片画出来
		img=tool.getImage(StartGame.class.getResource("/images/01.gif"));
		bgimg=tool.getImage(StartGame.class.getResource("/images/play.png"));
		Thread th=new Thread(this);
		th.start();
	}
	//初始界面的绘制
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
