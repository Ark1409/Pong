package com.pong.states.online;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import com.pong.graphics.RectangleButton;
import com.pong.graphics.Text;
import com.pong.graphics.online.OnlineBackButton;
import com.pong.graphics.online.RandomGameButton;
import com.pong.graphics.online.SpecificGameButton;
import com.pong.pong.Pong;
import com.pong.states.OnlineState;
import com.pong.states.State;

public class OnlineTypeChooserState extends OnlineState {
	private SpecificGameButton s;
	private RandomGameButton r;
	private Text titleText;
	private OnlineBackButton back;

	public OnlineTypeChooserState() {
		super(State.StateType.CHOOSING_ONLINE_STATE_MODE);
		this.s = new SpecificGameButton(Pong.getPong().getWidth() / 2 + 30,
				Pong.getPong().getHeight() / 2 - RectangleButton.DEFAULT_BUTTON_HEIGHT);
		// this.s.setX(Pong.getPong().getWidth() / 2 - this.s.getWidth() / 2);

		this.r = new RandomGameButton(this.s.getX() - RectangleButton.DEFAULT_BUTTON_WIDTH - 50, this.s.getY());
		try {
			this.back = new OnlineBackButton();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		Pong.getPong().getScreen().addMouseListener(this.r);
		Pong.getPong().getScreen().addMouseListener(this.s);
		Pong.getPong().getScreen().addMouseListener(this.back);
		titleText = new Text("Choose what type of game \nyou want to join", Text.DEFAULT_FONT.deriveFont(90f),
				Pong.getPong().getWidth() / 2 - 626, 150);

	}

	@Override
	public void tick() {
		this.r.tick();
		this.s.tick();
		this.back.tick();
	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
		g.setColor(State.backgroundColor);
		g.fillRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
		g.setColor(Color.white);
		this.r.render(g);
		this.s.render(g);
		this.titleText.render(g);
		this.back.render(g);
	}

	@Override
	public void exit() {
		super.exit();
		Pong.getPong().getScreen().removeMouseListener(this.r);
		Pong.getPong().getScreen().removeMouseListener(this.s);
		Pong.getPong().getScreen().removeMouseListener(this.back);
	}

}
