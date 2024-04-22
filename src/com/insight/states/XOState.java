package com.insight.states;

import com.insight.Content;
import com.insight.Game;
import com.insight.Input;
import com.insight.State;
import com.insight.graphics.Art;
import com.insight.graphics.Bitmap;
import com.insight.graphics.Button;
import com.insight.graphics.Screen;
import com.insight.graphics.SmallButton;

public class XOState extends State {
	private class XOCell {
		public enum XOT {
			X("./res/xo/x.png") {
				@Override
				public XOT next() {
					return O;
				}
			}, 
			
			O("./res/xo/o.png") {
				@Override
				public XOT next() {
					return Empty;
				}
			}, 
			
			Empty("./res/xo/e.png") {
				@Override
				public XOT next() {
					return X;
				}
			};
			
			private final Bitmap img;
			
			private XOT(final String path) {
				this.img = Art.load(path);
			}
			
			public void render(Screen screen, final int x, final int y) {
				screen.blit(this.img, x, y);
			}
			
			public abstract XOT next();
		}
		
		public XOT type;
		
		public XOCell() {
			this.type = XOT.Empty;
		}
		
		public void render(Screen screen, int x, int y) {
			this.type.render(screen, x, y);
		}
	}
	
	private final XOCell[] board;
	private int xback = 0;
	private Button back;

	public XOState(Game game) {
		super(game);
		
		this.board = new XOCell[9];
		for(int i = 0; i < this.board.length; ++i) {
			this.board[i] = new XOCell();
		}
		
		this.back = new SmallButton((Content.WIDTH - SmallButton.width()) >> 1, Content.HEIGHT - 10 - SmallButton.height(), "Back") {
			@Override
			public void clicked() {
				game.setState(State.MINIGAMES_STATE);
			}
		};
	}
	
	final int w = 10, s = 25;

	@Override
	public void render(Screen screen) {
		screen.fill(0, 0, screen.width, screen.height, 0xf6c858);
		screen.blitWrap(Art.back, xback * 1, 0);
		screen.blitWrap(Art.back, 30 + xback + Art.back.width, 0); ++xback;
		
		int posx = (screen.width >> 1) - ((w + s) * 3) / 2;
		int posy = (screen.height >> 1) - ((w + s) * 3) / 2;
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				this.board[j + i * 3].render(screen, posx + j * (w + s), posy + i * (w + s));
			}
		}
		
		this.back.render(screen, game.input);
	}

	@Override
	public void update(Input input) {
		int posx = (Content.WIDTH >> 1) - ((w + s) * 3) / 2;
		int posy = (Content.HEIGHT >> 1) - ((w + s) * 3) / 2;
		for(int i = 0; i < 3; ++i) {
			final int y = posy + i * (w + s);
			for(int j = 0; j < 3; ++j) {
				final int x = posx + j * (w + s);
				if(input.mx >= x && input.mx <= (x + 32) &&
						input.my >= y && input.my <= (y + 32)) {
					if(input.mouse[0]) {
						this.board[j + i * 3].type = this.board[j + i * 3].type.next();
						input.mouse[0] = false;
					}
				}
			}
		}
		
		this.back.update(input);
	}

}
