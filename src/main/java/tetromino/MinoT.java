package main.java.tetromino;

import java.awt.*;

/**
 *   1
 * 2 0 3
 */
public class MinoT extends Tetromino {

  public MinoT() {
    create(Color.MAGENTA);
  }



  @Override
  public void setXY(int x, int y) {
    blocks[0].x = x;
    blocks[0].y = y;
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y - Block.SIZE;
    blocks[2].x = blocks[0].x - Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x + Block.SIZE;
    blocks[3].y = blocks[0].y;
  }


  /**
   *   1
   * 2 0 3
   */
  @Override
  Tetromino getRotatedPosition1() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x;
    tempB[1].y = blocks[0].y - Block.SIZE;
    tempB[2].x = blocks[0].x - Block.SIZE;
    tempB[2].y = blocks[0].y;
    tempB[3].x = blocks[0].x + Block.SIZE;
    tempB[3].y = blocks[0].y;

    return this;
  }


  /**
   * 2
   * 0 1
   * 3
   */
  @Override
  Tetromino getRotatedPosition2() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x + Block.SIZE;
    tempB[1].y = blocks[0].y;
    tempB[2].x = blocks[0].x;
    tempB[2].y = blocks[0].y - Block.SIZE;
    tempB[3].x = blocks[0].x;
    tempB[3].y = blocks[0].y + Block.SIZE;

    return this;
  }


  /**
   * 3 0 2
   *   1
   */
  @Override
  Tetromino getRotatedPosition3() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x;
    tempB[1].y = blocks[0].y + Block.SIZE;
    tempB[2].x = blocks[0].x + Block.SIZE;
    tempB[2].y = blocks[0].y;
    tempB[3].x = blocks[0].x - Block.SIZE;
    tempB[3].y = blocks[0].y;

    return this;
  }


  /**
   *   3
   * 1 0
   *   2
   */
  @Override
  Tetromino getRotatedPosition4() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x - Block.SIZE;
    tempB[1].y = blocks[0].y;
    tempB[2].x = blocks[0].x;
    tempB[2].y = blocks[0].y + Block.SIZE;
    tempB[3].x = blocks[0].x;
    tempB[3].y = blocks[0].y - Block.SIZE;

    return this;
  }
}
