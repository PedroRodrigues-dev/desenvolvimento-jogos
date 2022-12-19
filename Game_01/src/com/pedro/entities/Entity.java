package com.pedro.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pedro.main.Game;

public class Entity {

	public static BufferedImage LIFEPACK = Game.spritesheet.getSprite(112, 0, 16, 16);
	public static BufferedImage WEAPON = Game.spritesheet.getSprite(128, 0, 16, 16);
	public static BufferedImage BULLET = Game.spritesheet.getSprite(112, 16, 16, 16);
	public static BufferedImage ENEMY = Game.spritesheet.getSprite(128, 16, 16, 16);

	protected double x;
	protected double y;
	protected int width;
	protected int height;

	private BufferedImage sprite;

	public Entity(double x, double y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	public void tick() {

	}

	public void render(Graphics graphics) {
		graphics.drawImage(sprite, this.getX(), this.getY(), null);
	}

	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
