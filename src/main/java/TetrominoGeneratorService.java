package main.java;

import main.java.tetromino.*;

import java.util.Random;

public class TetrominoGeneratorService {

  private static final Random RANDOM = new Random();

  public Tetromino pickRandomTetromino() {
    int i = RANDOM.nextInt(7);

    switch(i) {
      case 0: return new MinoBar();
      case 1: return new MinoL1();
      case 2: return new MinoL2();
      case 3: return new MinoSquare();
      case 4: return new MinoT();
      case 5: return new MinoZ1();
      case 6: return new MinoZ2();
      default: throw new IllegalStateException();
    }
  }
}