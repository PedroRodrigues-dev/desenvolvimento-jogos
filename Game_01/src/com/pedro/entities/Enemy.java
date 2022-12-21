package com.pedro.entities;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import com.pedro.main.Game;
import com.pedro.world.World;

public class Enemy extends Entity {

    private int speed = 1;

    private int maskX = 8;
    private int maskY = 8;
    private int maskWidth = 8;
    private int maskHeight = 10;

    public Enemy(double x, double y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    @Override
    public void tick() {
        if (super.y > Game.player.getY() && World.isFree(super.getX(), (int) (super.y - this.speed))
                && !this.isColliding(super.getX(), (int) (super.y - this.speed))) {
            super.y -= speed;
        } else if (super.y < Game.player.getY() && World.isFree(super.getX(), (int) (super.y + this.speed))
                && !this.isColliding(super.getX(), (int) (super.y - this.speed))) {
            super.y += speed;
        }

        if (super.x > Game.player.getX() && World.isFree((int) (super.x - this.speed), super.getY())
                && !this.isColliding((int) (super.x - this.speed), super.getY())) {
            super.x -= speed;
        } else if (super.x < Game.player.getX() && World.isFree((int) (super.x + this.speed), super.getY())
                && !this.isColliding((int) (super.x + this.speed), super.getY())) {
            super.x += speed;
        }
    }

    public boolean isColliding(int x, int y) {
        Rectangle currentEnemyRectangle = new Rectangle(x + this.maskX, y + this.maskY, this.maskWidth,
                this.maskHeight);

        for (Enemy enemy : Game.enemies) {
            if (enemy != this) {
                Rectangle targetEnemyRectangle = new Rectangle(enemy.getX() + this.maskX, enemy.getY() + this.maskY,
                        this.maskWidth, this.maskHeight);

                if (currentEnemyRectangle.intersects(targetEnemyRectangle)) {
                    return true;
                }
            }
        }

        return false;
    }

}
