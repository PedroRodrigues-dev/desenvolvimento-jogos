package game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Game;

public class Ball extends Rectangle {

    private Random random = new Random();

    private int speedX = 4;
    private int speedY = this.random.nextInt(this.speedX) + 1;

    private int alterDirectionX = -1;
    private int alterDirectionY = this.random.nextBoolean() ? 1 : -1;

    public Ball(int x, int y) {
        super(x, y, 5, 5);
    }

    public void tick() {
        super.x += this.speedX * this.alterDirectionX;
        super.y += this.speedY * this.alterDirectionY;

        if (isColiding(Game.player.x, Game.player.y)) {
            this.alterDirectionX = 1;

            this.speedY = this.random.nextInt(this.speedX) + 1;

            this.alterDirectionY = this.random.nextBoolean() ? 1 : -1;
        }

        if (isColiding(Game.enemy.x, Game.enemy.y)) {
            this.alterDirectionX = -1;

            this.speedY = this.random.nextInt(this.speedX) + 1;

            this.alterDirectionY = this.random.nextBoolean() ? 1 : -1;
        }

        if (super.x <= 0) {
            Game.enemyPoints += 1;

            this.resetBall();

            this.alterDirectionX = 1;
        } else if (super.x >= Game.WIDTH) {
            Game.playerPoints += 1;

            this.resetBall();

            this.alterDirectionX = -1;
        }

        if (super.y <= 0) {
            this.alterDirectionY = 1;
        } else if (super.y >= Game.HEIGHT) {
            this.alterDirectionY = -1;
        }
    }

    public void render(Graphics graphics) {
        graphics.fillRect(super.x, super.y, super.width, super.height);
    }

    public boolean isColiding(int x, int y) {
        if (this.intersects(new Rectangle(x, y, 10, 40))) {
            return true;
        }

        return false;
    }

    private void resetBall() {
        super.x = Game.ballStartPosition[0];
        super.y = Game.ballStartPosition[1];

        this.speedY = this.random.nextInt(this.speedX) + 1;

        this.alterDirectionY = this.random.nextBoolean() ? 1 : -1;
    }
}
