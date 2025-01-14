package com.pong.graphics.menu;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.pong.graphics.RectangleButton;
import com.pong.pong.Pong;
import com.pong.states.online.OnlineTypeChooserState;
import com.pong.states.online.WaitingForOnlineGameState;

public class OnlinePlayButton extends RectangleButton {

	public OnlinePlayButton(float x, float y) {
		super(Color.white, x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, true, true, "Online Play");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (clickable) {
			Pong.getPong().setCurrentState(new OnlineTypeChooserState());
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
