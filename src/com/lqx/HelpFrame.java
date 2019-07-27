package com.lqx;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelpFrame extends Frame {
	private static final long serialVersionUID = 1L;
	public static int width = 700;
	public static int height = 450;
	public static int TitleWidth = 60;
	public static int NoteWidth = 30;
	private TitleFrame titleFrame;
	
	private static Toolkit tool  = Toolkit.getDefaultToolkit();
	private Image img;
	
	public void paint(Graphics g){
		g.drawImage(img, 0, 0, this);
	}
	public HelpFrame(TitleFrame titleFrame){
		this.titleFrame=titleFrame;
		img=tool.getImage(StartGame.class.getResource("/images/help.jpg"));
		showWindow();
	}
	
	public void showWindow(){
		setSize(width,height);
		setTitle("∫£—Û«ÂΩ‡‘±");
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
	}
}
