package main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import main.constant.Sprite;
import main.enums.Direction;
import main.map.World;

public class Enemy extends Rectangle {

    public int speed = 2;

    private int currentAnimation = 0;
    private int currentFrame = 0;
    private int targetFrame = 15;

    private boolean isMoveToUp = false;
    private boolean isMoveToDown = false;
    private boolean isMoveToLeft = false;
    private boolean isMoveToRight = false;

    private Direction enemyLastDirection = Direction.DOWN;

    private BufferedImage[] enemyBackAnimation;
    private BufferedImage[] enemyFrontAnimation;
    private BufferedImage[] enemyLeftAnimation;
    private BufferedImage[] enemyRightAnimation;

    public Enemy(int x, int y, BufferedImage[] enemyBackAnimation, BufferedImage[] enemyFrontAnimation, 
                                BufferedImage[] enemyLeftAnimation,
                                BufferedImage[] enemyRightAnimation) {
        super(x, y, Sprite.WIDTH, Sprite.HEIGHT);
        this.enemyBackAnimation = enemyBackAnimation;
        this.enemyFrontAnimation = enemyFrontAnimation;
        this.enemyLeftAnimation = enemyLeftAnimation;
        this.enemyRightAnimation = enemyRightAnimation;
    }

    public void tick() {
        this.findPlayer();

        this.currentFrame++;
        if (this.currentFrame == this.targetFrame) {
            this.currentFrame = 0;
            this.currentAnimation++;
            if (this.currentAnimation == this.enemyBackAnimation.length) {
                this.currentAnimation = 0;
            }
        }
    }

    private void findPlayer() {
        Player player = Game.player;

        this.isMoveToUp = false;
        this.isMoveToDown = false;
        this.isMoveToLeft = false;
        this.isMoveToRight = false;

        if (this.y > player.y && !World.isColiding(this.x, this.y - speed)) {
            if (new Random().nextInt(100) < 50) {
                this.y -= this.speed;
                this.isMoveToUp = true;
                this.enemyLastDirection = Direction.UP;
            }
        } else if (this.y < player.y && !World.isColiding(this.x, this.y + speed)) {
            if (new Random().nextInt(100) < 50) {
                this.y += this.speed;
                this.isMoveToDown = true;
                this.enemyLastDirection = Direction.DOWN;
            }
        }

        if (this.x > player.x && !World.isColiding(this.x - speed, this.y)) {
            if (new Random().nextInt(100) < 50) {
                this.x -= this.speed;
                this.isMoveToLeft = true;
                this.enemyLastDirection = Direction.LEFT;
            }
        } else if (this.x < player.x && !World.isColiding(this.x + speed, this.y)) {
            if (new Random().nextInt(100) < 50) {
                this.x += this.speed;
                this.isMoveToRight = true;
                this.enemyLastDirection = Direction.RIGHT;
            }
        } 

        
    }

    public void render(Graphics graphics) {
        if (this.isMoveToUp || this.enemyLastDirection == Direction.UP) {
            graphics.drawImage(this.enemyBackAnimation[this.currentAnimation], this.x, this.y, Sprite.WIDTH, Sprite.HEIGHT, null);
        } else if (this.isMoveToDown || this.enemyLastDirection == Direction.DOWN) {
            graphics.drawImage(this.enemyFrontAnimation[this.currentAnimation], this.x, this.y, Sprite.WIDTH, Sprite.HEIGHT, null);
        } else if (this.isMoveToLeft || this.enemyLastDirection == Direction.LEFT) {
            graphics.drawImage(this.enemyLeftAnimation[this.currentAnimation], this.x, this.y, Sprite.WIDTH, Sprite.HEIGHT, null);
        } else if (this.isMoveToRight || this.enemyLastDirection == Direction.RIGHT) {
            graphics.drawImage(this.enemyRightAnimation[this.currentAnimation], this.x, this.y, Sprite.WIDTH, Sprite.HEIGHT, null);
        }
    }
    
}
