package com.pong.states.online;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.pong.entities.player.OnlinePlayer;
import com.pong.graphics.BackToMenuButton;
import com.pong.graphics.RectangleButton;
import com.pong.graphics.Text;
import com.pong.pong.Pong;
import com.pong.states.OnlineState;
import com.pong.states.State;

public class WaitingForOnlineGameState extends OnlineState {

	public WaitingForOnlineGameState(StateType t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	private String ip;
	private int port;
	private Socket s;
	private DataInputStream dis;
	private DataOutputStream dos;
	private boolean failed = false;
	private String errorMessage = "";
	private BackToMenuButton menu = null;

	public WaitingForOnlineGameState(String ip, int port) {
		super(State.StateType.GAME_WAITING_ONLINE);
		this.ip = ip;
		this.port = port;
		try {
			this.s = new Socket(this.ip, this.port);
			dis = new DataInputStream(this.s.getInputStream());
			dos = new DataOutputStream(this.s.getOutputStream());
		} catch (IOException e) {
			this.errorMessage = e.toString();
			this.menu = new BackToMenuButton(Pong.getPong().getWidth() / 2 - RectangleButton.DEFAULT_BUTTON_WIDTH / 2,
					Pong.getPong().getHeight() / 2 - RectangleButton.DEFAULT_BUTTON_HEIGHT / 2);
			failed = true;
		}

	}

	@Override
	public void init() {
		if (failed) {
			Pong.getPong().getScreen().addMouseListener(this.menu);
		}
	}

	@Override
	public void tick() {
		if (failed) {
			this.menu.tick();
			return;
		}
		Pong.getPong().setOnline(true);
		OnlinePlayer p = new OnlinePlayer(s);
		Pong.getPong().setOnlinePlayer(p);
		Pong.getPong().setCurrentState(new OnlineGameState(p));

	}

	@Override
	public void render(Graphics2D g) {
		if (failed) {
			g.clearRect(0, 0, Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);
			g.setColor(State.backgroundColor);
			g.fillRect(0, 0, Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);
			g.setColor(Color.white);
			g.setColor(Color.white);
			g.setFont(Pong.getPong().getDefaultFont().deriveFont(80.0f));
			Text t = new Text("Error\n" + "Error while connecting to server ip: " + this.ip + " port: " + this.port
					+ "; " + this.errorMessage, Pong.WINDOW_WIDTH / 2 - 55, Pong.WINDOW_HEIGHT / 2 - 300);
			g.setFont(Pong.getPong().getWindow().getIPTextBoxFont().deriveFont(30.0f));
			t.setFont(g.getFont());
			t.render(g);
			this.menu.render(g);
			return;
		}
		g.clearRect(0, 0, Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);
		g.setColor(State.backgroundColor);
		g.fillRect(0, 0, Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);
		g.setColor(Color.white);

	}

	@Override
	public void exit() {
		super.exit();
		this.failed = false;

//		try {
//			this.dis.close();
//			this.dos.close();
//			this.s.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	public void setIP(String ip) {
		this.ip = ip;
	}

	public String getIP() {
		return this.ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return this.port;
	}

}
