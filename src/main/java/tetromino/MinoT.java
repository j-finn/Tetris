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
  Tetromino rotatePosition1() {
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y - Block.SIZE;
    blocks[2].x = blocks[0].x - Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x + Block.SIZE;
    blocks[3].y = blocks[0].y;

    return this;
  }


  /**
   * 2
   * 0 1
   * 3
   */
  @Override
  Tetromino rotatePosition2() {
    blocks[1].x = blocks[0].x + Block.SIZE;
    blocks[1].y = blocks[0].y;
    blocks[2].x = blocks[0].x;
    blocks[2].y = blocks[0].y - Block.SIZE;
    blocks[3].x = blocks[0].x;
    blocks[3].y = blocks[0].y + Block.SIZE;

    return this;
  }


  /**
   * 3 0 2
   *   1
   */
  @Override
  Tetromino rotatePosition3() {
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y + Block.SIZE;
    blocks[2].x = blocks[0].x + Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x - Block.SIZE;
    blocks[3].y = blocks[0].y;

    return this;
  }


  /**
   *   3
   * 1 0
   *   2
   */
  @Override
  Tetromino rotatePosition4() {
    blocks[1].x = blocks[0].x - Block.SIZE;
    blocks[1].y = blocks[0].y;
    blocks[2].x = blocks[0].x;
    blocks[2].y = blocks[0].y + Block.SIZE;
    blocks[3].x = blocks[0].x;
    blocks[3].y = blocks[0].y - Block.SIZE;

    return this;
  }
}
