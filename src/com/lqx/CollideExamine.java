package com.lqx;


//Åö×²¼ì²â
public class CollideExamine {
	public static MyCleaner mp;
	public static Cleaner fp;
	public static Bullet mb;
	public static FightBullet fb;

	public CollideExamine(){	
	}
	
	public void CollidedPP(){
		if (mp.isPlane_live()&& fp.isPlane_live()){
			if (mp.getF_Localx() <= fp.getF_x() + fp.getF_Localx() && mp.getF_Localx()+ mp.getF_x() >= fp.getF_Localx()){
				if (mp.getF_Localy() <= fp.getF_y() + fp.getF_Localy() && mp.getF_Localy()+ mp.getF_y() >= fp.getF_Localy()){
					mp.cutBlood(5);
					if(mp.getBlood() <= 0){
						mp.setPlane_live(false);
						Music.gameover.play();
					}
					fp.setPlane_live(false);
					fp.overBlood();
					Music.blom.play();
					Feedback mpEpd = new Feedback(mp.getF_Localx()+mp.getF_x()/3, mp.getF_Localy(), GameFrame.explode_imgs, null, true);
					GameFrame.explode_list.add(mpEpd);
					Feedback fpEpd = new Feedback(fp.getF_Localx(), fp.getF_Localy(), GameFrame.explode_imgs, null, true);
					GameFrame.explode_list.add(fpEpd);				
				}
			}
		}
	}
	public void CollidedPBoss(){
		if (mp.isPlane_live()&& fp.isPlane_live()){
			if (mp.getF_Localx() <= fp.getF_x() + fp.getF_Localx() && mp.getF_Localx()+ mp.getF_x() >= fp.getF_Localx()){
				if (mp.getF_Localy() <= fp.getF_y() + fp.getF_Localy() && mp.getF_Localy()+ mp.getF_y() >= fp.getF_Localy()){
					mp.setPlane_live(false);
					Music.gameover.play();
					mp.overBlood();
					Feedback mpEpd = new Feedback(mp.getF_Localx()+mp.getF_x()/3, mp.getF_Localy(), GameFrame.explode_imgs, null, true);
					GameFrame.explode_list.add(mpEpd);
				}
			}
		}
		
	}
	public void CollidedBP(){
		if(mb.isBullet_live()&& fp.isPlane_live()){
			if (mb.getZd_x() <= fp.getF_x() + fp.getF_Localx() && mb.getZd_x()+ mb.getZd_width() >= fp.getF_Localx()){
				if (mb.getZd_y() <= fp.getF_y() + fp.getF_Localy() && mb.getZd_y()+ mb.getZd_height() >= fp.getF_Localy()){
					fp.cutBlood(1);
					if(mb.getZd_width()>= MyCleaner.bm_width){
						fp.cutBlood(2);
					}
					if(mb.getZd_width() == BigRecruit.PB_width && fp.f_Localx <= mb.zd_x+mb.getZd_width()){
						fp.cutBlood(10*Level.level);
					}
					if(fp.getBlood() <= 0){
						fp.setPlane_live(false);
						Music.blom.play();
						++ Level.KillFP;
						if(Level.KillFP == Level.lexelup[Level.level-1]-1){
							Music.danger.play();
						}
						if(Level.KillFP == Level.lexelup[Level.level-1]){
							Music.nuk.play();
						}
					}
					if(mb.getZd_width() != BigRecruit.PB_width) mb.bullet_live = false;
					if(fp.getF_x() >= Boss.Boss_width) mb.bullet_live = false;
					Feedback fpEpd = new Feedback(fp.getF_Localx(), fp.getF_Localy(), GameFrame.explode_imgs, null, true);
					GameFrame.explode_list.add(fpEpd);
					
				}
			}
		}
		
		
	}
	public void CollidedPB(){
		if(fb.isBullet_live()&&mp.isPlane_live()){
			if (fb.getZd_x() <= mp.getF_x() + mp.getF_Localx() && fb.getZd_x()+ fb.getZd_width() >= mp.getF_Localx()){
				if (fb.getZd_y() <= mp.getF_y() + mp.getF_Localy() && fb.getZd_y()+ fb.getZd_height() >= mp.getF_Localy()){
					mp.cutBlood(1);
					if(fb.getZd_width()==Boss.Bu_width0){
						mp.cutBlood(1);
					}
					if(fb.getZd_width()==Boss.Bu_width1){
						mp.cutBlood(2);
					}
					if(fb.getZd_width()==Boss.Bu_width2){
						mp.cutBlood(4);
					}
					if(fb.getZd_width()==Boss.Bu_width3){
						mp.cutBlood(7);
					}
					if(fb.getZd_width()==Boss.ms_width3){
						mp.cutBlood(2);
					}
					if(fb.getZd_width()==Boss.ms_width2){
						mp.cutBlood(2);
					}
					if(fb.getZd_width()==Boss.ms_width1){
						mp.cutBlood(1);
					}
					if(fb.getZd_width()==Boss.ms_width0){
						mp.cutBlood(1);
					}
					if(mp.getBlood() <= 0){
						mp.setPlane_live(false);
						mp.setBlood(0);
						Music.gameover.play();
					}
					Feedback mpEpd = new Feedback(mp.getF_Localx()+mp.getF_x()/3, mp.getF_Localy(), GameFrame.explode_imgs, null, true);
					GameFrame.explode_list.add(mpEpd);
					fb.bullet_live = false;
				}
			}
		}
	}
	
	public void CollideBB(){
		if(fb.isBullet_live()&&mb.isBullet_live()){
			if (fb.getZd_x() <= mb.getZd_x() + mb.getZd_width() && fb.getZd_x()+ fb.getZd_width() >= mb.getZd_x()){
				if (fb.getZd_y() <= mb.getZd_height() + mb.zd_y && fb.getZd_y()+ fb.getZd_height() >= mb.getZd_y()){
					if(fb.getZd_width() == mb.getZd_width()){
						fb.bullet_live = false;
						mb.bullet_live = false;
					}
					if(mb.getZd_width() == BigRecruit.PB_width){
						fb.bullet_live = false;
						Feedback mpEpd = new Feedback(fb.getZd_x(),fb.getZd_y(), GameFrame.explode_imgs, null, true);
						GameFrame.explode_list.add(mpEpd);
					}
				}
			}
		}		
	}
}
