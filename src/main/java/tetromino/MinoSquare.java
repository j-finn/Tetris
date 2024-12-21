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
    b[0].x = x;
    b[0].y = y;
    b[1].x = b[0].x + Block.SIZE;
    b[1].y = b[0].y;
    b[2].x = b[0].x;
    b[2].y = b[0].y + Block.SIZE;
    b[3].x = b[0].x + Block.SIZE;
    b[3].y = b[0].y + Block.SIZE;
  }


  @Override
  public void getDirection1() {
    // NO-OP
  }


  @Override
  public void getDirection2() {
    // NO-OP
  }


  @Override
  public void getDirection3() {
    // NO-OP
  }


  @Override
  public void getDirection4() {
    // NO-OP
  }
}