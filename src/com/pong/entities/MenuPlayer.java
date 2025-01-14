package com.pong.entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.pong.entities.ball.MenuBall;
import com.pong.entities.player.PlayerOne;
import com.pong.entities.player.PlayerTwo;
import com.pong.pong.Pong;
import com.pong.utils.PongUtils;

public class MenuPlayer extends Pallet {
	public static final float DEFAULT_XSPEED = 0.0f;
	public static final float DEFAULT_YSPEED = 6.0f;
	private boolean left, center;

	public MenuPlayer(boolean left) {
		super(left ? PlayerOne.DEFAULT_LOCAL_PLAYER1_X : PlayerTwo.DEFAULT_PLAYER2_X,
				left ? PlayerOne.DEFAULT_LOCAL_PLAYER1_Y : PlayerTwo.DEFAULT_PLAYER2_Y);
		this.left = left;
		this.center = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public boolean isOnline() {
		return false;
	}

	@Override
	public void init() {

	}

	public MenuBall getBall() {
		return (MenuBall) Pong.getPong().getBall();
	}

	@Override
	public void tick() {
		if (left) {
			if (this.getBall().getX() < Pong.getPong().getWidth() / 2 + 430) {
				center = false;
				if (y < (this.getBall().getY() - 5)) {
					y += ySpeed;
				}

				if (y > (this.getBall().getY() + 5)) {
					y -= ySpeed;
				}
			} else {
//				if (!(y == (Pong.getPong().getHeight() / 2) - Pallet.PALLET_HEIGHT / 2)) {
//					if (y > (Pong.getPong().getHeight() / 2)) {
//						 y = PongUtils.clamp((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2),
//								900, (int) (y - ySpeed));
//						//y -= ySpeed;
//					} else if (y < (Pong.getPong().getHeight() / 2)) {
//						//y += ySpeed;
//						 y = PongUtils.clamp(900,
//								 (Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2), (int) (y + ySpeed));
//					}
//				}
				if (!(y == (Pong.getPong().getHeight() / 2) - Pallet.PALLET_HEIGHT / 2)) {
					if (y > (Pong.getPong().getHeight() / 2) - 2) {
						if (!center) {
							y -= ySpeed;
							if (y < ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2))) {
								y = ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2));
								center = true;
							}
						}
					} else if (y < ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2))) {
						if (!center) {
							y += ySpeed;
							if (y > ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2))) {
								y = ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2));
								center = true;
							}
						}
					}
				}
			}
//
//			ticker++;
//			if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
//				Logger.logInfo("CPU X: " + x);
//				Logger.logInfo("CPU Y: " + y);
//				ticker = 0;
//			}
		} else {
			// if (this.getBall().getX() > Pong.getPong().getWidth() / 30) {
			// 480
			if (this.getBall().getX() > Pong.getPong().getWidth() / 2 - 430) {
				if (y < (this.getBall().getY() - 5)) {
					y += ySpeed;
				}

				if (y > (this.getBall().getY() + 5)) {
					y -= ySpeed;
				}
			} else {
				if (!(y == (Pong.getPong().getHeight() / 2) - Pallet.PALLET_HEIGHT / 2)) {
					if (y > (Pong.getPong().getHeight() / 2) - 2) {
						if (!center) {
							y -= ySpeed;
							if (y < ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2))) {
								y = ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2));
								center = true;
							}
						}
					} else if (y < ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2))) {
						if (!center) {
							y += ySpeed;
							if (y > ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2))) {
								y = ((Pong.getPong().getHeight() / 2) - (Pallet.PALLET_HEIGHT / 2));
								center = true;
							}
						}
					}
				}
			}

//			ticker++;
//			if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
//				Logger.logInfo("CPU X: " + x);
//				Logger.logInfo("CPU Y: " + y);
//				ticker = 0;
//			}
			// }
		}
		x = PongUtils.clamp(0, 1600 - width, (int) x);
		y = PongUtils.clamp(0, 900 - height, (int) y);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

}
