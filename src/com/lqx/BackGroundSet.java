/**
 * com.lqx.BackGroundSet.java
 */
package com.lqx;
/**
 * 导入相关的包
 */
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
/**
 * @data 2011-5-13
 * @author 兰全祥
 * @背景设置类（包括绘制背景、移动背景，游戏结束显示game over）
 */
public class BackGroundSet {
	/**
	 * 窗体对象
	 */
	GameFrame game;
	/**
	 * 重写构造函数
	 * @param game 游戏窗体实例
	 */
	public BackGroundSet(GameFrame game){
		this.game = game;
	}

	/**
	 * 背景图片参数设置
	 */
	private Image BackGround1;//背景图片1
	private Image BackGround2;//背景图片2
	private int BackGround1_x = 0;//背景图片1的x坐标初始化
	private int BackGround2_x = GameFrame.width;//背景图片2的x坐标初始化
	private int BackGroundSpeed = 2;//背景移动的速度
	/**
	 * 设置背景图片（根据关卡的不同切换背景图片）
	 * @param backGround
	 */
	public void setBackGround() {
		BackGround2 = BackGround1  = back_imgs[Level.level-1];
	}
	/**
	 * 重载方法（背景设置）
	 * @param backGround
	 */
	public void setBackGround(Image backGround) {
		BackGround2 = BackGround1  = backGround;
	}
	//工具包
	private Toolkit tool  = Toolkit.getDefaultToolkit();
	//以类为路径找相对路径(反射法)寻找背景
	/**
	 * 背景图片的数组，用于存储不同关卡的背景图片
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
	 * 背景移动
	 */
	public void PictureMove(){
		//每次移动一定距离，大小为移动速度
		BackGround1_x -= BackGroundSpeed;
		//如果一张背景移动到窗体外则还原至下一起始位置
		if(BackGround1_x == -GameFrame.width){
			BackGround1_x = GameFrame.width;
		}
		/*
		 *两张图片同时移动，方式和图片1 处理方式一样 
		 */
		BackGround2_x -= BackGroundSpeed;
		if(BackGround2_x == -GameFrame.width){
			BackGround2_x = GameFrame.width;
		}
	}
	/**
	 * 绘制背景
	 * @param g 背景画笔
	 */
	public void paint(Graphics g){
		//设置背景（根据关卡不同而改变）
		setBackGround();
		//移动背景
		PictureMove();	
		//绘制背景（背景大小设定为窗体可视化大小）
		g.drawImage(BackGround1, BackGround1_x, GameFrame.TitleWidth,GameFrame.width,GameFrame.height - GameFrame.TitleWidth, null );
		g.drawImage(BackGround2, BackGround2_x, GameFrame.TitleWidth,GameFrame.width,GameFrame.height - GameFrame.TitleWidth, null );
		//如果游戏结束，则加提示语
		if(game.myMyPlane.isPlane_live() == false){
			Font ft=g.getFont();
			g.setFont(new Font("Dialog",Font.PLAIN,30));
			g.drawString("Game Over!!!", GameFrame.width/2-80, GameFrame.height/2);
			g.setFont(new Font("Dialog",Font.PLAIN,20));
			g.drawString("按R键输入姓名", GameFrame.width/2-60, GameFrame.height/2+90);
			g.drawString("按T键重新开始", GameFrame.width/2-60, GameFrame.height/2+120);
			g.setFont(ft);
		}
	}

}
