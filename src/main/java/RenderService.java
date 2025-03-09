package main.java;

import main.java.tetromino.Block;
import main.java.tetromino.Tetromino;

import javax.swing.*;
import java.awt.*;

/**
 * Responsible for drawing.
 */
public class RenderService extends JPanel {

  GameModel gameModel;

  public RenderService(GameModel gameModel) {
    //Panel settings
    this.setPreferredSize(new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT));
    this.setBackground(Color.black);
    this.setLayout(null);

    this.gameModel = gameModel;
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    draw((Graphics2D) graphics);
  }


  public void draw(Graphics2D graphics2D) {
    drawPlayArea(graphics2D);

    drawWaitingRoomForNextTetromino(graphics2D);

    drawScoreboard(graphics2D);

//    List<Block> blockOutlinesToDelete = new ArrayList<>();
//
//    for (int j = 0; j < gameModel.getCurrentMino().getB().length; j++) {
//      for (int i = 0; i < droppedBlockOutlines.size(); i++) {
//        if (droppedBlockOutlines.get(i).x == gameModel.getCurrentMino().getB()[j].x &&
//          droppedBlockOutlines.get(i).y == gameModel.getCurrentMino().getB()[j].y) {
//          blockOutlinesToDelete.add(droppedBlockOutlines.get(i));
//        }
//      }
//    }
//
//    droppedBlockOutlines.removeAll(blockOutlinesToDelete);
//
//    for (int i = 0; i < droppedBlockOutlines.size(); i++) {
//      droppedBlockOutlines.get(i).drawOutline(graphics2D);
//    }

    // Draw the current mino
    if (gameModel.getCurrentMino() != null) {
      drawTetromino(graphics2D, gameModel.getCurrentMino());
    }

    // Draw the next mino
    if (gameModel.getNextMino() != null) {
      drawTetromino(graphics2D, gameModel.getNextMino());
    }

    for (int i = 0; i < gameModel.getStaticBlocks().size(); i++) {
      gameModel.getStaticBlocks().get(i).draw(graphics2D);
    }

    if (gameModel.isGameOver()) {
      drawGameOver(graphics2D);
    }

    if (gameModel.isGamePaused()) {
      drawGamePaused(graphics2D);
    }
  }


  public void drawTetromino(Graphics2D graphics2D, Tetromino tetromino) {
    int margin = 2; // for black outline
    graphics2D.setColor(tetromino.getMinoColor());

    for (Block block : tetromino.getBlocks()) {
      graphics2D.fillRect(block.getBlockX() + margin, block.getBlockY() + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
    }
  }


  private void drawPlayArea(Graphics2D graphics2D) {
    // Draw play area frame
    graphics2D.setColor(Color.white);
    graphics2D.setStroke(new BasicStroke(4f));
    // Note 4 subtracted from (x,y) co-ordinates is to account for the stroke width
    // We want the inner part of the frame border to be where collision happen
    graphics2D.drawRect(
      gameModel.getLeftBoundary() - 4,
      gameModel.getTopBoundary() - 4,
      gameModel.PLAY_AREA_WIDTH + 8,
      gameModel.PLAY_AREA_HEIGHT + 8);
  }


  private void drawWaitingRoomForNextTetromino(Graphics2D graphics2D) {
    int x = gameModel.getRightBoundary() + 100;
    int y = gameModel.getBottomBoundary() - 200;
    graphics2D.drawRect(x, y, 200, 200);
    // Add text for waiting room
    graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics2D.drawString("NEXT", x + 60, y + 60);
  }


  private void drawGamePaused(Graphics2D graphics2D) {
    graphics2D.setColor(Color.YELLOW);
    graphics2D.setFont(graphics2D.getFont().deriveFont(50f));

    int x = gameModel.getLeftBoundary() + 70;
    int y = gameModel.getTopBoundary() + 320;
    graphics2D.drawString("PAUSED", x, y);
  }


  private void drawGameOver(Graphics2D graphics2D) {
    graphics2D.setColor(Color.YELLOW);
    graphics2D.setFont(graphics2D.getFont().deriveFont(50f));

    int x = gameModel.getLeftBoundary() + 25;
    int y = gameModel.getTopBoundary() + 320;
    graphics2D.drawString("GAME OVER", x, y);
  }


  private void drawScoreboard(Graphics2D graphics2D) {
    int x = gameModel.getRightBoundary() + 100;
    graphics2D.drawRect(x, gameModel.getTopBoundary(), 250, 300);
    x += 40;
    graphics2D.drawString("LEVEL: " + gameModel.getLevel(), x, gameModel.getTopBoundary() + 90);
    graphics2D.drawString("LINES: " + gameModel.getLines(), x, gameModel.getTopBoundary() + 160);
    graphics2D.drawString("SCORE: " + gameModel.getScore(), x, gameModel.getTopBoundary() + 230);
  }
}