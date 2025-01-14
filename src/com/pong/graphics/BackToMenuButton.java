package com.pong.graphics;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.pong.pong.Pong;
import com.pong.states.MenuState;
import com.pong.utils.Logger;

public class BackToMenuButton extends RectangleButton {

	public BackToMenuButton() {
		this(Pong.getPong().getWidth() / 2 - RectangleButton.DEFAULT_BUTTON_WIDTH / 2,
				Pong.getPong().getHeight() / 2 - Pong.getPong().getHeight());
	}

	public BackToMenuButton(float x, float y) {
		super(Color.white, x, y, RectangleButton.DEFAULT_BUTTON_WIDTH, RectangleButton.DEFAULT_BUTTON_HEIGHT, true,
				true, "Back to Menu");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.clickable) {
			Pong.getPong().setCurrentState(new MenuState());
			if (Pong.getPong().isDebug()) {
				Logger.logDebug("Switching to 'Menu State'...");
			}
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

}
