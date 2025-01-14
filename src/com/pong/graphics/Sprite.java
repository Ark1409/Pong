package com.pong.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.pong.graphics.SpriteSheet.SpriteSheetException;
import com.pong.utils.PongUtils;

/**
 * The {@link com.pong.pong.Pong Pong} Sprite class. This class is used whenever
 * a {@link com.pong.graphics.Sprite Sprite} (or {@code Image}) is needed in the
 * game, or in a plugin being coded for the game. Use {@code this} instead of
 * the {@link com.pong.graphics.SpriteSheet SpriteSheet} class when you are
 * dealing with only a single Sprite in the {@link java.awt.image.BufferedImage
 * Image}.
 * 
 * @see com.pong.graphics.SpriteSheet SpriteSheet
 *
 */
public class Sprite {
	private BufferedImage i;
	private int width, height;

	/**
	 * Creates a new {@link com.pong.graphics.Sprite Sprite} from the give
	 * {@link java.awt.image.BufferedImage BufferedImage}.
	 * 
	 * @param i The {@link java.awt.image.BufferedImage BufferedImage}.
	 */
	public Sprite(BufferedImage i) {
		this.i = i;
		this.width = i.getWidth();
		this.height = i.getHeight();
	}

	/**
	 * Creates a new {@link com.pong.graphics.Sprite Sprite} from the give
	 * {@code file}, {@code width} and {@code height}.
	 * 
	 * @param f      The file thats contains the image
	 * @param width  The width of the image.
	 * @param height The height of the image.
	 * @throws IOException If the game was unable to load the file.
	 */
	public Sprite(File f, int width, int height) throws IOException {
		this.i = PongUtils.loadImage(f);
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates a new {@link com.pong.graphics.Sprite Sprite} from the give
	 * {@code path (file)}, {@code width} and {@code height}.
	 * 
	 * @param path   The path of the file
	 * @param width  The width of the image.
	 * @param height The height of the image.
	 * @throws IOException If the game was unable to load the file (from the
	 *                     {@code path} given).
	 * @see com.pong.graphics.Sprite#Sprite(File, int, int) Sprite(File, int, int)
	 */
	public Sprite(String path, int width, int height) throws IOException {
		this(new File(path), width, height);
	}

	/**
	 * 
	 * @return Returns the {@link java.awt.image.BufferedImage BufferedImage}
	 *         attached to this {@link com.pong.graphics.Sprite Sprite} class.
	 */
	public BufferedImage getImage() {
		return i;
	}

	/**
	 * Set's the {@link java.awt.image.BufferedImage BufferedImage} attached to this
	 * class.
	 * 
	 * @param i The {@link java.awt.image.BufferedImage BufferedImage}.
	 */
	public void setImage(BufferedImage i) {
		this.i = i;
	}

	/**
	 * 
	 * @return Returns the {@link com.pong.graphics.Sprite Sprite's} {@code width}.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set's the {@code width} of the {@link com.pong.graphics.Sprite Sprite}.
	 * 
	 * @param width The new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 
	 * @return Returns the {@link com.pong.graphics.Sprite Sprite's} {@code height}.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set's the {@code height} of the {@link com.pong.graphics.Sprite Sprite}.
	 * 
	 * @param height The new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @deprecated This {@code Method} doesn't know the width and height you want
	 *             for each sprite, so it just takes the sprite's width and height
	 *             to be interpreted as one {@link com.pong.graphics.Sprite Sprite}
	 *             in the {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @param s The {@link com.pong.graphics.Sprite Sprite} to be transformed.
	 * @return Transforms the given {@link com.pong.graphics.Sprite Sprite} into a
	 *         {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @see com.pong.graphics.SpriteSheet SpriteSheet
	 * @see com.pong.graphics.SpriteSheet {@link SpriteSheet#SpriteSheet(Sprite)}
	 */
	@Deprecated
	public static SpriteSheet toSpriteSheet(Sprite s) {
		// return toSpriteSheet(s, s.getWidth(), s.getHeight());
		return s.toSpriteSheet();
	}

	/**
	 * 
	 * @param sWidth  The width of each sprite in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @param sHeight The height of each sprite in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @return Transforms the current instance of {@link com.pong.graphics.Sprite
	 *         Sprite} into a {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @throws SpriteSheetException If the {@code sWidth} or {@code sHeight} is
	 *                              bigger than the {@code sprite's} width or
	 *                              height.
	 * @see com.pong.graphics.SpriteSheet SpriteSheet
	 * @see com.pong.graphics.SpriteSheet {@link SpriteSheet#SpriteSheet(Sprite)}
	 * @see com.pong.graphics.SpriteSheet
	 *      {@link SpriteSheet#SpriteSheet(Sprite,int,int)}
	 * 
	 */
	public SpriteSheet toSpriteSheet(int sWidth, int sHeight) throws SpriteSheetException {
		return new SpriteSheet(this, sWidth, sHeight);
	}

	/**
	 * Transforms the current instance of {@link com.pong.graphics.Sprite Sprite}
	 * into a {@link com.pong.graphics.SpriteSheet SpriteSheet}. It will be assumed
	 * that the width for the spritesheet is the width of this sprite, and the same
	 * for the height. You may use {@link #toSpriteSheet(int, int)} to choose the
	 * width and height. <br>
	 * This method never returns {@code null}, even if the
	 * {@link SpriteSheetException SpriteSheetException} is ignored.
	 * 
	 * @deprecated This {@code Method} doesn't know the width and height you want
	 *             for each sprite, so it just takes the sprite's width and height
	 *             to be interpreted as one {@link com.pong.graphics.Sprite Sprite}
	 *             in the {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @return Transforms the current instance of {@link com.pong.graphics.Sprite
	 *         Sprite} into a {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @see com.pong.graphics.SpriteSheet SpriteSheet
	 * @see com.pong.graphics.SpriteSheet#SpriteSheet(Sprite) SpriteSheet(Sprite)
	 * @see com.pong.graphics.SpriteSheet#SpriteSheet(Sprite, int, int)
	 *      SpriteSheet(Sprite, int, int)
	 */
	@Deprecated
	public SpriteSheet toSpriteSheet() {
		try {
			return toSpriteSheet(width, height);
		} catch (SpriteSheetException ignored) {
			return null;
		}
	}

	/**
	 * @param s       The {@link com.pong.graphics.Sprite Sprite} to be transformed.
	 * 
	 * @param sWidth  The width of each sprite in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @param sHeight The height of each sprite in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @return Transforms the given {@link com.pong.graphics.Sprite Sprite} into a
	 *         {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @throws SpriteSheetException If the {@code sWidth} or {@code sHeight} is
	 *                              bigger than the {@code sprite's} width or
	 *                              height.
	 * @see com.pong.graphics.SpriteSheet SpriteSheet
	 * @see com.pong.graphics.SpriteSheet {@link SpriteSheet#SpriteSheet(Sprite)}
	 * @see com.pong.graphics.SpriteSheet
	 *      {@link SpriteSheet#SpriteSheet(Sprite,int,int)}
	 */
	public static SpriteSheet toSpriteSheet(Sprite s, int sWidth, int sHeight) throws SpriteSheetException {
		return new SpriteSheet(s, sWidth, sHeight);
	}

	/**
	 * Creates a new {@link com.pong.graphics.Sprite Sprite} from the give
	 * {@link java.awt.image.BufferedImage BufferedImage}.
	 * 
	 * @param i The {@link java.awt.image.BufferedImage BufferedImage}.
	 * @return The created Sprite.
	 */
	public static Sprite createSprite(BufferedImage i) {
		return new Sprite(i);
	}

	/**
	 * Creates a new {@link com.pong.graphics.Sprite Sprite} from the give
	 * {@code file}, {@code width} and {@code height}.
	 * 
	 * @param f      The file thats contains the image
	 * @param width  The width of the image.
	 * @param height The height of the image.
	 * @throws IOException If the game was unable to load the file.
	 * @return The created Sprite.s
	 */
	public static Sprite createSprite(File f, int width, int height) throws IOException {
		return new Sprite(f, width, height);
	}

}
