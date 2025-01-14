package com.pong.graphics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.pong.graphics.SpriteSheet.SpriteSheetException;
import com.pong.pong.Pong;
import com.pong.utils.PongUtils;

public abstract class ImageButton extends GUIButton {
	private SpriteSheet s;
	private Sprite currentImage;
	private int id;
	public static final int DEFAULT_IMAGE_ID = 0;

	@Deprecated
	public ImageButton(Sprite s, float x, float y) {
		this(s, x, y, DEFAULT_IMAGE_ID);
	}

	@Deprecated
	public ImageButton(Sprite s, float x, float y, int index) {
		this(s, x, y, s.getWidth(), s.getHeight(), index);
	}

	public ImageButton(Sprite s, float x, float y, int sWidth, int sHeight) {
		this(s, x, y, sWidth, sHeight, DEFAULT_IMAGE_ID);
	}

	public ImageButton(Sprite s, float x, float y, int sWidth, int sHeight, int index) {
		super(x, y, sWidth, sHeight);
		try {
			this.s = new SpriteSheet(s, sWidth, sHeight);
		} catch (SpriteSheetException e) {
			e.printStackTrace();
		}
		this.id = 0;
		this.currentImage = this.s.getSprite(index);
	}

	public ImageButton(SpriteSheet s, float x, float y) {
		this(s, x, y, DEFAULT_IMAGE_ID);
	}

	public ImageButton(SpriteSheet s, float x, float y, int index) {
		super(x, y, s.getWidthPerSprite(), s.getHeightPerSprite());
		this.s = s;
		this.id = 0;
		this.currentImage = this.s.getSprite(index);
	}

	@Deprecated
	public ImageButton(Sprite[][] s, float x, float y) {
		this(new SpriteSheet(s), x, y);
	}

	@Deprecated
	public ImageButton(BufferedImage i, Sprite[][] s, float x, float y) {
		this(new SpriteSheet(i, s), x, y);
	}

	public ImageButton(BufferedImage i, float x, float y) {
		this(new SpriteSheet(i, i.getWidth(), i.getHeight()), x, y);
	}

	public ImageButton(BufferedImage i, int spriteWidth, int spriteHeight, float x, float y) {
		this(new SpriteSheet(i, spriteWidth, spriteHeight), x, y);
	}

	@Override
	public int getWidth() {
		return s.getWidth();
	}

	@Override
	public int getHeight() {
		return s.getHeight();
	}

	/**
	 * Changes the current {@link Sprite image} and the current image index to the next image.
	 * 
	 * @return The next {@link Sprite image}.
	 */
	public Sprite nextImage() {
		try {
			return this.currentImage = getSprite(++id);
		} catch (ArrayIndexOutOfBoundsException e) {
			return this.currentImage = getSprite(this.id = 0);
		}
	}

	/**
	 * Gets the next {@link Sprite image}, without changing the index.
	 * 
	 * @return The next {@link Sprite image}.
	 */
	public Sprite upcomingImage() {
		try {
			return getSprite(id + 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO fix this
			return getSprite(0);
		}
	}

	public Sprite setIndex(int id) {
		try {
			return this.currentImage = getSprite(this.id = id);
		} catch (ArrayIndexOutOfBoundsException e) {
			return this.currentImage = getSprite(this.id = PongUtils.clamp(0, spriteSize(), id));
		}
	}

	public Sprite setIndex(int x, int y) {
		try {
			return this.currentImage = getSprite(this.id = transformToID(x, y));
		} catch (ArrayIndexOutOfBoundsException e) {
			if (getSprites()[0].length == 1) {
				return this.currentImage = getSprite(0, PongUtils.clamp(0, getSprites()[0].length, y));
			}

			return this.currentImage = getSprite(PongUtils.clamp(0, getSprites().length - 1, x),
					PongUtils.clamp(0, getSprites()[0].length - 1, y));
		}
	}

	/**
	 * Set's the current image displayed by this
	 * {@link com.pong.graphics.ImageButton ImageButton} to be the once chosen.
	 * 
	 * @deprecated The {@code Image ID} won't change, which means you may not get
	 *             the result expected when using
	 *             {@link com.pong.graphics.ImageButton#nextImage() nextImage()}.
	 * @param s The {@link com.pong.graphics.Sprite Sprite}.
	 * @return Returns the current image displayed by this
	 *         {@link com.pong.graphics.ImageButton ImageButton}.
	 * @see com.pong.graphics.Sprite Sprite
	 */
	@Deprecated
	public Sprite setImage(Sprite s) {
		return this.currentImage = s;
	}

	public Sprite getSprite(int id) {
		return this.s.getSprite(id);
	}

	public Sprite getSprite(int x, int y) {
		return this.s.getSprite(x, y);
	}

	public Sprite[][] getSprites() {
		return this.s.getSprites();
	}

	@Deprecated
	public void setSprites(Sprite[][] sprites) {
		this.s.setSprites(sprites);
	}

	public BufferedImage getImage() {
		return this.s.getImage();
	}

	public int getWidthPerSprite() {
		return this.s.getWidthPerSprite();
	}

	public int getHeightPerSprite() {
		return s.getHeightPerSprite();
	}

	public Sprite getCurrentSprite() {
		return currentImage;
	}

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

	public Rectangle toRectangle() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public SpriteSheet getSpriteSheet() {
		return this.s;
	}

	@Override
	public String toString() {
		return this.s.toString() + "{x=" + this.x + ", y=" + this.y + "image=" + this.s.getImage().toString() + "}";
	}

	private int transformToID(int x, int y) {
		return (int) Math.ceil((double) x * this.getWidthPerSprite());
	}

	public int spriteSize() {
		return this.s.size();
	}

}
