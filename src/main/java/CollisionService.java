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
   * <p>Checks for collision, the caller must have updated the temporary blocks on
   * the provided {@link Tetromino} to their prospective location.</p>
   *
   * TODO: Update this method Javadoc depending on how I implement this behaviour. For rotation
   *  I am just directly using tempB. For movement, each method is attempting to move one block left/right/down.
   *
   * @param tetromino
//   * @param isRotation is true if the movement is a rotation
   * @return
   */
  public boolean checkMovementCollision(Tetromino tetromino/*, boolean isRotation*/) {

    return checkStaticBlockCollision(tetromino) || checkBoundaryCollision(tetromino);

//    if (isRotation) {
//      return checkStaticBlockCollision(tetromino) || checkBoundaryCollision(tetromino);
//    } else {
//      return checkStaticBlockMovementCollision(tetromino) || checkBoundaryMovementCollision(tetromino);
//    }
  }


  /**
   * <p>Verify if rotating the provided {@link Tetromino} would cause a collision
   * with the static blocks (i.e. inactive blocks).</p>
   *
   * @param currentTetromino whose collision status we are checking.
   *
   * @return true if the rotation would cause a collision. False otherwise.
   */
  private boolean checkStaticBlockCollision(Tetromino currentTetromino) {
    for (Block staticBlock: gameModel.getStaticBlocks()) {
      for (Block activeBlock: currentTetromino.getTempB()) {
        if (activeBlock.y == staticBlock.y && activeBlock.x == staticBlock.x) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * <p>Verify if rotating the provided {@link Tetromino} would cause a collision
   * with the static blocks (i.e. inactive blocks).</p>
   *
   * @param currentTetromino whose collision status we are checking.
   *
   * @return true if the rotation would cause a collision. False otherwise.
   */
  private boolean checkBoundaryCollision(Tetromino currentTetromino) {

    for (Block block: currentTetromino.getTempB()) {
      if (block.x == gameModel.getLeftBoundary()) {
        return true;
      }

      if (block.x == gameModel.getRightBoundary()) {
        return true;
      }

      if (block.y == gameModel.getBottomBoundary()) {
        return true;
      }
    }

    return false;
  }


//  /**
//   * <p>Verify if moving the provided {@link Tetromino} one block right/left/down
//   * would cause a collision with the static blocks (i.e. inactive blocks).</p>
//   *
//   * @param currentTetromino whose collision status we are checking.
//   *
//   * @return true if the movement would cause a collision. False otherwise.
//   */
//  private boolean checkStaticBlockMovementCollision(Tetromino currentTetromino) {
//
//    for (Block staticBlock: gameModel.getStaticBlocks()) {
//      for (Block activeBlock: currentTetromino.getTempB()) {
//
//        // bottom collision
//        if (activeBlock.y + Block.SIZE == staticBlock.y && activeBlock.x == staticBlock.x) {
//          return true;
//        }
//
//        // right collision
//        if (activeBlock.y == staticBlock.y && activeBlock.x + Block.SIZE == staticBlock.x) {
//          return true;
//        }
//
//        // left collision
//        if (activeBlock.y == staticBlock.y && activeBlock.x - Block.SIZE == staticBlock.x) {
//          return true;
//        }
//      }
//    }
//    return false;
//  }
//
//
//  /**
//   * <p>Verify if moving the provided {@link Tetromino} one block left/right/down
//   * would cause a collision with the game boundary.</p>
//   *
//   * @param currentTetromino whose collision status we are checking.
//   *
//   * @return true if the movement would cause a collision. False otherwise.
//   */
//  private boolean checkBoundaryMovementCollision(Tetromino currentTetromino) {
//
//    for (Block block: currentTetromino.getTempB()) {
//
//      // right boundary collision
//      if (block.x + Block.SIZE == gameModel.getRightBoundary()) {
//        return true;
//      }
//
//      // left boundary collision
//      if (block.x - Block.SIZE == gameModel.getLeftBoundary()) {
//        return true;
//      }
//
//      // bottom boundary collision
//      if (block.y + Block.SIZE == gameModel.getBottomBoundary()) {
//        return true;
//      }
//    }
//      return false;
//  }
}