package com.insight.states;

import com.insight.Content;
import com.insight.Database;
import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.*;

import java.util.List;
import java.util.Random;

public class CreateState extends State {
	private boolean idGenerated = false;
	private Button generateCode;
	private Button back, next;
	private String uniqueId;
	
	public CreateState(Game game) {
		super(game);

		this.next = new SmallButton((Content.WIDTH >> 1) - SmallButton.width() - 5, Content.HEIGHT - 10 - SmallButton.height(), "Next") {
			@Override
			public void clicked() {
				if(uniqueId == null) return;
				game.setState(PREPARE_STATE);
			}
		};

		this.back = new SmallButton((Content.WIDTH >> 1) + 5, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				uniqueId = null;
				idGenerated = false;
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
	
	public void init() {
		idGenerated = false;
		uniqueId = null;
	}
	
	public String getID() {
		return this.uniqueId;
	}
	
	public void resetID() {
		this.uniqueId = null;
		this.idGenerated = false;
	}
	
	private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String generateUniqueId() {
		Random random = new Random();
		String Id;

		List<String> existingIds = Database.get().getRoomsIDs();
		do {
			StringBuffer sb = new StringBuffer(6);
			for (int i = 0; i < 6; i++)
			{
				sb.append(chars.charAt(random.nextInt(chars.length())));
			}
			
			Id = sb.toString();
		} while(existingIds.contains(Id));
		
		Database.get().createRoom(Id);
		return Id;
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
		this.back.render(screen, game.input);
		this.next.render(screen, game.input);
		this.generateCode.render(screen, game.input);

		if (idGenerated && uniqueId != null) {
			Font.write(screen, uniqueId, ((screen.width - uniqueId.length() * Font.CHAR_WIDTH * 2) >> 1) - 1, ((screen.height - 65) >> 1) + 2, 0x132413, 2);
			Font.write(screen, uniqueId, (screen.width - uniqueId.length() * Font.CHAR_WIDTH * 2) >> 1, (screen.height - 65) >> 1, 0xffffff, 2);
		}
	}


	@Override
	public void update(Input input) {
		this.back.update(input);
		this.next.update(input);
		this.generateCode.update(input);
	}

}