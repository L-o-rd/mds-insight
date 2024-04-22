package com.insight.states;

import java.awt.event.KeyEvent;

import com.insight.Content;
import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.Art;
import com.insight.graphics.Button;
import com.insight.graphics.Font;
import com.insight.graphics.Screen;
import com.insight.graphics.SmallButton;
import com.insight.graphics.TextBox;

public class RoomState extends State {
	private int currentAvatar;
	private TextBox nameBox;
	private Button next, back;

	private double ang = 0.0;
	private int xarrow = 0;
	private int xback = 0;

	public RoomState(Game game) {
		super(game);
		
		this.nameBox = new TextBox(Content.HEIGHT - 15 * 4, 15, 15);
		this.currentAvatar = 0;
		
		this.next = new SmallButton((Content.WIDTH >> 1) - SmallButton.width() - 5, Content.HEIGHT - 10 - SmallButton.height(), "Next") {
			@Override
			public void clicked() {
				
			}
		};
		
		this.back = new SmallButton((Content.WIDTH >> 1) + 5, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				game.setState(JOIN_STATE);
			}
		};
	}

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
		screen.blitWrap(Art.back, xback * 1, 0);
		screen.blitWrap(Art.back, 30 + xback + Art.back.width, 0); ++xback;
		Font.write(screen, "Choose your avatar:", ((screen.width - 19 * Font.CHAR_WIDTH) >> 1) + 1, 11, 0x7f8f7f);
		Font.write(screen, "Choose your avatar:", (screen.width - 19 * Font.CHAR_WIDTH) >> 1, 10, 0xdfefdf);
		screen.blit(Art.avatars.get(this.currentAvatar), (screen.width - 64) >> 1, (screen.height - 96) >> 1);
		Font.write(screen, "Name:", 68, this.nameBox.y + 2, 0);
		this.nameBox.render(screen, game.input);
		
		if(this.currentAvatar == 0) {
			screen.blit(Art.arrow, ((screen.width - 64) >> 1) + 64 + 15 + xarrow, ((screen.height - 114) >> 1) + (114 - Art.arrow.height) >> 1);
		} else if(this.currentAvatar == Art.avatars.size() - 1) {
			screen.blitHFlip(Art.arrow, ((screen.width - 64) >> 1) - Art.arrow.width - (15 + xarrow), ((screen.height - 114) >> 1) + (114 - Art.arrow.height) >> 1);
		} else {
			screen.blit(Art.arrow, ((screen.width - 64) >> 1) + 64 + 15 + xarrow, ((screen.height - 114) >> 1) + (114 - Art.arrow.height) >> 1);
			screen.blitHFlip(Art.arrow, ((screen.width - 64) >> 1) - Art.arrow.width - (15 + xarrow), ((screen.height - 114) >> 1) + (114 - Art.arrow.height) >> 1);
		}
		
		this.next.render(screen, game.input);
		this.back.render(screen, game.input);
		
		xarrow = (int)(5 * Math.cos(ang));
		ang += -0.01 * (xarrow - 9);
	}

	@Override
	public void update(Input input) {
		this.nameBox.update(input);
		
		if(!this.nameBox.selected) {
			if(input.keys[KeyEvent.VK_LEFT] && this.currentAvatar > 0) {
				--this.currentAvatar;
				input.keys[KeyEvent.VK_LEFT] = false;
			}
			
			if(input.keys[KeyEvent.VK_RIGHT] && this.currentAvatar < Art.avatars.size() - 1) {
				++this.currentAvatar;
				input.keys[KeyEvent.VK_RIGHT] = false;
			}
			
			this.next.update(input);
			this.back.update(input);
		}
	}

}
