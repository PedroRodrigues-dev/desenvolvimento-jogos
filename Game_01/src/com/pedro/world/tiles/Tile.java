package com.pedro.world.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pedro.main.Game;
import com.pedro.world.Camera;

public class Tile {
    public static BufferedImage FLOOR_SPRITE = Game.spritesheet.getSprite(0, 0, 16, 16);
    public static BufferedImage WALL_SPRITE = Game.spritesheet.getSprite(16, 0, 16, 16);

    private BufferedImage sprite;

    private int x;
    private int y;

    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;

        this.sprite = sprite;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.sprite, this.x - Camera.x, this.y - Camera.y, null);
    }
}
