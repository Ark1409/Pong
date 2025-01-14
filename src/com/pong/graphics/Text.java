package com.pong.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.pong.pong.Pong;

public class Text {
	private String s;
	private Color c;
	private Font f;

	/**
	 * Only to be used when a empty space is needed. Do not use for things relative
	 * to positioning (empty text will display at 0,0)
	 */
	public static final Text EMPTY_TEXT = new Text("", 0f, 0f);

	/**
	 * Only to be used when a blank space is needed. Do not use for things relative
	 * to positioning (blank text will display at 0,0)
	 */
	public static final Text BLANK_TEXT = new Text(" ", 0f, 0f);

	public static final Color DEFAULT_COLOR = Color.white;

	public static final Font DEFAULT_FONT = Pong.getPong().getDefaultFont().deriveFont(25.0f);

	private float x, y;
	private boolean blank;

	public Text(Color c, float x, float y) {
		this(c, DEFAULT_FONT, x, y);

	}

	public Text(float x, float y) {
		this(Color.white, DEFAULT_FONT, x, y);
	}

	public Text(Font f, float x, float y) {
		this(Color.white, DEFAULT_FONT, x, y);
	}

	public Text(String s, float x, float y) {
		this(s, Color.white, DEFAULT_FONT, x, y);
	}

	public Text(Color c, Font f, float x, float y) {
		this.s = Text.EMPTY_TEXT.toString();
		this.x = x;
		this.y = y;
		this.blank = true;
		this.f = f;
		this.c = c;
	}

	public Text(String s, Color c, Font f, float x, float y) {
		this.s = s;
		this.x = x;
		this.y = y;
		this.blank = false;
		this.f = f;
		this.c = c;
	}

	public Text(Color c, Font f, Text t, float x, float y) {
		this.s = t.toString();
		this.x = x;
		this.y = y;
		this.blank = t.isBlank();
		this.c = c;
		this.f = f;
	}

	public Text(Text t) {
		this(t, t.getX(), t.getY());
	}

	public Text(Text t, float x, float y) {
		this.s = t.toString();
		this.x = x;
		this.y = y;
		this.blank = t.isBlank();
		this.c = t.getColor();
		this.f = t.getFont();
	}

	public Text(Text t, float x, float y, Color c) {
		this.s = t.toString();
		this.x = x;
		this.y = y;
		this.blank = t.isBlank();
		this.c = c;
		this.f = t.getFont();
	}

	public Text(Text t, float x, float y, Color c, Font f) {
		this.s = t.toString();
		this.x = x;
		this.y = y;
		this.blank = t.isBlank();
		this.c = c;
		this.f = f;
	}

	public Text(byte[] bytes, float x, float y) {
		this(DEFAULT_COLOR, DEFAULT_FONT, bytes, x, y);
	}

	public Text(Color c, Font f, byte[] bytes, float x, float y) {
		this.s = new String(bytes);
		this.x = x;
		this.y = y;
		this.blank = false;
		this.c = c;
		this.f = f;
	}

	public Text(int[] codePoints, int offset, int count, float x, float y) {
		this(DEFAULT_COLOR, DEFAULT_FONT, codePoints, offset, count, x, y);
	}

	public Text(Color c, Font f, int[] codePoints, int offset, int count, float x, float y) {
		this.s = new String(codePoints, offset, count);
		this.x = x;
		this.y = y;
		this.blank = false;
		this.f = f;
		this.c = c;
	}

	@Deprecated
	public Text(byte[] ascii, int hibyte, int offset, int count, float x, float y) {
		this.s = new String(ascii, hibyte, offset, count);
		this.x = x;
		this.y = y;
		this.blank = false;
	}

	public Text(String s, Font f, float x, float y) {
		this.s = s;
		this.c = DEFAULT_COLOR;
		this.x = x;
		this.y = y;
		this.f = f;
		this.blank = s.isBlank() || s.isEmpty();
	}

	public int length() {
		return this.s.length();
	}

	public boolean isEmpty() {
		return this.s.isEmpty();
	}

	public char charAt(int index) {
		return this.s.charAt(index);
	}

	public int codePointAt(int index) {
		return this.s.codePointAt(index);
	}

	public int codePointBefore(int index) {
		return this.s.codePointBefore(index);
	}

	public int codePointCount(int beginIndex, int endIndex) {
		return this.s.codePointCount(beginIndex, endIndex);
	}

	public int offsetByCodePoints(int index, int codePointOffset) {
		return this.s.offsetByCodePoints(index, codePointOffset);
	}

	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		this.s.getChars(srcBegin, srcEnd, dst, dstBegin);
	}

	@Deprecated
	public void getBytes(int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
		this.s.getBytes(srcBegin, srcEnd, dst, dstBegin);
	}

	public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
		return this.s.getBytes(charsetName);
	}

	public byte[] getBytes(Charset charset) {
		return this.s.getBytes(charset);
	}

	public byte[] getBytes() {
		return this.s.getBytes();
	}

	public boolean equals(Object anObject) {
		return this.s.equals(anObject);
	}

	public boolean contentEquals(StringBuffer sb) {
		return this.s.contentEquals(sb);
	}

	public boolean contentEquals(CharSequence cs) {
		return this.s.contentEquals(cs);
	}

	public boolean equalsIgnoreCase(String anotherString) {
		return this.s.equalsIgnoreCase(anotherString);
	}

	public int compareTo(String anotherString) {
		return this.s.compareTo(anotherString);
	}

	public int compareToIgnoreCase(String str) {
		return this.s.compareToIgnoreCase(str);
	}

	public boolean regionMatches(int toffset, String other, int ooffset, int len) {
		return this.s.regionMatches(toffset, other, ooffset, len);
	}

	public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
		return this.s.regionMatches(ignoreCase, toffset, other, ooffset, len);
	}

	public boolean startsWith(String prefix, int toffset) {
		return this.s.startsWith(prefix, toffset);
	}

	public boolean startsWith(String prefix) {
		return this.s.startsWith(prefix);
	}

	public boolean endsWith(String suffix) {
		return this.s.endsWith(suffix);
	}

	public int hashCode() {
		return this.s.hashCode();
	}

	public int indexOf(int ch) {
		return this.s.indexOf(ch);
	}

	public int indexOf(int ch, int fromIndex) {
		return this.s.indexOf(ch, fromIndex);
	}

	public int lastIndexOf(int ch) {
		return this.s.lastIndexOf(ch);
	}

	public int lastIndexOf(int ch, int fromIndex) {
		return this.s.lastIndexOf(ch, fromIndex);
	}

	public int indexOf(String str) {
		return this.s.indexOf(str);
	}

	public int indexOf(String str, int fromIndex) {
		return this.s.indexOf(str, fromIndex);
	}

	public int lastIndexOf(String str) {
		return this.s.lastIndexOf(str);
	}

	public int lastIndexOf(String str, int fromIndex) {
		return this.s.lastIndexOf(str, fromIndex);
	}

	public Text substring(int beginIndex) {
		this.s = this.s.substring(beginIndex);
		return this;
	}

	public Text substring(int beginIndex, int endIndex) {
		this.s = this.s.substring(beginIndex, endIndex);
		return this;
	}

	public CharSequence subSequence(int beginIndex, int endIndex) {
		return this.s.subSequence(beginIndex, endIndex);
	}

	public Text concat(String str) {
		this.s = this.s.concat(str);
		return this;
	}

	public Text replace(char oldChar, char newChar) {
		this.s = this.s.replace(oldChar, newChar);
		return this;
	}

	public boolean matches(String regex) {
		return this.s.matches(regex);
	}

	public boolean contains(CharSequence s) {
		return this.s.contains(s);
	}

	public Text replaceFirst(String regex, String replacement) {
		this.s = this.s.replaceFirst(regex, replacement);
		return this;
	}

	public Text replaceAll(String regex, String replacement) {
		this.s = this.s.replaceAll(regex, replacement);
		return this;
	}

	public Text replace(CharSequence target, CharSequence replacement) {
		this.s = this.s.replace(target, replacement);
		return this;
	}

	public Text replaceLast(String regex, String replacement) {
		this.s = this.s.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
		return this;
	}

	public Text replaceLast(Text regex, Text replacement) {
		this.s = this.s.replaceFirst("(?s)" + regex.toString() + "(?!.*?" + regex.toString() + ")",
				replacement.toString());
		return this;
	}

	public Text replaceLast(String regex, Text replacement) {
		this.s = this.s.replaceFirst("(?s)" + regex.toString() + "(?!.*?" + regex.toString() + ")",
				replacement.toString());
		return this;
	}
	
	public Text replaceLast(Text regex, String replacement) {
		this.s = this.s.replaceFirst("(?s)" + regex.toString() + "(?!.*?" + regex.toString() + ")",
				replacement);
		return this;
	}

	public Text[] split(String regex, int limit, float x, float y) {
		String[] a = this.s.split(regex, limit);
		Text[] t = new Text[a.length];
		for (int i = 0; i < a.length; i++) {
			t[i] = new Text(a[i], x, y);
		}
		return t;
	}

	public Text[] split(String regex, int limit) {
		return split(regex, limit, this.x, this.y);
	}

	public Text[] split(String regex) {
		return split(regex, this.x, this.y);
	}

	public Text[] split(String regex, float x, float y) {
		String[] a = this.s.split(regex);
		Text[] t = new Text[a.length];
		for (int i = 0; i < a.length; i++) {
			t[i] = new Text(a[i], x, y);
		}
		return t;
	}

	public Text toLowerCase(Locale locale) {
		this.s = this.s.toLowerCase(locale);
		return this;
	}

	public Text toLowerCase() {
		this.s = this.s.toLowerCase();
		return this;
	}

	public Text toUpperCase(Locale locale) {
		this.s = this.s.toUpperCase(locale);
		return this;
	}

	public Text toUpperCase() {
		this.s = this.s.toUpperCase();
		return this;
	}

	public Text trim() {
		this.s = this.s.trim();
		return this;
	}

	public Text strip() {
		this.s = this.s.strip();
		return this;
	}

	public Text stripLeading() {
		this.s = this.s.stripLeading();
		return this;
	}

	public Text stripTrailing() {
		this.s = this.s.stripTrailing();
		return this;
	}

	public boolean isBlank() {
		return this.blank || this.s.isBlank() || this.s.isEmpty();
	}

	public Stream<String> lines() {
		return this.s.lines();
	}

	public IntStream chars() {
		return this.s.chars();
	}

	public IntStream codePoints() {
		return this.s.codePoints();
	}

	public char[] toCharArray() {
		return this.s.toCharArray();
	}

	public Text intern() {
		this.s = this.s.intern();
		return this;
	}

	public Text repeat(int count) {
		this.s = this.s.repeat(count);
		return this;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return this.s;
	}

	public Color getColor() {
		return c;
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

	public void setBlank() {
		setBlank(true);
	}

	public void setEmpty() {
		setEmpty(true);
	}

	public void setBlank(boolean b) {
		if (b) {
			this.s = Text.BLANK_TEXT.toString();
			this.blank = true;
		} else {
			this.blank = false;
		}
	}

	public void setEmpty(boolean b) {
		if (b) {
			this.s = Text.EMPTY_TEXT.toString();
			this.blank = true;
		} else {
			this.blank = false;
		}

	}

	public void render(Graphics g) {
		g.setColor(this.getColor());
		g.setFont(this.getFont());
		float prevX = x;
		float prevY = y;
		Text[] t = this.split("\n");
		Text center = t[0];
		for (int i = 0; i < t.length; i++) {
			if (i >= 1) {
				g.drawString(t[i].toString(), (int) prevX + ((g.getFontMetrics().stringWidth(center.toString())) / 2
						- (g.getFontMetrics().stringWidth(t[i].toString())) / 2), (int) prevY);
				prevY += g.getFontMetrics().getHeight();
				continue;
			}
			g.drawString(t[i].toString(), (int) prevX, (int) prevY);
			prevY += g.getFontMetrics().getHeight();
		}
		g.setColor(Color.white);
	}

	public void setText(Text t) {
		this.setText(t.toString());
//		this.x = t.getX();
//		this.y = t.getY();
//		this.blank = t.isBlank();
//		this.c = t.getColor();
//		this.f = t.getFont();
	}

	public void setText(String s) {
		this.s = s;
	}

	public String getText() {
		return this.s;
	}

}
