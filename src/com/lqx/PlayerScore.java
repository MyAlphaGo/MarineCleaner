package com.lqx;

import java.io.Serializable;

public class PlayerScore implements Serializable, Comparable<PlayerScore>{
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	
	public PlayerScore() {
	}
	public PlayerScore(String name, int score) {
		this.name = name;
		this.score = score;
	}
	public int compareTo(PlayerScore o) {
		if (o.getScore() > this.getScore()) {
			return 1;
		} else if (o.getScore() < this.getScore()) {
			return -1;
		}
		return 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
