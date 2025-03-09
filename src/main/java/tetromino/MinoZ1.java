package main.java.tetromino;

import java.awt.*;

/**
 * Shape:
 * 1
 * 2 0
 * 3
 */
public class MinoZ1 extends Tetromino {


  /**
   * Each row in the array is a different one of the 4 possible rotations.
   * Each row contains a dy and dx for each of the 4 blocks relative to 0 which
   * is block around which the mino rotates. Positive values are right in the x direction,
   * and down in the y direction.
   */
  static int[][] rotationOffsets = {
    {0, 0, 0, -1, -1, 0, -1, 1}, // Rotation state 1
    {0, 0, 1, 0, 0, -1, -1, -1}, // Rotation state 2
    {0, 0, 0, 1, 1, 0, 1, -1}, // Rotation state 3
    {0, 0, -1, 0, 0, 1, 1, 1}  // Rotation state 4
  };


  public MinoZ1() {
    create(Color.RED);
  }


  @Override
  public void setXY(int x, int y) {
    for (int i = 0; i < blocks.length; i++) {
      blocks[i].setBlockX(x + rotationOffsets[0][i * 2] * Block.SIZE);
      blocks[i].setBlockY(y + rotationOffsets[0][i * 2 + 1] * Block.SIZE);
    }
  }


  @Override
  Tetromino rotateToPosition(int number) {
    for (int i = 0; i < blocks.length; i++) {
      blocks[i].setBlockX(blocks[0].getBlockX() + rotationOffsets[number][i * 2] * Block.SIZE);
      blocks[i].setBlockY(blocks[0].getBlockY() + rotationOffsets[number][i * 2 + 1] * Block.SIZE);
    }

    direction++;
    return this;
  }
}