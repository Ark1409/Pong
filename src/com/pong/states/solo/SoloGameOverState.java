package com.pong.states.solo;

import java.awt.Graphics2D;

import com.pong.graphics.BackToMenuButton;
import com.pong.pong.Pong;
import com.pong.states.OfflineState;
import com.pong.states.State;

public class SoloGameOverState extends OfflineState {

	private BackToMenuButton menu;

	public SoloGameOverState() {
		super(State.StateType.SOLO_GAME_OVER);
	}

	@Override
	public void init() {
		this.menu = new BackToMenuButton(300, 300);
		Pong.getPong().getWindow().getScreen().addMouseListener(this.menu);
		Pong.getPong().getWindow().getFrame().addMouseListener(this.menu);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		Pong.getPong().getWindow().getScreen().removeMouseListener(this.menu);
		Pong.getPong().getWindow().getFrame().removeMouseListener(this.menu);
		super.exit();

	}

}
