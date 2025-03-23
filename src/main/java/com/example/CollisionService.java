package com.example;

import com.example.tetromino.Block;
import com.example.tetromino.Tetromino;

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
        if (activeBlock.getBlockY() == staticBlock.getBlockY() && activeBlock.getBlockX() == staticBlock.getBlockX()) {
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
      if (block.getBlockX() < gameModel.getLeftBoundary()) {
        // The x coordinate of the block is the left hand edge. If x coordinate equals left boundary
        // the block is just moving down the left hand boundary which is okay.
        return true;
      }

      if (block.getBlockX() >= gameModel.getRightBoundary()) {
        // x coordinate is left hand edge, so it's only in bounds if the x coordinate is less than the boundary x.
        return true;
      }

      if (block.getBlockY() >= gameModel.getBottomBoundary()) {
        // The y coordinate of block is the top edge and y increases further down the screen.
        // Only in bounds if the y coordinate is less than the bottom boundary.
        return true;
      }
    }
    return false;
  }
}