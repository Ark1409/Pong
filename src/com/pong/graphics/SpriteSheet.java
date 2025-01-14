package com.pong.graphics;

import java.awt.image.BufferedImage;

/**
 * The {@link com.pong.pong.Pong Pong} SpriteSheet class. This class is used
 * whenever a {@link com.pong.graphics.SpriteSheet SpriteSheet} is needed in the
 * game, or in a plugin being coded for the game. Use {@code this} instead of
 * the {@link com.pong.graphics.Sprite Sprite} class when you are dealing with
 * more than one sprite in the {@link java.awt.image.BufferedImage Image}.
 * 
 * @see com.pong.graphics.Sprite Sprite
 *
 */
public class SpriteSheet {
	private Sprite[][] sprites;
	private BufferedImage i;
	private int sAmountX, sAmountY;

	/**
	 * Creates a {@link com.pong.graphics.SpriteSheet SpriteSheet} from the given
	 * {@code 2D} {@link com.pong.graphics.Sprite Sprite} {@code Array}. The amount
	 * of sprites on the {@code x axis} is {@code sprites[0].length} and the amount
	 * on the {@code y axis} is {@code sprites.length}
	 * 
	 * @deprecated Using this constructor will result in the image of the file to be
	 *             {@code null}, but the sprites in it wont be (therefore, you will
	 *             be unable to use the method 'toImage()', you may only use
	 *             'toImage()' with accessing a sprite in the sprites array)
	 * @param sprites The {@code 2D} {@link com.pong.graphics.Sprite Sprite}
	 *                {@code Array} used for this instance.
	 */
	@Deprecated
	public SpriteSheet(Sprite[][] sprites) {
		this.sprites = sprites;
		this.i = null;
		this.sAmountX = this.sprites[0].length;
		this.sAmountY = this.sprites.length;
	}

	/**
	 * Creates a {@link com.pong.graphics.SpriteSheet SpriteSheet} from the given
	 * {@link java.awt.image.BufferedImage BufferedImage}. The amount of sprites on
	 * the {@code x axis} is {@code sprites[0].length} and the amount on the
	 * {@code y axis} is {@code sprites.length}
	 * 
	 * @deprecated This constructor won't check if the sprites in the
	 *             {@code sprite array} and the sprites in the {@code BufferedImage}
	 *             match; make sure that if you're using this constructor, the two
	 *             match.
	 * @param i       The {@link java.awt.image.BufferedImage BufferedImage}
	 * @param sprites All the {@link com.pong.graphics.Sprite Sprites} in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 */
	@Deprecated
	public SpriteSheet(BufferedImage i, Sprite[][] sprites) {
		this.sprites = sprites;
		this.i = i;
		this.sAmountX = this.sprites[0].length;
		this.sAmountY = this.sprites.length;
	}

	/**
	 * Creates a {@link com.pong.graphics.SpriteSheet SpriteSheet} from the given
	 * sprite. It will use the sprite's width as the width per sprite and the sprite
	 * height as the height per sprite.
	 * 
	 * @deprecated This {@code Method} doesn't know the width and height you want
	 *             for each sprite, so it just takes the sprite's width and height
	 *             to be interpreted as one {@link com.pong.graphics.Sprite Sprite}
	 *             in the {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * 
	 * @param s The sprite you want to transform into a
	 *          {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 */
	@Deprecated
	public SpriteSheet(Sprite s) {
		this.sprites = new Sprite[][] { { s } };
		this.i = s.getImage();
		this.sAmountX = 1;
		this.sAmountY = 1;
	}

	/**
	 * Creates a {@link com.pong.graphics.SpriteSheet SpriteSheet} from the
	 * {@link com.pong.graphics.Sprite Sprite} class given. The
	 * {@link java.awt.image.BufferedImage Image} for this
	 * {@link com.pong.graphics.SpriteSheet SpriteSheet} becomes the
	 * {@link java.awt.image.BufferedImage Image} from the
	 * {@link com.pong.graphics.Sprite Sprite}.
	 * 
	 * @param s       The {@link com.pong.graphics.Sprite Sprite} you want to
	 *                transform into a {@link com.pong.graphics.SpriteSheet
	 *                SpriteSheet}.
	 * @param sWidth  The width per {@link com.pong.graphics.Sprite Sprite} in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @param sHeight The height per {@link com.pong.graphics.Sprite Sprite} in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @throws SpriteSheetException
	 */
	public SpriteSheet(Sprite s, int sWidth, int sHeight) throws SpriteSheetException {
		if (s.getWidth() == sWidth && s.getHeight() == sHeight) {
			this.sprites = new Sprite[][] { { s } };
			this.i = s.getImage();
			this.sAmountX = 1;
			this.sAmountY = 1;
			return;
		}
		if (sWidth > s.getWidth() || sHeight > s.getHeight()) {
			throw new SpriteSheetException(
					"SpriteSheet width/height must be the same or smaller than the Sprite width/height");
		}
		this.i = s.getImage();
		this.sAmountX = i.getWidth() / sWidth;
		this.sAmountY = i.getHeight() / sHeight;
		sprites = new Sprite[this.sAmountX][this.sAmountY];
		for (int y = 0; y < sAmountY; y++) {
			for (int x = 0; x < sAmountX; x++) {
				sprites[x][y] = new Sprite(this.i.getSubimage(x * sWidth, y * sWidth, sWidth, sHeight));
			}
		}
	}

	/**
	 * Creates a {@link com.pong.graphics.SpriteSheet SpriteSheet} from the
	 * {@link java.awt.image.BufferedImage BufferedImage} given.
	 * 
	 * @param i       The {@link java.awt.image.BufferedImage BufferedImage}
	 * @param sWidth  The width per {@link com.pong.graphics.Sprite Sprite} in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 * @param sHeight The height per {@link com.pong.graphics.Sprite Sprite} in the
	 *                {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 */
	public SpriteSheet(BufferedImage i, int sWidth, int sHeight) {
		this.i = i;
		this.sAmountX = i.getWidth() / sWidth;
		this.sAmountY = i.getHeight() / sHeight;
		sprites = new Sprite[this.sAmountX][this.sAmountY];
		for (int y = 0; y < sAmountY; y++) {
			for (int x = 0; x < sAmountX; x++) {
				sprites[x][y] = new Sprite(this.i.getSubimage(x * sWidth, y * sWidth, sWidth, sHeight));
			}
		}
	}

	/**
	 * 
	 * @param id The {@link com.pong.graphics.Sprite Sprite} {@code ID} relative to
	 *           the file.
	 * @return Returns the {@link com.pong.graphics.Sprite Sprite} from the given
	 *         id.
	 *         <p>
	 *         It goes from left to right, then up to down. So, if you had {@code 3}
	 *         {@link com.pong.graphics.Sprite Sprites} on 1 row (left to right) and
	 *         {@code 2} on the next row (left to right, under the first row), then
	 *         if you entered sprite {@code 4}, it would give you the {@code 1st}
	 *         {@link com.pong.graphics.Sprite Sprite} on the {@code 2nd} row.
	 *         </p>
	 * @see com.pong.graphics.SpriteSheet#getSprite(int, int)
	 *      SpriteSheet.getSprite(int, int)
	 */
	public Sprite getSprite(int id) {
		return getSprite(id % sAmountX, id / sAmountX);
	}

	/**
	 * 
	 * @param x The {@link com.pong.graphics.Sprite Sprite} {@code X} relative to
	 *          the file (row).
	 * @param y The {@link com.pong.graphics.Sprite Sprite} {@code Y} relative to
	 *          the file (column).
	 * @return Returns the {@link com.pong.graphics.Sprite Sprite} from the given
	 *         {@code X} and the given {@code Y} relative to the file (or
	 *         {@link java.awt.image.BufferedImage Image}).
	 *         <p>
	 *         The {@code X} value is the row (left to right) and the {@code Y}
	 *         value is the column (up to down).
	 *         </p>
	 * @see com.pong.graphics.Sprite Sprite
	 */
	public Sprite getSprite(int x, int y) {
		return sprites[x][y];
	}

	/**
	 * 
	 * @return Returns all the images in the {@link java.awt.image.BufferedImage
	 *         BufferedImage}, converted to a {@code 2D Array} of
	 *         {@link com.pong.graphics.Sprite Sprites}.
	 * @see com.pong.graphics.Sprite Sprite
	 */
	public Sprite[][] getSprites() {
		return sprites;
	}

	/**
	 * 
	 * @param row The row that contains all the {@link com.pong.graphics.Sprite
	 *            Sprites}.
	 * @return Returns a {@code 1D Array} of {@link com.pong.graphics.Sprite
	 *         Sprites} representing the row of {@link java.awt.image.BufferedImage
	 *         Images} specified.
	 * @see com.pong.graphics.Sprite Sprite
	 */
	public Sprite[] getSprites(int row) {
		return sprites[row];
	}

	/**
	 * Sets the {@link com.pong.graphics.Sprite Sprite} {@code Array} used to
	 * reference each {@link com.pong.graphics.Sprite
	 * Sprite}/{@link java.awt.image.BufferedImage Image} in the file to the
	 * specified {@code 2D} {@link com.pong.graphics.Sprite Sprite} {@code Array}.
	 * 
	 * @deprecated The sprites in this array may not match the width/height of the
	 *             sprites in the actual {@link java.awt.image.BufferedImage Image},
	 *             so be careful when using this {@code Method}.
	 * @param sprites The {@code 2D Array} you want to set the sprites too.
	 * @see com.pong.graphics.Sprite Sprite
	 */
	@Deprecated
	public void setSprites(Sprite[][] sprites) {
		this.sprites = sprites;
	}

	/**
	 * 
	 * @return Returns the {@link com.pong.graphics.SpriteSheet SpriteSheet} as a
	 *         {@link java.awt.image.BufferedImage Image}.
	 */
	public BufferedImage getImage() {
		return this.i;
	}

	/**
	 * 
	 * @return Returns the {@code width} of the entire
	 *         {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 */
	public int getWidth() {
		return this.i.getWidth();
	}

	/**
	 * 
	 * @return Returns the {@code height} of the entire
	 *         {@link com.pong.graphics.SpriteSheet SpriteSheet}.
	 */
	public int getHeight() {
		return this.i.getHeight();
	}

	/**
	 * 
	 * @return Returns the {@code width} of each sprite in the
	 *         {@link com.pong.graphics.SpriteSheet SpriteSheet}, specified in the
	 *         {@link com.pong.graphics.SpriteSheet#SpriteSheet(BufferedImage, int, int)
	 *         SpriteSheet Constructor}.
	 */
	public int getWidthPerSprite() {
		return this.sAmountX;
	}

	/**
	 * 
	 * @return Returns the {@code height} of each sprite in the
	 *         {@link com.pong.graphics.SpriteSheet SpriteSheet}, specified in the
	 *         {@link com.pong.graphics.SpriteSheet#SpriteSheet(BufferedImage, int, int)
	 *         SpriteSheet Constructor}.
	 */
	public int getHeightPerSprite() {
		return this.sAmountY;
	}

	/**
	 * 
	 * @return Returns the {@link com.pong.graphics.SpriteSheet SpriteSheet} as a
	 *         {@link com.pong.graphics.Sprite Sprite}.
	 */
	public Sprite toSprite() {
		return new Sprite(this.i);
	}

	@Override
	public String toString() {
		return "{[spriteAmount=" + size() + ", width=" + this.getWidth() + ", height=" + this.getHeight()
				+ ", widthPerSprite=" + this.getWidthPerSprite() + ", heightPerSprite=" + this.getHeightPerSprite()
				+ ", image=" + this.getImage().toString() + "][" + super.toString() + "]}";
	}

	/**
	 * 
	 * @param s The {@link com.pong.graphics.SpriteSheet SpriteSheet} that's being
	 *          transformed.
	 * @return Transforms the given {@link com.pong.graphics.SpriteSheet
	 *         SpriteSheet} into a {@link com.pong.graphics.Sprite Sprite}.
	 */
	public static Sprite toSprite(SpriteSheet s) {
		return s.toSprite();
	}

	/**
	 * 
	 * @return Returns the amount of {@link com.pong.graphics.Sprite Sprites} in the
	 *         {@link java.awt.image.BufferedImage Image}.
	 */
	public int size() {
		int i = 0;
		for (int i1 = 0; i1 < getSprites().length; i1++) {
			for (int i2 = 0; i2 < getSprites()[i1].length; i2++) {
				i++;
			}
		}
		return i;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	@Deprecated
	public static Sprite toSprite(Sprite[][] s) {
		return new SpriteSheet(s).toSprite();
	}

	@Deprecated
	public static Sprite toSprite(BufferedImage i, Sprite[][] s) {
		return new SpriteSheet(i, s).toSprite();
	}

	public static Sprite toSprite(BufferedImage i, int sWidth, int sHeight) {
		return new SpriteSheet(i, sWidth, sHeight).toSprite();
	}

	@Deprecated
	public static SpriteSheet createSpriteSheet(Sprite s) {
		return new SpriteSheet(s);
	}

	/**
	 * 
	 * @param s       The {@link com.pong.graphics.Sprite Sprite} that is going to
	 *                be transformed into a {@link com.pong.graphics.SpriteSheet
	 *                SpriteSheet}.
	 * @param sWidth  The {@code width} of each {@link com.pong.graphics.Sprite
	 *                Sprite} in the {@link com.pong.graphics.SpriteSheet
	 *                SpriteSheet}.
	 * @param sHeight The {@code height} of each {@link com.pong.graphics.Sprite
	 *                Sprite} in the {@link com.pong.graphics.SpriteSheet
	 *                SpriteSheet}.
	 * @return Creates a {@link com.pong.graphics.SpriteSheet SpriteSheet
	 *         SpriteSheet} with the information given. Please note that this does
	 *         the same thing as if you used the
	 *         {@link com.pong.graphics.SpriteSheet#SpriteSheet(Sprite, int, int)
	 *         SpriteSheet(Sprite, int, int)} {@code Constructor}.
	 * @throws SpriteSheetException If the {@code sWidth} or {@code sHeight} is
	 *                              bigger than the {@code sprite's} width or
	 *                              height.
	 */
	public static SpriteSheet createSpriteSheet(Sprite s, int sWidth, int sHeight) throws SpriteSheetException {
		return new SpriteSheet(s, sWidth, sHeight);
	}

	@Deprecated
	public static SpriteSheet createSpriteSheet(Sprite[][] s) {
		return new SpriteSheet(s);
	}

	@Deprecated
	public static SpriteSheet createSpriteSheet(BufferedImage i, Sprite[][] s) {
		return new SpriteSheet(i, s);
	}

	public static SpriteSheet createSpriteSheet(BufferedImage i, int sWidth, int sHeight) {
		return new SpriteSheet(i, sWidth, sHeight);
	}

	/**
	 * Exception for {@link SpriteSheet SpriteSheets}.
	 */
	public final class SpriteSheetException extends Exception {
		private static final long serialVersionUID = 3879841200339442394L;

		/**
		 * Constructs a SpriteSheetException.
		 */
		public SpriteSheetException() {
			super();
		}

		/**
		 * Constructs a SpriteSheetException with the specified message.
		 * 
		 * @param The exception's message.
		 */
		public SpriteSheetException(String message) {
			super(message);
		}

	}

}
