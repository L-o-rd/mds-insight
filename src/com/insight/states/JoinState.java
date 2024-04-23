package com.insight.states;

import com.insight.graphics.*;
import com.insight.Content;
import com.insight.Input;
import com.insight.State;
import com.insight.Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JoinState extends State {
	private boolean codeFound, wasClicked;
	private String inputText;
	private Button submit;
	private Button back;
	private TextBox box;

	public JoinState(Game game) {
		super(game);
		
		wasClicked = false;
		this.box = new TextBox((Content.HEIGHT - 15) >> 1, 15, 6);

		this.submit = new BigButton((Content.WIDTH - BigButton.width()) >> 1, ((Content.HEIGHT + SmallButton.height()) >> 1) + 10, "Submit") {
			@Override
			public void clicked() {
				// Set the input text to the variable when the submit button is clicked
				inputText = box.getInputText();
				verifyInputFile(inputText);
				wasClicked = true;

				if (codeFound) {
					game.setState(ROOM_STATE);
				}
			}
		};
		
		this.back = new SmallButton((Content.WIDTH - SmallButton.width()) >> 1, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				wasClicked = false;
				game.setState(State.MENU_STATE);
			}
		};
	}

	private void verifyInputFile(String inputCode) {
		this.codeFound = false;
		try {
			BufferedReader bufferedReader = new BufferedReader(
				new FileReader(new File(CreateState.ID_FILE_PATH))
			);

			String code;
			while ((code = bufferedReader.readLine()) != null) {
				if (code.equalsIgnoreCase(inputCode)) {
					codeFound = true;
					break;
				}
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);

		if (wasClicked && !codeFound) {
			final String msg = "Join code '" + inputText + "' is not valid!";
			Font.write(screen, msg, (screen.width - msg.length() * Font.CHAR_WIDTH) >> 1, Font.CHAR_HEIGHT * 6, 0);
		}

		this.box.render(screen, game.input);
		this.back.render(screen, game.input);
		this.submit.render(screen, game.input);
	}

	@Override
	public void update(Input input) {
		this.box.update(input);
		this.back.update(input);
		this.submit.update(input);
	}

	// Method to get the input text stored in the variable
	public String getInputText() {
		return inputText;
	}
}