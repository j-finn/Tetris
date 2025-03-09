package main.java.tetromino;

import java.awt.*;

/**
 * Shape:
 * 1 0 2 3
 */
public class MinoBar extends Tetromino {

  static int[][] rotationOffsets = {
    {0, 0, 0, 1, 0, -1, 0, -2}, // Rotation state 1
    {0, 0, -1, 0, 1, 0, 2, 0}, // Rotation state 2
    {0, 0, 0, -1, 0, 1, 0, 2}, // Rotation state 3
    {0, 0, 1, 0, -1, 0, -2, 0}  // Rotation state 4
  };


  public MinoBar() {
    create(Color.CYAN);
  }


  @Override
  public void setXY(int x, int y) {
    for (int i = 0; i < blocks.length; i++) {
      blocks[i].setBlockX(x + rotationOffsets[1][i * 2] * Block.SIZE);
      blocks[i].setBlockY(y + rotationOffsets[1][i * 2 + 1]* Block.SIZE);
    }
  }


  Tetromino rotateToPosition(int number) {
    for (int i = 0; i < blocks.length; i++) {
      blocks[i].setBlockX(blocks[0].getBlockX() + rotationOffsets[number][i * 2]* Block.SIZE);
      blocks[i].setBlockY(blocks[0].getBlockY() + rotationOffsets[number][i * 2 + 1]* Block.SIZE);
    }

    direction++;
    return this;
  }
}