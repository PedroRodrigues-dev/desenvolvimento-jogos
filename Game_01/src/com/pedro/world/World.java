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
                    this.tiles[x + (y * World.WIDTH)] = new Floor(x * 16, y * 16, Tile.FLOOR_SPRITE);
                    switch (mapPixels[x + (y * World.WIDTH)]) {
                        case 0xFFFFFFFF:
                            this.tiles[x + (y * World.WIDTH)] = new Wall(x * 16, y * 16, Tile.WALL_SPRITE);
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
                            Game.entities.add(new Enemy(x * 16, y * 16, 16, 16, Entity.ENEMY_SPRITE));
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

        int xFinal = xStart + (Game.WIDTH >> 4) - 2;
        int yFinal = yStart + (Game.HEIGHT >> 4) - 2;

        for (int x = xStart; x <= xFinal; x++) {
            for (int y = yStart; y <= yFinal; y++) {
                Tile tile = this.tiles[x + (y * World.WIDTH)];
                tile.render(graphics);
            }
        }
    }
}
