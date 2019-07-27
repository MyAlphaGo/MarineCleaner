package com.lqx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Scores {
	private static Scores scores;
	private static String filePath=StartGame.class.getResource("/option/scores.txt").toString().substring(8);
	
	public static Scores getScores(){
		if(scores==null){
			scores=new Scores();
		}
		return scores;
	}
	private LinkedList<PlayerScore> list;
	public Scores(){
	}
	public void inputName(int score){
		String playerName = JOptionPane.showInputDialog("请输入你的大名！");
		if (null == playerName || playerName.trim().length() == 0) {
			playerName = "Player";
		}
		insertPlayerInfo(playerName,score);
	}
	@SuppressWarnings("unchecked")
	public void insertPlayerInfo(String name, int score) {
		try {
			File file = new File(filePath);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(filePath);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				this.list = (LinkedList) obj;
				ois.close();
				fis.close();
			}
			PlayerScore playerScore = new PlayerScore(name, score);
			this.list.add(playerScore);
			FileOutputStream out = new FileOutputStream(filePath,false);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(this.list);
			oos.flush();
			oos.close();
			out.close();
			this.list = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public LinkedList<String> getPlayerName(int num) {
		LinkedList<PlayerScore> linkList = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			linkList = (LinkedList) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(linkList);
		Iterator<PlayerScore> it = linkList.iterator();
		int i = 0;
		LinkedList<String> temp=new LinkedList<String>();
		PlayerScore playerScore = null;
		while (it.hasNext()) {
			playerScore = it.next();
			StringBuffer s = new StringBuffer();
			s.insert(s.lastIndexOf(""), (i+1)+"  "+ playerScore.getName());
			temp.add(s.toString());
			s = new StringBuffer();
			s.insert(s.lastIndexOf(""), playerScore.getScore());
			temp.add(s.toString());
			i++;
			if (i >= num)
				break;
		}
		linkList = null;
		return temp;
	}
}
