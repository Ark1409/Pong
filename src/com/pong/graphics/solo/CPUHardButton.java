package com.pong.graphics.solo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.pong.entities.player.PlayerTwoCPU;
import com.pong.graphics.RectangleButton;
import com.pong.graphics.Text;
import com.pong.pong.Pong;
import com.pong.states.solo.SoloGameState;

public class CPUHardButton extends RectangleButton {
	public static final Text HOVER_TEXT = new Text(
			"Computer will follow the ball at a fast pace.\nPretty hard to beat this one.",
			Pong.getPong().getWidth() / 2 - 297, Pong.getPong().getHeight() - 30);

	public CPUHardButton(float x, float y) {
		this(x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT);
	}

	public CPUHardButton(float x, float y, int width, int height) {
		this(Color.white, Pong.getPong().getDefaultFont().deriveFont(25.0f), x, y, width, height);
	}

	public CPUHardButton(Color c, Font f, float x, float y, int width, int height) {
		super(c, f, x, y, width, height, true, true, "Hard");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.clickable) {
			Pong.getPong().setCurrentState(new SoloGameState(PlayerTwoCPU.Level.HARD));
			this.clickable = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
	}

}
