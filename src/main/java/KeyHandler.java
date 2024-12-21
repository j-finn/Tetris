package main.java;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  public static boolean upPressed, downPressed, rightPressed, leftPressed;

  @Override
  public void keyPressed(KeyEvent e) {

    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) {
      upPressed = true;
    }

    if (code == KeyEvent.VK_D) {
      rightPressed = true;
    }

    if (code == KeyEvent.VK_A) {
      leftPressed = true;
    }

    if (code == KeyEvent.VK_S) {
      downPressed = true;
    }


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
