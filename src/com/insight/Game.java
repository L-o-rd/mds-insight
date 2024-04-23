package com.insight;

import java.util.ArrayList;
import java.util.List;

import com.insight.graphics.Screen;
import com.insight.states.CreateState;
import com.insight.states.JoinState;
import com.insight.states.MenuState;
import com.insight.states.MinigamesState;
import com.insight.states.PrepareState;
import com.insight.states.RoomState;
import com.insight.states.XOState;

public class Game {
	private List<State> states;
	private int current;
	
	public final Input input;
	
	public Game(final Input input) {
		this.states = new ArrayList<>();
		this.states.add(new MenuState(this));
		this.states.add(new JoinState(this));
		this.states.add(new CreateState(this));
		this.states.add(new MinigamesState(this));
		this.states.add(new RoomState(this));
		this.states.add(new XOState(this));
		this.states.add(new PrepareState(this));
		this.current = State.MENU_STATE;
		this.input = input;
	}
	
	public final State getState() {
		return this.states.get(current);
	}
	
	public void setState(final int state) {
		this.current = state;
	}
	
	public void render(Screen screen) {
		this.states.get(current).render(screen);
	}
	
	public void update() {
		this.states.get(current).update(this.input);
	}
}
