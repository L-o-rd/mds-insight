package com.insight.states;

import java.util.List;
import java.util.ArrayList;

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

public class PrepareState extends State {
	private TextBox question, choice1, choice2, choice3, choice4;
	private Button add, view, start, back, delete;
	private List<Question> questions;
	private boolean viewQuestions;
	private int xback = 0;
	
	public PrepareState(Game game) {
		super(game);
		
		this.questions = new ArrayList<>();
		
		this.add = new SmallButton(((Content.WIDTH) >> 1) - (SmallButton.width()) - 5, 7, "Add") {
			@Override
			public void clicked() {
				
			}
		};
		
		this.view = new SmallButton(((Content.WIDTH) >> 1) + 5, 7, "View") {
			@Override
			public void clicked() {
				viewQuestions = true;
			}
		};
		
		this.start = new SmallButton(((Content.WIDTH) >> 1) - (SmallButton.width()) - 5, Content.HEIGHT - 7 - SmallButton.height(), "Start") {
			@Override
			public void clicked() {
				
			}
		};
		
		this.back = new SmallButton(((Content.WIDTH) >> 1) + 5, Content.HEIGHT - 7 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				if(!viewQuestions) {
					game.setState(CREATE_STATE);
				} else {
					viewQuestions = false;
				}
			}
		};
		
		this.delete = new SmallButton(((Content.WIDTH) >> 1) - (SmallButton.width()) - 5, Content.HEIGHT - 7 - SmallButton.height(), "Delete") {
			@Override
			public void clicked() {
				
			}
		};
		
		this.question = new TextBox(7 + SmallButton.height() + 15, 15, 20);
		this.question.x = (Content.WIDTH - this.question.width) >> 1;
		this.viewQuestions = false;
	}

	@Override
	public void render(Screen screen) {
		screen.clear(0xf6c858);
		screen.blitWrap(Art.back, xback * 1, 0);
		screen.blitWrap(Art.back, 30 + xback + Art.back.width, 0);
		++xback;
		
		if(!this.viewQuestions) {
			this.add.render(screen, game.input);
			this.view.render(screen, game.input);
			this.start.render(screen, game.input);
			
			this.question.render(screen, game.input);
		} else {
			this.delete.render(screen, game.input);
		}

		this.back.render(screen, game.input);
	}

	@Override
	public void update(Input input) {
		if(!this.viewQuestions) {
			this.add.update(input);
			this.view.update(input);
			this.start.update(input);
			
			this.question.update(input);
		} else {
			this.delete.update(input);
		}

		this.back.update(input);
	}

}
