package com.pong.entities.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

import com.pong.entities.Pallet;
import com.pong.entities.ball.Ball;
import com.pong.pong.Pong;
import com.pong.utils.Logger;
import com.pong.utils.PongUtils;

public class OnlinePlayer extends Pallet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -812599404868012228L;

	public static final float DEFAULT_ONLINE_PLAYER_XSPEED = 0.0f;
	public static final float DEFAULT_ONLINE_PLAYER_YSPEED = 7.0f;

	public static final float DEFAULT_ONLINE_PLAYER_X = 50.0f;
	public static final float DEFAULT_ONLINE_PLAYER_Y = (Pong.getPong().getWindow().getHeight() / 2
			- Pallet.PALLET_HEIGHT / 2);
	private int ticker;
	private Socket s;
	private DataInputStream in;
	private DataOutputStream out;
	private boolean[] keys = new boolean[999];
	private String host;
	private int port;
	private boolean hit = false;
	private boolean closed = false;
	private boolean started = false;
	private boolean waiting = true;
	// private float receivedY = PlayerTwo.DEFAULT_PLAYER2_Y;
	private float receivedY = 0;
	private boolean receivedHit = false;
	private boolean lastHit = false;
	private boolean otherLastHit = false;
	private float ballx = 800, bally = 450;

	public OnlinePlayer(final String host, final int port) {
		this(Color.white, DEFAULT_ONLINE_PLAYER_X, DEFAULT_ONLINE_PLAYER_Y, DEFAULT_ONLINE_PLAYER_XSPEED,
				DEFAULT_ONLINE_PLAYER_YSPEED, host, port);
	}

	public OnlinePlayer(float x, float y, final String host, final int port) {
		this(x, y, DEFAULT_ONLINE_PLAYER_XSPEED, DEFAULT_ONLINE_PLAYER_YSPEED, host, port);
	}

	public OnlinePlayer(float x, float y, float xSpeed, float ySpeed, final String host, final int port) {
		this(Color.white, x, y, xSpeed, ySpeed, host, port);
	}

	public OnlinePlayer(Color c, float x, float y, float xSpeed, float ySpeed, final String host, final int port) {
		super(c, x, y, xSpeed, ySpeed);
		this.ticker = 0;
		this.host = host;
		this.port = port;

	}

	public OnlinePlayer(Socket s) {
		super(Color.white, DEFAULT_ONLINE_PLAYER_X, DEFAULT_ONLINE_PLAYER_Y, DEFAULT_ONLINE_PLAYER_XSPEED,
				DEFAULT_ONLINE_PLAYER_YSPEED);
		this.s = s;
	}

	@Override
	public boolean isOnline() {
		return true;
	}

	@Override
	public void init() {
		connect();
	}

	private void connect() {
		try {
			if (s == null) {
				try {
					this.s = new Socket(host == null ? "localhost" : host, port);
				} catch (ConnectException e) {
					// TODO change to screen where it says go to menu
				}
			}
			System.out.println("Made the socket");
			System.out.println("Made the two");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() throws IOException {
		if (in != null)
			this.in.close();
		if (out != null)
			this.out.close();
		if (s != null)
			this.s.close();
		this.closed = true;
	}

	@Override
	public void tick() {
//		try {
//			if ((s.isClosed()) || (s.getOutputStream() == null) || (!s.isConnected()) || (s.isInputShutdown())
//					|| (s.isOutputShutdown()) || (s.getInputStream() == null) || (s == null)) {
//				closed = true;
//			}
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		if (closed) {
			Pong.getPong().setCurrentState(Pong.getPong().getFallBackState());
			return;
		}
		try {
			if (in == null || out == null) {
				if (s == null) {
					this.s = new Socket(host == null ? "localhost" : host, port);
				}
				in = new DataInputStream(this.s.getInputStream());
				out = new DataOutputStream(this.s.getOutputStream());
			}
			if (this.waiting) {

				if (in.available() > 0) {
					this.waiting = false;
					this.started = false;
				}
				// String data = "y=" + this.y + ";hit=" + this.hit;
				return;
			}

			if (!started) {

				if (in.available() > 0) {
					this.started = in.readBoolean();
				}
				// String data = "y=" + this.y + ";hit=" + this.hit;
//			ticker++;
//			if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
//				Logger.logInfo("Player X: " + x);
//				Logger.logInfo("Player Y: " + y);
//				ticker = 0;
//			}
				return;
			}

			y += -ySpeed;
			if (keys[KeyEvent.VK_W]) {
				ySpeed = DEFAULT_ONLINE_PLAYER_YSPEED;
			} else if (keys[KeyEvent.VK_S]) {
				ySpeed = -DEFAULT_ONLINE_PLAYER_YSPEED;
			} else {
				ySpeed = 0.0f;
			}

			x = PongUtils.clamp(0, 1600 - width, (int) x);
			y = PongUtils.clamp(0, 900 - height, (int) y);
			ticker++;

			if (Pong.getPong().isDebug() && ticker >= Pong.getPong().getFPS() / 2) {
				Logger.logDebug("Player X: " + x);
				Logger.logDebug("Player Y: " + y);
				ticker = 0;
			}
			if (this.isColliding(Pong.getPong().getBall().getHitBox())) {
				this.hit = true;
			}

			if (in.available() > 0) {
				float py = this.in.readFloat();
				boolean phit = this.in.readBoolean();
				this.waiting = this.in.readBoolean();
				this.started = this.in.readBoolean();
				this.ballx = this.in.readFloat();
				this.bally = this.in.readFloat();
				this.receivedY = py;
				this.hit = phit;

			}

		} catch (SocketException e) {
			try {
				close();
				Pong.getPong().setCurrentState(Pong.getPong().getFallBackState());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			try {
				close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Pong.getPong().setCurrentState(Pong.getPong().getFallBackState());
		}

	}

	@Override
	public void render(Graphics2D g) {
//		try {
//			if ((s.isClosed()) || (s.getOutputStream() == null) || (!s.isConnected()) || (s.isInputShutdown())
//					|| (s.isOutputShutdown()) || (s.getInputStream() == null) || (s == null)) {
//				closed = true;
//			}
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		if (closed) {
			Pong.getPong().setCurrentState(Pong.getPong().getFallBackState());
			return;
		}
		if (this.waiting) {
			g.setColor(Color.white);
			g.setFont(Pong.getPong().getDefaultFont().deriveFont(50.0f));
			String drawString = "Waiting for an opponent...";
			g.drawString(drawString, Pong.WINDOW_WIDTH / 2 - g.getFontMetrics().stringWidth(drawString) / 2,
					Pong.WINDOW_HEIGHT / 2);
			return;
		}

		if (!started) {
			g.setColor(Color.white);
			g.setFont(Pong.getPong().getDefaultFont().deriveFont(50.0f));
			String drawString = "Waiting for host to start the game...";
			g.drawString(drawString, Pong.WINDOW_WIDTH / 2 - g.getFontMetrics().stringWidth(drawString) / 2,
					Pong.WINDOW_HEIGHT / 2);
			return;
		}

		super.render(g);
		g.setColor(Color.white);
		g.fillRect((int) PlayerTwo.DEFAULT_PLAYER2_X, (int) receivedY, Pallet.PALLET_WIDTH, Pallet.PALLET_HEIGHT);
		g.fillOval((int) ballx, (int) bally, Ball.BALL_WIDTH, Ball.BALL_HEIGHT);
		// System.out.println("received: " + dataString);

	}

	public DataInputStream getDataInputStream() {
		try {
			if (this.s == null) {
				this.s = new Socket(host, port);
			}
			return this.in == null ? this.in = new DataInputStream(this.s.getInputStream()) : this.in;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DataOutputStream getDataOutputStream() {

		try {
			if (this.s == null) {
				this.s = new Socket(host, port);
			}
			return this.out == null ? this.out = new DataOutputStream(this.s.getOutputStream()) : this.out;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public InputStream getInputStream() throws IOException {
		if (this.s == null) {
			this.s = new Socket(host, port);
		}
		return this.s.getInputStream();
	}

	public OutputStream getOutputStream() throws IOException {
		if (this.s == null) {
			this.s = new Socket(host, port);
		}
		return this.s.getOutputStream();
	}

	public Socket getSocket() {
		return this.s;
	}

	public String getHostName() {
		return host;
	}

	public void setHostName(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public boolean hasHit() {
		return this.hit;
	}

	public void setHit(boolean b) {
		this.hit = b;
	}

	public boolean[] getKeys() {
		return keys;
	}

	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	public boolean isLastHit() {
		return lastHit;
	}

	public void setLastHit(boolean b) {
		this.lastHit = b;
	}
}
