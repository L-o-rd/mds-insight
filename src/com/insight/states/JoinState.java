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
	private Button back;
	private TextBox box;
	// Variable to store the input text
	private Button submit;
	private String inputText;

	public JoinState(Game game) {
		super(game);
		
		this.box = new TextBox((Content.HEIGHT - 15) >> 1, 15, 6);

		this.submit = new BigButton((Content.WIDTH - BigButton.width()) >> 1, ((Content.HEIGHT + SmallButton.height()) >> 1) + 10, "Submit") {
			@Override
			public void clicked() {
				// Set the input text to the variable when the submit button is clicked
				inputText = box.getInputText();
				boolean codeFound = verifyInputFile(inputText);
				//	boolean codeFound = verifyInput(inputText); // daca nu merge cu fisierul text codes.txt
																// uncomment this and comment verifyInputFile(inputText)
				if (codeFound) {
					game.setState(MINIGAMES_STATE);
				} else {
					game.setState(MENU_STATE);
				}
			}
		};
		
		this.back = new SmallButton((Content.WIDTH - SmallButton.width()) >> 1, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				game.setState(State.MENU_STATE);
			}
		};
	}

	public boolean verifyInput(String inputCode) {
		boolean codeFound = false;

		String[] codes = {"123abc", "123456", "abc456"};

		for (String code : codes) {
			if (code.equalsIgnoreCase(inputCode)) {
				codeFound = true;
				break;
			}
		}

		if (codeFound) {
			System.out.println("The code " + inputCode + " is correct");
		} else {
			System.out.println("The code " + inputCode + " is not correct");
		}

		return codeFound;
	}

	public boolean verifyInputFile(String inputCode) {

		String filePath = new File("src/com/insight/files/codes.txt").getAbsolutePath();
		System.out.println("Text file located at: " + filePath);

		File file = new File(filePath);
		boolean codeFound = false;

		try {
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);

			String code;
			while ((code = bufferedReader.readLine()) != null) {
				if (code.equalsIgnoreCase(inputCode)) {
					codeFound = true;
					break;
				}
			}

			bufferedReader.close();

			if (codeFound) {
				System.out.println("The code " + inputCode + " is correct");
			} else {
				System.out.println("The code " + inputCode + " is not correct");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return codeFound;
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
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
