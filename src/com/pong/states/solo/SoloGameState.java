package com.pong.states.solo;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import com.pong.entities.ball.Ball;
import com.pong.entities.ball.SoloBall;
import com.pong.entities.player.PlayerOne;
import com.pong.entities.player.PlayerTwoCPU;
import com.pong.pong.Pong;
import com.pong.states.OfflineState;
import com.pong.states.State;
import com.pong.utils.Logger;

public class SoloGameState extends OfflineState {
	private PlayerOne p1;
	private PlayerTwoCPU cpu;
	private boolean countedDown;
	private int count;
	private SoloBall b;
	private PlayerTwoCPU.Level level;

	public SoloGameState(PlayerTwoCPU.Level level) {
		super(State.StateType.GAME_SOLO);
		this.level = level;
	}

	@Override
	public void init() {
		countedDown = false;
		count = 0;

		int screenWidth = Pong.getPong().getWindow().getWidth();
		int screenHeight = Pong.getPong().getWindow().getHeight();
		// TODO server
		this.p1 = new PlayerOne();
		this.cpu = new PlayerTwoCPU(level);
		this.b = new SoloBall(screenWidth / 2, screenHeight / 2, Ball.DEFAULT_BALL_XSPEED, Ball.DEFAULT_BALL_YSPEED,
				this.p1, this.cpu);
		Pong.getPong().setBall(this.b);
		Pong.getPong().setCurrentPlayerOne(this.p1);
		Pong.getPong().setCurrentPlayerTwo(this.cpu);

		this.b.init();
		this.p1.init();
		this.cpu.init();
		Pong.getPong().getScreen().addKeyListener(this.p1);
	}

	@Override
	public void tick() {
		if (!countedDown) {
			count++;
			return;
		}

		if (this.cpu.getLevel() == PlayerTwoCPU.Level.EASY) {

			this.cpu.setYSpeed(PlayerTwoCPU.DEFAULT_CPU_YSPEED);

		} else if (this.cpu.getLevel() == PlayerTwoCPU.Level.MEDIUM) {

			this.cpu.setYSpeed(PlayerTwoCPU.DEFAULT_CPU_YSPEED + 2f);

		} else if (this.cpu.getLevel() == PlayerTwoCPU.Level.HARD) {

			this.cpu.setYSpeed(PlayerTwoCPU.DEFAULT_CPU_YSPEED + 3f);

		} else if (this.cpu.getLevel() == PlayerTwoCPU.Level.IMPOSSIBLE) {

			this.cpu.setYSpeed(PlayerTwoCPU.DEFAULT_CPU_YSPEED + 30f);

		}
		this.b.tick();
		this.p1.tick();
		this.cpu.tick();
	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
		g.setColor(State.backgroundColor);
		g.fillRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
		g.setColor(Color.white);
		this.b.render(g);
		g.setColor(Color.white);
		this.p1.render(g);
		g.setColor(Color.white);
		this.cpu.render(g);
		g.setColor(Color.white);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(100.0f));
		g.drawString(String.valueOf(this.p1.getPoints()), Pong.getPong().getWidth() / 2 - 180, 100);
		g.drawString(String.valueOf(this.cpu.getPoints()), Pong.getPong().getWidth() / 2 + 170, 100);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(30.0f));
		g.drawString("P1", Pong.getPong().getWidth() / 2 - 167, 30);
		g.drawString("CPU", Pong.getPong().getWidth() / 2 + 170, 30);

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
					Logger.logDebug("Solo Game Starting in 3...");
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
					Logger.logDebug("Solo Game Starting in 2...");
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
					Logger.logDebug("Solo Game Starting in 1...");
				}
			}

		}
		if (count >= 180 && !this.countedDown) {
			this.countedDown = true;
			if (Pong.getPong().isDebug()) {
				Logger.logDebug("Solo Game Started.");
			}
		}

		g.setColor(Color.white);
	}

	public PlayerOne getP1() {
		return p1;
	}

	public void setP1(PlayerOne p1) {
		this.p1 = p1;
	}

	public PlayerTwoCPU getCPU() {
		return cpu;
	}

	public void setCPU(PlayerTwoCPU cpu) {
		this.cpu = cpu;
	}

	public SoloBall getBall() {
		return this.b;
	}

	public void setBall(SoloBall b) {
		this.b = b;
	}

	public PlayerTwoCPU.Level getLevel() {
		return this.level;
	}

	@Override
	public void exit() {
		Pong.getPong().getScreen().removeKeyListener(this.p1);
		super.exit();
	}

}
