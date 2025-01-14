package com.pong.states;

import java.awt.Color;
import java.awt.Graphics2D;

import com.pong.pong.Pong;

public abstract class State {
	protected StateType t;
	public static Color backgroundColor = Color.black.brighter().brighter().brighter().brighter().brighter().brighter();

	public State(StateType t) {
		this.t = t;
	}

	public abstract boolean isOnline();

	public abstract void init();

	public abstract void tick();

	public abstract void render(Graphics2D g);

	public void exit() {
		Pong.getPong().setBall(null);
		Pong.getPong().setCurrentPlayerOne(null);
		Pong.getPong().setCurrentPlayerTwo(null);
	}

	public State.StateType getStateType() {
		return this.t;
	}

	public enum StateType {
		GAME_MENU(0, "The main menu of the game."), GAME_SOLO(1, "Playing a match against the computer."),
		GAME_LOCAL(2, "Playing a match with someone (locally)."), GAME_ONLINE(3, "Playing a match online."),
		GAME_WAITING_ONLINE(4, "Waiting to enter a match (online)."), LOCAL_GAME_OVER(5, "Local game has ended."),
		CHOOSING_ONLINE_STATE_MODE(6,
				"Player is choosing whether he wants to play publically or on his custom server."),
		SOLO_GAME_OVER(7, "The solo game has finished."),
		ONLINE_GAME_CHOOSING_CONNECTION(8, "Player is entering ip and port.");
		private final int id;
		private final String description;

		private StateType(int id, String description) {
			this.id = id;
			this.description = description;
		}

		public static StateType getStateTypeByID(int i) {
			if (!isValidState(i))
				return null;
			if (i == 0) {
				return GAME_MENU;
			} else if (i == 1) {
				return GAME_SOLO;
			} else if (i == 2) {
				return GAME_LOCAL;
			} else if (i == 3) {
				return GAME_ONLINE;
			} else if (i == 4) {
				return GAME_WAITING_ONLINE;
			} else if (i == 5) {
				return LOCAL_GAME_OVER;
			} else if (i == 6) {
				return CHOOSING_ONLINE_STATE_MODE;
			} else if (i == 7) {
				return SOLO_GAME_OVER;
			} else if (i == 8) {
				return ONLINE_GAME_CHOOSING_CONNECTION;
			}
			return null;
		}

		public static boolean isValidState(int i) {
			return i <= State.StateType.values().length - 1 && i >= 0;
		}

		public static boolean isValidState(String desc) {
			return desc.equalsIgnoreCase("The main menu of the game.")
					|| desc.equalsIgnoreCase("Playing a match against the computer.")
					|| desc.equalsIgnoreCase("Playing a match with someone (locally).")
					|| desc.equalsIgnoreCase("Playing a match online.")
					|| desc.equalsIgnoreCase("Waiting to enter a match (online).")
					|| desc.equalsIgnoreCase("Local game has ended.")
					|| desc.equalsIgnoreCase(
							"Player is choosing whether he wants to play publically or on his custom server.")
					|| desc.equalsIgnoreCase("The solo game has finished.")
					|| desc.equalsIgnoreCase("Player is entering ip and port.")
					|| desc.equalsIgnoreCase("Playing a match against the computer")
					|| desc.equalsIgnoreCase("Playing a match with someone (locally)")
					|| desc.equalsIgnoreCase("Playing a match online")
					|| desc.equalsIgnoreCase("Waiting to enter a match (online)")
					|| desc.equalsIgnoreCase("Local game has ended")
					|| desc.equalsIgnoreCase(
							"Player is choosing whether he wants to play publically or on his custom server.")
					|| desc.equalsIgnoreCase("The solo game has finished")
					|| desc.equalsIgnoreCase("Player is entering ip and port")
					|| desc.equalsIgnoreCase("The main menu of the game");
		}

		public static StateType getStateType(String desc) {
			return getStateTypeByString(desc);
		}

		public static StateType getStateTypeByString(String desc) {
			if (!isValidState(desc))
				return null;
			if (desc.equalsIgnoreCase("The main menu of the game.")
					|| desc.equalsIgnoreCase("The main menu of the game")) {
				return StateType.GAME_MENU;
			}
			if (desc.equalsIgnoreCase("Playing a match against a computer.")
					|| desc.equalsIgnoreCase("Playing a match against the computer")) {
				return StateType.GAME_SOLO;
			}
			if (desc.equalsIgnoreCase("Playing a match with someone (locally).")
					|| desc.equalsIgnoreCase("Playing a match with someone (locally)")) {
				return StateType.GAME_LOCAL;
			}
			if (desc.equalsIgnoreCase("Playing a match online.") || desc.equalsIgnoreCase("Playing a match online")) {
				return StateType.GAME_ONLINE;
			}
			if (desc.equalsIgnoreCase("Waiting to enter a match (online).")
					|| desc.equalsIgnoreCase("Waiting to enter a match (online)")) {
				return StateType.GAME_WAITING_ONLINE;
			}

			if (desc.equalsIgnoreCase("Local game has ended") | desc.equalsIgnoreCase("Local game has ended")) {
				return StateType.LOCAL_GAME_OVER;
			}

			if (desc.equalsIgnoreCase("Player is choosing whether he wants to play publically or on his custom server.")
					|| desc.equalsIgnoreCase(
							"Player is choosing whether he wants to play publically or on his custom server.")) {
				return StateType.CHOOSING_ONLINE_STATE_MODE;
			}

			if (desc.equalsIgnoreCase("The solo game has finished.")
					|| desc.equalsIgnoreCase("The solo game has finished")) {
				return StateType.SOLO_GAME_OVER;
			}

			if (desc.equalsIgnoreCase("Player is entering ip and port.")
					|| desc.equalsIgnoreCase("Player is entering ip and port")) {
				return StateType.ONLINE_GAME_CHOOSING_CONNECTION;
			}
			return null;
		}

		public static StateType getStateType(int id) {
			return getStateTypeByID(id);
		}

		public int getID() {
			return this.id;
		}

		public String getDescription() {
			return this.description;
		}

	}
}
