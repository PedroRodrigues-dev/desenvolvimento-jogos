package com.pedro.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pedro.main.Game;

public class Player extends Entity {

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;

	private Direction lastDirection = Direction.RIGHT;

	public double speed = 1.4;

	private int frames = 0;
	private int maxFrames = 5;

	private int frameIndex = 0;
	private int maxFrameIndex = 3;

	private boolean moved = false;

	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		this.rightPlayer = new BufferedImage[4];
		this.leftPlayer = new BufferedImage[4];

		for (int i = 0; i <= 3; i++) {
			this.rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
			this.leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
		}
	}

	@Override
	public void tick() {
		this.moved = false;

		if (this.up) {
			this.moved = true;

			super.y -= this.speed;
		} else if (this.down) {
			this.moved = true;

			super.y += this.speed;
		}

		if (this.left) {
			this.moved = true;

			this.lastDirection = Direction.LEFT;

			super.x -= this.speed;
		} else if (this.right) {
			this.moved = true;

			this.lastDirection = Direction.RIGHT;

			super.x += this.speed;
		}

		if (this.moved) {
			this.frames++;

			if (this.frames == this.maxFrames) {
				this.frames = 0;
				this.frameIndex++;

				if (this.frameIndex > this.maxFrameIndex) {
					this.frameIndex = 0;
				}
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		if (this.lastDirection == Direction.LEFT) {
			graphics.drawImage(this.leftPlayer[this.frameIndex], super.getX(), super.getY(), null);
		} else if (this.lastDirection == Direction.RIGHT) {
			graphics.drawImage(this.rightPlayer[this.frameIndex], super.getX(), super.getY(), null);
		}
	}
}

enum Direction {
	LEFT,
	RIGHT
}
