package com.pong.graphics.online;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.pong.graphics.RectangleButton;
import com.pong.graphics.Text;
import com.pong.pong.Pong;

public class RandomGameButton extends RectangleButton {

	public static final Text HOVER_TEXT = new Text("Join a random game with anyone in the world.",
			Pong.getPong().getWidth() / 2 - 297, Pong.getPong().getHeight() - 30);

	public RandomGameButton(float x, float y) {
		super(Color.white, x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, true, true, "Join Random Game");
		this.setFont(DEFAULT_FONT.deriveFont(26.0f));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.clickable) {
			// TODO code join random game
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
			HOVER_TEXT.render(g);
		}
		g.setColor(Color.white);
	}

}
