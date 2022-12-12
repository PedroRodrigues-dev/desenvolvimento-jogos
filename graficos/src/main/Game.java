package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	public static JFrame frame;

	private final int WIDTH = 240;
	private final int HEIGHT = 160;
	private final int SCALE = 3;

	private Thread thread;
	private boolean isRunning = false;

	private BufferedImage bufferedImage;

	private Spritesheet spritesheet;

	private BufferedImage[] player;
	private int playerFrames = 0;
	private int playerMaxFrames = 10;
	private int playerCurrentAnimation = 0;
	private int playerMaxAnimation = 4;

	public Game() {
		super.setPreferredSize(new Dimension(this.WIDTH * this.SCALE, this.HEIGHT * this.SCALE));

		this.createFrame();

		this.bufferedImage = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.spritesheet = new Spritesheet("../res/spritesheet.png");

		this.player = new BufferedImage[4];
		this.player[0] = this.spritesheet.getSprite(0, 0, 16, 16);
		this.player[1] = this.spritesheet.getSprite(16, 0, 16, 16);
		this.player[2] = this.player[0];
		this.player[3] = this.spritesheet.getSprite(32, 0, 16, 16);
	}

	private void createFrame() {
		Game.frame = new JFrame("Game");

		Game.frame.add(this);
		Game.frame.setResizable(false);
		Game.frame.pack();

		Game.frame.setLocationRelativeTo(null);
		Game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game.frame.setVisible(true);
	}

	public synchronized void start() {
		this.thread = new Thread(this);
		this.isRunning = true;
		this.thread.start();
	}

	public synchronized void stop() {
		this.isRunning = false;

		try {
			thread.join();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void tick() {
		this.playerFrames++;
		if (this.playerFrames > this.playerMaxFrames) {
			this.playerFrames = 0;
			this.playerCurrentAnimation++;
			if (this.playerCurrentAnimation >= this.playerMaxAnimation) {
				this.playerCurrentAnimation = 0;
			}
		}
	}

	public void render() {
		BufferStrategy bufferStrategy = super.getBufferStrategy();

		if (bufferStrategy == null) {
			super.createBufferStrategy(3);
			return;
		}

		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(new Color(0, 0, 255));
		graphics.fillRect(0, 0, this.WIDTH, this.HEIGHT);

		Graphics2D graphics2D = (Graphics2D) graphics;

		graphics2D.drawImage(this.player[this.playerCurrentAnimation], 90, 90, null);

		graphics.dispose();

		graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(bufferedImage, 0, 0, this.WIDTH * this.SCALE, this.HEIGHT * this.SCALE, null);

		bufferStrategy.show();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double nanoSeconds = 1000000000 / amountOfTicks;
		double delta = 0;

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSeconds;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();
				delta--;
			}
		}

		this.stop();
	}

}
