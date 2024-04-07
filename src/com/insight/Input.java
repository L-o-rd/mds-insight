package com.insight;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
	private static final double MSCALE = 1 / (double) Content.SCALE;
	
	public final boolean[] keys;
	public boolean mouse[];
	public double mx, my;
	
	public Input() {
		this.keys = new boolean[KeyEvent.KEY_LAST + 1];
		this.mouse = new boolean[2];
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		var code = e.getKeyCode();
		
		if(0 <= code && code < this.keys.length) {
			this.keys[code] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		var code = e.getKeyCode();
		
		if(0 <= code && code < this.keys.length) {
			this.keys[code] = false;
		}
	}
	
	public boolean any() {
		for(int i = 0; i < this.keys.length; ++i) {
			if(this.keys[i]) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = (double)e.getX() * MSCALE;
		my = (double)e.getY() * MSCALE;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) mouse[0] = true;
		else if(e.getButton() == MouseEvent.BUTTON3) mouse[1] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) mouse[0] = false;
		else if(e.getButton() == MouseEvent.BUTTON3) mouse[1] = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
