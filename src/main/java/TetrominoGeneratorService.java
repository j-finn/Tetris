package main.java;

import main.java.tetromino.*;

import java.util.Random;

public class TetrominoGeneratorService {

  private static final Random RANDOM = new Random();

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


  private enum TetrominoType {
    MINO_Z1,
    MINO_Z2,
    MINO_T,
    MINO_BAR,
    MINO_SQUARE,
    MINO_L1,
    MINO_L2
  }
}