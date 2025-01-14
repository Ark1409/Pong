package com.pong.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JFrame;

import com.pong.pong.Pong;
import com.pong.states.State;

public class Window {
	private JFrame frame;
	private int width, height;
	private String title;
	private Screen s;

	private TextField ipTextBox;
	private Font ipTextBoxFont;

	private TextField portTextBox;
	private Font portTextBoxFont;
	public static final String DEFAULT_IP_TEXTBOX_TEXT = "IP";
	public static final String DEFAULT_PORT_TEXTBOX_TEXT = "Port";

	public Window(String s, int width, int height, Font ipTextBoxFont) {
		this.frame = new JFrame(s);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setFocusable(false);
		this.frame.setResizable(true);
		this.title = s;
		this.width = width;
		this.height = height;
		this.s = new Screen(width, height);

		this.ipTextBox = new TextField(1);
		this.portTextBox = new TextField(1);

		this.ipTextBoxFont = ipTextBoxFont;
		this.portTextBoxFont = this.ipTextBoxFont;

		this.ipTextBox.setBounds(Pong.getIpBoxX(), Pong.getIpBoxY(), Pong.getIpBoxWidth(), Pong.getIpBoxHeight());
		this.ipTextBox.setText(DEFAULT_IP_TEXTBOX_TEXT);
		this.ipTextBox.setPreferredSize(new Dimension(Pong.getIpBoxWidth(), Pong.getIpBoxHeight()));
		this.ipTextBox.setMinimumSize(new Dimension(Pong.getIpBoxWidth(), Pong.getIpBoxHeight()));
		this.ipTextBox.setMaximumSize(new Dimension(Pong.getIpBoxWidth(), Pong.getIpBoxHeight()));
		this.ipTextBox.setSize(Pong.getIpBoxWidth(), Pong.getIpBoxHeight());

		this.ipTextBox.setBackground(State.backgroundColor);
		this.ipTextBox.setForeground(Color.white);
		this.ipTextBox.setVisible(false);
		this.ipTextBox.setFont(this.ipTextBoxFont);

		this.portTextBox.setBounds(Pong.getPortBoxX(), Pong.getPortBoxY(), Pong.getPortBoxWidth(),
				Pong.getPortBoxHeight());
		this.portTextBox.setText(DEFAULT_PORT_TEXTBOX_TEXT);
		this.portTextBox.setPreferredSize(new Dimension(Pong.getPortBoxWidth(), Pong.getPortBoxHeight()));
		this.portTextBox.setMinimumSize(new Dimension(Pong.getPortBoxWidth(), Pong.getPortBoxHeight()));
		this.portTextBox.setMaximumSize(new Dimension(Pong.getPortBoxWidth(), Pong.getPortBoxHeight()));
		this.portTextBox.setSize(Pong.getPortBoxWidth(), Pong.getPortBoxHeight());

		this.portTextBox.setBackground(State.backgroundColor);
		this.portTextBox.setForeground(Color.white);
		this.portTextBox.setVisible(false);
		this.portTextBox.setFont(this.portTextBoxFont);

		this.frame.add(this.ipTextBox);
		this.frame.add(this.portTextBox);
		this.frame.add(this.s);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	public void tick() {
		this.ipTextBox.setBackground(State.backgroundColor);
		this.ipTextBox.setForeground(Color.white);
		this.ipTextBox.setVisible(Pong.getPong().isIPTextBoxVisible());
		this.ipTextBox.setFont(this.ipTextBoxFont);
		this.portTextBox.setBackground(State.backgroundColor);
		this.portTextBox.setForeground(Color.white);
		this.portTextBox.setVisible(Pong.getPong().isPortTextBoxVisible());
		this.portTextBox.setFont(this.portTextBoxFont);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Screen getScreen() {
		return this.s;
	}

	public TextField getIPTextBox() {
		return this.ipTextBox;
	}

	public TextField getPortTextBox() {
		return this.portTextBox;
	}

	public Font getIPTextBoxFont() {
		return this.ipTextBoxFont;
	}

	public Font getPortTextBoxFont() {
		return this.portTextBoxFont;
	}
}
