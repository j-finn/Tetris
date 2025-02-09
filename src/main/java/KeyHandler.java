package main.java;

import main.GameActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  GameActionListener gameActionListener;

  public KeyHandler(GameActionListener gameActionListener) {
    this.gameActionListener = gameActionListener;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    GameAction action = translateKeyEvent(e);

    if (action != null && gameActionListener != null) {
      gameActionListener.handleGameAction(action);
    }
  }


  private GameAction translateKeyEvent(KeyEvent keyEvent) {
    int code = keyEvent.getKeyCode();

    if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
      return GameAction.ROTATE;
    }

    if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
      return GameAction.MOVE_RIGHT;
    }

    if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
      return GameAction.MOVE_LEFT;
    }

    if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
      return GameAction.MOVE_DOWN;
    }

    if (code == KeyEvent.VK_ESCAPE) {
      return GameAction.PAUSE;
    }

    if (code == KeyEvent.VK_SPACE) {
      return GameAction.DROP;
    }

    if (code == KeyEvent.VK_M) {
      return GameAction.MUTE;
    }

    // TODO: Not implemented yet.
//    if (code == KeyEvent.VK_ENTER) {
//      restartGamePressed = true;
//    }

    return null;
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