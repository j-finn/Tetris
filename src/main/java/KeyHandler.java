package main.java;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  public static boolean upPressed, downPressed, rightPressed, leftPressed, pausePressed, dropPressed, mutePressed, restartGamePressed;


  @Override
  public void keyPressed(KeyEvent e) {

    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
      upPressed = true;
    }

    if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
      rightPressed = true;
    }

    if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
      leftPressed = true;
    }

    if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
      downPressed = true;
    }

    if (code == KeyEvent.VK_ESCAPE) {
      pausePressed = !pausePressed; // toggle pause
    }

    if (code == KeyEvent.VK_SPACE) {
      dropPressed = true;
    }

    if (code == KeyEvent.VK_M) {
      mutePressed = true;
    }

    // TODO: Not implemented yet.
//    if (code == KeyEvent.VK_ENTER) {
//      restartGamePressed = true;
//    }
  }


  @Override
  public void keyReleased(KeyEvent e) {
    // NO-OP
  }


  @Override
  public void keyTyped(KeyEvent e) {
    // NO-OP
  }
}