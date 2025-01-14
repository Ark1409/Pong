package com.pong.states;

public abstract class OfflineState extends State {

	public OfflineState(StateType t) {
		super(t);
	}

	@Override
	public boolean isOnline() {
		return false;
	}

}
