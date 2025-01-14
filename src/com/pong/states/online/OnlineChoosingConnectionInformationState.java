package com.pong.states.online;

import java.awt.Color;
import java.awt.Graphics2D;

import com.pong.graphics.Text;
import com.pong.graphics.online.JoinSpecificGameButton;
import com.pong.graphics.online.OnlineBackButton;
import com.pong.pong.Pong;
import com.pong.states.OnlineState;
import com.pong.states.State;
import com.pong.utils.PongUtils;

public class OnlineChoosingConnectionInformationState extends OnlineState {
	private Text ip;
	private Text port;
	private static final float IP_TEXT_FONT_SIZE = 50.0f;
	private static final float PORT_TEXT_FONT_SIZE = IP_TEXT_FONT_SIZE;
	private JoinSpecificGameButton button;
	private OnlineBackButton menu;

	public OnlineChoosingConnectionInformationState() {
		super(State.StateType.ONLINE_GAME_CHOOSING_CONNECTION);

	}

	@Override
	public void init() {
		ip = new Text("IP: ", 515, Pong.getPong().getHeight() / 2 - 115);
		ip.setFont(Pong.getPong().getDefaultFont().deriveFont(IP_TEXT_FONT_SIZE));
		ip.setColor(Color.white);

		port = new Text("Port: ", ip.getX(), ip.getY() + 55);
		port.setFont(Pong.getPong().getDefaultFont().deriveFont(PORT_TEXT_FONT_SIZE));
		port.setColor(Color.white);
		this.button = new JoinSpecificGameButton();
		Pong.getPong().getWindow().getScreen().addMouseListener(this.button);
		Pong.getPong().setIPTextBoxtVisible(true);
		Pong.getPong().setPortTextBoxtVisible(true);
	}

	@Override
	public void tick() {
		// Logger.logInfo("Text for ip: " +
		// Pong.getPong().getWindow().getIPTextBox().getText());
		// Logger.logInfo("Text for port: " +
		// Pong.getPong().getWindow().getPortTextBox().getText());
		String ip = Pong.getPong().getWindow().getIPTextBox().getText() == null ? ""
				: Pong.getPong().getWindow().getIPTextBox().getText();
		String portText = Pong.getPong().getWindow().getPortTextBox().getText() == null ? "255"
				: Pong.getPong().getWindow().getPortTextBox().getText();
		int port;
		try {
			port = Integer.parseInt(portText);
		} catch (NumberFormatException e) {
			if (portText.equalsIgnoreCase("localport"))
				port = 25565;
			else
				port = 255;

		}
		port = PongUtils.clamp(255, 65534, port);
		if (this.button != null) {
			this.button.setIP(ip == null ? "localhost" : ip);
			this.button.setPort(port);
			this.button.tick();
		}

	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, Pong.getPong().getWidth(), Pong.getPong().getHeight());
		g.setColor(State.backgroundColor);
		g.fillRect(0, 0, Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);
		ip.render(g);
		port.render(g);
		g.fillRect(Pong.getIpBoxX() - 5, Pong.getIpBoxY() - 5, Pong.getIpBoxWidth() + 10, Pong.getIpBoxHeight() + 10);
		g.fillRect(Pong.getPortBoxX() - 5, Pong.getPortBoxY() - 5, Pong.getPortBoxWidth() + 10,
				Pong.getPortBoxHeight() + 10);
		g.setColor(Color.white);
		if (button != null && g != null) {
			button.render(g);
		}

		g.setColor(Color.white);
		// menu.render(g);
	}

	@Override
	public void exit() {
		super.exit();
		Pong.getPong().getScreen().removeMouseListener(this.button);
		Pong.getPong().getWindow().getScreen().removeMouseListener(this.button);
		Pong.getPong().setIPTextBoxtVisible(false);
		Pong.getPong().setPortTextBoxtVisible(false);
	}

	public OnlineBackButton getBackButton() {
		return this.menu;
	}

	public JoinSpecificGameButton getJoinSpecificGameButton() {
		return this.button;
	}

	public Text getIPText() {
		return this.ip;
	}

	public Text getPortText() {
		return this.port;
	}

	public void setIPText(Text ip) {
		this.ip = ip;
	}

	public void setPortText(Text port) {
		this.port = port;
	}

	public void setMenuBackButton(OnlineBackButton menu) {
		this.menu = menu;
	}

}
