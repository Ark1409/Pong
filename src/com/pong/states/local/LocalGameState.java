package com.pong.states.local;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import com.pong.entities.Pallet;
import com.pong.entities.ball.Ball;
import com.pong.entities.ball.LocalBall;
import com.pong.entities.player.PlayerOne;
import com.pong.entities.player.PlayerTwoLocal;
import com.pong.pong.Pong;
import com.pong.states.OfflineState;
import com.pong.states.State;
import com.pong.utils.Logger;

public class LocalGameState extends OfflineState {
	protected PlayerOne p1;
	protected PlayerTwoLocal p2;
	protected LocalBall b;
	private boolean countedDown;
	private int count;

	public LocalGameState(PlayerOne p1, PlayerTwoLocal p2) {
		super(State.StateType.GAME_LOCAL);
		this.p1 = p1;
		this.p2 = p2;
		int screenWidth = Pong.getPong().getWindow().getWidth();
		int screenHeight = Pong.getPong().getWindow().getHeight();
		this.b = new LocalBall(screenWidth / 2, screenHeight / 2, Ball.DEFAULT_BALL_XSPEED, Ball.DEFAULT_BALL_YSPEED,
				this.p1, this.p2);
	}

	@Override
	public void init() {
		this.countedDown = false;
		this.count = 0;
		Pong.getPong().getWindow().getScreen().addKeyListener(this.p1);
		Pong.getPong().getWindow().getScreen().addKeyListener(this.p2);
		Pong.getPong().setBall(this.b);
		Pong.getPong().setCurrentPlayerOne(this.p1);
		Pong.getPong().setCurrentPlayerTwo(this.p2);
		this.b.init();
		this.p1.init();
		this.p2.init();
	}

	@Override
	public void tick() {
		if (this.p1.getPoints() >= Pallet.MAX_POINTS) {
			this.p1.setWon(true);
			if (Pong.getPong().isDebug()) {
				Logger.logDebug("Player 1 has won.");
				Logger.logDebug("Stopping player one movement...");
				Logger.logDebug("Stopping player two movement...");
				Logger.logDebug("Stopping player ball movement...");
				Logger.logDebug("Switching to 'Local Game Over State'...");
			}

			Pong.getPong().setCurrentState(new LocalGameOverState(this));
			return;
		}

		if (this.p2.getPoints() >= Pallet.MAX_POINTS) {
			this.p2.setWon(true);
			if (Pong.getPong().isDebug()) {
				Logger.logDebug("Player 2 has won.");
				Logger.logDebug("Stopping player one movement...");
				Logger.logDebug("Stopping player two movement...");
				Logger.logDebug("Stopping player ball movement...");
				Logger.logDebug("Switching to 'Local Game Over State'...");
			}
			Pong.getPong().setCurrentState(new LocalGameOverState(this));
			return;
		}
		if (!countedDown) {
			count++;
			return;
		}
		this.b.tick();
		this.p1.tick();
		this.p2.tick();

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(State.backgroundColor);
		g.fillRect(0, 0, Pong.getPong().getWindow().getWidth(), Pong.getPong().getWindow().getHeight());
		g.setColor(Color.white);
		this.b.render(g);
		g.setColor(Color.white);
		this.p1.render(g);
		g.setColor(Color.white);
		this.p2.render(g);
		g.setColor(Color.white);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(100.0f));
		g.drawString(String.valueOf(this.p1.getPoints()), Pong.getPong().getWidth() / 2 - 180, 100);
		g.drawString(String.valueOf(this.p2.getPoints()), Pong.getPong().getWidth() / 2 + 170, 100);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(30.0f));
		g.drawString("P1", Pong.getPong().getWidth() / 2 - 167, 30);
		g.drawString("P2", Pong.getPong().getWidth() / 2 + 179, 30);
		g.setColor(Color.white);
		if (count <= 60 && !this.countedDown) {
			g.setColor(Color.black);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
			g.fillRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.setColor(Color.white);
			g.setFont(Pong.getPong().getDefaultFont());
			g.drawString("3", (Pong.getPong().getWidth() / 2), (Pong.getPong().getHeight() / 2));
			if (count == 1) {
				if (Pong.getPong().isDebug()) {
					Logger.logDebug("Local Game Starting in 3");
				}
			}

		}
		if (count <= 120 && count > 60 && !this.countedDown) {
			g.setColor(Color.black);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
			g.fillRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.setColor(Color.white);
			g.setFont(Pong.getPong().getDefaultFont());
			g.drawString("2", (Pong.getPong().getWidth() / 2), (Pong.getPong().getHeight() / 2));
			if (count == 61) {
				if (Pong.getPong().isDebug()) {
					Logger.logDebug("Local Game Starting in 2");
				}
			}
		}
		if (count <= 180 && count > 120 && !this.countedDown) {
			g.setColor(Color.black);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
			g.fillRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.setColor(Color.white);
			g.setFont(Pong.getPong().getDefaultFont());
			g.drawString("1", (Pong.getPong().getWidth() / 2), (Pong.getPong().getHeight() / 2));
			if (count == 121) {
				if (Pong.getPong().isDebug()) {
					Logger.logDebug("Local Game Starting in 1");
				}
			}

		}
		if (count >= 180 && !this.countedDown) {
			this.countedDown = true;
			if (Pong.getPong().isDebug()) {
				Logger.logDebug("Local Game Started.");
			}
		}
		g.setColor(Color.white);
	}

	public PlayerOne getP1() {
		return p1;
	}

	public PlayerOne getPlayerOne() {
		return p1;
	}

	public void setP1(PlayerOne p1) {
		this.p1 = p1;
	}

	public PlayerTwoLocal getP2() {
		return p2;
	}

	public PlayerTwoLocal getPlayerTwo() {
		return p2;
	}

	public void setP2(PlayerTwoLocal p2) {
		this.p2 = p2;
	}

	public LocalBall getBall() {
		return this.b;
	}

	public void setBall(LocalBall b) {
		this.b = b;
	}

	public boolean hasCountedDown() {
		return this.countedDown;
	}

	@Override
	public void exit() {
		Pong.getPong().getWindow().getScreen().removeKeyListener(this.p1);
		Pong.getPong().getWindow().getScreen().removeKeyListener(this.p2);
		super.exit();
	}
}
