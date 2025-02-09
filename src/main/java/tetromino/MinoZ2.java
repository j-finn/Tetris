package main.java.tetromino;

import java.awt.*;

public class MinoZ2 extends Tetromino {

  public MinoZ2() {
    create(Color.GREEN);
  }


  /**
   *   1
   *   0 2
   *     3
   */
  @Override
  public void setXY(int x, int y) {
    blocks[0].x = x;
    blocks[0].y = y;
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y - Block.SIZE;
    blocks[2].x = blocks[0].x + Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x + Block.SIZE;
    blocks[3].y = blocks[0].y + Block.SIZE;
  }


  /**
   *   1
   *   0 2
   *     3
   */
  @Override
  Tetromino rotatePosition1() {
    blocks[1].x = blocks[0].x;
    blocks[1].y = blocks[0].y - Block.SIZE;
    blocks[2].x = blocks[0].x - Block.SIZE;
    blocks[2].y = blocks[0].y;
    blocks[3].x = blocks[0].x - Block.SIZE;
    blocks[3].y = blocks[0].y + Block.SIZE;

    return this;
  }


  /**
   *     0 1
   *   3 2
   */
  @Override
  Tetromino rotatePosition2() {
    blocks[1].x = blocks[0].x + Block.SIZE;
    blocks[1].y = blocks[0].y;
    blocks[2].x = blocks[0].x;
    blocks[2].y = blocks[0].y + Block.SIZE;
    blocks[3].x = blocks[0].x - Block.SIZE;
    blocks[3].y = blocks[0].y + Block.SIZE;

    return this;
  }


  /**
   *   3
   *   2 0
   *     1
   */
  @Override
  Tetromino rotatePosition3() {
    return rotatePosition1();
  }


  /**
   *     2 3
   *   1 0
   */
  @Override
  Tetromino rotatePosition4() {
    return rotatePosition2();
  }
}