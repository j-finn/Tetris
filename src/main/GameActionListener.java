package main;

import main.java.GameAction;
import main.java.GameController;


/**
 * <p>Fired when a valid key has been pressed and a game action needs
 * to be handled by the {@link GameController}.</p>
 */
public interface GameActionListener {

  void handleGameAction(GameAction action);
}
