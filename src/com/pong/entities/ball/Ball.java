package com.pong.entities.ball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.pong.entities.Entity;
import com.pong.entities.Pallet;
import com.pong.entities.player.OnlinePlayer;
import com.pong.pong.Pong;
import com.pong.utils.Logger;
import com.pong.utils.PongUtils;

public abstract class Ball extends Entity {
	public static final int BALL_WIDTH = 20;
	public static final int BALL_HEIGHT = 20;
	public static float DEFAULT_BALL_XSPEED = 7.5f;
	public static float DEFAULT_BALL_YSPEED = 7.5f;
	protected int directionStart;
	protected int ticker;
	protected float mult;
	protected boolean once = false;
	protected OnlinePlayer p;
	protected boolean online = Pong.getPong().isOnline();

	protected Ball(float x, float y, float xSpeed, float ySpeed) {
		super(x, y, BALL_WIDTH, BALL_HEIGHT, xSpeed, ySpeed);
		this.directionStart = 0;
		this.mult = 0f;
		this.ticker = 0;
		this.once = false;
	}

	@Override
	public void init() {
//		x += speed;
//		y += speed;
//		
//		if(x <= 0 || x - width >= Pong.getPong().getWindow().getWidth() || (y <= 0 || y - height >= Pong.getPong().getWindow().getHeight());
//			speed = -speed;
		Random r = new Random();
		if ((PongUtils.clamp(0, 1, r.nextInt(1))) == 0) {
			this.directionStart = -1;
		} else {
			this.directionStart = 1;
		}
		this.mult = 0f;
		this.once = false;
	}
//
//	@Override
//	public void tick() {
////		if (this.directionStart >= 1) {
////			if (this.online) {
////
////			}
////			if (x <= 0) {
////				reset(p);
////				return;
////			}
//////				if (x + width >= Pong.getPong().getWindow().getWidth()) {
//////					DEFAULT_BALL_XSPEED = 7.5f;
//////					reset(p1);
//////					return;
//////				}
////			if (y <= 0 || y + height >= Pong.getPong().getWindow().getHeight()) {
////				DEFAULT_BALL_YSPEED *= -1;
////			}
////			if (this.isColliding(this.p.getRectangle()) || this.isColliding(this.p.getRectangle())) {
////
////				if (!this.p.hasHit()) {
////					mult = 1.0f;
////					this.once = true;
////				} else {
////					mult += 0.1f;
////				}
////
////				DEFAULT_BALL_XSPEED *= -(mult);
////
////			}
////			x += DEFAULT_BALL_XSPEED;
////			y += DEFAULT_BALL_YSPEED;
////			return;
////
////		} else if (this.directionStart <= -1) {
////			if (x <= 0) {
////				DEFAULT_BALL_XSPEED = 7.5f;
////				reset(p2);
////				return;
////			}
////			if (x + width >= Pong.getPong().getWindow().getWidth()) {
////				DEFAULT_BALL_XSPEED = 7.5f;
////				reset(p1);
////				return;
////			}
////
////			if (y <= 0 || y + height >= Pong.getPong().getWindow().getHeight()) {
////				DEFAULT_BALL_YSPEED *= -1;
////			}
////
////			if (this.isColliding(Pong.getPong().getCurrentPlayerOne().getRectangle())
////					|| this.isColliding(Pong.getPong().getCurrentPlayerTwo().getRectangle())) {
////
////				if (!this.once) {
////					mult = 1.0f;
////					this.once = true;
////				} else {
////					mult += 0.1f;
////				}
////
////				DEFAULT_BALL_XSPEED *= -(mult);
////
////			}
////			x -= DEFAULT_BALL_XSPEED;
////			y += DEFAULT_BALL_YSPEED;
////		}
////
////		ticker++;
////		if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
////			Logger.logDebug("Ball X: " + x);
////			Logger.logDebug("Ball Y: " + y);
////			ticker = 0;
////		}
//
//	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.white);
		g.fillOval((int) x, (int) y, width, height);
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	public void reset(Pallet p) {
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Giving point to according player in menu...");
		}
		p.setPoints(p.getPoints() + 1);
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Reseting X position in menu...");
		}
		this.x = Pong.getPong().getWindow().getWidth() / 2;
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Reseting Y position in menu...");
		}
		this.y = Pong.getPong().getWindow().getHeight() / 2;
		this.mult = 0;
		this.once = false;
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Reseting Ball speed in menu...");
		}
		DEFAULT_BALL_XSPEED = 7.5f;
		DEFAULT_BALL_YSPEED = 7.5f;
	}

	public boolean online() {
		return this.online;
	}

}
