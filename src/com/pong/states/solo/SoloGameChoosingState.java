package com.pong.states.solo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import com.pong.graphics.RectangleButton;
import com.pong.graphics.Text;
import com.pong.graphics.solo.CPUEasyButton;
import com.pong.graphics.solo.CPUHardButton;
import com.pong.graphics.solo.CPUImpossibleButton;
import com.pong.graphics.solo.CPUMediumButton;
import com.pong.graphics.solo.SoloBackButton;
import com.pong.pong.Pong;
import com.pong.states.OfflineState;
import com.pong.states.State;
import com.pong.utils.Logger;

public class SoloGameChoosingState extends OfflineState {
	/**
	 * = new SimpleDateFormat("[hh:mm:ss]").format(new Date()) + "[System]"
	 */
	private CPUEasyButton cpuEasyButton;
	private CPUMediumButton cpuMediumButton;
	private CPUHardButton cpuHardButton;
	private CPUImpossibleButton cpuImpossibleButton;
	private SoloBackButton backButton;
	private Text titleText;
	private static final String title = "Choose CPU Level";
	private static final float titleY = 200.0f;

	public SoloGameChoosingState() {
		super(State.StateType.GAME_SOLO);
		this.cpuEasyButton = new CPUEasyButton(50 + 190,
				Pong.getPong().getHeight() / 2 - RectangleButton.DEFAULT_BUTTON_HEIGHT / 2);
		this.cpuMediumButton = new CPUMediumButton(
				this.cpuEasyButton.getX() + RectangleButton.DEFAULT_BUTTON_WIDTH + 30,
				Pong.getPong().getHeight() / 2 - RectangleButton.DEFAULT_BUTTON_HEIGHT / 2);
		this.cpuHardButton = new CPUHardButton(this.cpuMediumButton.getX() + RectangleButton.DEFAULT_BUTTON_WIDTH + 30,
				Pong.getPong().getHeight() / 2 - RectangleButton.DEFAULT_BUTTON_HEIGHT / 2);
		this.cpuImpossibleButton = new CPUImpossibleButton(
				this.cpuHardButton.getX() + RectangleButton.DEFAULT_BUTTON_WIDTH + 30,
				Pong.getPong().getHeight() / 2 - RectangleButton.DEFAULT_BUTTON_HEIGHT / 2);
		try {
			this.backButton = new SoloBackButton();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Adding 'CPU Easy button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUEasyButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUEasyButton());
			Logger.logDebug("Adding 'CPU Medium button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUMediumButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUMediumButton());
			Logger.logDebug("Adding 'CPU Hard button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUHardButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUHardButton());
			Logger.logDebug("Adding 'CPU Impossible button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUImpossibleButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUImpossibleButton());
			Logger.logDebug("Adding 'Solo Back button' to the screen...");
			Pong.getPong().getWindow().getScreen().addMouseListener(getSoloBackButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getSoloBackButton());
		} else {
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUEasyButton());
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUMediumButton());
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUHardButton());
			Pong.getPong().getWindow().getScreen().addMouseListener(getCPUImpossibleButton());
			Pong.getPong().getWindow().getScreen().addMouseListener(getSoloBackButton());

			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUEasyButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUMediumButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUHardButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getCPUImpossibleButton());
			Pong.getPong().getWindow().getFrame().addMouseListener(getSoloBackButton());
		}
	}

	@Override
	public void init() {

	}

	@Override
	public void tick() {
		this.cpuEasyButton.tick();
		this.cpuMediumButton.tick();
		this.cpuHardButton.tick();
		this.cpuImpossibleButton.tick();
		if (this.backButton != null) {
			this.backButton.tick();
		}

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(this.backgroundColor);
		g.fillRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
		g.setColor(Color.white);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(100.0f));
		titleText = new Text(title, Color.white, Pong.getPong().getDefaultFont().deriveFont(100.0f),
				(Pong.getPong().getWidth() / 2) - (g.getFontMetrics().stringWidth(title) / 2), titleY);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(100.0f));
		titleText.render(g);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(25.0f));
		g.setColor(Color.white);
		this.cpuEasyButton.render(g);
		g.setColor(Color.white);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(25.0f));
		this.cpuMediumButton.render(g);
		g.setColor(Color.white);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(25.0f));
		this.cpuHardButton.render(g);
		g.setColor(Color.white);
		g.setFont(Pong.getPong().getDefaultFont().deriveFont(25.0f));
		this.cpuImpossibleButton.render(g);
		if (this.backButton != null) {
			this.backButton.render(g);
		}

	}

	public CPUEasyButton getCPUEasyButton() {
		return cpuEasyButton;
	}

	public void setCPUEasyButton(CPUEasyButton cpuEasyButton) {
		this.cpuEasyButton = cpuEasyButton;
	}

	public CPUMediumButton getCPUMediumButton() {
		return cpuMediumButton;
	}

	public void setCPUMediumButton(CPUMediumButton cpuMediumButton) {
		this.cpuMediumButton = cpuMediumButton;
	}

	public CPUHardButton getCPUHardButton() {
		return cpuHardButton;
	}

	public void setCPUHardButton(CPUHardButton cpuHardButton) {
		this.cpuHardButton = cpuHardButton;
	}

	public CPUImpossibleButton getCPUImpossibleButton() {
		return cpuImpossibleButton;
	}

	public void setCPUImpossibleButton(CPUImpossibleButton cpuImpossibleButton) {
		this.cpuImpossibleButton = cpuImpossibleButton;
	}

	public SoloBackButton getSoloBackButton() {
		return this.backButton;
	}

	@Override
	public void exit() {
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Removing 'CPU Easy Button' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUEasyButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUEasyButton());
			Logger.logDebug("Removing 'CPU Medium Button' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUMediumButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUMediumButton());
			Logger.logDebug("Removing 'CPU Hard Button' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUHardButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUHardButton());
			Logger.logDebug("Removing 'Impossible' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUImpossibleButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUImpossibleButton());
			Logger.logDebug("Removing 'Solo Back Button' from screen...");
			Pong.getPong().getWindow().getScreen().removeMouseListener(getSoloBackButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getSoloBackButton());
		} else {
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUEasyButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUMediumButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUHardButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getCPUImpossibleButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getSoloBackButton());
			Pong.getPong().getWindow().getScreen().removeMouseListener(getSoloBackButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUEasyButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUMediumButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUHardButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getCPUImpossibleButton());
			Pong.getPong().getWindow().getFrame().removeMouseListener(getSoloBackButton());
		}
		super.exit();
	}

}
