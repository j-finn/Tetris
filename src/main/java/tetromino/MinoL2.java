package main.java.tetromino;

import java.awt.*;

/**
 * Shape:
 *    1
 *    0
 *  3 2
 *
 *    Rotate around 0
 */
public class MinoL2 extends Tetromino {

  public MinoL2() {
    create(Color.BLUE);
  }


  @Override
  public void setXY(int x, int y) {
    blocks[0].x = x;
    blocks[0].y = y;
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y - Block.SIZE;
    blocks[2].x = blocks[0].x;
    blocks[2].y = blocks[0].y + Block.SIZE;
    blocks[3].x = blocks[0].x - Block.SIZE;
    blocks[3].y = blocks[0].y + Block.SIZE;
  }


  /**
   *    1
   *    0
   *  3 2
   */
  @Override
  Tetromino getRotatedPosition1() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x;
    tempB[1].y = blocks[0].y - Block.SIZE;
    tempB[2].x = blocks[0].x;
    tempB[2].y = blocks[0].y + Block.SIZE;
    tempB[3].x = blocks[0].x - Block.SIZE;
    tempB[3].y = blocks[0].y + Block.SIZE;

    return this;
  }


  /**
   *  3
   *  2 0 1
   */
  @Override
  Tetromino getRotatedPosition2() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x + Block.SIZE;
    tempB[1].y = blocks[0].y;
    tempB[2].x = blocks[0].x - Block.SIZE;
    tempB[2].y = blocks[0].y;
    tempB[3].x = blocks[0].x - Block.SIZE;
    tempB[3].y = blocks[0].y - Block.SIZE;

    return this;
  }


  /**
   *  2 3
   *  0
   *  1
   */
  @Override
  Tetromino getRotatedPosition3() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x;
    tempB[1].y = blocks[0].y + Block.SIZE;
    tempB[2].x = blocks[0].x;
    tempB[2].y = blocks[0].y - Block.SIZE;
    tempB[3].x = blocks[0].x + Block.SIZE;
    tempB[3].y = blocks[0].y - Block.SIZE;

    return this;
  }


  /**
   *  1 0 2
   *      3
   */
  @Override
  Tetromino getRotatedPosition4() {
    tempB[0].x = blocks[0].x;
    tempB[0].y = blocks[0].y;
    tempB[1].x = blocks[0].x - Block.SIZE;
    tempB[1].y = blocks[0].y;
    tempB[2].x = blocks[0].x + Block.SIZE;
    tempB[2].y = blocks[0].y;
    tempB[3].x = blocks[0].x + Block.SIZE;
    tempB[3].y = blocks[0].y + Block.SIZE;

    return this;
  }
}
