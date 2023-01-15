package game.menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;

public class TitleScreen extends Rectangle {

    public TitleScreen() {
        super(0, 0, Game.WIDTH, Game.HEIGHT);
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        graphics.drawString("PONG", Game.WIDTH / 2 - 40, 30);

        graphics.setFont(new Font("Arial", Font.BOLD, 10));

        if (Game.playerPoints > Game.enemyPoints) {
            graphics.drawString("VOCE VENCEU!", Game.WIDTH / 2 - 90, 70);
        } else if (Game.playerPoints < Game.enemyPoints) {
            graphics.drawString("ADVERSARIO VENCEU!", Game.WIDTH / 2 - 90, 70);
        }

        graphics.drawString("PRECIONE ENTER PARA COMECAR", Game.WIDTH / 2 - 90, 100);

        graphics.dispose();
    }
}
