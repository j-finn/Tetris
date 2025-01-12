package main.java;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static int FPS = 60;

    Thread gameThread;
//    Thread blockDropThread;
    main.java.PlayManager playManager;

    public static main.java.Sound music = new main.java.Sound();
    public static main.java.Sound soundEffect = new main.java.Sound();

    public static boolean musicMuted = false;

    public GamePanel() {

        //Panel settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);

        //Implement key listener
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        playManager = new main.java.PlayManager();
    }


    public void launchGame() {
        startThreads();

        music.play(2, true);
        music.loop();
    }



    private void startThreads() {

        // TODO: Separate thread for dropping the block?
//        if (blockDropThread == null) {
//            blockDropThread = new Thread(this);
//            blockDropThread.start();
//        }

        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }


    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            // 60 F/S
            // 15 x 10^-9 * 60 / 1 * 10^9

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;
            }
        }

    }


    private void update() {
        if (!main.java.KeyHandler.pausePressed && !playManager.gameOver) {
            playManager.update();
        }

        if (KeyHandler.mutePressed) {
            toggleMusic();
        }

        // TODO: Not implemented yet.
//        if (playManager.gameOver && KeyHandler.restartGamePressed) {
//            launchGame();
//        }
    }


    public void toggleMusic() {
        if (musicMuted) {
            music.play(2, true);
            music.loop();

        } else {
            music.stop();
        }

        musicMuted = !musicMuted;

        KeyHandler.mutePressed = false;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        playManager.draw((Graphics2D) graphics);
    }
}