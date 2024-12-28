package main.java;

import main.java.tetromino.*;

import java.awt.*;
import java.util.Random;

/**
 * Responsible for:
 * <li> Drawing UI
 * <li> Managing tetrominoes
 * <li> Handles game play actions (e.g. deleting lines, adding scores)
 */
public class PlayManager {

    final int WIDTH = 360; // block is 30px, so 12 fit horizontally
    final int HEIGHT = 600; // ... and 20 fit vertically

    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    Tetromino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;

    public final static int dropInterval = 60; // mino drops every 60 frames

    public PlayManager() {
        //Main play area frame
        left_x = (GamePanel.WIDTH - WIDTH) / 2; // (1280 - 360)/2 = 460 (i.e. divide the extra space evenly
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        // initialise mino start position
        MINO_START_X = left_x + (WIDTH / 2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        // initialise mino
        currentMino = pickRandomTetromino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);
    }


    public void update() {
        currentMino.update();
    }


    public void draw(Graphics2D graphics2D) {
        // Draw play area frame
        graphics2D.setColor(Color.white);
        graphics2D.setStroke(new BasicStroke(4f));
        // Note 4 subtracted from (x,y) co-ordinates is to account for the stroke width
        // We want the inner part of the frame border to be where collision happen
        graphics2D.drawRect(left_x - 4,  top_y - 4, WIDTH + 8, HEIGHT + 8);


        // Draw frame for upcoming tetrominoes
        int x = right_x + 100;
        int y = bottom_y - 200;
        graphics2D.drawRect(x, y, 200, 200);
        // Add text for waiting room
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString("NEXT", x + 60, y +60);


        // Draw the current mino
        if (currentMino != null) {
            currentMino.draw(graphics2D);
        }

        // Draw pause
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(graphics2D.getFont().deriveFont(50f));
        if (KeyHandler.pausePressed) {
            x = left_x + 70;
            y = top_y + 320;
            graphics2D.drawString("PAUSED", x, y);
        }
    }


    private Tetromino pickRandomTetromino() {
        Tetromino tetromino = null;
        int i = new Random().nextInt(7);

        switch(i) {
            case 0: tetromino = new MinoBar();
            break;
            case 1: tetromino = new MinoL1();
            break;
            case 2: tetromino = new MinoL2();
            break;
            case 3: tetromino = new MinoSquare();
            break;
            case 4: tetromino = new MinoT();
            break;
            case 5: tetromino = new MinoZ1();
            break;
            case 6: tetromino = new MinoZ2();
            break;
        }

        return tetromino;
    }
}