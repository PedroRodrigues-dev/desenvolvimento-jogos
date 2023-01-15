package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;

public class Player extends Rectangle {

    public boolean moveUp = false;
    public boolean moveDown = false;

    private int speed = 4;

    public Player(int x, int y) {
        super(x, y, 10, 40);
    }

    public void tick() {
        if (moveUp && !isColiding(super.y - this.speed)) {
            super.y -= speed;
        } else if (moveDown && !isColiding(super.y + this.speed)) {
            super.y += speed;
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
