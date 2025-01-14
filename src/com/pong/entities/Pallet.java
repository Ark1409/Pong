package com.pong.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyListener;

public abstract class Pallet extends Entity implements KeyListener {
	protected Color c;
	public static final int PALLET_WIDTH = 16;
	public static final int PALLET_HEIGHT = 123;
	public static final float DEFAULT_PALLET_SPEED = 5.0f;
	public static final int MAX_POINTS = 3;
	protected boolean won;
	protected int points;
	protected boolean hit;

	public Pallet(Color c, float x, float y, float xSpeed, float ySpeed) {
		super(x, y, PALLET_WIDTH, PALLET_HEIGHT, xSpeed, ySpeed);
		this.c = c;
		this.points = 0;
		this.won = false;
		this.hit = false;
	}

	public Pallet(Color c, float x, float y, float speed) {
		this(c, x, y, speed, speed);
	}

	public Pallet(float x, float y, float speed) {
		this(Color.white, x, y, speed, speed);
	}

	public Pallet(float x, float y) {
		this(Color.white, x, y, DEFAULT_PALLET_SPEED);
	}

	public Pallet(float x, float y, float xSpeed, float ySpeed) {
		this(Color.white, x, y, xSpeed, ySpeed);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(this.c);
		((Graphics2D) g).fillRect((int) x, (int) y, width, height);
		g.setColor(Color.white);
	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public Rectangle getRectangle() {
		return this.hitBox = new Rectangle((int) x, (int) y, width, height);
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean hasWon() {
		return this.won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public abstract boolean isOnline();

	public boolean hasHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	
}
