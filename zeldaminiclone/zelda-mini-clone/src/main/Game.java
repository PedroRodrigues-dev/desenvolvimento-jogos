package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;

import main.constant.Frame;
import main.constant.StartPosition;
import main.entity.Player;
import main.entity.RedOctorok;
import main.graphics.Spritesheet;
import main.map.World;

public class Game extends Canvas implements Runnable, KeyListener {

    public static Player player;

    public LinkedList<RedOctorok> redOctoroks = new LinkedList<RedOctorok>();

    public World world;

    public Game() {
        this.addKeyListener(this);

        this.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT));

        new Spritesheet();

        Game.player = new Player(StartPosition.PLAYER[0], StartPosition.PLAYER[1]);

        this.redOctoroks.add(new RedOctorok(400, 200));
        this.redOctoroks.add(new RedOctorok(400, 200));

        this.world = new World();
    }

    public void tick() {
        player.tick();

        for (RedOctorok redOctorok : redOctoroks) {
            redOctorok.tick();
        }
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, Frame.WIDTH, Frame.HEIGHT);

        Game.player.render(graphics);

        for (RedOctorok redOctorok : redOctoroks) {
            redOctorok.render(graphics);
        }

        world.render(graphics);

        bufferStrategy.show();
    }

    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle(Frame.GAME_TITLE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();

            try {
                Thread.sleep(1000/60);
            } catch(InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        this.playerActions(event, true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        this.playerActions(event, false);
    }

    @Override
    public void keyTyped(KeyEvent event) {}
    
    private void playerActions(KeyEvent event, Boolean isPressed) {
        int keyCode = event.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            player.up = isPressed;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            player.down = isPressed;
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            player.left = isPressed;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.right = isPressed;
        }

        if (keyCode == KeyEvent.VK_Z) {
            player.shoot = isPressed;
        }
    }
}
