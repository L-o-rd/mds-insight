package com.insight.states;

import com.insight.Content;
import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.*;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CreateState extends State {

	private Button next,back;
	private boolean idGenerated = false;
	private Button generateCode;
	private static final String ID_FILE_PATH = "./res/ids";
	private String uniqueId;
	public CreateState(Game game) {
		super(game);

		this.next = new SmallButton((Content.WIDTH >> 1) - SmallButton.width() - 5, Content.HEIGHT - 10 - SmallButton.height(), "Next") {
			@Override
			public void clicked() {

			}
		};

		this.back = new SmallButton((Content.WIDTH >> 1) + 5, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				game.setState(MENU_STATE);
			}
		};

		this.generateCode = new BigButton((Content.WIDTH - BigButton.width()) / 2,  ((Content.HEIGHT - BigButton.height() ) /2),"Generate Code") {
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
		try(BufferedReader reader = new BufferedReader(new FileReader(ID_FILE_PATH))) {
			String line;
			while ((line = reader.readLine()) != null) {
				existingIds . add (line.trim());
			}
		}catch (IOException e) {
			System.err.println("File not found or error reading the file:");
			e.printStackTrace();
		}
		return existingIds;
	}

	private static void saveIdInFile (String id) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ID_FILE_PATH))){
			writer.write(id);
			writer.newLine();
		}catch (IOException e){
			System.err.println("File not found or error writing the file:");
			e.printStackTrace();
		}
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
		this.back.render(screen, game.input);
		this.next.render(screen, game.input);
		this.generateCode.render(screen, game.input);

		if (idGenerated && uniqueId != null) {
			Font.write(screen, uniqueId, (screen.width - uniqueId.length() - 40) >> 1, (screen.height + 20) / 2, 0x607981 );
		}
	}


	@Override
	public void update(Input input) {
		this.back.update(input);
		this.next.update(input);
		this.generateCode.update(input);
	}

}