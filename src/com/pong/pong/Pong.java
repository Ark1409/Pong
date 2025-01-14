package com.pong.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.pong.entities.ball.Ball;
import com.pong.entities.player.OnlinePlayer;
import com.pong.entities.player.PlayerOne;
import com.pong.entities.player.PlayerTwo;
import com.pong.events.PongWindowEvents;
import com.pong.graphics.Screen;
import com.pong.graphics.Window;
import com.pong.states.MenuState;
import com.pong.states.State;
import com.pong.utils.Logger;
import com.pong.utils.PongUtils;

public final class Pong implements Runnable {
	private Thread t;
	public static final int WINDOW_WIDTH = 1600;
	public static final int WINDOW_HEIGHT = 900;
	private boolean isRunning = false;
	private Window w;
	private Screen sc;
	private static final int fps = 60;
	private static int currentFps = 0;
	public static final String systemPrefix = "[Pong]";
	public static final String infoPrefix = systemPrefix + " [INFO]";
	public static final String warnPrefix = systemPrefix + " [WARN]";
	public static final String errPrefix = systemPrefix + " [ERROR]";
	public static final String debugPrefix = systemPrefix + " [DEBUG]";
	public static final String debugWarnPrefix = warnPrefix + " [DEBUG]";
	public static final String debugErrorPrefix = errPrefix + " [DEBUG]";
	private boolean debug = false;
	private static Pong instance;
	private State currentState;
	private Font defaultFont;
	private static State fallBackState;
	private static BufferedImage icon;
	private static PlayerOne currentPlayerOne;
	private static PlayerTwo currentPlayerTwo;
	private static OnlinePlayer currentOnlinePlayer;
	private static Ball b;
	private boolean online;
	private static final int ipBoxX = 660;
	private static final int ipBoxY = 300;
	private static final int ipBoxWidth = 300;
	private static final int ipBoxHeight = 40;
	private static final int portBoxX = ipBoxX;
	private static final int portBoxY = ipBoxY + 60;
	private static final int portBoxWidth = ipBoxWidth;
	private static final int portBoxHeight = ipBoxHeight;
	public static Font ipBoxFont;
	private boolean plugins = false;
	private boolean ipTextBoxVisible = false;
	private boolean portTextBoxVisible = false;
	private Graphics2D g;

	public Pong(Window w) {
		this(w, false, false);
		
	}

	public Pong(Window w, boolean debug) {
		this(w, debug, false);
	}

	public Pong(Window w, boolean debug, boolean plugins) {
		this.w = w;
		this.sc = w.getScreen();
		this.debug = debug;
		this.plugins = plugins;
	}

	public void init() {
		instance = this;
		if (isDebug()) {
			Logger.logDebug("Debug mode Enabled!");
		}
		try {
			if (isDebug())
				Logger.logDebug("Loading icon image...");
			icon = PongUtils.loadImage(Window.class.getResourceAsStream("/images/icon.png"));
		} catch (IOException e) {
			Logger.logError("Unable to load icon image.\n");
			e.printStackTrace();
		}
		this.w.getFrame().addWindowListener(new PongWindowEvents());
		if (isDebug())
			Logger.logDebug("Setting default font...");
		this.defaultFont = PongUtils.loadFont(Pong.class.getResourceAsStream("/fonts/Boo City.ttf"), 150.0f);
		Pong.fallBackState = new MenuState();
		this.currentState = Pong.fallBackState;
		this.currentState.init();

		if (isDebug())
			Logger.logDebug("Setting icon image...");
		this.w.getFrame().setIconImage(icon);
		this.online = false;
//		ipBoxFont = 

	}

	@Override
	public void run() {
		Logger.logInfo("Initializing...");

		init();
		double timePerTick = 1000000000 / fps;
		long now;
		long lastTime = System.nanoTime();
		double delta = 0, ticks = fps;
		double f = 0;
		Logger.logInfo("Starting game...");
		while (isRunning) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1) {
				this.w.tick();
				tick();
				render();
				delta = 0;
				ticks++;
				f++;
				if (ticks > fps) {
					ticks = fps;
				}
				currentFps = (int) ticks;
				if (f > fps) {
					f = 0;
					ticks = fps;
				}

			}

		}

		stop();

	}

	public void tick() {
		// Pong.systemPrefix = new SimpleDateFormat("hh:mm:ss").format(new Date()) +
		// "[System]";
		if (currentState != null)
			currentState.tick();
	}

	public void render() {
		BufferStrategy bs = w.getScreen().getBufferStrategy();
		if (bs == null) {
			w.getScreen().createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		this.setGraphics(g);
		// g.clearRect(0, 0, this.w.getWidth(), this.w.getHeight());
		if (currentState != null) {
			g.setColor(Color.white);
			currentState.render(g);
		}
		if (this.isDebug()) {
			g.setFont(Pong.getPong().getDefaultFont().deriveFont(25.0f));
			g.setColor(Color.white);
			g.drawString(String.valueOf(currentFps), 10, 20);
		}
		g.dispose();
		bs.show();
	}

	public synchronized void start() {
		if (isRunning) {
			return;
		}
		isRunning = true;
		this.t = new Thread(this, "Pong");
		t.start();
	}

	public synchronized void stop() {
		if (!isRunning) {
			return;
		}
		isRunning = false;
		instance = null;
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Screen getScreen() {
		return sc;
	}

	public void setScreen(Screen sc) {
		this.sc = sc;
	}

	public Thread getThread() {
		return t;
	}

	public Window getWindow() {
		return w;
	}

	public boolean isDebug() {
		return debug;
	}

	@Deprecated
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public static Pong getInstance() {
		return getPong();
	}

	public static Pong getPong() {
		return instance;
	}

	public State getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(State s) {
		if (this.currentState == s)
			return;
		this.currentState.exit();
		this.currentState = s;
		this.currentState.init();
	}

	public Font getDefaultFont() {
		return defaultFont;
	}

	/**
	 * 
	 * @return Returns the width of the window.
	 */
	public int getWidth() {
		return WINDOW_WIDTH;
	}

	/**
	 * 
	 * @return Returns the height of the window.
	 */
	public int getHeight() {
		return WINDOW_HEIGHT;
	}

	public int getFPS() {
		return Pong.fps;
	}

	public State getFallBackState() {
		return Pong.fallBackState == null ? new MenuState() : Pong.fallBackState;
	}

	/**
	 * @deprecated If you set it to something else than the menu state, it may throw
	 *             some errors.
	 * @param paramState (The state).
	 */

	@Deprecated
	public void setFallBackState(State paramState) {
		Pong.fallBackState = paramState;
	}

	public BufferedImage getIcon() {
		return Pong.icon;
	}

	public void setIcon(BufferedImage i) {
		Pong.icon = i;
		this.w.getFrame().setIconImage(i);
	}

	public PlayerOne getCurrentPlayerOne() {
		return Pong.currentPlayerOne;
	}

	public PlayerTwo getCurrentPlayerTwo() {
		return Pong.currentPlayerTwo;
	}

	public void setCurrentPlayerOne(PlayerOne p) {
		Pong.currentPlayerOne = p;
	}

	public void setCurrentPlayerTwo(PlayerTwo p) {
		Pong.currentPlayerTwo = p;
	}

	public Ball getBall() {
		return b;
	}

	public void setBall(Ball b) {
		Pong.b = b;
	}

	public boolean isOnline() {
		return this.online;
	}

	public void setOnline(boolean b) {
		this.online = b;
	}

	public OnlinePlayer getOnlinePlayer() {
		if (!this.isOnline())
			return null;
		return Pong.currentOnlinePlayer;
	}

	public void setOnlinePlayer(OnlinePlayer p) {
		Pong.currentOnlinePlayer = p;
	}

	public static int getIpBoxX() {
		return ipBoxX;
	}

	public static int getIpBoxY() {
		return ipBoxY;
	}

	public static int getIpBoxWidth() {
		return ipBoxWidth;
	}

	public static int getIpBoxHeight() {
		return ipBoxHeight;
	}

	public static int getPortBoxX() {
		return portBoxX;
	}

	public static int getPortBoxY() {
		return portBoxY;
	}

	public static int getPortBoxWidth() {
		return portBoxWidth;
	}

	public static int getPortBoxHeight() {
		return portBoxHeight;
	}

	public static int getCurrentFPS() {
		return Pong.currentFps;
	}

	public boolean isPlugins() {
		return this.plugins;
	}

	@Deprecated
	public void setPlugins(boolean plugins) {
		this.plugins = plugins;
	}

	public void setIPTextBoxtVisible(boolean b) {
		this.ipTextBoxVisible = b;
	}

	public void setPortTextBoxtVisible(boolean b) {
		this.portTextBoxVisible = b;
	}

	public boolean isPortTextBoxVisible() {
		return this.portTextBoxVisible;
	}

	public boolean isIPTextBoxVisible() {
		return this.ipTextBoxVisible;
	}

	public Graphics2D getGraphics() {
		return this.g;
	}

	private void setGraphics(Graphics2D g) {
		this.g = g;
	}

}
