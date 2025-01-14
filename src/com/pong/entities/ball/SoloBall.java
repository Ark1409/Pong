package com.pong.entities.ball;

import com.pong.entities.player.PlayerOne;
import com.pong.entities.player.PlayerTwoCPU;
import com.pong.pong.Pong;
import com.pong.utils.Logger;

public class SoloBall extends Ball {
	private static float BALL_XSPEED = 7.5f;
	private static float BALL_YSPEED = 7.5f;
	private PlayerOne p1;
	private PlayerTwoCPU p2;

	public SoloBall(float x, float y, PlayerOne p1, PlayerTwoCPU p2) {
		this(x, y, BALL_XSPEED, BALL_YSPEED, p1, p2);
	}

	public SoloBall(float x, float y, float xSpeed, float ySpeed, PlayerOne p1, PlayerTwoCPU p2) {
		super(x, y, xSpeed, ySpeed);
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public void tick() {
		if (this.directionStart >= 1) {
			if (x <= 0) {
				reset(p2);
				return;
			}
			if (x + width >= Pong.getPong().getWindow().getWidth()) {
				BALL_XSPEED = 7.5f;
				reset(p1);
				return;
			}
//				if (x + width >= Pong.getPong().getWindow().getWidth()) {
//					BALL_XSPEED = 7.5f;
//					reset(p1);
//					return;
//				}
			if (y <= 0 || y + height >= Pong.getPong().getWindow().getHeight()) {
				BALL_YSPEED *= -1;
			}
			if (this.isColliding(this.p1.getRectangle()) || this.isColliding(this.p2.getRectangle())) {

				if (!once) {
					mult = 1.0f;
					this.once = true;
				} else {
					mult += 0.1f;
				}

				BALL_XSPEED *= -(mult);

			}
			x += BALL_XSPEED;
			y += BALL_YSPEED;
			ticker++;
			if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
				Logger.logInfo("Ball X: " + x);
				Logger.logInfo("Ball Y: " + y);
				ticker = 0;
			}
			return;

		} else if (this.directionStart <= -1) {
			if (x <= 0) {
				BALL_XSPEED = 7.5f;
				reset(p2);
				return;
			}
			if (x + width >= Pong.getPong().getWindow().getWidth()) {
				BALL_XSPEED = 7.5f;
				reset(p1);
				return;
			}

			if (y <= 0 || y + height >= Pong.getPong().getWindow().getHeight()) {
				BALL_YSPEED *= -1;
			}

			if (this.isColliding(p1.getRectangle()) || this.isColliding(p2.getRectangle())) {

				if (!this.once) {
					mult = 1.0f;
					this.once = true;
				} else {
					mult += 0.1f;
				}

				BALL_XSPEED *= -(mult);

			}
			x -= BALL_XSPEED;
			y += BALL_YSPEED;
			ticker++;
			if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
				Logger.logDebug("Ball X: " + x);
				Logger.logDebug("Ball Y: " + y);
				ticker = 0;
			}
			return;
		}

		ticker++;
		if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
			Logger.logDebug("Ball X: " + x);
			Logger.logDebug("Ball Y: " + y);
			ticker = 0;
		}
	}

	public PlayerOne getP1() {
		return p1;
	}

	public void setP1(PlayerOne p1) {
		this.p1 = p1;
	}

	public PlayerTwoCPU getP2() {
		return p2;
	}

	public void setP2(PlayerTwoCPU p2) {
		this.p2 = p2;
	}

}
