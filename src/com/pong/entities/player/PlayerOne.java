package com.pong.entities.player;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pong.entities.Pallet;
import com.pong.pong.Pong;
import com.pong.utils.Logger;
import com.pong.utils.PongUtils;

public class PlayerOne extends Pallet implements KeyListener {
	private boolean[] keys = new boolean[999];

	public static final float DEFAULT_LOCAL_PLAYER1_XSPEED = 0.0f;
	public static final float DEFAULT_LOCAL_PLAYER1_YSPEED = 7.0f;

	public static final float DEFAULT_LOCAL_PLAYER1_X = 50.0f;
	public static final float DEFAULT_LOCAL_PLAYER1_Y = (Pong.getPong().getWindow().getHeight() / 2
			- Pallet.PALLET_HEIGHT / 2);
	private int ticker;

	public PlayerOne() {
		this(DEFAULT_LOCAL_PLAYER1_X, DEFAULT_LOCAL_PLAYER1_Y);
	}

	public PlayerOne(float x, float y) {
		this(x, y, DEFAULT_LOCAL_PLAYER1_XSPEED, DEFAULT_LOCAL_PLAYER1_YSPEED);
	}

	public PlayerOne(float x, float y, float xSpeed, float ySpeed) {
		this(Color.white, x, y, xSpeed, ySpeed);

	}

	public PlayerOne(Color c, float x, float y, float xSpeed, float ySpeed) {
		super(c, x, y, xSpeed, ySpeed);
		this.ticker = 0;
	}

	@Override
	public void tick() {

		y += -ySpeed;
		if (keys[KeyEvent.VK_W]) {
			ySpeed = DEFAULT_LOCAL_PLAYER1_YSPEED;
		} else if (keys[KeyEvent.VK_S]) {
			ySpeed = -DEFAULT_LOCAL_PLAYER1_YSPEED;
		} else {
			ySpeed = 0.0f;
		}

		x = PongUtils.clamp(0, 1600 - width, (int) x);
		y = PongUtils.clamp(0, 900 - height, (int) y);
		ticker++;
		if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
			Logger.logDebug("Player X: " + x);
			Logger.logDebug("Player Y: " + y);
			ticker = 0;
		}

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
	}

	@Override
	public boolean isOnline() {
		return false;
	}

}
