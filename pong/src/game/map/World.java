package game.map;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;

public class World extends Rectangle {

    public World() {
        super(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        graphics.drawRect(0, 0, Game.WIDTH - 1, Game.HEIGHT - 1);
        graphics.drawLine(Game.WIDTH / 2, 0, Game.WIDTH / 2, Game.HEIGHT);

        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        graphics.drawString(Integer.toString(Game.enemyPoints), Game.WIDTH / 2 + 25, 25);
        graphics.drawString(Integer.toString(Game.playerPoints), Game.WIDTH / 2 - 40, 25);
        graphics.dispose();
    }
}
