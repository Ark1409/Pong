package com.pong.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import com.pong.pong.Pong;

public final class PongUtils {

	private PongUtils() {
	}

	public static Font loadFont(InputStream i) {
		return loadFont(i, 100.0f);
	}

	public static Font loadFont(InputStream i, float size) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, i).deriveFont(size);
		} catch (FontFormatException | IOException e) {
			Logger.logError("Unable to load font from InputStream.\n");
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage loadImage(String path) throws IOException {
		return loadImage(path, Pong.class);
	}

	public static <T> BufferedImage loadImage(String path, Class<T> source) throws IOException {
		return loadImage(new File(path), source);
	}

	public static BufferedImage loadImage(URL url) throws IOException {
		return ImageIO.read(url);
	}

	public static BufferedImage loadImage(File f) throws IOException {
		return loadImage(f, PongUtils.class);

	}

	public static <T> BufferedImage loadImage(File f, Class<T> source) throws IOException {
		try {
			return loadImage(f.toURI().toURL());
		} catch (Exception e) {
			return loadImage(source.getResource(f.getPath().replaceAll("\\\\", "/")));
		}

	}

	public static BufferedImage loadImage(ImageInputStream iis) throws IOException {
		return ImageIO.read(iis);
	}

	public static BufferedImage loadImage(InputStream is) throws IOException {
		return ImageIO.read(is);
	}

	public static int clamp(int min, int max, int value) {
		if (value > max) {
			return max;
		}
		if (value < min) {
			return min;
		}
		return value;
	}

}
