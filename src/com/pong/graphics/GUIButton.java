package com.pong.graphics;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;

import com.pong.pong.Pong;

public abstract class GUIButton implements MouseListener {
	protected float x, y;
	protected int width, height;
	protected boolean clickable;
	protected Point mouse;
	public static final int MOUSE_DETECTION_SIZE = 2;

	public GUIButton(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.clickable = false;
		this.mouse = Pong.getPong().getWindow().getScreen().getMousePosition();
	}

	public abstract void init();

	public abstract void tick();

	public abstract void render(Graphics2D g);

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isClickable() {
		return this.clickable;
	}

	public void setClickable(boolean click) {
		this.clickable = click;
	}

	public Point getMouse() {
		return this.mouse;
	}

}
