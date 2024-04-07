package com.insight.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public final class Art {
	public final static Bitmap logo = Art.load("./res/logo.png");
	public final static Bitmap font = Art.load("./res/font.png");
	
	public static Bitmap load(final String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			var ans = new Bitmap(image.getWidth(), image.getHeight());
			image.getRGB(0, 0, ans.width, ans.height, ans.pixels, 0, ans.width);
			return ans;
		} catch (Exception ie) {
			ie.printStackTrace();
		}
		
		return null;
	}
}
