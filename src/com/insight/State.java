package com.insight;

import com.insight.graphics.Screen;

public abstract class State {
	public static final int MENU_STATE = 0;
	public static final int JOIN_STATE = 1;
	public static final int CREATE_STATE = 2;
	public static final int MINIGAMES_STATE = 3;
	
	protected Game game;
	
	public State(final Game game) {
		this.game = game;
	}
	
	public abstract void render(Screen screen);
	public abstract void update(Input input);
}