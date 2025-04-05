package com.example;

import com.example.tetromino.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import static com.example.TetrominoType.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TetrominoGeneratorService}.
 */
class TestTetrominoGeneratorService {

  TetrominoGeneratorService tetrominoGeneratorService;
  GameModel gameModel;

  @BeforeEach
  void setUp() {
    gameModel = new GameModel();
    tetrominoGeneratorService = new TetrominoGeneratorService(gameModel);
  }


  /**
   * Tests that the random mino generator returns one of the expected types. This
   * test will generate a random result so we need to just check it is one of
   * the expected Mino types.
   */
  @Test
  public void testRandomGenerator() {
    assertThat(tetrominoGeneratorService.pickRandomTetromino())
      .describedAs("Mino generator should return one of these types.")
      .isInstanceOfAny(
        MinoBar.class,
        MinoL1.class,
        MinoL2.class,
        MinoSquare.class,
        MinoT.class,
        MinoZ1.class,
        MinoZ2.class
      );
  }


  /**
   * Tests that counter for the Tetromino generator.
   */
  @Test
  public void testTetrominoGeneratorCounter() {
    // GIVEN:
    var minoBar = new MinoBar();
    var minoL1 = new MinoL1();
    var minoL2 = new MinoL2();
    var minoZ1 = new MinoZ1();
    var minoZ2 = new MinoZ2();
    var minoT = new MinoT();
    var minoSquare = new MinoSquare();

   ArrayList<Tetromino> minoList = new ArrayList<>(Set.of(minoBar, minoL1, minoL2, minoZ1, minoZ2, minoT, minoSquare));

    // check initial state
    assertThat(gameModel.getMinoCount())
      .describedAs("No Minos have been counted, it should be zero for each type.")
      .containsExactlyInAnyOrderEntriesOf(Map.of(
        MINO_Z1, 0,
        MINO_Z2, 0,
        MINO_T, 0,
        MINO_BAR, 0,
        MINO_SQUARE, 0,
        MINO_L1, 0,
        MINO_L2, 0
      ));

    // WHEN:
    // ...one of each Tetromino is added
    for (Tetromino mino: minoList) {
      tetrominoGeneratorService.incrementMinoCount(mino);
    }

    // ... plus one extra for good measure
    tetrominoGeneratorService.incrementMinoCount(minoBar);

    // THEN:
    assertThat(gameModel.getMinoCount())
      .describedAs("One of each should have been counted, plus an extra mino bar.")
      .containsExactlyInAnyOrderEntriesOf(Map.of(
        MINO_Z1, 1,
        MINO_Z2, 1,
        MINO_T, 1,
        MINO_BAR, 2,
        MINO_SQUARE, 1,
        MINO_L1, 1,
        MINO_L2, 1
      ));
  }
}