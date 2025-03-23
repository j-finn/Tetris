package com.example;

import com.example.tetromino.*;
import org.junit.jupiter.api.Test;

import static java.awt.Color.RED;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests {@link CollisionService}. Note that each test uses a different Tetromino. For each Tetromino the
 * (x,y) coordinates specified in the test are for 'Block 0', see Javadoc for each Tetromino to identify
 * which block is 0.
 */
public class TestCollisionService {


  GameModel gameModel = new GameModel();
  CollisionService collisionService = new CollisionService(gameModel);


  /**
   * <p>Tests that if a Tetromino has the same (x,y) coordinates as the right boundary that
   * it is considered to have collided. Uses an L1 mino, shape must be considered when
   * setting up mino test position.</p>
   */
  @Test
  public void testRightBoundaryCollision() {
    // GIVEN:
    Tetromino tetromino = new MinoL1();
    tetromino.setXY(760, 80);

    assertFalse(collisionService.checkMovementCollision(tetromino),
      "Block has not collided with right side yet, should be false.");

    // WHEN:
    tetromino.moveRight();

    // THEN:
    assertTrue(collisionService.checkMovementCollision(tetromino),
      "Block has now collided with the side, should be true.");
  }


  /**
   * <p>Tests that if a Tetromino has the same (x,y) coordinates as the left boundary that
   * it is considered to have collided. Uses an Square mino, shape must be considered when
   * setting up mino test position.</p>
   */
  @Test
  public void testLeftBoundaryCollision() {
    // GIVEN:
    Tetromino tetromino = new MinoSquare();
    tetromino.setXY(460, 80);

    assertFalse(collisionService.checkMovementCollision(tetromino),
      "Block has not collided with left side yet, should be false.");

    // WHEN:
    tetromino.moveLeft();

    // THEN:
    assertTrue(collisionService.checkMovementCollision(tetromino),
      "Block has now collided with the side, should be true.");
  }


  /**
   * <p>Tests that if a Tetromino has the same (x,y) coordinates as the bottom boundary that
   * it is considered to have collided. Uses an Square mino, shape must be considered when
   * setting up mino test position.</p>
   */
  @Test
  public void testBottomBoundaryCollision() {
    // GIVEN:
    Tetromino tetromino = new MinoZ1();
    tetromino.setXY(490, 590);

    assertFalse(collisionService.checkMovementCollision(tetromino),
      "Block has not collided with the bottom yet, should be false.");

    // WHEN:
    tetromino.moveDown();

    // THEN:
    assertTrue(collisionService.checkMovementCollision(tetromino),
      "Block has not collided with the bottom yet, should be true.");
  }


  /**
   * Tests that if a static block has the same (x,y) coordinates as a Tetromino, that it is
   * considered to have collided.
   */
  @Test
  public void testStaticCollision() {
    // GIVEN:
    Block block = createBlock(120, 120);
    gameModel.getStaticBlocks().add(block);

    Tetromino tetromino = new MinoSquare();
    tetromino.setXY(block.getBlockX(), block.getBlockY());

    // WHEN && THEN:
    assertTrue(collisionService.checkMovementCollision(tetromino));
  }


  private Block createBlock(int x, int y) {
    Block block = new Block(RED);
    block.setBlockX(x);
    block.setBlockY(y);

    return block;
  }
}