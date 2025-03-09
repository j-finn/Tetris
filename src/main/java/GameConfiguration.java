package main.java;

import main.java.tetromino.Block;
import main.java.tetromino.Tetromino;

/**
 * Configuration that is required globally.
 */
public class GameConfiguration {
  public static final int WINDOW_WIDTH = 1280;
  public static final int WINDOW_HEIGHT = 720;
  public static final int FPS = 60;
  public static final int PLAY_AREA_WIDTH = 360; // block is 30px, so 12 fit horizontally
  public static final int PLAY_AREA_HEIGHT = 600; // ... and 20 fit vertically
  public static final int BLOCKS_PER_ROW =  PLAY_AREA_WIDTH / Block.SIZE;
  public static final int BLOCKS_PER_COLUMN =  PLAY_AREA_HEIGHT / Block.SIZE;
}