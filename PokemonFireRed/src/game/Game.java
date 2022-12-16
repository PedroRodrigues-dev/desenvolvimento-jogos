package game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import game.aniamtions.Intro;

public class Game extends Canvas implements Runnable {

	private JFrame frame;

	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	private final int SCALE = 4;

	private Thread thread;
	private boolean isRunning = false;

	public static int framesPerSecond = 60;

	private BufferedImage bufferedImage;

	private Intro intro;

	private Game() {
		super.setPreferredSize(new Dimension(Game.WIDTH * this.SCALE, Game.HEIGHT * this.SCALE));

		this.createFrame();

		this.bufferedImage = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.intro = new Intro();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	private void createFrame() {
		this.frame = new JFrame("Pokemon Fire Red");

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

	private void tick() {
		this.intro.tick();
	}

	private void render() {
		BufferStrategy bufferStrategy = super.getBufferStrategy();

		if (bufferStrategy == null) {
			super.createBufferStrategy(3);
			return;
		}

		Graphics graphics = this.bufferedImage.getGraphics();

		super.paint(graphics);

		Graphics2D graphics2D = (Graphics2D) graphics;

		// intro.render(graphics2D);

		graphics.dispose();

		graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(bufferedImage, 0, 0, Game.WIDTH * this.SCALE, Game.HEIGHT * this.SCALE, null);

		bufferStrategy.show();

	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = Game.framesPerSecond;
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

}
