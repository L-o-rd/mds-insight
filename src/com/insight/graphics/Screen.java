package com.insight.graphics;

public class Screen extends Bitmap {
	public Screen(int width, int height, int[] pixels) {
		super(width, height, pixels);
	}

	public void fillSquare(int x, int y, int size, int color) {
		for (int yy = y; yy < y + size; yy++) {
			for (int xx = x; xx < x + size; xx++) {
				if (xx >= 0 && xx < width && yy >= 0 && yy < height) {
					pixels[xx + yy * width] = color;
				}
			}
		}
	}
	public void render() {
		
	}
}
