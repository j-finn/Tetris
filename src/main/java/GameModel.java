package main.java;

import main.java.tetromino.Block;
import main.java.tetromino.Tetromino;

import java.util.ArrayList;

import static main.java.GameConfiguration.WINDOW_WIDTH;

/**
 * Stores data about the game:
 * <li> Game frame (bottom, left, right, top edges)
 * <li> Inactive tetromino positions
 * <li> Active tetromino positions
 * <li> Player score
 * <li> Current level
 * <li> Game state (paused or not)
 *
 * Updated by the GameController. Utilised by the render service.
 */
public class GameModel {

  private ArrayList<Block> staticBlocks;

  private int LEFT_BOUNDARY;
  private int RIGHT_BOUNDARY;
  private int TOP_BOUNDARY;
  private int BOTTOM_BOUNDARY;

  final int PLAY_AREA_WIDTH = 360; // block is 30px, so 12 fit horizontally
  final int PLAY_AREA_HEIGHT = 600; // ... and 20 fit vertically

  private Tetromino currentMino;
  private int deactivateCounter;
  private Tetromino nextMino;

  private final int MINO_START_X;
  private final int MINO_START_Y;
  private final int NEXT_MINO_START_X;
  private final int NEXT_MINO_START_Y;

  private boolean gamePaused;
  private boolean gameOver;

  // Score
  private int level = 1;
  private int score;
  private int lines;


  public GameModel() {
    this.staticBlocks = new ArrayList<>();

    //Main play area frame
    LEFT_BOUNDARY = ((WINDOW_WIDTH - PLAY_AREA_WIDTH) / 2); // (1280 - 360)/2 = 460 (i.e. divide the extra space evenly
    RIGHT_BOUNDARY = LEFT_BOUNDARY + PLAY_AREA_WIDTH;
    TOP_BOUNDARY = 50;
    BOTTOM_BOUNDARY = TOP_BOUNDARY + PLAY_AREA_HEIGHT;

    // initialise mino start position
    MINO_START_X = LEFT_BOUNDARY + (PLAY_AREA_WIDTH / 2) - Block.SIZE;
    MINO_START_Y = TOP_BOUNDARY + Block.SIZE;

    // initialise next mino start position
    NEXT_MINO_START_X = RIGHT_BOUNDARY + 175;
    NEXT_MINO_START_Y = TOP_BOUNDARY + 500;

    this.gamePaused = false;
    this.deactivateCounter = 0;
  }

  public ArrayList<Block> getStaticBlocks() {
    return staticBlocks;
  }

  public int getMinoStartX() {
    return MINO_START_X;
  }

  public int getMinoStartY() {
    return MINO_START_Y;
  }

  public int getNextMinoStartX() {
    return NEXT_MINO_START_X;
  }

  public int getNextMinoStartY() {
    return NEXT_MINO_START_Y;
  }

  public void setCurrentMino(Tetromino currentMino) {
    this.currentMino = currentMino;
  }

  public Tetromino getCurrentMino() {
    return currentMino;
  }

  public Tetromino getNextMino() {
    return nextMino;
  }

  public void setNextMino(Tetromino nextMino) {
    this.nextMino = nextMino;
  }

  public int getBottomBoundary() {
    return BOTTOM_BOUNDARY;
  }

  public int getRightBoundary() {
    return RIGHT_BOUNDARY;
  }

  public int getLeftBoundary() {
    return LEFT_BOUNDARY;
  }

  public int getTopBoundary() {
    return TOP_BOUNDARY;
  }

  public boolean isGamePaused() {
    return gamePaused;
  }

  public void togglePause() {
    this.gamePaused = !this.gamePaused;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public int getDeactivateCounter() {
    return deactivateCounter;
  }

  public void setDeactivateCounter(int deactivateCounter) {
    this.deactivateCounter = deactivateCounter;
  }

  public int getLevel() {
    return level;
  }

  public int getScore() {
    return score;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setLines(int lines) {
    this.lines = lines;
  }

  public int getLines() {
    return lines;
  }
}