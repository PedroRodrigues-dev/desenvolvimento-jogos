package com.pedro.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.pedro.entities.Entity;
import com.pedro.entities.Player;
import com.pedro.graphics.Spritesheet;
import com.pedro.world.World;

public class Game extends Canvas implements Runnable, KeyListener {

	public static JFrame frame;

	private final int WIDTH = 240;
	private final int HEIGHT = 160;
	private final int SCALE = 3;

	private Thread thread;
	private boolean isRunning = false;

	private BufferedImage bufferedImage;
	public static Spritesheet spritesheet;

	public static World world;

	public List<Entity> entities;
	private Player player;

	public Game() {
		super.setPreferredSize(new Dimension(this.WIDTH * this.SCALE, this.HEIGHT * this.SCALE));

		super.addKeyListener(this);

		this.createFrame();

		this.bufferedImage = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Game.spritesheet = new Spritesheet("/spritesheet.png");

		Game.world = new World("/map.png");

		this.entities = new ArrayList<Entity>();

		this.player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
		this.entities.add(this.player);
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void tick() {
		for (Entity entity : this.entities) {
			if (entity instanceof Player) {

			}
			entity.tick();
		}
	}

	public void render() {
		BufferStrategy bufferStrategy = super.getBufferStrategy();

		if (bufferStrategy == null) {
			super.createBufferStrategy(3);
			return;
		}

		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(new Color(0, 0, 0));
		graphics.fillRect(0, 0, this.WIDTH, this.HEIGHT);

		Game.world.render(graphics);

		for (Entity entity : this.entities) {
			entity.render(graphics);
		}

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

	@Override
	public void keyTyped(KeyEvent keyEvent) {
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				this.player.up = true;
				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				this.player.down = true;
				break;
		}

		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				this.player.left = true;
				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				this.player.right = true;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				this.player.up = false;
				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				this.player.down = false;
				break;
		}

		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				this.player.left = false;
				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				this.player.right = false;
				break;
		}
	}

}
