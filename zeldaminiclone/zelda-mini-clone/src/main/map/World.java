package main.map;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import main.constant.Frame;
import main.constant.Sprite;

public class World {

    public static LinkedList<Block> blocks = new LinkedList<Block>();

    private final int worldWidthInBlocks = Math.round(Frame.WIDTH / Sprite.WIDTH - 1);
    private final int worldHeightInBlocks = Math.round(Frame.HEIGHT / Sprite.HEIGHT - 1);

    public World() {
        for (int worldY = 0; worldY <= worldHeightInBlocks; worldY++) {
            for (int worldX = 0; worldX <= worldWidthInBlocks; worldX++) {
                if (isWorldBorder(worldX, worldY)) {
                    blocks.add(new Block(worldX * Sprite.WIDTH, worldY * Sprite.HEIGHT));
                }
            }
        }

        blocks.add(new Block(6 * Sprite.WIDTH, 6 * Sprite.HEIGHT));
        blocks.add(new Block(7 * Sprite.WIDTH, 6 * Sprite.HEIGHT));
        blocks.add(new Block(8 * Sprite.WIDTH, 6 * Sprite.HEIGHT));
    }

    public void render(Graphics graphics) {
        for (Block block : blocks) {
            block.render(graphics);
        }
    }

    public static boolean isColiding(int x, int y) {
        for (Block block : blocks) {
            if (block.intersects(new Rectangle(x, y, Sprite.WIDTH, Sprite.HEIGHT))) {
                return true;
            }
        }

        return false;
    }

    private boolean isWorldBorder(int x, int y) {
        if (y == 0 || y == worldHeightInBlocks) {
            return true;
        }

        if (x == 0 || x == worldWidthInBlocks) {
            return true;
        }

        return false;
    }
}
