package main.java.tetromino;

import java.awt.*;

/**
 * 0 1
 * 2 3
 */
public class MinoSquare extends Tetromino {


  public MinoSquare() {
    create(Color.YELLOW);
  }


  @Override
  public void setXY(int x, int y) {
    blocks[0].x = x;
    blocks[0].y = y;
    blocks[1].x = blocks[0].x + Block.SIZE;
    blocks[1].y = blocks[0].y;
    blocks[2].x = blocks[0].x;
    blocks[2].y = blocks[0].y + Block.SIZE;
    blocks[3].x = blocks[0].x + Block.SIZE;
    blocks[3].y = blocks[0].y + Block.SIZE;
  }


  @Override
  Tetromino rotatePosition1() {
    // NO-OP
    return this;
  }


  @Override
  Tetromino rotatePosition2() {
    // NO-OP
    return this;
  }


  @Override
  Tetromino rotatePosition3() {
    // NO-OP
    return this;
  }


  @Override
  Tetromino rotatePosition4() {
    // NO-OP
    return this;
  }
}