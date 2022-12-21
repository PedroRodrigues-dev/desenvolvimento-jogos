package com.pedro.entities;

import java.awt.image.BufferedImage;

import com.pedro.main.Game;
import com.pedro.world.World;

public class Enemy extends Entity {

    private int speed = 1;

    public Enemy(double x, double y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    @Override
    public void tick() {
        if (super.y > Game.player.getY() && World.isFree(super.getX(), (int) (super.y - this.speed))) {
            super.y -= speed;
        } else if (super.y < Game.player.getY() && World.isFree(super.getX(), (int) (super.y + this.speed))) {
            super.y += speed;
        }

        if (super.x > Game.player.getX() && World.isFree((int) (super.x - this.speed), super.getY())) {
            super.x -= speed;
        } else if (super.x < Game.player.getX() && World.isFree((int) (super.x + this.speed), super.getY())) {
            super.x += speed;
        }
    }

}
