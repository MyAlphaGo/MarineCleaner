/**
 * com.lqx.GameFrame.java
 */
package com.lqx;
/**
 * 需要添加的 java 类
 */

import java.awt.Color; 
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;



/**
 * 游戏窗口类继承窗口类
 */
public class GameFrame extends Frame {
	/**
	 * 设置一个缺省的 serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 窗口宽度
	 */
	public static int width = 700;
	/**
	 * 窗口高度
	 */
	public static int height = 450;
	/**
	 * 题目栏高度
	 */
	public static int TitleWidth = 60;
	/**
	 * 提示栏的高度
	 */
	public static int NoteWidth = 30;
	
	/**
	 * 实例化一个清洁员对象
	 */
	MyCleaner myMyPlane = new MyCleaner();
	/**
	 * 实例化背景设置对象
	 */
	BackGroundSet Bg = new BackGroundSet(this);
	/**
	 * 垃圾集合
	 */
	public List<Garbage> fightPlane_list = new ArrayList<Garbage>();
	/**
	 * 爆炸图片数组
	 */
	public static List<Feedback> explode_list = new ArrayList<Feedback>();
	/**
	 * 临时子弹集合，用于保存捕获垃圾或者降解垃圾之前发出的子弹
	 */
	List<FightBullet> tempbullet_list = new ArrayList<FightBullet>();
	/**
	 * 实例化一个静态工具包
	 */
	private static Toolkit tool  = Toolkit.getDefaultToolkit();
	//获取爆炸需要的4个图片
	public static Image[] explode_imgs = {
		tool.getImage(StartGame.class.getResource("/images/huicheng0.png")),
		tool.getImage(StartGame.class.getResource("/images/huicheng1.png")),
		tool.getImage(StartGame.class.getResource("/images/huicheng2.png")),
		tool.getImage(StartGame.class.getResource("/images/huicheng3.png"))
	} ;
	//媒体跟踪器
	public static MediaTracker mt = null; 
	
	/**
	 * 声明一个背景缓冲图片
	 */
	private Image bg_img = null;
	/**
	 * 随时更新背景图片，利用双缓冲消除闪屏
	 */
	public void update(Graphics g){
		//如果缓冲图片为空，就可以创建这个图片
		if (bg_img == null){
			//创建缓冲图片，大小与窗体大小一样
			bg_img = this.createImage(width, height);
		}
		//得到缓冲图片的画笔
		Graphics bgGp = bg_img.getGraphics();
		
		//设置缓冲图片的背颜色
		bgGp.setColor(Color.white);
		//在缓冲图片上划一个实心的矩形，高度和宽度与窗体大小一致
		bgGp.fillRect(0, 0, width, height);
		
		//在缓冲图片上面画图
		paint(bgGp);
		
		//将缓冲图片画在主窗体上
		g.drawImage(bg_img, 0, 0, this);
	}
	/**
	 * 飞弹数量标签
	 */
	Label labPB = new Label("大招:        ");
	Label labMB = new Label("技能:        ");
	/**
	 * 清洁员关卡提示标签
	 */
	Label lablevel = new Label("关卡:  ");
	/**
	 * 消灭敌人数量提示标签
	 */
	Label labkill = new Label("垃圾:   ");
	/**
	 * 玩家体力值提示标签
	 */
	Label labblood = new Label("血量:  ");
	/**
	 * BOSS体力值提示标签（有BOSS时提示，没有时显示一句话）
	 */
	Label Bossblood = new Label("Welcome!Danger...");
	
	private TitleFrame titleFrame;
	public static boolean gameover=false;
	public GameFrame(TitleFrame titleFrame){
		this.titleFrame=titleFrame;
		showWindow();
	}
	
	//显示窗体
	public void showWindow(){
		/**
		 * 背景音乐
		 */
		Music.backMusic.loop();
		//循环播放清洁员潜水的声音
		Music.bosscopter.loop();
		//设置名称
		setTitle("海洋清洁员");
		//设置窗体大小
		setSize(width, height);
		//定位窗体位置（定位于屏幕中心）
		int w_width = (int)tool.getScreenSize().getWidth()/2 - (width/2);
		int w_height = (int)tool.getScreenSize().getHeight()/2 - (height/2);
		setLocation(w_width, w_height);
		//设置此窗体是否可由用户调整大小
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			//匿名内部类，关闭窗口的事件响应
			public void windowClosing(WindowEvent arg0){
				// 退出系统
				System.exit(0);
			}
		});
		/**
		 * 添加键盘事件监听
		 */
		addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			/**
			 * 重写键盘按下事件
			 */
			public void keyPressed(KeyEvent key) {
				//调用玩家清洁员的清洁员控制方法
				myMyPlane.ControlPressed(key);
				/**
				 * 自定义按键C，清除所有垃圾
				 */
				if(key.getKeyCode() == KeyEvent.VK_C){
					fightPlane_list.clear();
					if(myMyPlane.isPlane_live()){
						Bg.setBackGround(tool.getImage(GameFrame.class.getResource("/images/51.gif")));
					}
				}
				/**
				 * 自定义按键B，恢复体力值
				 */
				if(key.getKeyCode() == KeyEvent.VK_B ){
					myMyPlane.setBlood(100);
					myMyPlane.setFULLBLOOD(100);
				}
				/**
				 * 自定义按键U，直接晋级
				 */
				if(key.getKeyCode() == KeyEvent.VK_U ){
					if(Level.level < 9){
						++ Level.level;
					}
					level.updateLevel();
					FPThread.resume();
				}
				/**
				 * 自定义按键X，超多技能和大招
				 */
				if(key.getKeyCode() == KeyEvent.VK_X ){
					MyCleaner.expsum = 1000;
					MyCleaner.PPbm = 100;
				}
				
				if(key.getKeyCode()==KeyEvent.VK_R){
					if(myMyPlane.isPlane_live()==false){
						Scores.getScores().inputName(Level.KillFP);
						new RankFrame(titleFrame);
						FPThread.suspend();
						Music.backMusic.stop();
						Music.bosscopter.stop();
						Music.enemyfire.stop();
						setVisible(false);
						setEnabled(false);
						dispose();
					}
				}
				if(key.getKeyCode()==KeyEvent.VK_T){
					if(myMyPlane.isPlane_live()==false){
						setVisible(false);
						dispose();
						new GameFrame(titleFrame);
						titleFrame.setVisible(false);
						titleFrame.setEnabled(false);
					}
				}
			}
		});
		/**
		 * 添加键盘监听，释放按钮时相应
		 */
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent key) {
				//调用玩家清洁员实例的控制方法进行响应
				myMyPlane.ControlReleased(key);
			}
		});

		//添加垃圾线程
		
		//启动线程
		FPThread.start();
		/**
		 * 调用线程重绘
		 */
		PaintThread PThread = new PaintThread();
		//启动线程
		PThread.start();
		
		//创建一个媒体跟踪器
		mt = new MediaTracker(this);
		//把爆炸的图片加载到媒体跟踪器里面
		mt.addImage(explode_imgs[0], 0);
		mt.addImage(explode_imgs[1], 0);
		mt.addImage(explode_imgs[2], 0);
		mt.addImage(explode_imgs[3], 0);
		
		try {
			//开始加载媒体跟踪器里面的图片
			mt.waitForID(0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}		
		//按照流布局排列所有标签，右对齐
		setLayout(new FlowLayout(FlowLayout.RIGHT));	
		//添加所有标签
		add(labPB);
		add(labMB);
		add(lablevel);
		add(labkill);
		add(labblood);
		add(Bossblood);
		/**
		 * 设置窗体可见
		 */
		setVisible(true);
	}

	/**
	 * 添加垃圾的共有成员方法
	 */
	public void addFightPlane(){
		/**
		 * 实例话一个随机数
		 */
		Random r = new Random();
		/**
		 * 产生一个随机数，范围时窗体高度之内，用于随机生成垃圾的位置
		 */
		int x = r.nextInt(height - 20 - TitleWidth)+TitleWidth;
		//实例化一个垃圾对象
		Garbage pl = new Garbage(width,x);
		//pl.openFire();
		/**
		 * 将垃圾添加到垃圾集合里面
		 */
		fightPlane_list.add(pl);
	}
	/**
	 * 重绘线程
	 */
	private class PaintThread extends Thread{

		@Override
		/**
		 * 重写 run 方法重绘
		 */
		public void run() {
			while(true){
				try {
					//线程暂停 30 ms
					PaintThread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//重绘
				repaint();
			}
		}
		
	}

	/**
	 * 内部类
	 * 自动产生垃圾线程
	 *
	 */
	private class FPlaneThread extends Thread{
		@SuppressWarnings("deprecation")
		public void run() {
			while(true){
				addFightPlane();	
				try {
					//线程暂停，时间根据等级不同而改变
					FPlaneThread.sleep(Level.FPCreateSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/**
				 * 如果杀敌数到达关卡设定值，则将所有已经产生的垃圾全部销毁
				 * 将自动产生垃圾的进程挂起
				 * 等待打败BOSS后唤醒
				 */
				if(Level.KillFP == Level.lexelup[Level.level-1]-1){
					//清除所有垃圾
					fightPlane_list.clear();
					//挂起现在自动产生垃圾线程
					FPThread.suspend();
				}
			}
		}
	}
	/**
	 * 实例化的一个垃圾产生线程
	 */
	FPlaneThread FPThread = new FPlaneThread();
	/**
	 * 实例化的一个关卡指标对象
	 */
	private Level level = new Level();
	/**
	 * 实例化的一个boss对象
	 */
	Boss boss = new Boss();
	//定一个玩家图像，用于显示在界面之上
	private Image people = tool.getImage(MyCleaner.class.getResource("/images/p.png"));
	@SuppressWarnings("deprecation")
	@Override
	//重写paint方法
	public void paint(Graphics g) {
		//在指定位置画出玩家的头像
		g.drawImage(people, 10, GameFrame.TitleWidth - GameFrame.NoteWidth,30,30 , null );
		//更新玩家的收集数量标签
		labkill.setText("垃圾:" +  Level.KillFP);
		//更新玩家的游戏关卡标签
		lablevel.setText("关卡:" +  Level.level);
		//更新玩家的体力值标签
		labblood.setText("血量:" + myMyPlane.getBlood());    
		if(myMyPlane.isPlane_live()==false){
			labblood.setText("血量: 0");
		}		
		//更新玩家拥有技能和大招数量
		labMB.setText("技能:" + MyCleaner.expsum);
		labPB.setText("大招:" + MyCleaner.PPbm);
		//绘制背景图片
		Bg.paint(g);
		//绘制自己的清洁员
		myMyPlane.paint(g);
		/*
		 * 如果杀敌数达到关卡设置数量，清除所有垃圾 
		 * 显示BOSS
		 */
		if(Level.KillFP == Level.lexelup[Level.level-1]-1){
			/**
			 * 初始化boss
			 */
			boss.setIsAddBullet(true);
			//清空所有垃圾
			fightPlane_list.clear();
			//添加BOSS体力值
			Bossblood.setText("BOSS体力:" + boss.getBlood());
			//绘制BOSS
			boss.paint(g);
			//对清洁员和BOSS进行检测
			CollideExamine coexPP = new CollideExamine();
			//设置检测参数
			CollideExamine.mp = myMyPlane;
			CollideExamine.fp = boss;
			
			//调用相应的检测方法
			coexPP.CollidedPBoss();
			for(int k =0; k < myMyPlane.PB_list.size();++k){
				CollideExamine co = new CollideExamine();
				CollideExamine.mb = myMyPlane.PB_list.get(k);
						for(int r =0; r < boss.bullet_list.size();++r){
							CollideExamine.fb = boss.bullet_list.get(r);
							co.CollideBB();
				}			
			}
			//绘制清洁员
			myMyPlane.paint(g);
			//更新关卡提示信息
			level.updateLevel();
		}
		if(boss.isPlane_live() == false){
			//更新关卡信息
			level.updateLevel();
			//播放相应的音乐
			Music.complete.play();
			Bossblood.setText("Congratulations!");
			//为清洁员添加技能数量作为奖励
			MyCleaner.expsum += Level.lexelup[Level.level-1];
			MyCleaner.PPbm += Level.level;
			//boss死亡，唤醒垃圾产生线程
			FPThread.resume();
			//将下一关boss激活
			boss.setPlane_live(true);
			
			boss.setIsAddBullet(false);
			//为下一关boss设置体力值
			boss.setBlood();
		}
		
		for(int k =0; k < myMyPlane.bullet_list.size();++k){
			CollideExamine co = new CollideExamine();
			CollideExamine.mb = myMyPlane.bullet_list.get(k);
			for(int l =0; l < fightPlane_list.size();++l){
					for(int r =0; r < fightPlane_list.get(l).bullet_list.size();++r){
						CollideExamine.fb = fightPlane_list.get(l).bullet_list.get(r);
						co.CollideBB();
					}
			}			
		}
		for(int k =0; k < myMyPlane.PB_list.size();++k){
			CollideExamine co = new CollideExamine();
			CollideExamine.mb = myMyPlane.PB_list.get(k);
			for(int l =0; l < fightPlane_list.size();++l){
					for(int r =0; r < fightPlane_list.get(l).bullet_list.size();++r){
						CollideExamine.fb = fightPlane_list.get(l).bullet_list.get(r);
						co.CollideBB();
					}
			}			
		}
		
		//显示所有垃圾
		for(int i = 0; i < fightPlane_list.size(); ++i){
			Garbage pl = fightPlane_list.get(i);
			//实例化碰撞对象
			CollideExamine coexPP = new CollideExamine();
			CollideExamine.mp = myMyPlane;
			CollideExamine.fp = pl;
			//为清洁员和垃圾进行碰撞检测
			coexPP.CollidedPP();
			//如果清洁员销毁，则所有垃圾以及子弹消失
			if(myMyPlane.isPlane_live() == false){
				fightPlane_list.clear();
				tempbullet_list.clear();
				Music.backMusic.stop();
				Music.bosscopter.stop();
				FPThread.suspend();
			}
			//绘制清洁员
			myMyPlane.paint(g);
			//绘制垃圾
			pl.paint(g);
			/*
			 * 如果垃圾被击毁，则移除垃圾
			 * 更新关卡参数
			 * 
			 */
			if(pl.isPlane_live() == false){	
				level.updateLevel();
				fightPlane_list.remove(pl);
				
			}
				
		}
		
		//显示爆炸
		for(int i=0;i<explode_list.size();i++) {
			Feedback explode = explode_list.get(i);
			explode.drawEpdImages(g);
		}
	}
}
