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

public class OptionFrame extends Frame implements Runnable {
	private static final long serialVersionUID = 1L;
	public static int width = 700;
	public static int height = 450;
	public static int TitleWidth = 60;
	public static int NoteWidth = 30;
	private Image img;
	private Image bufferImg;
	private Graphics bufferG;
	private int choose=0;
	private String displayName[]={"普攻","技能","大招"};
	private boolean keyPressed=false;
	private boolean getKey=false;
	
	private static Toolkit tool  = Toolkit.getDefaultToolkit();
	public OptionFrame(final TitleFrame titleFrame) {
		img=tool.getImage(StartGame.class.getResource("/images/option.jpg"));
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
				if(getKey==false){
					if(key.getKeyCode()==KeyEvent.VK_UP && keyPressed==false){
						choose--;
						if(choose==-1)
							choose=3;
						keyPressed=true;
					}
					if(key.getKeyCode()==KeyEvent.VK_DOWN && keyPressed==false){
						choose++;
						if(choose==4)
							choose=0;
						keyPressed=true;
					}
					if(key.getKeyCode()==KeyEvent.VK_ENTER){
						switch(choose){
							case 0:
							case 1:
							case 2:
								getKey=true;
								break;
							case 3:
								KeyConfig.getKeyConfig().saveProperty();
								setVisible(false);
								setEnabled(false);
								titleFrame.setVisible(true);
								titleFrame.setEnabled(true);
								dispose();
								break;
						}
					}
				}
				else{
					if(getKey){
						if(key.getKeyCode()==KeyEvent.VK_R)
							return;
						KeyConfig.getKeyConfig().setProperty(choose, key.getKeyCode());
						getKey=false;							
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
		bufferG.drawImage(img, 0, 0, null);
		bufferG.setColor(Color.black);

		bufferG.setFont(new Font("宋体",Font.PLAIN,15));
		bufferG.drawString("设置说明：选择对应项目按回车后再按下想要设置的键位即可。（不可以为R键）", 150, 110);
		bufferG.setFont(new Font("宋体",Font.PLAIN,20));
		bufferG.drawString("按键设置", 300, 80);
		for(int i=0;i<3;++i){
			if(choose==i)
				bufferG.setColor(Color.red);
			bufferG.drawString(displayName[i], 270, 150+i*20);
			bufferG.drawString(KeyEvent.getKeyText(KeyConfig.getKeyConfig().getKeyCode()[i]),310+60,150+i*20);
			bufferG.setColor(Color.black);
		}
		if(choose==3)
			bufferG.setColor(Color.red);
		bufferG.drawString("退出", 320, 240);
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
