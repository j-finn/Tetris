package main.java;

import main.java.tetromino.Block;
import main.java.tetromino.Tetromino;

import java.util.ArrayList;

import static main.java.GameConfiguration.*;

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

  //Main play area frame
  public static int LEFT_BOUNDARY = ((WINDOW_WIDTH - PLAY_AREA_WIDTH) / 2); // (1280 - 360)/2 = 460 (i.e. divide the extra space evenly
  public static int RIGHT_BOUNDARY = LEFT_BOUNDARY + PLAY_AREA_WIDTH;
  public static int TOP_BOUNDARY = 50;
  public static int BOTTOM_BOUNDARY = TOP_BOUNDARY + PLAY_AREA_HEIGHT;

  private Tetromino currentMino;
  private Tetromino projectedMino;
  private int deactivateCounter;
  private Tetromino nextMino;

  // Initialise mino start position
  private static final int MINO_START_X = LEFT_BOUNDARY + (PLAY_AREA_WIDTH / 2) - Block.SIZE;
  private static final int MINO_START_Y = TOP_BOUNDARY + Block.SIZE;

  // Initialise next mino start position
  private static final int NEXT_MINO_START_X = RIGHT_BOUNDARY + 175;
  private static final int NEXT_MINO_START_Y = TOP_BOUNDARY + 500;

  private boolean gamePaused;
  private boolean gameOver;

  // Score
  private int level = 1;
  private int score;
  private int lines;


  public GameModel() {
    this.staticBlocks = new ArrayList<>();
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


  public Tetromino getProjectedMino() {
    return projectedMino;
  }


  public void setProjectedMino(Tetromino projectedMino) {
    this.projectedMino = projectedMino;
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