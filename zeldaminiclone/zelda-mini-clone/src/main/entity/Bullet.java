package main.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.constant.Sprite;
import main.enums.Direction;

public class Bullet extends Rectangle {

    private Direction playerLastDirection;

    private int speed = 8;

    public Bullet(int x, int y, Direction playerLastDirection) {
        super(x + Sprite.WIDTH / 2, y + Sprite.HEIGHT / 2, 10, 10);
        this.playerLastDirection = playerLastDirection;
    }

    public void tick() {
        switch (this.playerLastDirection) {
            case UP:
                this.y -= this.speed;
                break;
            case DOWN:
                this.y += this.speed;
                break;
            case LEFT:
                this.x -= this.speed;
                break;
            case RIGHT:
                this.x += this.speed;
                break;
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.fillOval(this.x, this.y, this.width, this.height);
    }
}
