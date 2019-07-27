package com.lqx;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class KeyConfig {
	private static KeyConfig config;
	private int keyCode[]={KeyEvent.VK_J,KeyEvent.VK_K,KeyEvent.VK_L};
	private String keyName[]={"Shoot","Missile","Bomb"};
	private static String filePath=StartGame.class.getResource("/option/keyconfig.txt").toString().substring(8);
	private Properties pro=new Properties();
	
	public static KeyConfig getKeyConfig(){
		if(config==null){
			config=new KeyConfig();
		}
		return config;
	}
	public String[] getKeyName(){
		return keyName;
	}
	public int[] getKeyCode(){
		return keyCode;
	}
	public Properties getProperties(){
		return pro;
	}
	public KeyConfig(){
		loadProperty();
		updateKeyConfig();
	}
	public void loadProperty(){
		FileInputStream in;
		try {
			in = new FileInputStream(filePath);
			pro.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateKeyConfig(){
		for(int i=0;i<3;++i){
			keyCode[i]=getKeyToValue(keyName[i]);
		}
	}
	public void setProperty(int i, int value){
		pro.setProperty(keyName[i], Integer.toString(value));
		keyCode[i]=value;
		this.saveProperty();
	}
	public void saveProperty(){
		FileOutputStream out;
		try {
			out = new FileOutputStream(filePath, false);
			pro.store(out, null);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int getKeyToValue(String key) {
		String value = pro.getProperty(key);
		if(value==null)
			value="0";
		return Integer.parseInt(value);
	}
}
