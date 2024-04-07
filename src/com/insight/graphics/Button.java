package com.insight.graphics;

import com.insight.Input;

public abstract class Button {
	protected Bitmap image;
	private String label;
	public int x, y;
	
	public Button(final int width, final int height, final int x, final int y, final String label) {
		this(width, height, x, y);
		this.label = label;
	}
	
	public Button(final int width, final int height, final int x, final int y) {
		this.image = new Bitmap(width, height);
		this.image.fill(0, 0, width, height, 0xdfdfdf);
		this.x = x; this.y = y;
	}
	
	public void render(Screen screen, Input input) {
		if(this.hovered(input)) {
			screen.fill(this.x + 3, this.y + 3, this.image.width, this.image.height, 0xafafaf);
			screen.blit(this.image, this.x + 1, this.y + 1);
			Font.write(screen, label, x + 2 + ((image.width - label.length() * Font.CHAR_WIDTH) >> 1), y + 2 + ((image.height - Font.CHAR_HEIGHT) >> 1), 0x7f7f7f);
		} else {
			screen.blit(this.image, this.x, this.y);
			Font.write(screen, label, x + ((image.width - label.length() * Font.CHAR_WIDTH) >> 1), y + ((image.height - Font.CHAR_HEIGHT) >> 1), 0xffffff);
		}
	}
	
	public void update(Input input) {
		if(this.hovered(input) && input.mouse[0]) {
			this.clicked();
			input.mouse[0] = false;
		}
	}
	
	public abstract void clicked();
	
	public boolean hovered(final Input input) {
		return input.mx >= this.x && input.my >= this.y &&
				input.mx <= (this.x + this.image.width) &&
				input.my <= (this.y + this.image.height);
	}
}
