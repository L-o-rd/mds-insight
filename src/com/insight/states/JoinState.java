package com.insight.states;

import com.insight.graphics.SmallButton;
import com.insight.graphics.TextBox;
import com.insight.graphics.Button;
import com.insight.graphics.Screen;
import com.insight.Content;
import com.insight.Input;
import com.insight.State;
import com.insight.Game;

public class JoinState extends State {
	private Button back;
	private TextBox box;

	public JoinState(Game game) {
		super(game);
		
		this.box = new TextBox((Content.HEIGHT - 15) >> 1, 15, 6);
		
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
		this.box.render(screen, game.input);
		this.back.render(screen, game.input);
	}

	@Override
	public void update(Input input) {
		this.box.update(input);
		this.back.update(input);
	}

}
