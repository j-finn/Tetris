package com.example;

import com.example.tetromino.Tetromino;

/**
 * Responsible for projecting where a {@link Tetromino} would
 * land if it was dropped.
 */
public class MinoProjectionService {

  private CollisionService collisionService;

  public MinoProjectionService(CollisionService collisionService) {
    this.collisionService = collisionService;
  }

  /**
   * @return the projected position of the {@link Tetromino}.
   */
  public Tetromino project(Tetromino tetromino) {
    Tetromino projectedMino = tetromino.clone();

    while (!collisionService.checkMovementCollision(projectedMino)) {
      projectedMino.moveDown();
    }

    // Once it's collided, we need to adjust it back up to the pre-collision position
    return projectedMino.moveUp();
  }
}