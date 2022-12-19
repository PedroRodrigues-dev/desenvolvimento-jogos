package com.pedro.world.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pedro.main.Game;

public class Tile {
    public static BufferedImage FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
    public static BufferedImage WALL = Game.spritesheet.getSprite(16, 0, 16, 16);

    private BufferedImage sprite;

    private int x;
    private int y;

    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;

        this.sprite = sprite;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.sprite, this.x, this.y, null);
    }
}