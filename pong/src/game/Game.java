package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import game.entity.Ball;
import game.entity.Enemy;
import game.entity.Player;
import game.map.World;
import game.menu.TitleScreen;

public class Game extends Canvas implements Runnable, KeyListener {

    private JFrame frame;

    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    private final int SCALE = 3;

    private Thread thread;
    private boolean isRunning = false;

    private BufferedImage bufferedImage;

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    private World world;
    private TitleScreen titleScreen;

    public static int[] playerStartPosition = { 3, 80 };
    public static int[] enemyStartPosition = { Game.WIDTH - 13, 80 };
    public static int[] ballStartPosition = { Game.WIDTH / 2 - 2, Game.HEIGHT / 2 + 2 };

    public static int playerPoints = 0;
    public static int enemyPoints = 0;

    private boolean isTitleScreen = true;

    private Game() {
        super.setPreferredSize(new Dimension(Game.WIDTH * this.SCALE, Game.HEIGHT * this.SCALE));

        this.createFrame();

        this.addKeyListener(this);

        this.bufferedImage = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

        Game.player = new Player(Game.playerStartPosition[0], Game.playerStartPosition[1]);
        Game.enemy = new Enemy(Game.enemyStartPosition[0], Game.enemyStartPosition[1]);
        Game.ball = new Ball(Game.ballStartPosition[0], Game.ballStartPosition[1]);

        this.world = new World();
        this.titleScreen = new TitleScreen();
    }

    private void createFrame() {
        this.frame = new JFrame("Pong");

        this.frame.add(this);
        this.frame.setResizable(false);
        this.frame.pack();

        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    private synchronized void start() {
        this.thread = new Thread(this);
        this.isRunning = true;
        thread.start();
    }

    private synchronized void stop() {
        this.isRunning = false;

        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }

    private void tick() {
        if (this.isTitleScreen) {
            this.titleScreen.tick();
        } else {
            this.world.tick();

            Game.player.tick();
            Game.enemy.tick();
            Game.ball.tick();
        }

        if (Game.playerPoints == 9 || Game.enemyPoints == 9) {
            this.isTitleScreen = true;
        }
    }

    private void render() {
        BufferStrategy bufferStrategy = super.getBufferStrategy();

        if (bufferStrategy == null) {
            super.createBufferStrategy(3);
            return;
        }

        Graphics graphics = this.bufferedImage.getGraphics();

        super.paint(graphics);

        graphics.setColor(Color.WHITE);

        if (this.isTitleScreen) {
            this.titleScreen.render(graphics);
        }

        Game.player.render(graphics);
        Game.enemy.render(graphics);
        Game.ball.render(graphics);

        this.world.render(graphics);

        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(bufferedImage, 0, 0, Game.WIDTH * this.SCALE, Game.HEIGHT * this.SCALE, null);

        bufferStrategy.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double delta = 0;

        while (isRunning) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / nanoSeconds;
            lastTime = currentTime;

            if (delta >= 1) {
                this.tick();
                this.render();
                delta--;
            }
        }

        this.stop();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        this.movePlayer(keyEvent, true);

        if (this.isTitleScreen && keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            Game.playerPoints = 0;
            Game.enemyPoints = 0;

            Game.player.y = Game.playerStartPosition[1];
            Game.enemy.y = Game.enemyStartPosition[1];

            this.isTitleScreen = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        this.movePlayer(keyEvent, false);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    private void movePlayer(KeyEvent keyEvent, boolean isPressed) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            Game.player.moveUp = isPressed;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            Game.player.moveDown = isPressed;
        }
    }
}
