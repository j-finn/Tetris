package main.java;

import main.java.tetromino.*;

import java.awt.*;
import java.util.ArrayList;
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
    Tetromino nextMino;
    final int NEXT_MINO_START_X;
    final int NEXT_MINO_START_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    // Others
    public static int dropInterval = 60; // mino drops every 60 frames
    public boolean gameOver = false;

    // Score
    int level = 1;
    int score;
    int lines;

    public PlayManager() {
        //Main play area frame
        left_x = (GamePanel.WIDTH - WIDTH) / 2; // (1280 - 360)/2 = 460 (i.e. divide the extra space evenly
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        // initialise mino start position
        MINO_START_X = left_x + (WIDTH / 2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        // initialise next mino start position
        NEXT_MINO_START_X = right_x + 175;
        NEXT_MINO_START_Y = top_y + 500;

        // initialise mino
        currentMino = pickRandomTetromino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);

        // initialise next mino
        nextMino = pickRandomTetromino();
        nextMino.setXY(NEXT_MINO_START_X, NEXT_MINO_START_Y);
    }


    public void update() {

        if (!currentMino.isBlockActive()) {
            staticBlocks.add(currentMino.b[0]);
            staticBlocks.add(currentMino.b[1]);
            staticBlocks.add(currentMino.b[2]);
            staticBlocks.add(currentMino.b[3]);

            if (currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y) {
                // means that block has collided immediately
                gameOver = true;
                GamePanel.music.stop();
                GamePanel.soundEffect.play(1, true);
            }

            currentMino.isBlockDeactivating = false;

            // Replace current mino with next mino
            currentMino = nextMino;
            currentMino.setXY(MINO_START_X, MINO_START_Y);
            nextMino = pickRandomTetromino();
            nextMino.setXY(NEXT_MINO_START_X, NEXT_MINO_START_Y);

            checkDelete();

        } else {
            currentMino.update();
        }
    }


    private void checkDelete() {

        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;

        while (x < right_x && y < bottom_y) {

            for (int i = 0; i < staticBlocks.size(); i++) {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
                    blockCount++;
                }
            }

            x += Block.SIZE;

            if (x == right_x) {
                if (blockCount == 12) {

                    for (int i = (staticBlocks.size() - 1); i > -1; i--) {
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }

                    lineCount++;
                    lines++;

                    // Drop speed
                    if (lines % 10 == 0 && dropInterval > 1) {
                        level++;

                        if (dropInterval > 10) {
                            dropInterval -= 10;
                        } else {
                            dropInterval -= 1;
                        }
                    }

                    // line has been deleted -> move down all other blocks
                    for (int i = 0; i < staticBlocks.size(); i++) {
                        if (staticBlocks.get(i).y < y) {
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }

                    // play deletion sound effect
                    GamePanel.soundEffect.play(0, true);
                }

                blockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }
        }

        // Add score
        if (lineCount > 0) {
            int singleLineScore = 10 * level;
            score += singleLineScore * lineCount;
        }
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

        // Draw scoreboard
        graphics2D.drawRect(x, top_y, 250, 300);
        x += 40;
        graphics2D.drawString("LEVEL: " + level, x, top_y + 90);
        graphics2D.drawString("LINES: " + lines, x, top_y + 160);
        graphics2D.drawString("SCORE: " + score, x, top_y + 230);

        // Draw the current mino
        if (currentMino != null) {
            currentMino.draw(graphics2D);
        }

        // Draw the next mino
        if (nextMino != null) {
            nextMino.draw(graphics2D);
        }

        for (int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(graphics2D);
        }

        // Draw pause
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(graphics2D.getFont().deriveFont(50f));

        if (gameOver) {
            x = left_x + 25;
            y = top_y + 320;
            graphics2D.drawString("GAME OVER", x, y);
        }

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