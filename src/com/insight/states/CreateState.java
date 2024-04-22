package com.insight.states;

import com.insight.Content;
import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CreateState extends State {

	private Button back;
	private boolean idGenerated = false;
	private Button generateCode;
	private static final String ID_FILE_PATH = "./res/ids";
	private String uniqueId;
	public CreateState(Game game) {
		super(game);

		this.back = new SmallButton((Content.WIDTH - SmallButton.width()) >> 1, Content.HEIGHT -10-SmallButton.height(), "Back") {
			@Override
			public void clicked() {game.setState(State.MENU_STATE); }
		};

		this.generateCode = new BigButton((Content.WIDTH - BigButton.width()-200)  >>1, ((Content.HEIGHT + BigButton.height() - 170) >> 1) ,"Generate Code") {
			@Override
			public void clicked() {
				idGenerated = true;
				uniqueId = generateUniqueId();
			}
		};
	}


	//random 6 characters alphanumeric code
	public static String generateUniqueId() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		String Id;

		Set<String> existingIds = loadExistingIds();
		do {
			StringBuffer sb = new StringBuffer(6);
			for ( int i = 0; i<=5; i++)
				{
					sb.append(chars.charAt(random.nextInt(chars.length())));
				}
			Id = sb.toString();
		}while(existingIds.contains(Id));
		saveIdInFile (Id);
		return Id;
	}

	private static Set<String> loadExistingIds() {
		Set<String> existingIds = new HashSet<>();
		if (fileExists(ID_FILE_PATH)) {
		try(BufferedReader reader = new BufferedReader(new FileReader(ID_FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				existingIds . add (line.trim());
			}
		} catch (IOException e) {
			System.err.println("error reading the file:" + e.getMessage());
		} }
		return existingIds;
	}

	private static void saveIdInFile (String id) {
		if (fileExists(ID_FILE_PATH)) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE_PATH, true))) {
				writer.write(id);
				writer.newLine();
			} catch (IOException e) {
				System.err.println("error writing the file:" + e.getMessage());
			}
		}
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
		this.back.render(screen, game.input);
		this.generateCode.render(screen, game.input);

		if (idGenerated && uniqueId != null) {
			Font.write(screen, uniqueId, (screen.width - uniqueId.length() - 60) >> 1, (screen.height - 140) >> 1, 0x607981 );
		}
	}


	@Override
	public void update(Input input) {
		this.back.update(input);
		this.generateCode.update(input);
	}

	private static boolean fileExists(String ID_FILE_PATH) {
		return Files.exists(Paths.get(ID_FILE_PATH));
	}

}
