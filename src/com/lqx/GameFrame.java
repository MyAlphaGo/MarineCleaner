/**
 * com.lqx.GameFrame.java
 */
package com.lqx;
/**
 * ��Ҫ��ӵ� java ��
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
 * ��Ϸ������̳д�����
 */
public class GameFrame extends Frame {
	/**
	 * ����һ��ȱʡ�� serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ���ڿ��
	 */
	public static int width = 700;
	/**
	 * ���ڸ߶�
	 */
	public static int height = 450;
	/**
	 * ��Ŀ���߶�
	 */
	public static int TitleWidth = 60;
	/**
	 * ��ʾ���ĸ߶�
	 */
	public static int NoteWidth = 30;
	
	/**
	 * ʵ����һ�����Ա����
	 */
	MyCleaner myMyPlane = new MyCleaner();
	/**
	 * ʵ�����������ö���
	 */
	BackGroundSet Bg = new BackGroundSet(this);
	/**
	 * ��������
	 */
	public List<Garbage> fightPlane_list = new ArrayList<Garbage>();
	/**
	 * ��ըͼƬ����
	 */
	public static List<Feedback> explode_list = new ArrayList<Feedback>();
	/**
	 * ��ʱ�ӵ����ϣ����ڱ��沶���������߽�������֮ǰ�������ӵ�
	 */
	List<FightBullet> tempbullet_list = new ArrayList<FightBullet>();
	/**
	 * ʵ����һ����̬���߰�
	 */
	private static Toolkit tool  = Toolkit.getDefaultToolkit();
	//��ȡ��ը��Ҫ��4��ͼƬ
	public static Image[] explode_imgs = {
		tool.getImage(StartGame.class.getResource("/images/huicheng0.png")),
		tool.getImage(StartGame.class.getResource("/images/huicheng1.png")),
		tool.getImage(StartGame.class.getResource("/images/huicheng2.png")),
		tool.getImage(StartGame.class.getResource("/images/huicheng3.png"))
	} ;
	//ý�������
	public static MediaTracker mt = null; 
	
	/**
	 * ����һ����������ͼƬ
	 */
	private Image bg_img = null;
	/**
	 * ��ʱ���±���ͼƬ������˫������������
	 */
	public void update(Graphics g){
		//�������ͼƬΪ�գ��Ϳ��Դ������ͼƬ
		if (bg_img == null){
			//��������ͼƬ����С�봰���Сһ��
			bg_img = this.createImage(width, height);
		}
		//�õ�����ͼƬ�Ļ���
		Graphics bgGp = bg_img.getGraphics();
		
		//���û���ͼƬ�ı���ɫ
		bgGp.setColor(Color.white);
		//�ڻ���ͼƬ�ϻ�һ��ʵ�ĵľ��Σ��߶ȺͿ���봰���Сһ��
		bgGp.fillRect(0, 0, width, height);
		
		//�ڻ���ͼƬ���滭ͼ
		paint(bgGp);
		
		//������ͼƬ������������
		g.drawImage(bg_img, 0, 0, this);
	}
	/**
	 * �ɵ�������ǩ
	 */
	Label labPB = new Label("����:        ");
	Label labMB = new Label("����:        ");
	/**
	 * ���Ա�ؿ���ʾ��ǩ
	 */
	Label lablevel = new Label("�ؿ�:  ");
	/**
	 * �������������ʾ��ǩ
	 */
	Label labkill = new Label("����:   ");
	/**
	 * �������ֵ��ʾ��ǩ
	 */
	Label labblood = new Label("Ѫ��:  ");
	/**
	 * BOSS����ֵ��ʾ��ǩ����BOSSʱ��ʾ��û��ʱ��ʾһ�仰��
	 */
	Label Bossblood = new Label("Welcome!Danger...");
	
	private TitleFrame titleFrame;
	public static boolean gameover=false;
	public GameFrame(TitleFrame titleFrame){
		this.titleFrame=titleFrame;
		showWindow();
	}
	
	//��ʾ����
	public void showWindow(){
		/**
		 * ��������
		 */
		Music.backMusic.loop();
		//ѭ���������ԱǱˮ������
		Music.bosscopter.loop();
		//��������
		setTitle("�������Ա");
		//���ô����С
		setSize(width, height);
		//��λ����λ�ã���λ����Ļ���ģ�
		int w_width = (int)tool.getScreenSize().getWidth()/2 - (width/2);
		int w_height = (int)tool.getScreenSize().getHeight()/2 - (height/2);
		setLocation(w_width, w_height);
		//���ô˴����Ƿ�����û�������С
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			//�����ڲ��࣬�رմ��ڵ��¼���Ӧ
			public void windowClosing(WindowEvent arg0){
				// �˳�ϵͳ
				System.exit(0);
			}
		});
		/**
		 * ��Ӽ����¼�����
		 */
		addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override
			/**
			 * ��д���̰����¼�
			 */
			public void keyPressed(KeyEvent key) {
				//����������Ա�����Ա���Ʒ���
				myMyPlane.ControlPressed(key);
				/**
				 * �Զ��尴��C�������������
				 */
				if(key.getKeyCode() == KeyEvent.VK_C){
					fightPlane_list.clear();
					if(myMyPlane.isPlane_live()){
						Bg.setBackGround(tool.getImage(GameFrame.class.getResource("/images/51.gif")));
					}
				}
				/**
				 * �Զ��尴��B���ָ�����ֵ
				 */
				if(key.getKeyCode() == KeyEvent.VK_B ){
					myMyPlane.setBlood(100);
					myMyPlane.setFULLBLOOD(100);
				}
				/**
				 * �Զ��尴��U��ֱ�ӽ���
				 */
				if(key.getKeyCode() == KeyEvent.VK_U ){
					if(Level.level < 9){
						++ Level.level;
					}
					level.updateLevel();
					FPThread.resume();
				}
				/**
				 * �Զ��尴��X�����༼�ܺʹ���
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
		 * ��Ӽ��̼������ͷŰ�ťʱ��Ӧ
		 */
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent key) {
				//����������Աʵ���Ŀ��Ʒ���������Ӧ
				myMyPlane.ControlReleased(key);
			}
		});

		//��������߳�
		
		//�����߳�
		FPThread.start();
		/**
		 * �����߳��ػ�
		 */
		PaintThread PThread = new PaintThread();
		//�����߳�
		PThread.start();
		
		//����һ��ý�������
		mt = new MediaTracker(this);
		//�ѱ�ը��ͼƬ���ص�ý�����������
		mt.addImage(explode_imgs[0], 0);
		mt.addImage(explode_imgs[1], 0);
		mt.addImage(explode_imgs[2], 0);
		mt.addImage(explode_imgs[3], 0);
		
		try {
			//��ʼ����ý������������ͼƬ
			mt.waitForID(0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}		
		//�����������������б�ǩ���Ҷ���
		setLayout(new FlowLayout(FlowLayout.RIGHT));	
		//������б�ǩ
		add(labPB);
		add(labMB);
		add(lablevel);
		add(labkill);
		add(labblood);
		add(Bossblood);
		/**
		 * ���ô���ɼ�
		 */
		setVisible(true);
	}

	/**
	 * ��������Ĺ��г�Ա����
	 */
	public void addFightPlane(){
		/**
		 * ʵ����һ�������
		 */
		Random r = new Random();
		/**
		 * ����һ�����������Χʱ����߶�֮�ڣ������������������λ��
		 */
		int x = r.nextInt(height - 20 - TitleWidth)+TitleWidth;
		//ʵ����һ����������
		Garbage pl = new Garbage(width,x);
		//pl.openFire();
		/**
		 * ��������ӵ�������������
		 */
		fightPlane_list.add(pl);
	}
	/**
	 * �ػ��߳�
	 */
	private class PaintThread extends Thread{

		@Override
		/**
		 * ��д run �����ػ�
		 */
		public void run() {
			while(true){
				try {
					//�߳���ͣ 30 ms
					PaintThread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//�ػ�
				repaint();
			}
		}
		
	}

	/**
	 * �ڲ���
	 * �Զ����������߳�
	 *
	 */
	private class FPlaneThread extends Thread{
		@SuppressWarnings("deprecation")
		public void run() {
			while(true){
				addFightPlane();	
				try {
					//�߳���ͣ��ʱ����ݵȼ���ͬ���ı�
					FPlaneThread.sleep(Level.FPCreateSpeed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/**
				 * ���ɱ��������ؿ��趨ֵ���������Ѿ�����������ȫ������
				 * ���Զ����������Ľ��̹���
				 * �ȴ����BOSS����
				 */
				if(Level.KillFP == Level.lexelup[Level.level-1]-1){
					//�����������
					fightPlane_list.clear();
					//���������Զ����������߳�
					FPThread.suspend();
				}
			}
		}
	}
	/**
	 * ʵ������һ�����������߳�
	 */
	FPlaneThread FPThread = new FPlaneThread();
	/**
	 * ʵ������һ���ؿ�ָ�����
	 */
	private Level level = new Level();
	/**
	 * ʵ������һ��boss����
	 */
	Boss boss = new Boss();
	//��һ�����ͼ��������ʾ�ڽ���֮��
	private Image people = tool.getImage(MyCleaner.class.getResource("/images/p.png"));
	@SuppressWarnings("deprecation")
	@Override
	//��дpaint����
	public void paint(Graphics g) {
		//��ָ��λ�û�����ҵ�ͷ��
		g.drawImage(people, 10, GameFrame.TitleWidth - GameFrame.NoteWidth,30,30 , null );
		//������ҵ��ռ�������ǩ
		labkill.setText("����:" +  Level.KillFP);
		//������ҵ���Ϸ�ؿ���ǩ
		lablevel.setText("�ؿ�:" +  Level.level);
		//������ҵ�����ֵ��ǩ
		labblood.setText("Ѫ��:" + myMyPlane.getBlood());    
		if(myMyPlane.isPlane_live()==false){
			labblood.setText("Ѫ��: 0");
		}		
		//�������ӵ�м��ܺʹ�������
		labMB.setText("����:" + MyCleaner.expsum);
		labPB.setText("����:" + MyCleaner.PPbm);
		//���Ʊ���ͼƬ
		Bg.paint(g);
		//�����Լ������Ա
		myMyPlane.paint(g);
		/*
		 * ���ɱ�����ﵽ�ؿ���������������������� 
		 * ��ʾBOSS
		 */
		if(Level.KillFP == Level.lexelup[Level.level-1]-1){
			/**
			 * ��ʼ��boss
			 */
			boss.setIsAddBullet(true);
			//�����������
			fightPlane_list.clear();
			//���BOSS����ֵ
			Bossblood.setText("BOSS����:" + boss.getBlood());
			//����BOSS
			boss.paint(g);
			//�����Ա��BOSS���м��
			CollideExamine coexPP = new CollideExamine();
			//���ü�����
			CollideExamine.mp = myMyPlane;
			CollideExamine.fp = boss;
			
			//������Ӧ�ļ�ⷽ��
			coexPP.CollidedPBoss();
			for(int k =0; k < myMyPlane.PB_list.size();++k){
				CollideExamine co = new CollideExamine();
				CollideExamine.mb = myMyPlane.PB_list.get(k);
						for(int r =0; r < boss.bullet_list.size();++r){
							CollideExamine.fb = boss.bullet_list.get(r);
							co.CollideBB();
				}			
			}
			//�������Ա
			myMyPlane.paint(g);
			//���¹ؿ���ʾ��Ϣ
			level.updateLevel();
		}
		if(boss.isPlane_live() == false){
			//���¹ؿ���Ϣ
			level.updateLevel();
			//������Ӧ������
			Music.complete.play();
			Bossblood.setText("Congratulations!");
			//Ϊ���Ա��Ӽ���������Ϊ����
			MyCleaner.expsum += Level.lexelup[Level.level-1];
			MyCleaner.PPbm += Level.level;
			//boss�������������������߳�
			FPThread.resume();
			//����һ��boss����
			boss.setPlane_live(true);
			
			boss.setIsAddBullet(false);
			//Ϊ��һ��boss��������ֵ
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
		
		//��ʾ��������
		for(int i = 0; i < fightPlane_list.size(); ++i){
			Garbage pl = fightPlane_list.get(i);
			//ʵ������ײ����
			CollideExamine coexPP = new CollideExamine();
			CollideExamine.mp = myMyPlane;
			CollideExamine.fp = pl;
			//Ϊ���Ա������������ײ���
			coexPP.CollidedPP();
			//������Ա���٣������������Լ��ӵ���ʧ
			if(myMyPlane.isPlane_live() == false){
				fightPlane_list.clear();
				tempbullet_list.clear();
				Music.backMusic.stop();
				Music.bosscopter.stop();
				FPThread.suspend();
			}
			//�������Ա
			myMyPlane.paint(g);
			//��������
			pl.paint(g);
			/*
			 * ������������٣����Ƴ�����
			 * ���¹ؿ�����
			 * 
			 */
			if(pl.isPlane_live() == false){	
				level.updateLevel();
				fightPlane_list.remove(pl);
				
			}
				
		}
		
		//��ʾ��ը
		for(int i=0;i<explode_list.size();i++) {
			Feedback explode = explode_list.get(i);
			explode.drawEpdImages(g);
		}
	}
}
