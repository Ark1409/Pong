package com.pong.entities.player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pong.pong.Pong;
import com.pong.utils.Logger;
import com.pong.utils.PongUtils;

public class PlayerTwoLocal extends PlayerTwo implements KeyListener {
	private boolean[] keys = new boolean[999];

	private static final float DEFAULT_LOCAL_PLAYER2_XSPEED = 0.0f;
	private static final float DEFAULT_LOCAL_PLAYER2_YSPEED = 7.0f;
	private int ticker;

	public PlayerTwoLocal() {
		this(DEFAULT_PLAYER2_X, DEFAULT_PLAYER2_Y);
	}

	public PlayerTwoLocal(float x, float y) {
		this(x, y, DEFAULT_LOCAL_PLAYER2_XSPEED, DEFAULT_LOCAL_PLAYER2_YSPEED);
	}

	public PlayerTwoLocal(float x, float y, float xSpeed, float ySpeed) {
		super(x, y, xSpeed, ySpeed);
		this.ticker = 0;
	}

	@Override
	public void tick() {
		y += -ySpeed;
		if (keys[KeyEvent.VK_UP]) {
			ySpeed = DEFAULT_LOCAL_PLAYER2_YSPEED;
		} else if (keys[KeyEvent.VK_DOWN]) {
			ySpeed = -DEFAULT_LOCAL_PLAYER2_YSPEED;
		} else {
			ySpeed = 0.0f;
		}
		x = PongUtils.clamp(0, 1600 - width, (int) x);
		y = PongUtils.clamp(0, 900 - height, (int) y);
		ticker++;
		if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
			Logger.logDebug("Player 2 X: " + x);
			Logger.logDebug("Player 2 Y: " + y);
			ticker = 0;
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOnline() {
		// TODO Auto-generated method stub
		return false;
	}

}
