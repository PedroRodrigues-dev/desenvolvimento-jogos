package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Game;

public class Enemy extends Rectangle {

    private int speed = 4;

    private Random random = new Random();

    public Enemy(int x, int y) {
        super(x, y, 10, 40);
    }

    public void tick() {
        if (super.y + super.height / 2 >= Game.ball.y && !isColiding(super.y - this.speed)
                && this.random.nextInt(100) <= 50) {
            super.y -= this.speed;
        } else if (super.y + super.height / 2 <= Game.ball.y && !isColiding(super.y + this.speed)
                && this.random.nextInt(100) <= 50) {
            super.y += this.speed;
        }
    }

    public void render(Graphics graphics) {
        graphics.fillRect(super.x, super.y, super.width, super.height);
    }

    private boolean isColiding(int y) {
        if (y >= Game.HEIGHT - super.height) {
            return true;
        } else if (y <= 0) {
            return true;
        }

        return false;
    }
}
