package com.example;

import com.example.tetromino.Tetromino;

import javax.swing.*;

import static com.example.GameAction.PAUSE;
import static com.example.SoundService.Sound.*;

/**
 * Responsible for orchestrating all game services.
 */
public class GameController implements Runnable, GameActionListener {

  GameModel gameModel;
  Thread gameThread;

  // Others
  public static int dropInterval = 60; // mino drops every 60 frames
  int autoDropCounter = 0; // mino drops once this hits the dropInterval

  //-------------
  // SERVICES
  //-------------
  TetrominoGeneratorService tetrominoGeneratorService;
  RenderService renderService;
  CollisionService collisionService;
  LineClearingService lineClearingService;
  MinoProjectionService minoProjectionService;

  //-------------
  // SOUND
  //-------------
  public static SoundService soundService = new SoundService();



  public GameController(RenderService renderService,
                        GameModel gameModel,
                        TetrominoGeneratorService tetrominoGeneratorService,
                        CollisionService collisionService,
                        LineClearingService lineClearingService,
                        MinoProjectionService minoProjectionService) {
    this.renderService = renderService;
    this.tetrominoGeneratorService = tetrominoGeneratorService;
    this.gameModel = gameModel;
    this.collisionService = collisionService;
    this.lineClearingService = lineClearingService;
    this.minoProjectionService = minoProjectionService;

    // initialise first mino and handle the count
    Tetromino firstMino = tetrominoGeneratorService.pickRandomTetromino();
    tetrominoGeneratorService.incrementMinoCount(firstMino);
    gameModel.setCurrentMino(firstMino);
    gameModel.getCurrentMino().setXY(gameModel.getMinoStartX(), gameModel.getMinoStartY());

    // initialise next mino
    gameModel.setNextMino(tetrominoGeneratorService.pickRandomTetromino());
    gameModel.getNextMino().setXY(gameModel.getNextMinoStartX(), gameModel.getNextMinoStartY());
  }


  public static void main(String[] args) {
    JFrame window = new JFrame("Simple frame");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);

    GameModel gameModel = new GameModel();
    TetrominoGeneratorService tetrominoGeneratorService = new TetrominoGeneratorService(gameModel);
    RenderService renderService = new RenderService(gameModel);
    CollisionService collisionService = new CollisionService(gameModel);
    LineClearingService lineClearingService = new LineClearingService();
    MinoProjectionService minoProjectionService = new MinoProjectionService(collisionService);
    GameController gameController = new GameController(renderService, gameModel, tetrominoGeneratorService, collisionService, lineClearingService, minoProjectionService);

    KeyHandler keyHandler = new KeyHandler(gameController);

    renderService.addKeyListener(keyHandler);
    renderService.setFocusable(true);

    window.add(renderService);
    window.pack(); // i.e. size of window becomes size of GamePanel

    window.setLocationRelativeTo(null);
    window.setVisible(true);

    gameController.launchGame();
  }


  /**
   * {@link KeyHandler} translates the key inputs and then calls {@link GameController}
   * to handle the {@link GameAction}.
   */
  @Override
  public void handleGameAction(GameAction gameAction) {

    if (gameModel.isGamePaused() && !gameAction.equals(PAUSE)) {
      // If paused, Mino should not be moveable but it must be possible to unpause
      return;
    }

    if (gameModel.isGameOver()) {
      // TODO: If implementing restart, will need to permit the key press
      //  that restarts the game here.
      return;
    }

    switch (gameAction) {
      case MOVE_RIGHT:
        moveRight();
        break;
      case MOVE_LEFT:
        moveLeft();
        break;
      case MOVE_DOWN:
        if (!hasHitBottom()) {
          moveDown();
        }
        break;
      case ROTATE:
        rotate();
        break;
      case PAUSE:
        gameModel.togglePause();
        break;
      case DROP:
        drop();
        break;
      case MUTE:
        soundService.toggleMusic(TETRIS_THEME);
        break;
      case RESTART:
        // TODO
        break;
      default:
        throw new IllegalArgumentException("Unidentified game action provided: [" + gameAction + "]");
    }
  }


  public void rotate() {
    Tetromino copyMino = gameModel.getCurrentMino().clone();

    if (!collisionService.checkMovementCollision(copyMino.rotatePosition())) {
      gameModel.getCurrentMino().rotatePosition();
    }
  }


  public void moveRight() {
    Tetromino copyMino = gameModel.getCurrentMino().clone();

    if (!collisionService.checkMovementCollision(copyMino.moveRight())) {
      gameModel.getCurrentMino().moveRight();
    }
  }


  public void moveLeft() {
    Tetromino copyMino = gameModel.getCurrentMino().clone();

    if (!collisionService.checkMovementCollision(copyMino.moveLeft())) {
      gameModel.getCurrentMino().moveLeft();
    }
  }


  public boolean hasHitBottom() {
    Tetromino copyMino = gameModel.getCurrentMino().clone();
    return collisionService.checkMovementCollision(copyMino.moveDown());
  }


  public void moveDown() {
    gameModel.getCurrentMino().moveDown();

    autoDropCounter = 0; // reset drop counter
  }


  public void drop() {
    Tetromino copyMino = gameModel.getCurrentMino().clone();

    while (!collisionService.checkMovementCollision(copyMino)) {
      copyMino.moveDown();
    }

    // cloned mino has collided, shift back up to valid position and set as our current mino
    copyMino.setMinoActive(false);
    gameModel.setCurrentMino(copyMino.moveUp());
  }


  public void launchGame() {
    startThreads();

    soundService.toggleMusic(TETRIS_THEME);
    soundService.loop();
  }


  private void startThreads() {
    if (gameThread == null) {
      gameThread = new Thread(this);
      gameThread.start();
    }
  }


  @Override
  public void run() {
    double drawInterval = (double) 1_000_000_000 / GameConfiguration.FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();

      // 60 F/S
      // 15 x 10^-9 * 60 / 1 * 10^9
      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;

      if (delta > 1) {
        update();
        renderService.repaint();
        delta--;
      }
    }
  }


  public void deactivating() {
    gameModel.setDeactivateCounter(gameModel.getDeactivateCounter() + 1);

    // wait 30 frames until deactivating
    if (gameModel.getDeactivateCounter() == 45) {
      // check bottom is still touching
      if (hasHitBottom()) {
        gameModel.getCurrentMino().setMinoActive(false);
        gameModel.setDeactivateCounter(0);
      } else {
        gameModel.setDeactivateCounter(0);
      }
    }
  }


  /**
   * Spawns the next Mino and increments the count of generated Minos.
   */
  private void nextMinoSpawn() {
    // Promote the next Mino
    gameModel.setCurrentMino(gameModel.getNextMino());

    // Update its coordinates to the active mino start position
    gameModel.getCurrentMino().setXY(gameModel.getMinoStartX(), gameModel.getMinoStartY());

    // Increment the count of spawned Minos
    tetrominoGeneratorService.incrementMinoCount(gameModel.getCurrentMino());
  }


  public void update() {
    if (gameModel.isGamePaused() || gameModel.isGameOver()) {
      return;
    }

    if (gameModel.getCurrentMino().isBlockDeactivating()) {
      deactivating();
    }

    if (gameModel.getCurrentMino().isActive()) {

      // Project current mino
      gameModel.setProjectedMino(minoProjectionService.project(gameModel.getCurrentMino()));

      if (hasHitBottom()) {
        gameModel.getCurrentMino().setBlockDeactivating(true);
      } else {
        if (autoDropCounter >= GameController.dropInterval) {
          moveDown();
        }
      }
      autoDropCounter++; // counter increases every frame

    } else {
      gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[0]);
      gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[1]);
      gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[2]);
      gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[3]);

      gameModel.getCurrentMino().setBlockDeactivating(false);

      // Replace current mino with next mino
      nextMinoSpawn();

      // Set next mino
      gameModel.setNextMino(tetrominoGeneratorService.pickRandomTetromino());
      gameModel.getNextMino().setXY(gameModel.getNextMinoStartX(), gameModel.getNextMinoStartY());

      // instant collision on spawn -> game over
      if (collisionService.checkMovementCollision(gameModel.getCurrentMino())) {
        gameModel.setGameOver(true);
        soundService.stop();
        soundService.toggleMusic(GAME_OVER);
      }

      int numberOfDeletedLines = lineClearingService.checkDelete(gameModel.getStaticBlocks());

      // Add score
      if (numberOfDeletedLines > 0) {
        soundService.playSoundEffect(DELETE_LINE);

        int singleLineScore = 10 * gameModel.getLevel();
        gameModel.setScore(gameModel.getScore() + singleLineScore * numberOfDeletedLines);

        gameModel.setLines(gameModel.getLines() + 1);

        // Drop speed
        if (gameModel.getLines() % 10 == 0 && dropInterval > 1) {
          gameModel.setLevel(gameModel.getLevel() + 1);

          if (dropInterval > 10) {
            dropInterval -= 10;
          } else {
            dropInterval -= 1;
          }
        }
      }
    }
  }
}