package com.pong.states.local;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.pong.entities.player.PlayerOne;
import com.pong.entities.player.PlayerTwoLocal;
import com.pong.graphics.BackToMenuButton;
import com.pong.pong.Pong;
import com.pong.states.OfflineState;
import com.pong.states.State;

public class LocalGameOverState extends OfflineState {
	protected PlayerOne p1;
	protected PlayerTwoLocal p2;
	protected LocalGameState s;
	protected final Font gameOverFont = Pong.getPong().getDefaultFont();
	protected final String gameOverString = "Game Over";
	protected final String p1WinsString = "P1 wins!";
	protected final String p2WinsString = "P2 wins!";
	protected BackToMenuButton menu;

	public LocalGameOverState(LocalGameState s) {
		super(State.StateType.LOCAL_GAME_OVER);
		this.s = s;
		this.p1 = s.getP1();
		this.p2 = s.getP2();
	}

	public LocalGameOverState(LocalGameState s, PlayerOne p1, PlayerTwoLocal p2) {
		super(State.StateType.LOCAL_GAME_OVER);
		this.s = s;
		this.p1 = p1;
		this.p2 = p2;

	}

	@Override
	public void init() {
		this.menu = new BackToMenuButton(300, 300);
		Pong.getPong().getWindow().getScreen().addMouseListener(this.menu);
		Pong.getPong().getWindow().getFrame().addMouseListener(this.menu);
	}

	@Override
	public void tick() {
		this.menu.tick();
	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, Pong.getPong().getWindow().getWidth(), Pong.getPong().getWindow().getHeight());
		g.setColor(Color.white);
		getLocalGameState().render(g);
		g.setColor(Color.black);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
		g.fillRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.setColor(Color.white);

		g.setColor(Color.white);
		g.setFont(gameOverFont);
		if (this.getP1().hasWon()) {
			g.drawString(gameOverString,
					(Pong.getPong().getWindow().getWidth() / 2) - (g.getFontMetrics().stringWidth(gameOverString) / 2),
					Pong.getPong().getWindow().getHeight() / 2);
			g.drawString(p1WinsString,
					(Pong.getPong().getWindow().getWidth() / 2) - (g.getFontMetrics().stringWidth(p1WinsString) / 2),
					Pong.getPong().getWindow().getHeight() / 2 - 100);
		}
		if (this.getP2().hasWon()) {
			g.drawString(gameOverString,
					(Pong.getPong().getWindow().getWidth() / 2) - (g.getFontMetrics().stringWidth(gameOverString) / 2),
					Pong.getPong().getWindow().getHeight() / 2);
			g.drawString(p2WinsString,
					(Pong.getPong().getWindow().getWidth() / 2) - (g.getFontMetrics().stringWidth(p2WinsString) / 2),
					Pong.getPong().getWindow().getHeight() / 2 - 100);
		}
		this.menu.render(g);
	}

	public PlayerOne getP1() {
		return this.p1;
	}

	public PlayerTwoLocal getP2() {
		return this.p2;
	}

	public void setP1(PlayerOne p1) {
		this.p1 = p1;
	}

	public void setP2(PlayerTwoLocal p2) {
		this.p2 = p2;
	}

	private LocalGameState getLocalGameState() {
		return this.s;
	}

	@Override
	public void exit() {
		Pong.getPong().getWindow().getScreen().removeMouseListener(this.menu);
		Pong.getPong().getWindow().getFrame().removeMouseListener(this.menu);
		super.exit();
	}

}
