package main.java;

import main.GameActionListener;
import main.java.tetromino.Block;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Responsible for orchestrating all game services.
 */
public class GameController implements Runnable, GameActionListener {

    public static ArrayList<Block> droppedBlockOutlines = new ArrayList<>();

    // Others
    public static int dropInterval = 60; // mino drops every 60 frames

    GameModel gameModel;
    Thread gameThread;
    int autoDropCounter = 0; // mino drops once this hits the dropInterval

    //-------------
    // SERVICES
    //-------------
    TetrominoGeneratorService tetrominoGeneratorService;
    RenderService renderService;
    CollisionService collisionService;

    //-------------
    // SOUND
    //-------------
    public static main.java.Sound music = new main.java.Sound();
//    public static main.java.Sound soundEffect = new main.java.Sound(); // TODO Re-implement
    public static boolean musicMuted = false;



    public GameController(RenderService renderService,
                          GameModel gameModel,
                          TetrominoGeneratorService tetrominoGeneratorService,
                          CollisionService collisionService) {
        this.renderService = renderService;
        this.tetrominoGeneratorService = tetrominoGeneratorService;
        this.gameModel = gameModel;
        this.collisionService = collisionService;

        // initialise mino
        gameModel.setCurrentMino(tetrominoGeneratorService.pickRandomTetromino());
        gameModel.getCurrentMino().setXY(gameModel.getMinoStartX(), gameModel.getMinoStartY());
        gameModel.getCurrentMino().resetTemp();

        // initialise next mino
        gameModel.setNextMino(tetrominoGeneratorService.pickRandomTetromino());
        gameModel.getNextMino().setXY(gameModel.getNextMinoStartX(), gameModel.getNextMinoStartY());
        gameModel.getNextMino().resetTemp();
    }


    public static void main(String[] args) {
        JFrame window = new JFrame("Simple frame");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GameModel gameModel = new GameModel();
        TetrominoGeneratorService tetrominoGeneratorService = new TetrominoGeneratorService();
        RenderService renderService = new RenderService(gameModel);
        CollisionService collisionService = new CollisionService(gameModel);
        GameController gameController = new GameController(renderService, gameModel, tetrominoGeneratorService, collisionService);

        KeyHandler keyHandler = new KeyHandler(gameController);

        renderService.addKeyListener(keyHandler);
        renderService.setFocusable(true);

        // TODO: How to set up key listener properly and attach it to the right component?

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
        switch (gameAction) {
            case MOVE_RIGHT:
                moveRight();
                break;
            case MOVE_LEFT:
                moveLeft();
                break;
            case MOVE_DOWN:
                moveDown();
                break;
            case ROTATE:
                rotate();
                break;
            case PAUSE:
                gameModel.togglePause();
                break;
            case DROP:
                // TODO
                break;
            case MUTE:
                toggleMusic();
                break;
            case RESTART:
                // TODO
                break;
            default:
                throw new IllegalArgumentException("Unidentified game action provided: [" + gameAction + "]");
        }
    }


    public void rotate() {
        if (!collisionService.checkMovementCollision(gameModel.getCurrentMino().getRotatedPosition())) {
            gameModel.getCurrentMino().rotate();
        }
    }


    public void moveRight() {
        if (!collisionService.checkMovementCollision(gameModel.getCurrentMino().getRightMovePosition())) {
            gameModel.getCurrentMino().moveRight();
        }
    }


    public void moveLeft() {
        if (!collisionService.checkMovementCollision(gameModel.getCurrentMino().getLeftMovePosition())) {
            gameModel.getCurrentMino().moveLeft();
        }
    }


    public void moveDown() {
        if (!collisionService.checkMovementCollision(gameModel.getCurrentMino().getDownMovePosition())) {
            gameModel.getCurrentMino().moveDown();

            // reset drop counter
            autoDropCounter = 0;
        }
    }


    public void launchGame() {
        startThreads();

        music.play(2, true);
        music.loop();
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


    public void toggleMusic() {
        if (musicMuted) {
            music.play(2, true);
            music.loop();
        } else {
            music.stop();
        }
        musicMuted = !musicMuted;
    }


    public void deactivating() {
        gameModel.setDeactivateCounter(gameModel.getDeactivateCounter() + 1);

        // wait 45 frames until deactivating
        if (gameModel.getDeactivateCounter() == 45) {

            gameModel.setDeactivateCounter(0);
            collisionService.checkMovementCollision(gameModel.getCurrentMino()); // check bottom is still touching
        }
    }


    public void update() {
        if (gameModel.isGamePaused() || gameModel.isGameOver()) {
            return;
        }

        if (gameModel.getCurrentMino().isBlockDeactivating()) {
            deactivating();
        }

        if (!gameModel.getCurrentMino().isBlockActive()) {
            gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[0]);
            gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[1]);
            gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[2]);
            gameModel.getStaticBlocks().add(gameModel.getCurrentMino().getBlocks()[3]);

            if (gameModel.getCurrentMino().getBlocks()[0].x == gameModel.getMinoStartX()
               && gameModel.getCurrentMino().getBlocks()[0].y == gameModel.getMinoStartY()) {
                // means that block has collided immediately
                gameModel.setGameOver(true);
                music.stop();
//                soundEffect.play(1, true);
            }

            gameModel.getCurrentMino().setBlockDeactivating(false);

            // Replace current mino with next mino
            gameModel.setCurrentMino(gameModel.getNextMino());
            gameModel.getCurrentMino().setXY(gameModel.getMinoStartX(), gameModel.getMinoStartY());
            gameModel.setNextMino(tetrominoGeneratorService.pickRandomTetromino());
            gameModel.getNextMino().setXY(gameModel.getNextMinoStartX(), gameModel.getNextMinoStartY());

            checkDelete();

        } else {

//            if (collisionService.checkMovementCollision(gameModel.getCurrentMino())) {
//                gameModel.getCurrentMino().setBlockDeactivating(true);
////            GamePanel.soundEffect.play(4, true);
//            } else {
                autoDropCounter++; // counter increases every frame

                if (autoDropCounter == GameController.dropInterval) {
                    // TODO: Why can't we just set b[0] and update the rest in one go?
                    gameModel.getCurrentMino().getBlocks()[0].y += Block.SIZE;
                    gameModel.getCurrentMino().getBlocks()[1].y += Block.SIZE;
                    gameModel.getCurrentMino().getBlocks()[2].y += Block.SIZE;
                    gameModel.getCurrentMino().getBlocks()[3].y += Block.SIZE;
                    autoDropCounter = 0;
//                }
            }

//            droppedBlockOutlines.clear();
//            droppedBlockOutlines.addAll(Arrays.asList(currentMino.dropBlockForDrawing()));


//            update();
        }
    }


    private void checkDelete() {

        int x = gameModel.getLeftBoundary();
        int y = gameModel.getTopBoundary();
        int blockCount = 0;
        int lineCount = 0;

        while (x < gameModel.getRightBoundary() && y < gameModel.getBottomBoundary()) {

            for (int i = 0; i < gameModel.getStaticBlocks().size(); i++) {
                if (gameModel.getStaticBlocks().get(i).x == x && gameModel.getStaticBlocks().get(i).y == y) {
                    blockCount++;
                }
            }

            x += Block.SIZE;

            if (x == gameModel.getRightBoundary()) {
                if (blockCount == 12) {

                    for (int i = (gameModel.getStaticBlocks().size() - 1); i > -1; i--) {
                        if (gameModel.getStaticBlocks().get(i).y == y) {
                            gameModel.getStaticBlocks().remove(i);
                        }
                    }

                    lineCount++;
                    gameModel.setLines(gameModel.getLines()+ 1);

                    // Drop speed
                    if (gameModel.getLines() % 10 == 0 && dropInterval > 1) {
                       gameModel.setLevel(gameModel.getLevel() + 1);

                        if (dropInterval > 10) {
                            dropInterval -= 10;
                        } else {
                            dropInterval -= 1;
                        }
                    }

                    // line has been deleted -> move down all other blocks
                    for (int i = 0; i < gameModel.getStaticBlocks().size(); i++) {
                        if (gameModel.getStaticBlocks().get(i).y < y) {
                            gameModel.getStaticBlocks().get(i).y += Block.SIZE;
                        }
                    }

                    // play deletion sound effect
//                    GamePanel.soundEffect.play(0, true);
                }

                blockCount = 0;
                x = gameModel.getLeftBoundary();
                y += Block.SIZE;
            }
        }

        // Add score
        if (lineCount > 0) {
            int singleLineScore = 10 * gameModel.getLevel();
            gameModel.setScore(gameModel.getScore() + singleLineScore * lineCount);
        }
    }
}