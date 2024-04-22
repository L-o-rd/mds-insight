package com.insight.states;

import com.insight.Content;
import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.Font;
import com.insight.graphics.Screen;
import com.insight.graphics.SmallButton;
import com.insight.graphics.Button;

public class MinigamesState extends State {

	private Button back;

	public MinigamesState(Game game) {
		super(game);

		this.back = new SmallButton((Content.WIDTH - SmallButton.width()) >> 1, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				game.setState(State.MENU_STATE);
			}
		};
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
		Font.write(screen, "minigames...", (screen.width - 12 * 7) >> 1, (screen.height - 9) >> 1, 0xdfdfdf);

		this.back.render(screen, game.input);
	}

	@Override
	public void update(Input input) {
		this.back.update(input);
	}

}
