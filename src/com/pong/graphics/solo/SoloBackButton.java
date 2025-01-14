package com.pong.graphics.solo;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.pong.graphics.ImageButton;
import com.pong.graphics.Sprite;
import com.pong.graphics.SpriteSheet;
import com.pong.pong.Pong;
import com.pong.states.MenuState;
import com.pong.utils.PongUtils;

public class SoloBackButton extends ImageButton {

	public static final int SOLO_BACK_BUTTON_IMAGE_SIZE = 50;

	public SoloBackButton(float x, float y) throws IOException {
		super(new SpriteSheet(PongUtils.loadImage("/images/back-arrow.png"), 16, 16), x, y);

	}

	public SoloBackButton() throws IOException {
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
		Sprite next = this.nextImage();
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
			g.drawImage(this.getCurrentSprite().getImage(), (int) x, (int) y, SOLO_BACK_BUTTON_IMAGE_SIZE,
					SOLO_BACK_BUTTON_IMAGE_SIZE, null);
		} catch (Exception e) {

		}
	}

	@Override
	public Rectangle toRectangle() {
		return new Rectangle((int) x + 18, (int) y + 16, 30, 28);
	}

}
