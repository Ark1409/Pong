package com.pong.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import com.pong.pong.Pong;
import com.pong.states.State;
import com.pong.states.online.OnlineGameState;
import com.pong.utils.Logger;

public class PongWindowEvents implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		if (Pong.getPong().isDebug()) {
			Logger.logDebug("Pong Window Opened.");
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Logger.logInfo("Stopping...");
		Pong.getPong().getCurrentState().exit();
		// Pong.getPong().getThread().stop();
		if (Pong.getPong().getCurrentState().getStateType().equals(State.StateType.GAME_ONLINE)) {
			OnlineGameState state = (OnlineGameState) Pong.getPong().getCurrentState();
			try {
				state.getOnlinePlayer().close();
			} catch (IOException e1) {
				if (Pong.getPong().isDebug()) {
					Logger.logError("Unable to close Socket, InputStream and OutputStream for the OnlinePlayer.");
					e1.printStackTrace();
				}
			}
		}
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
