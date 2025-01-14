package com.pong.graphics;

import java.awt.Canvas;
import java.awt.Dimension;

public class Screen extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width, height;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		setSize(this.width, this.height);
		setMinimumSize(new Dimension(this.width, this.height));
		setMaximumSize(new Dimension(this.width, this.height));
		setFocusable(true);
	}

	@Override
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		setSize(this.width, this.height);
		setMinimumSize(new Dimension(this.width, this.height));
		setMaximumSize(new Dimension(this.width, this.height));
	}

	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		setSize(this.width, this.height);
		setMinimumSize(new Dimension(this.width, this.height));
		setMaximumSize(new Dimension(this.width, this.height));
	}

}
