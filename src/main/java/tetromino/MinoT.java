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
    b[0].x = x;
    b[0].y = y;
    b[1].x = b[0].x;
    b[1].y = b[0].y - Block.SIZE;
    b[2].x = b[0].x - Block.SIZE;
    b[2].y = b[0].y;
    b[3].x = b[0].x + Block.SIZE;
    b[3].y = b[0].y;
  }


  /**
   *   1
   * 2 0 3
   */
  @Override
  public void getDirection1() {
    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x;
    tempB[1].y = b[0].y - Block.SIZE;
    tempB[2].x = b[0].x - Block.SIZE;
    tempB[2].y = b[0].y;
    tempB[3].x = b[0].x + Block.SIZE;
    tempB[3].y = b[0].y;

    updateXY(1);
  }


  /**
   * 2
   * 0 1
   * 3
   */
  @Override
  public void getDirection2() {
    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x + Block.SIZE;
    tempB[1].y = b[0].y;
    tempB[2].x = b[0].x;
    tempB[2].y = b[0].y - Block.SIZE;
    tempB[3].x = b[0].x;
    tempB[3].y = b[0].y + Block.SIZE;

    updateXY(2);
  }


  /**
   * 3 0 2
   *   1
   */
  @Override
  public void getDirection3() {
    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x;
    tempB[1].y = b[0].y + Block.SIZE;
    tempB[2].x = b[0].x + Block.SIZE;
    tempB[2].y = b[0].y;
    tempB[3].x = b[0].x - Block.SIZE;
    tempB[3].y = b[0].y;

    updateXY(3);
  }


  /**
   *   3
   * 1 0
   *   2
   */
  @Override
  public void getDirection4() {
    tempB[0].x = b[0].x;
    tempB[0].y = b[0].y;
    tempB[1].x = b[0].x - Block.SIZE;
    tempB[1].y = b[0].y;
    tempB[2].x = b[0].x;
    tempB[2].y = b[0].y + Block.SIZE;
    tempB[3].x = b[0].x;
    tempB[3].y = b[0].y - Block.SIZE;

    updateXY(4);
  }
}
