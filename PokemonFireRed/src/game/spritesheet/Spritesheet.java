package game.spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

    private BufferedImage spritesheet;

    public Spritesheet(String path) {
        try {
            this.spritesheet = ImageIO.read(getClass().getResource(path));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return this.spritesheet.getSubimage(x, y, width, height);
    }

}