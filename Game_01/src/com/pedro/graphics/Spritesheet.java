package com.pedro.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private BufferedImage spritesheet;
	
	public Spritesheet(String path) {
		try {
			this.spritesheet = ImageIO.read(this.getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int heigth) {
		return spritesheet.getSubimage(x, y, width, heigth);		
	}
}
