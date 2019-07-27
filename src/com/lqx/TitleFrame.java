package com.lqx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.zip.DeflaterInputStream;

public class TitleFrame extends Frame implements Runnable{
	private static final long serialVersionUID = 1L;
	public static int width = 700;
	public static int height = 450;
	public static int TitleWidth = 60;
	public static int NoteWidth = 30;
	private Image img;
	private Image buttonImg;
	private Image bufferImg;
	private Graphics bufferG;
	private TitleFrame titleFrame=this;
	
	private static int buttonWidth=96;
	private static int buttonHeight=32;
	
	private int choose=0;
	private boolean keyPressed=false;
	
	private static Toolkit tool  = Toolkit.getDefaultToolkit();
	public TitleFrame(){
		img=tool.getImage(StartGame.class.getResource("/images/temp.jpg"));
		buttonImg=tool.getImage(StartGame.class.getResource("/images/button.png"));
		setSize(width,height);
		setTitle("Marine cleaner");
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
				if(key.getKeyCode()==KeyEvent.VK_LEFT && keyPressed==false){
					choose--;
					if(choose==-1)
						choose=4;
					keyPressed=true;
				}
				if(key.getKeyCode()==KeyEvent.VK_RIGHT && keyPressed==false){
					choose++;
					if(choose==5)
						choose=0;
					keyPressed=true;
				}
				if(key.getKeyCode()==KeyEvent.VK_ENTER){
					switch(choose){
						case 0:
							new GameFrame(titleFrame);
							titleFrame.setVisible(false);
							titleFrame.setEnabled(false);
							break;
						case 1:
							new RankFrame(titleFrame);
							titleFrame.setVisible(false);
							titleFrame.setEnabled(false);
							break;
						case 2:
							new OptionFrame(titleFrame);
							titleFrame.setVisible(false);
							titleFrame.setEnabled(false);
							break;
						case 3:
							new HelpFrame(titleFrame);
							titleFrame.setVisible(false);
							titleFrame.setEnabled(false);
							break;
						case 4:
							System.exit(0);
							break;
					}
				}
			}
			public void keyReleased(KeyEvent key){
				keyPressed=false;
			}
		});
		setVisible(true);
		bufferImg = this.createImage(width, height);
		bufferG = bufferImg.getGraphics();
		Thread th=new Thread(this);
		th.start();
	}
	public void update(Graphics g){
		bufferG.drawImage(img, 0, 0, width, height,null);
		bufferG.setColor(Color.YELLOW);
		bufferG.setFont(new Font("Calibri",Font.PLAIN,80));
		bufferG.drawString("Marine cleaner", width/2-250, height/2-40);
		for(int i=0;i<5;++i){
			bufferG.drawImage(buttonImg, i*112+100, 315, i*112+buttonWidth+100, 315+buttonHeight, 0, i*buttonHeight, buttonWidth, (i+1)*buttonHeight, null);
		}
		bufferG.drawImage(buttonImg, choose*112+100, 315, choose*112+buttonWidth+100, 315+buttonHeight, 0, (choose+5)*buttonHeight, buttonWidth, (choose+6)*buttonHeight, null);
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
