package main;

import java.util.ArrayList;

public class Game implements Runnable {

    private boolean isRunning;
    private Thread thread;

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public Game() {
        entities.add(new Entity());
        entities.add(new Entity());
        entities.add(new Entity());
        entities.add(new Entity());

        for (Entity entity : entities) {
            System.out.println("HEY");
        }
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
    
    public synchronized void start() {
        this.isRunning = true;

        thread = new Thread(this);
        thread.start();
    }

    public void tick() {
        // System.out.println("TICK");
    }

    public void render() {
        // System.out.println("RENDER");
    }

    @Override
    public void run() {
        while (isRunning) {
            this.tick();
            this.render();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
