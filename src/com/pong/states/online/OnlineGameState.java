package com.pong.states.online;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import com.pong.entities.ball.OnlineBall;
import com.pong.entities.player.OnlinePlayer;
import com.pong.pong.Pong;
import com.pong.states.OnlineState;
import com.pong.states.State;

public class OnlineGameState extends OnlineState {
	private OnlinePlayer p;
	private OnlineBall b;

	public OnlineGameState(OnlinePlayer p) {
		super(State.StateType.GAME_ONLINE);
		this.p = p;
		int screenWidth = Pong.getPong().getWindow().getWidth();
		int screenHeight = Pong.getPong().getWindow().getHeight();
		try {
			this.b = new OnlineBall(screenWidth / 2, screenHeight / 2, this.p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		Pong.getPong().setOnline(true);
		Pong.getPong().getScreen().addKeyListener(p);
		Pong.getPong().setOnlinePlayer(p);
		Pong.getPong().setBall(b);
		p.init();
		b.init();
		
		// b.init();
	}

	@Override
	public void tick() {
		this.b.tick();
		this.p.tick();
	}

	@Override
	public void render(Graphics2D g) {
		int screenWidth = Pong.getPong().getWindow().getWidth();
		int screenHeight = Pong.getPong().getWindow().getHeight();
		g.clearRect(0, 0, screenWidth, screenHeight);
		g.setColor(State.backgroundColor);
		g.fillRect(0, 0, screenWidth, screenHeight);
		g.setColor(Color.white);
		this.p.render(g);
		this.b.render(g);
	}

	@Override
	public void exit() {
		super.exit();
		Pong.getPong().getScreen().removeKeyListener(p);
		Pong.getPong().setOnlinePlayer(null);
		Pong.getPong().setOnline(false);
		try {
			if (p != null)
				p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public OnlinePlayer getOnlinePlayer() {
		return this.p;
	}

	public void setOnlinePlayer(OnlinePlayer p) {
		this.p = p;
	}

	public OnlineBall getBall() {
		return this.b;
	}

	public void setBall(OnlineBall b) {
		this.b = b;
	}

}
