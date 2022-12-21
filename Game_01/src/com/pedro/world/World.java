package com.pedro.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.pedro.entities.Bullet;
import com.pedro.entities.Enemy;
import com.pedro.entities.Entity;
import com.pedro.entities.LifePack;
import com.pedro.entities.Weapon;
import com.pedro.main.Game;
import com.pedro.world.tiles.Floor;
import com.pedro.world.tiles.Tile;
import com.pedro.world.tiles.Wall;

public class World {

    public static int WIDTH;
    public static int HEIGHT;

    public static final int TILE_SIZE = 16;

    public static Tile[] tiles;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(this.getClass().getResource(path));

            World.WIDTH = map.getWidth();
            World.HEIGHT = map.getHeight();

            int[] mapPixels = new int[World.WIDTH * World.HEIGHT];

            map.getRGB(0, 0, World.WIDTH, World.HEIGHT, mapPixels, 0, World.WIDTH);

            World.tiles = new Tile[World.WIDTH * World.HEIGHT];

            for (int x = 0; x < World.WIDTH; x++) {
                for (int y = 0; y < World.HEIGHT; y++) {
                    World.tiles[x + (y * World.WIDTH)] = new Floor(x * 16, y * 16, Tile.FLOOR_SPRITE);
                    switch (mapPixels[x + (y * World.WIDTH)]) {
                        case 0xFFFFFFFF:
                            World.tiles[x + (y * World.WIDTH)] = new Wall(x * 16, y * 16, Tile.WALL_SPRITE);
                            break;
                        case 0xFFB200FF:
                            Game.entities.add(new LifePack(x * 16, y * 16, 16, 16, Entity.LIFEPACK_SPRITE));
                            break;
                        case 0xFFFF6A00:
                            Game.entities.add(new Weapon(x * 16, y * 16, 16, 16, Entity.WEAPON_SPRITE));
                            break;
                        case 0xFFFFD800:
                            Game.entities.add(new Bullet(x * 16, y * 16, 16, 16, Entity.BULLET_SPRITE));
                            break;
                        case 0xFFFF0000:
                            Enemy enemy = new Enemy(x * 16, y * 16, 16, 16, Entity.ENEMY_SPRITE);
                            Game.entities.add(enemy);
                            Game.enemies.add(enemy);
                            break;
                        case 0xFF4800FF:
                            Game.player.setX(x * 16);
                            Game.player.setY(y * 16);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics graphics) {
        int xStart = Camera.x >> 4;
        int yStart = Camera.y >> 4;

        int xFinal = xStart + (Game.WIDTH >> 4);
        int yFinal = yStart + (Game.HEIGHT >> 4);

        for (int x = xStart; x <= xFinal; x++) {
            for (int y = yStart; y <= yFinal; y++) {
                if (x >= 0 && x < World.WIDTH && y >= 0 && y < World.HEIGHT) {
                    Tile tile = World.tiles[x + (y * World.WIDTH)];
                    tile.render(graphics);
                }
            }
        }
    }

    public static boolean isFree(int x, int y) {
        int xTile1 = x / World.TILE_SIZE;
        int yTile1 = y / World.TILE_SIZE;

        int xTile2 = (x + World.TILE_SIZE - 1) / World.TILE_SIZE;
        int yTile2 = y / World.TILE_SIZE;

        int xTile3 = x / World.TILE_SIZE;
        int yTile3 = (y + World.TILE_SIZE - 1) / World.TILE_SIZE;

        int xTile4 = (x + World.TILE_SIZE - 1) / World.TILE_SIZE;
        int yTile4 = (y + World.TILE_SIZE - 1) / World.TILE_SIZE;

        return !(World.tiles[xTile1 + (yTile1 * World.WIDTH)] instanceof Wall ||
                World.tiles[xTile2 + (yTile2 * World.WIDTH)] instanceof Wall ||
                World.tiles[xTile3 + (yTile3 * World.WIDTH)] instanceof Wall ||
                World.tiles[xTile4 + (yTile4 * World.WIDTH)] instanceof Wall);
    }
}
