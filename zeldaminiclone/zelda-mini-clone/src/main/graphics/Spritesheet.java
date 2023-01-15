package main.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
    public static BufferedImage spritesheet;

    public static BufferedImage[] playerFront;
    public static BufferedImage[] playerBack;
    public static BufferedImage[] playerLeft;
    public static BufferedImage[] playerRight;

    public static BufferedImage[] redOctorokFront;
    public static BufferedImage[] redOctorokBack;
    public static BufferedImage[] redOctorokLeft;
    public static BufferedImage[] redOctorokRight;

    public static BufferedImage tileWall;

    public Spritesheet() {
        try {
            Spritesheet.spritesheet = ImageIO.read(getClass().getResource("../../resource/spritesheet/spritesheet.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        this.setPlayerSprites();

        this.setRedOctorokSprites();

        Spritesheet.tileWall = Spritesheet.getSprite(300, 230, 16, 16);
    }

    private void setPlayerSprites() {
        Spritesheet.playerFront = new BufferedImage[2];

        Spritesheet.playerFront[0] = Spritesheet.getSprite(2, 9, 16, 16);
        Spritesheet.playerFront[1] = Spritesheet.getSprite(18, 9, 16, 16);

        Spritesheet.playerBack = new BufferedImage[2];

        Spritesheet.playerBack[0] = Spritesheet.getSprite(70, 9, 16, 16);
        Spritesheet.playerBack[1] = Spritesheet.getSprite(87, 9, 16, 16);

        Spritesheet.playerLeft = new BufferedImage[2];

        Spritesheet.playerLeft[0] = Spritesheet.getSprite(318, 248, 16, 16);
        Spritesheet.playerLeft[1] = Spritesheet.getSprite(301, 248, 16, 16);

        Spritesheet.playerRight = new BufferedImage[2];

        Spritesheet.playerRight[0] = Spritesheet.getSprite(35, 9, 16, 16);
        Spritesheet.playerRight[1] = Spritesheet.getSprite(52, 9, 16, 16);
    }

    private void setRedOctorokSprites() {
        Spritesheet.redOctorokFront = new BufferedImage[2];

        Spritesheet.redOctorokFront[0] = Spritesheet.getSprite(252, 278, 16, 16);
        Spritesheet.redOctorokFront[1] = Spritesheet.getSprite(257, 211, 16, 16);

        Spritesheet.redOctorokBack = new BufferedImage[2];

        Spritesheet.redOctorokBack[0] = Spritesheet.getSprite(311, 278, 16, 16);
        Spritesheet.redOctorokBack[1] = Spritesheet.getSprite(317, 211, 16, 16);

        Spritesheet.redOctorokLeft = new BufferedImage[2];

        Spritesheet.redOctorokLeft[0] = Spritesheet.getSprite(282, 278, 16, 16);
        Spritesheet.redOctorokLeft[1] = Spritesheet.getSprite(286, 211, 16, 16);

        Spritesheet.redOctorokRight = new BufferedImage[2];

        Spritesheet.redOctorokRight[0] = Spritesheet.getSprite(342, 278, 16, 16);
        Spritesheet.redOctorokRight[1] = Spritesheet.getSprite(348, 211, 16, 16);
    }

    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return Spritesheet.spritesheet.getSubimage(x, y, width, height);
    }
}
