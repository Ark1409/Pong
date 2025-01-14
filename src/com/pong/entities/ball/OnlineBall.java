package com.pong.entities.ball;

import com.pong.entities.player.OnlinePlayer;
import com.pong.pong.Pong;
import com.pong.utils.Logger;

public class OnlineBall extends Ball {
	private OnlinePlayer p;
	private static float BALL_XSPEED = 7.5f;
	private static float BALL_YSPEED = 7.5f;

	public OnlineBall(float x, float y, OnlinePlayer p) throws Exception {
		this(x, y, BALL_XSPEED, BALL_YSPEED, p);
	}

	public OnlineBall(float x, float y, float xSpeed, float ySpeed, OnlinePlayer p) throws Exception {
		super(x, y, xSpeed, ySpeed);
		if (!Pong.getPong().isOnline()) {
			throw new Exception("The player is not set as online yet!");
		}
		this.p = p;
	}

	@Override
	public void tick() {
		if (this.directionStart >= 1) {
			if (x <= 0) {
				// TODO give other point.
				return;
			}
			if(this.isColliding(p.getRectangle())) {
				p.setLastHit(true);
			}
		} else if (this.directionStart <= -1) {
			if (x + width >= Pong.getPong().getWindow().getWidth()) {
				DEFAULT_BALL_XSPEED = 7.5f;

				reset();
				return;
			}
			if (y <= 0 || y + height >= Pong.getPong().getWindow().getHeight()) {
				DEFAULT_BALL_YSPEED *= -1;
			}
		}

		ticker++;
		if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
			Logger.logDebug("Ball X: " + x);
			Logger.logDebug("Ball Y: " + y);
			ticker = 0;
		}

	}

	private void reset() {
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Giving point to according player...");
		}
		p.setPoints(p.getPoints() + 1);
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Reseting X position...");
		}
		this.x = Pong.getPong().getWindow().getWidth() / 2;
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Reseting Y position...");
		}
		this.y = Pong.getPong().getWindow().getHeight() / 2;
		this.mult = 0;
		this.once = false;
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Reseting Ball speed...");
		}
		DEFAULT_BALL_XSPEED = 7.5f;
		DEFAULT_BALL_YSPEED = 7.5f;
	}

}
