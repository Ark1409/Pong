package com.pong.graphics.menu;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.pong.entities.player.PlayerOne;
import com.pong.entities.player.PlayerTwoLocal;
import com.pong.graphics.RectangleButton;
import com.pong.pong.Pong;
import com.pong.states.local.LocalGameState;
import com.pong.utils.Logger;

public class LocalPlayButton extends RectangleButton {

	public LocalPlayButton(float x, float y) {
		super(Color.white, DEFAULT_FONT, x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, true, true, "Local Play");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (clickable) {
			Pong.getPong().setCurrentState(new LocalGameState(new PlayerOne(), new PlayerTwoLocal()));
			if (Pong.getPong().isDebug()) {
				Logger.logDebug("Switching to 'Local Game State'...");
				Logger.logDebug("Player entered new local game.");
			}
			clickable = false;
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
