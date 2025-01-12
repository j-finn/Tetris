package main.java;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static int FPS = 60;

    Thread gameThread;
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
        gameThread = new Thread(this);
        gameThread.start();

        music.play(2, true);
        music.loop();
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

            if (musicMuted) {
                music.play(2, true);
                music.loop();

            } else {
                music.stop();
            }

            musicMuted = !musicMuted;

            KeyHandler.mutePressed = false;
        }
    }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        playManager.draw((Graphics2D) graphics);
    }
}