package com.pedro.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pedro.world.tiles.Floor;
import com.pedro.world.tiles.Tile;
import com.pedro.world.tiles.Wall;

public class World {

    public static int WIDTH;
    public static int HEIGHT;

    private Tile[] tiles;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(this.getClass().getResource(path));

            World.WIDTH = map.getWidth();
            World.HEIGHT = map.getHeight();

            int[] mapPixels = new int[World.WIDTH * World.HEIGHT];

            map.getRGB(0, 0, World.WIDTH, World.HEIGHT, mapPixels, 0, World.WIDTH);

            this.tiles = new Tile[World.WIDTH * World.HEIGHT];

            for (int x = 0; x < World.WIDTH; x++) {
                for (int y = 0; y < World.HEIGHT; y++) {
                    switch (mapPixels[x + (y * World.WIDTH)]) {
                        case 0xFFFFFFFF:
                            this.tiles[x + (y * World.WIDTH)] = new Wall(x * 16, y * 16, Tile.WALL);
                            break;
                        case 0xFF000000:
                            this.tiles[x + (y * World.WIDTH)] = new Floor(x * 16, y * 16, Tile.FLOOR);
                            break;
                        case 0xFF4800FF:
                        default:
                            this.tiles[x + (y * World.WIDTH)] = new Floor(x * 16, y * 16, Tile.FLOOR);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics graphics) {
        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {
                Tile tile = this.tiles[x + (y * World.WIDTH)];
                tile.render(graphics);
            }
        }
    }
}
