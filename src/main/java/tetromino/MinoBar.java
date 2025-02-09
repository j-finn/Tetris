package main.java.tetromino;

import java.awt.*;

/**
 * 1 0 2 3
 */
public class MinoBar extends Tetromino {

  public MinoBar() {
    create(Color.CYAN);
  }

  @Override
  public void setXY(int x, int y) {
    blocks[0].x = x;
    blocks[0].y = y;
    blocks[1].x = blocks[0].x - Block.SIZE;
    blocks[1].y = blocks[0].y;
    blocks[2].x = blocks[0].x + Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x + (2 * Block.SIZE);
    blocks[3].y = blocks[0].y;
  }


  /**
   * 1 0 2 3
   */
  @Override
  Tetromino rotatePosition1() {
    blocks[1].x = blocks[0].x - Block.SIZE;
    blocks[1].y = blocks[0].y;
    blocks[2].x = blocks[0].x + Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x + (2 * Block.SIZE);
    blocks[3].y = blocks[0].y;

    return this;
  }


  /**
   *   1
   *   0
   *   2
   *   3
   */
  @Override
  Tetromino rotatePosition2() {
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y - Block.SIZE;
    blocks[2].x = blocks[0].x;
    blocks[2].y = blocks[0].y + Block.SIZE;
    blocks[3].x = blocks[0].x;
    blocks[3].y = blocks[0].y + (2 * Block.SIZE);

    return this;
  }


  /**
   *   3 2 0 1
   */
  @Override
  Tetromino rotatePosition3() {
    blocks[1].x = blocks[0].x + Block.SIZE;
    blocks[1].y = blocks[0].y;
    blocks[2].x = blocks[0].x - Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x - (2 * Block.SIZE);
    blocks[3].y = blocks[0].y;

    return this;
  }


  /**
   *   3
   *   2
   *   0
   *   1
   */
  @Override
  Tetromino rotatePosition4() {
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y + Block.SIZE;
    blocks[2].x = blocks[0].x;
    blocks[2].y = blocks[0].y - Block.SIZE;
    blocks[3].x = blocks[0].x;
    blocks[3].y = blocks[0].y - (2 * Block.SIZE);

    return this;
  }
}