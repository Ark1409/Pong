package com.pong.graphics.menu;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.pong.graphics.RectangleButton;
import com.pong.pong.Pong;
import com.pong.states.solo.SoloGameChoosingState;

public class SinglePlayerButton extends RectangleButton {

	public SinglePlayerButton(float x, float y) {
		super(Color.WHITE, DEFAULT_FONT, x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, true, true,
				"Single Player");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (clickable) {
			Pong.getPong().setCurrentState(new SoloGameChoosingState());
			clickable = false;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
