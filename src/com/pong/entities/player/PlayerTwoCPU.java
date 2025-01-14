package com.pong.entities.player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.pong.entities.Pallet;
import com.pong.entities.ball.Ball;
import com.pong.pong.Pong;
import com.pong.utils.Logger;
import com.pong.utils.PongUtils;

public class PlayerTwoCPU extends PlayerTwo {
	public static final float DEFAULT_CPU_XSPEED = 0.0f;
	public static final float DEFAULT_CPU_YSPEED = 6.0f;
	protected PlayerTwoCPU.Level level;
	public static final Level DEFAULT_LEVEL = Level.EASY;
	private int ticker;

	public PlayerTwoCPU() {
		this(DEFAULT_PLAYER2_X, DEFAULT_PLAYER2_Y, DEFAULT_CPU_XSPEED, DEFAULT_CPU_YSPEED, DEFAULT_LEVEL);
	}

	public PlayerTwoCPU(Level l) {
		this(DEFAULT_PLAYER2_X, DEFAULT_PLAYER2_Y, DEFAULT_CPU_XSPEED, DEFAULT_CPU_YSPEED, l);
	}

	public PlayerTwoCPU(float x, float y) {
		this(x, y, DEFAULT_CPU_XSPEED, DEFAULT_CPU_YSPEED, DEFAULT_LEVEL);
	}

	public PlayerTwoCPU(float x, float y, Level l) {
		this(x, y, DEFAULT_CPU_XSPEED, DEFAULT_CPU_YSPEED, l);
	}

	public PlayerTwoCPU(float x, float y, float xSpeed, float ySpeed) {
		this(x, y, xSpeed, ySpeed, DEFAULT_LEVEL);
	}

	public PlayerTwoCPU(float x, float y, float xSpeed, float ySpeed, Level l) {
		super(x, y, xSpeed, ySpeed);
		this.level = l;
		this.ticker = 0;
	}

	@Override
	public void init() {

	}

	@Override
	public void tick() {
		if (this.getLevel() != PlayerTwoCPU.Level.IMPOSSIBLE) {
			if (this.getBall().getX() > Pong.getPong().getWidth() / 2) {
				if (y < (this.getBall().getY() - 5)) {
					y += ySpeed;
				}

				if (y > (this.getBall().getY() + 5)) {
					y -= ySpeed;
				}
			} else {
				if (!(y == (Pong.getPong().getHeight() / 2) - Pallet.PALLET_HEIGHT / 2)) {
					if (y > (Pong.getPong().getHeight() / 2) - 2) {
						y -= ySpeed;
					} else if (y > (Pong.getPong().getHeight() / 2) + 2) {
						y += ySpeed;
					}
				}
			}

			ticker++;
			if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
				Logger.logDebug("CPU X: " + x);
				Logger.logDebug("CPU Y: " + y);
				ticker = 0;
			}
		} else {
			// if (this.getBall().getX() > Pong.getPong().getWidth() / 30) {
			y = this.getBall().getY();
			// }
		}
		x = PongUtils.clamp(0, 1600 - width, (int) x);
		y = PongUtils.clamp(0, 900 - height, (int) y);

	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	public PlayerTwoCPU.Level getLevel() {
		return this.level;
	}

	public void setLevel(PlayerTwoCPU.Level level) {
		this.level = level;
	}

	public Ball getBall() {
		return Pong.getPong().getBall();
	}

	public enum Level {
		EASY(0), MEDIUM(1), HARD(2), IMPOSSIBLE(3);
		private int id;

		private Level(int id) {
			this.id = id;
		}

		public static Level getLevel(int i) {
			if (!isValidLevel(i))
				return null;
			if (i == 0) {
				return EASY;
			} else if (i == 1) {
				return MEDIUM;
			} else if (i == 2) {
				return HARD;
			} else if (i == 3) {
				return IMPOSSIBLE;
			}
			return null;
		}

		public static boolean isValidLevel(int i) {
			return i > -1 && i <= Level.values().length;
		}

		public int getID() {
			return this.id;
		}

	}

	@Override
	public boolean isOnline() {
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
