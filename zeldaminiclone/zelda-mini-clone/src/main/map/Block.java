package main.map;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.constant.Sprite;
import main.graphics.Spritesheet;

public class Block extends Rectangle {
    
    public Block(int x, int y) {
        super(x, y, Sprite.WIDTH, Sprite.HEIGHT);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(Spritesheet.tileWall, x, y, Sprite.WIDTH, Sprite.HEIGHT, null);
    }
}
