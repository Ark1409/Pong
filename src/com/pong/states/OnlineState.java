package com.pong.states;

public abstract class OnlineState extends State {

	public OnlineState(StateType t) {
		super(t);
	}

	@Override
	public boolean isOnline() {
		return true;
	}
}
