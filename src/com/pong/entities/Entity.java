package com.pong.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity {
	protected float x, y;
	protected Rectangle hitBox;
	protected int width, height;
	protected float xSpeed, ySpeed;
	public static final float DEFAULT_SPEED = 3.0f;

	public Entity(float x, float y, int width, int height) {
		this(x, y, width, height, DEFAULT_SPEED, DEFAULT_SPEED);
	}

	public Entity(float x, float y, int width, int height, float xSpeed, float ySpeed) {
		this.x = x;
		this.y = y;
		this.hitBox = new Rectangle(width, height);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.width = width;
		this.height = height;
	}

	public abstract void init();

	public abstract void tick();

	public abstract void render(Graphics2D g);

	public abstract boolean isSolid();

	public boolean isColliding(Rectangle r) {
		return this.getHitBox().intersects(r);
	}

	public Rectangle getHitBox() {
		return this.hitBox = new Rectangle((int) x, (int) y, width, height);
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(float xSpeed) {
		this.xSpeed = xSpeed;
	}

	public float getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(float ySpeed) {
		this.ySpeed = ySpeed;
	}

}
