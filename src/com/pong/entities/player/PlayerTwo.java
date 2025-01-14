package com.pong.entities.player;

import java.awt.Color;

import com.pong.entities.Pallet;
import com.pong.pong.Pong;

public abstract class PlayerTwo extends Pallet {
	public static final float DEFAULT_PLAYER2_XSPEED = 0.0f;
	public static final float DEFAULT_PLAYER2_YSPEED = 5.0f;

	public static final float DEFAULT_PLAYER2_X = 1500.0f;
	public static final float DEFAULT_PLAYER2_Y = (Pong.getPong().getWindow().getHeight() / 2
			- Pallet.PALLET_HEIGHT / 2) - 2;

	protected PlayerTwo() {
		this(DEFAULT_PLAYER2_X, DEFAULT_PLAYER2_Y);
	}

	protected PlayerTwo(float x, float y) {
		this(x, y, DEFAULT_PLAYER2_XSPEED, DEFAULT_PLAYER2_YSPEED);
	}

	protected PlayerTwo(float x, float y, float xSpeed, float ySpeed) {
		this(Color.white, x, y, xSpeed, ySpeed);
	}

	protected PlayerTwo(Color c, float x, float y, float xSpeed, float ySpeed) {
		super(c, x, y, xSpeed, ySpeed);
	}

}
