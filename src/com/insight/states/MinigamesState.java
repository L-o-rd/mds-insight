package com.insight.states;

import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.Font;
import com.insight.graphics.Screen;

public class MinigamesState extends State {

	public MinigamesState(Game game) {
		super(game);
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
		Font.write(screen, "minigames...", (screen.width - 12 * 7) >> 1, (screen.height - 9) >> 1, 0xdfdfdf);
	}

	@Override
	public void update(Input input) {
		
	}

}
