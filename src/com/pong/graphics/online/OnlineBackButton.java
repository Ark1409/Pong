package com.pong.graphics.online;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.pong.graphics.ImageButton;
import com.pong.graphics.SpriteSheet;
import com.pong.pong.Pong;
import com.pong.states.MenuState;
import com.pong.utils.PongUtils;

public class OnlineBackButton extends ImageButton {
	public static final int ONLINE_BACK_BUTTON_IMAGE_SIZE = 50;

	public OnlineBackButton(float x, float y) throws IOException {
		super(new SpriteSheet(PongUtils.loadImage("/images/back-arrow.png"), 16, 16), x, y);

	}

	public OnlineBackButton() throws IOException {
		this(10, 10);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.clickable) {
			Pong.getPong().setCurrentState(new MenuState());
			this.clickable = false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void init() {
	}

	@Override
	public void render(Graphics2D g) {
		if (this.clickable) {
			this.setIndex(1);
		} else {
			this.setIndex(0);
		}
		try {
			g.drawImage(this.getCurrentSprite().getImage(), (int) x, (int) y, ONLINE_BACK_BUTTON_IMAGE_SIZE,
					ONLINE_BACK_BUTTON_IMAGE_SIZE, null);
		} catch (Exception e) {

		}
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public Rectangle toRectangle() {
		return new Rectangle((int) x + 18, (int) y + 16, 30, 28);
	}

}
