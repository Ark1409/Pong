package com.pong.graphics.online;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.pong.entities.player.OnlinePlayer;
import com.pong.graphics.RectangleButton;
import com.pong.pong.Pong;
import com.pong.states.online.OnlineGameState;

public class JoinSpecificGameButton extends RectangleButton {
	public static final String JOIN_BUTTON_TEXT = "Join Game";
	private String ip;
	private int port;

	public JoinSpecificGameButton() {
		super(Color.white, Pong.getPong().getWidth() / 2 - RectangleButton.DEFAULT_BUTTON_WIDTH + 130,
				Pong.getPong().getHeight() / 2 - RectangleButton.DEFAULT_BUTTON_HEIGHT + 20,
				RectangleButton.DEFAULT_BUTTON_WIDTH, RectangleButton.DEFAULT_BUTTON_HEIGHT, true, true,
				JOIN_BUTTON_TEXT);

	}

	public JoinSpecificGameButton(float x, float y) {
		super(Color.white, x, y, RectangleButton.DEFAULT_BUTTON_WIDTH, RectangleButton.DEFAULT_BUTTON_HEIGHT, true,
				true, JOIN_BUTTON_TEXT);
	}

	public JoinSpecificGameButton(Color c, float x, float y, int width, int height) {
		super(c, x, y, width, height, true, true, JOIN_BUTTON_TEXT);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.clickable) {
			Pong.getPong().setOnline(true);
			OnlinePlayer p = new OnlinePlayer(this.ip, this.port);
			Pong.getPong().setOnlinePlayer(p);
			
			Pong.getPong().setCurrentState(new OnlineGameState(p));
			this.clickable = false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		if (this.clickable) {
			// g.getFontMetrics().stringWidth(HOVER_TEXT.toString());
			// HOVER_TEXT.render(g);
		}
	}

	@Override
	public void tick() {
		super.tick();
	}

	public void setIP(String ip) {
		this.ip = ip;
	}

	public String getIP() {
		return this.ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return this.port;
	}

}
