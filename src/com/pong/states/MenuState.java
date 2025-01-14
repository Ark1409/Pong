package com.pong.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.pong.entities.MenuPlayer;
import com.pong.entities.ball.Ball;
import com.pong.entities.ball.MenuBall;
import com.pong.entities.player.PlayerTwoCPU;
import com.pong.graphics.menu.LocalPlayButton;
import com.pong.graphics.menu.OnlinePlayButton;
import com.pong.graphics.menu.SinglePlayerButton;
import com.pong.pong.Pong;
import com.pong.utils.Logger;

public class MenuState extends OfflineState {
	private SinglePlayerButton singlePlay;
	private LocalPlayButton localPlay;
	private OnlinePlayButton onlinePlay;
	private final Font titleFont = Pong.getPong().getDefaultFont();
	private final String title = "Pong";
	private MenuPlayer cpuLeft;
	private MenuPlayer cpuRight;
	private MenuBall b;

	public MenuState() {
		super(State.StateType.GAME_MENU);
	}

	@Override
	public void init() {
		int screenWidth = Pong.getPong().getWindow().getWidth();
		int screenHeight = Pong.getPong().getWindow().getHeight();
		this.singlePlay = new SinglePlayerButton((screenWidth / 2) - SinglePlayerButton.DEFAULT_BUTTON_WIDTH / 2 - 2,
				(screenHeight / 2) - SinglePlayerButton.DEFAULT_BUTTON_HEIGHT / 2 - 150);
		this.localPlay = new LocalPlayButton(this.singlePlay.getX(),
				this.singlePlay.getY() + LocalPlayButton.DEFAULT_BUTTON_HEIGHT);
		this.onlinePlay = new OnlinePlayButton(this.localPlay.getX(),
				this.localPlay.getY() + OnlinePlayButton.DEFAULT_BUTTON_HEIGHT);
		this.singlePlay.init();
		this.localPlay.init();
		this.onlinePlay.init();

		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Adding 'Single Play button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getSinglePlayerButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getSinglePlayerButton());
			Logger.logDebug("Adding 'Local Play button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getLocalPlayButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getLocalPlayButton());
			Logger.logDebug("Adding 'Online Play button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getOnlinePlayButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getOnlinePlayButton());
		} else {
			Pong.getPong().getWindow().getScreen().addMouseListener(getSinglePlayerButton());
			Pong.getPong().getWindow().getScreen().addMouseListener(getLocalPlayButton());
			Pong.getPong().getWindow().getScreen().addMouseListener(getOnlinePlayButton());

			Pong.getPong().getWindow().getFrame().addMouseListener(getSinglePlayerButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getLocalPlayButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getOnlinePlayButton());
		}

		this.cpuLeft = new MenuPlayer(true);

		this.cpuRight = new MenuPlayer(false);
		this.cpuLeft.init();
		this.cpuRight.init();
		this.b = new MenuBall(screenWidth / 2, screenHeight / 2, Ball.DEFAULT_BALL_XSPEED, Ball.DEFAULT_BALL_YSPEED,
				this.cpuLeft, this.cpuRight);
		this.b.init();
		Pong.getPong().setBall(b);
	}

	@Override
	public void tick() {
		if (this.singlePlay != null)
			this.singlePlay.tick();
		if (this.localPlay != null)
			this.localPlay.tick();
		if (this.onlinePlay != null)
			this.onlinePlay.tick();
		if (this.cpuLeft != null) {
			this.cpuLeft.setYSpeed(PlayerTwoCPU.DEFAULT_CPU_YSPEED + 3f);
			this.cpuLeft.tick();
		} else {
			Logger.logDebug("CPU on the LEFT is null");
		}
		if (this.cpuRight != null) {
			this.cpuRight.setYSpeed(PlayerTwoCPU.DEFAULT_CPU_YSPEED + 3f);
			this.cpuRight.tick();
		} else {
			Logger.log("null");
		}
		if (this.b != null) {
			this.b.tick();
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(State.backgroundColor);
		g.fillRect(0, 0, Pong.getPong().getWindow().getWidth(), Pong.getPong().getWindow().getHeight());
		int screenWidth = Pong.getPong().getWindow().getWidth();
		g.setColor(Color.white);

		// g.fillRect((screenWidth / 2), (screenHeight / 2), this.singlePlay.getWidth(),
		// this.singlePlay.getHeight());

		g.setFont(this.titleFont);
		int stringWidth = (int) g.getFontMetrics().getStringBounds(this.title, g).getWidth();
		g.drawString(this.title, (int) ((screenWidth / 2) - stringWidth / 2), 150);

		this.singlePlay.render(g);
		g.setColor(Color.white);
		this.localPlay.render(g);
		g.setColor(Color.white);
		this.onlinePlay.render(g);
		g.setColor(Color.white);
		this.cpuLeft.render(g);
		g.setColor(Color.white);
		this.cpuRight.render(g);
		g.setColor(Color.white);
		this.b.render(g);
		g.setColor(Color.white);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(100.0f));
		g.drawString(String.valueOf(this.cpuLeft.getPoints()), Pong.getPong().getWidth() / 2 - 395, 100);
		g.drawString(String.valueOf(this.cpuRight.getPoints()), Pong.getPong().getWidth() / 2 + 380, 100);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(30.0f));
		g.drawString("P1", Pong.getPong().getWidth() / 2 - 384, 30);
		g.drawString("P2", Pong.getPong().getWidth() / 2 + 390, 30);

	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		State.backgroundColor = backgroundColor;
	}

	public SinglePlayerButton getSinglePlayerButton() {
		return this.singlePlay;
	}

	public LocalPlayButton getLocalPlayButton() {
		return this.localPlay;
	}

	public OnlinePlayButton getOnlinePlayButton() {
		return this.onlinePlay;
	}

	public String getTitle() {
		return this.title;
	}

	public Font getTitleFont() {
		return this.titleFont;
	}

	@Override
	public void exit() {
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Removing 'Single Play button' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getSinglePlayerButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getSinglePlayerButton());
			Logger.logDebug("Removing 'Local Play button' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getLocalPlayButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getLocalPlayButton());
			Logger.logDebug("Removing 'Online Play button' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getOnlinePlayButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getOnlinePlayButton());

		} else {
			Pong.getPong().getWindow().getScreen().removeMouseListener(getSinglePlayerButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getLocalPlayButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getOnlinePlayButton());

			Pong.getPong().getWindow().getFrame().removeMouseListener(getSinglePlayerButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getLocalPlayButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getOnlinePlayButton());
		}

	}

}
