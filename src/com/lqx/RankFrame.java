package com.lqx;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

public class RankFrame extends Frame implements Runnable {
	private static final long serialVersionUID = 1L;
	public static int width = 700;
	public static int height = 450;
	public static int TitleWidth = 60;
	public static int NoteWidth = 30;
	private Image img;
	private Image bufferImg;
	private Graphics bufferG;
	private LinkedList<String> rank;
	private static Toolkit tool  = Toolkit.getDefaultToolkit();

	public RankFrame(final TitleFrame titleFrame) {
		img=tool.getImage(StartGame.class.getResource("/images/rank.jpg"));
		setSize(width,height);
		setTitle("海洋清洁员");
		int w_width = (int)tool.getScreenSize().getWidth()/2 - (width/2);
		int w_height = (int)tool.getScreenSize().getHeight()/2 - (height/2);
		setLocation(w_width, w_height);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0){
				System.exit(0);
			}
		});
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent key){
				setVisible(false);
				setEnabled(false);
				titleFrame.setVisible(true);
				titleFrame.setEnabled(true);
				dispose();
			}
		});
		setVisible(true);
		bufferImg = this.createImage(width, height);
		bufferG = bufferImg.getGraphics();
		rank=Scores.getScores().getPlayerName(10);
		Thread th=new Thread(this);
		th.start();
	}
	public void update(Graphics g){
		bufferG.drawImage(img, 0, 0, null);
		bufferG.setFont(new Font("黑体",Font.PLAIN,50));
		bufferG.drawString("排行榜", 280, 100);
		bufferG.setFont(new Font("宋体",Font.PLAIN,15));
		bufferG.drawString("姓名", 310, 130);
		bufferG.drawString("得分", 400, 130);
		for(int i=0;i<rank.size()&&i<20;i+=2)
		{
			bufferG.drawString(rank.get(i), 285, 150+10*i);
			bufferG.drawString(rank.get(i+1), 400, 150+10*i);
		}
		bufferG.drawString("按任意键返回主界面", 285, 400);
		paint(g);
	}
	public void paint(Graphics g){
		g.drawImage(bufferImg, 0, 0, this);
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
}
