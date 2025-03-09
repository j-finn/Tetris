package main.java;

import main.java.tetromino.Block;
import main.java.tetromino.Tetromino;

/**
 * Responsible for checking for collisions only.
 */
public class CollisionService {

  GameModel gameModel;


  public CollisionService(GameModel gameModel) {
    this.gameModel = gameModel;
  }


  /**
   * <p>Checks if the provided {@link Tetromino} has collided.</p>
   *
   * @param tetromino whose collision status we are checking.
   *
   * @return true if the {@link Tetromino} has collided. False otherwise.
   */
  public boolean checkMovementCollision(Tetromino tetromino) {
    return checkStaticBlockCollision(tetromino) || checkBoundaryCollision(tetromino);
  }


  /**
   * <p>Verify if the provided {@link Tetromino} has collided with static blocks (i.e. inactive blocks).</p>
   *
   * @param tetromino whose collision status we are checking.
   *
   * @return true if the {@link Tetromino} has collided with a static block. False otherwise.
   */
  private boolean checkStaticBlockCollision(Tetromino tetromino) {
    for (Block staticBlock: gameModel.getStaticBlocks()) {
      for (Block activeBlock: tetromino.getBlocks()) {
        if (activeBlock.y == staticBlock.y && activeBlock.x == staticBlock.x) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * <p>Verify if the provided {@link Tetromino} has collided with the game boundary.</p>
   *
   * @param currentTetromino whose collision status we are checking.
   *
   * @return true if the {@link Tetromino} has collided with the boundary. False otherwise.
   */
  private boolean checkBoundaryCollision(Tetromino currentTetromino) {

    for (Block block: currentTetromino.getBlocks()) {
      if (block.x == gameModel.getLeftBoundary() - Block.SIZE) {
      // The x coordinate of the block is the left hand edge. We subtract the block size from the boundary as
      // we only care if a left move would move us further than boundary x == block x.
        return true;
      }

      if (block.x == gameModel.getRightBoundary()) {
        // Note we do not need to adjust this boundary since x coordinate of block is the left edge
        // so if block.x == right boundary x, the block has gone too far.
        return true;
      }

      if (block.y == gameModel.getBottomBoundary()) {
        // Likewise, y coordinate of block is the top. We shift the boundary up and check for equality.
        // If we get a match, then we know the bottom of the block is touching the boundary.
        return true;
      }
    }
    return false;
  }
}