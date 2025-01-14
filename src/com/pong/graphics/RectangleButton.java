package com.pong.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.pong.pong.Pong;

public abstract class RectangleButton extends GUIButton {
	protected Rectangle r;
	protected String text;
	protected Color c;
	// new Font("Century Gothic", Font.BOLD, 30)
	public static final Font DEFAULT_FONT = Pong.getPong().getDefaultFont().deriveFont(30.0f);
	public static final Color DEFAULT_TEXT_COLOR = Color.white;
	protected static String DEFAULT_TEXT = "";
	protected Font f;
	protected boolean fill;
	protected boolean centered;

	public static final int DEFAULT_BUTTON_WIDTH = 250;
	public static final int DEFAULT_BUTTON_HEIGHT = 50;

	public RectangleButton(Color c, float x, float y, int width, int height) {
		this(c, x, y, width, height, false);
	}

	public RectangleButton(Color c, float x, float y, int width, int height, String text) {
		this(c, x, y, width, height, false, text);
	}

	public RectangleButton(Color c, float x, float y, int width, int height, boolean fill, String text) {
		this(c, x, y, width, height, fill, true, text);
	}

	public RectangleButton(Color c, float x, float y, int width, int height, boolean fill) {
		this(c, DEFAULT_FONT, x, y, width, height, fill, true);
	}

	public RectangleButton(Color c, Font f, float x, float y, int width, int height) {
		this(c, f, x, y, width, height, false, true);
	}

	public RectangleButton(Color c, float x, float y, int width, int height, boolean fill, boolean centered) {
		this(c, DEFAULT_FONT, x, y, width, height, fill, centered);
	}

	public RectangleButton(Color c, Font f, float x, float y, int width, int height, boolean fill, boolean centered) {
		this(c, f, x, y, width, height, fill, centered, DEFAULT_TEXT);
	}

	public RectangleButton(Color c, float x, float y, int width, int height, boolean fill, boolean centered,
			String text) {
		this(c, DEFAULT_FONT, x, y, width, height, fill, centered, text);
	}

	public RectangleButton(Color c, Font f, float x, float y, int width, int height, boolean fill, boolean centered,
			String text) {
		super(x, y, width, height);
		this.r = new Rectangle((int) x, (int) y, width, height);
		this.text = text;
		this.c = c;
		this.f = f;
		this.fill = fill;
		this.centered = centered;

	}

	public Rectangle toRectangle() {
		r = new Rectangle((int) x, (int) y, width, height);
		return r;
	}

	public void setRectangle(Rectangle r) {
		this.r = r;
		this.x = (float) r.getX();
		this.y = (float) r.getY();
		this.width = (int) r.getWidth();
		this.height = (int) r.getHeight();
	}

	public void setText(String s) {
		this.text = s;
	}

	public String getText() {
		return text;
	}

	public boolean hasText() {
		return (text != null) || !(text.equalsIgnoreCase(""));
	}

	public Color getColor() {
		return this.c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public Font getFont() {
		return f;
	}

	public void setFont(Font f) {
		this.f = f;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}

	public boolean isClickable() {
		return this.clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(c);
		if (isFill()) {
			if (clickable) {
				g.setColor(Color.black);
				g.fillRect((int) x, (int) y, width, height);
				g.setFont(f);
				int stringWidth = (int) g.getFontMetrics().getStringBounds(getText(), g).getWidth();
				int stringHeight = (int) g.getFontMetrics().getStringBounds(getText(), g).getHeight();
				g.setColor(Color.white);
				g.drawString(getText(), (int) (x + width / 2 - stringWidth / 2),
						(int) ((y + height / 2 + stringHeight / 2) - g.getFontMetrics().getAscent() / 4));
			} else {
				g.fillRect((int) x, (int) y, width, height);
				g.setFont(f);
				int stringWidth = (int) g.getFontMetrics().getStringBounds(getText(), g).getWidth();
				int stringHeight = (int) g.getFontMetrics().getStringBounds(getText(), g).getHeight();
				g.setColor(Color.black);
				g.drawString(getText(), (int) (x + width / 2 - stringWidth / 2),
						(int) ((y + height / 2 + stringHeight / 2) - g.getFontMetrics().getAscent() / 4));
			}
		} else {
			g.drawRect((int) x, (int) y, width, height);
			g.setFont(f);
			int stringWidth = (int) g.getFontMetrics().getStringBounds(getText(), g).getWidth();
			int stringHeight = (int) g.getFontMetrics().getStringBounds(getText(), g).getHeight();
			g.setColor(Color.black);
			g.drawString(getText(), (int) (x + width / 2 - stringWidth / 2),
					(int) ((y + height / 2 + stringHeight / 2) - g.getFontMetrics().getAscent() / 4));
		}

	}

	@Override
	public void tick() {
		mouse = Pong.getPong().getWindow().getScreen().getMousePosition();
		if (mouse != null) {
			if (new Rectangle(mouse.x, mouse.y, MOUSE_DETECTION_SIZE, MOUSE_DETECTION_SIZE)
					.intersects(this.toRectangle())) {
				clickable = true;
			} else {
				clickable = false;
			}
		}
	}

}
