package main.java;

import main.java.tetromino.*;

import java.util.Random;

public class TetrominoGeneratorService {

  private static final Random RANDOM = new Random();

  private GameModel gameModel;

  public TetrominoGeneratorService(GameModel gameModel) {
    this.gameModel = gameModel;
  }


  public Tetromino pickRandomTetromino() {
    TetrominoType[] values = TetrominoType.values();
    TetrominoType randomType = values[RANDOM.nextInt(values.length)];

    switch(randomType) {
      case MINO_BAR: return new MinoBar();
      case MINO_L1: return new MinoL1();
      case MINO_L2: return new MinoL2();
      case MINO_SQUARE: return new MinoSquare();
      case MINO_T: return new MinoT();
      case MINO_Z1: return new MinoZ1();
      case MINO_Z2: return new MinoZ2();
      default: throw new IllegalStateException("Unknown Mino type has been generated: " + randomType);
    }
  }


  /**
   * Used to count the number of each type of Mino that has been generated.
   *
   * @param tetromino whose type we need to increment.
   */
  public void incrementMinoCount(Tetromino tetromino) {
    gameModel.getMinoCount().compute(tetromino.getType(), (k,v) -> v == null ? 1 : v + 1);
  }
}