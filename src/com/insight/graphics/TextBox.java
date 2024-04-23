package com.insight.graphics;

import java.awt.event.KeyEvent;
import java.util.List;

import com.insight.Content;
import com.insight.Input;

public class TextBox {
	private static final int BORDER_SIZE = 2;
	
	public int x, y, width, height, limit;
	public boolean selected;
	public StringBuffer sb;
	
	public TextBox(final int y, final int height, final int limit) {
		this.width = 4 + limit * Font.CHAR_WIDTH;
		this.x = (Content.WIDTH - this.width) >> 1;
		this.sb = new StringBuffer();
		this.selected = false;
		this.height = height;
		this.limit = limit;
		this.y = y;
	}
	
	public void render(Screen screen, Input input) {
		screen.fill(x - BORDER_SIZE, y - BORDER_SIZE, width + 2 * BORDER_SIZE, height + 2 * BORDER_SIZE, 0x7f7f7f);
		
		if(this.selected) {
			screen.fill(x, y, width, height, 0x9f9f9f);
		} else {
			screen.fill(x, y, width, height, 0xefefef);
		}
		
		Font.write(screen, sb.toString(), x + 2, y + (height - Font.CHAR_HEIGHT) / 2, 0);
	}
	
	public void update(Input input) {
		if(this.hovered(input) && input.mouse[0]) {
			this.selected = true;
			return;
		} else if(input.mouse[0]) {
			this.selected = false;
			return;
		}
		
		if(this.selected) {
			if(input.keys[KeyEvent.VK_ENTER]) {
				this.selected = false;
				return;
			}
			
			if(input.keys[KeyEvent.VK_BACK_SPACE]) {
				if(!this.sb.isEmpty()) {
					this.sb.deleteCharAt(sb.length() - 1);
					input.lastChar = '\b';
				}
				
				input.keys[KeyEvent.VK_BACK_SPACE] = false;
				return;
			} else {
				if(this.sb.length() >= this.limit) return;
				if(input.lastChar == '\b') return;
				this.sb.append(input.lastChar);
				input.lastChar = '\b';
				/* for(int i = 0; i < input.keys.length; ++i) {
					if(forbidden.contains(i)) continue;
					if(input.keys[i]) {
						this.sb.append((char)i);
						input.keys[i] = false;
						break;
					}
				} */
			}
		} else {
			if(input.keys[KeyEvent.VK_ENTER]) {
				input.keys[KeyEvent.VK_ENTER] = false;
			}
		}
	}
	
	public String getInputText() {
		return sb.toString();
	}
	
	public boolean hovered(final Input input) {
		return input.mx >= this.x && input.my >= this.y &&
				input.mx <= (this.x + this.width) &&
				input.my <= (this.y + this.height);
	}
}
