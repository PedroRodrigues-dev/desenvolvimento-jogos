package game.aniamtions;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import game.Game;
import game.spritesheet.Spritesheet;

public class Intro {

    private Spritesheet spritesheet;

    private float alpha = 1f;

    private int framesToFinalizeAnimation = 120;
    private int framesPassed = 0;

    private int animationNumber = 0;

    public Intro() {
        this.spritesheet = new Spritesheet("../../res/spritesheet/Intro.png");
    }

    public void tick() {
        switch (this.animationNumber) {
            case 0: {
                this.framesPassed++;
                if (this.framesPassed >= this.framesToFinalizeAnimation && this.alpha > 0.01) {
                    if (this.alpha <= 0.05) {
                        this.alpha = 0.01f;
                    } else {
                        this.alpha -= 0.05;
                    }
                }

                if (this.alpha <= 0.01) {
                    this.animationNumber++;
                }

                break;
            }
            case 1: {
                if (this.alpha <= 0.9) {
                    this.alpha += 0.1;
                }

                break;
            }
        }
    }

    public void render(Graphics2D graphics2D) {
        switch (this.animationNumber) {
            case 0: {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
                graphics2D.drawImage(this.spritesheet.getSprite(5, 181, Game.WIDTH, Game.HEIGHT), 0, 0, null);

                break;
            }
            case 1: {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha));
                graphics2D.drawImage(this.spritesheet.getSprite(248, 181, Game.WIDTH, Game.HEIGHT), 0, 0, null);

                break;
            }
        }
    }
}
