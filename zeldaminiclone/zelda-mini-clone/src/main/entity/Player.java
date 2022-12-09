package main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import main.constant.Sprite;
import main.enums.Direction;
import main.graphics.Spritesheet;
import main.map.World;

public class Player extends Rectangle {

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    public boolean shoot = false;

    public int speed = 4;

    private int currentAnimation = 0;
    private int currentFrame = 0;
    private int targetFrame = 15;

    private int bulletFrameNow = 0;

    private boolean isMoveToUp = false;
    private boolean isMoveToDown = false;
    private boolean isMoveToLeft = false;
    private boolean isMoveToRight = false;
    private Direction playerLastDirection = Direction.DOWN;

    private LinkedList<Bullet> bullets = new LinkedList<>();

    public Player(int x, int y) {
        super(x, y, Sprite.WIDTH, Sprite.HEIGHT);
    }

    public void tick() {
        this.isMoveToUp = false;
        this.isMoveToDown = false;
        this.isMoveToLeft = false;
        this.isMoveToRight = false;

        if (this.up && !World.isColiding(this.x, this.y - speed)) {
            this.y -= speed;
            this.isMoveToUp = true;
            this.playerLastDirection = Direction.UP;
        } else if (this.down && !World.isColiding(this.x, this.y + speed)) {
            this.y += speed;
            this.isMoveToDown = true;
            this.playerLastDirection = Direction.DOWN;
        }

        if (this.left && !World.isColiding(this.x - speed, this.y)) {
            this.x -= speed;
            this.isMoveToLeft = true;
            this.playerLastDirection = Direction.LEFT;
        } else if (this.right && !World.isColiding(this.x + speed, this.y)) {
            this.x += speed;
            this.isMoveToRight = true;
            this.playerLastDirection = Direction.RIGHT;
        }

        if (this.isMoveToDown || this.isMoveToUp || this.isMoveToLeft || this. isMoveToRight) {
            currentFrame++;
            if (currentFrame == targetFrame) {
                currentFrame = 0;
                currentAnimation++;
                if (currentAnimation == Spritesheet.playerFront.length) {
                    currentAnimation = 0;
                }
            }
        }

        if (this.shoot) {
            this.shoot = false;
            bullets.add(new Bullet(this.x, this.y, this.playerLastDirection));
        }

        for (Bullet bullet : this.bullets) {
            bullet.tick();
        }

        if (!this.bullets.isEmpty()) {
            this.bulletFrameNow++;
             if (this.bulletFrameNow == 60) {
                this.bulletFrameNow = 0;
                this.bullets.removeFirst();
            }
        }

    }

    public void render(Graphics graphics) {
        BufferedImage[] playerFront = Spritesheet.playerFront;
        BufferedImage[] playerBack = Spritesheet.playerBack;
        BufferedImage[] playerLeft = Spritesheet.playerLeft;
        BufferedImage[] playerRight = Spritesheet.playerRight;

        if (this.isMoveToUp || this.playerLastDirection == Direction.UP) {
            graphics.drawImage(playerBack[currentAnimation], x, y, Sprite.WIDTH, Sprite.HEIGHT, null);
        } else if (this.isMoveToDown || this.playerLastDirection == Direction.DOWN) {
            graphics.drawImage(playerFront[currentAnimation], x, y, Sprite.WIDTH, Sprite.HEIGHT, null);
        } else if (this.isMoveToLeft || this.playerLastDirection == Direction.LEFT) {
            graphics.drawImage(playerLeft[currentAnimation], x, y, Sprite.WIDTH, Sprite.HEIGHT, null);
        } else if (this.isMoveToRight || this.playerLastDirection == Direction.RIGHT) {
            graphics.drawImage(playerRight[currentAnimation], x, y, Sprite.WIDTH, Sprite.HEIGHT, null);
        }

        for (Bullet bullet : this.bullets) {
            bullet.render(graphics);
        }
    }
    
}
