package main.java;

import main.java.tetromino.Block;
import main.java.tetromino.Tetromino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static main.java.GameConfiguration.PLAY_AREA_HEIGHT;
import static main.java.GameConfiguration.PLAY_AREA_WIDTH;
import static main.java.tetromino.Block.SIZE;

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

    // Draw the projection of current mino
    if (gameModel.getProjectedMino() != null) {
      drawOutline(graphics2D, gameModel.getProjectedMino());
    }

    // Draw the current mino
    if (gameModel.getCurrentMino() != null) {
      drawTetromino(graphics2D, gameModel.getCurrentMino());
    }

    // Draw the next mino
    if (gameModel.getNextMino() != null) {
      drawTetromino(graphics2D, gameModel.getNextMino());
    }

    for (int i = 0; i < gameModel.getStaticBlocks().size(); i++) {
      draw(graphics2D, gameModel.getStaticBlocks().get(i));
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
      graphics2D.fillRect(block.getBlockX() + margin, block.getBlockY() + margin, SIZE - (margin * 2), SIZE - (margin * 2));
    }
  }


  public void draw(Graphics2D graphics2D, Block block) {
    int margin = 2;
    graphics2D.setColor(block.getColour());
    graphics2D.fillRect(block.getBlockX() + margin , block.getBlockY() + margin, SIZE - (margin * 2), SIZE - (margin * 2));
  }


  public void drawOutline(Graphics2D graphics2D, Tetromino tetromino) {
    int margin = 2;

    ArrayList<Block> blockOutlinesToDraw = new ArrayList<>(Arrays.asList(tetromino.getBlocks()));

    for (Block projectedBlock : tetromino.getBlocks()) {
      for (Block currentBlock : gameModel.getCurrentMino().getBlocks()) {
        if (projectedBlock.equals(currentBlock)) {
          blockOutlinesToDraw.remove(projectedBlock);
        }
      }
    }

    for (Block block : blockOutlinesToDraw) {
      graphics2D.setColor(tetromino.getMinoColor());
      graphics2D.fillRect(block.getBlockX(), block.getBlockY(), SIZE, SIZE);
      graphics2D.setColor(Color.BLACK);
      graphics2D.fillRect(block.getBlockX() + margin, block.getBlockY() + margin, SIZE - (margin * 2), SIZE - (margin * 2));
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
      PLAY_AREA_WIDTH + 8,
      PLAY_AREA_HEIGHT + 8);
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