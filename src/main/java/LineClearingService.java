package main.java;

import main.java.tetromino.Block;

import java.util.ArrayList;

import static main.java.GameConfiguration.*;
import static main.java.GameModel.LEFT_BOUNDARY;
import static main.java.GameModel.TOP_BOUNDARY;
import static main.java.tetromino.Block.SIZE;

/**
 * Responsible for checking complete lines and adjusting static blocks as required.
 */
public class LineClearingService {


  /**
   * Converts the static block arraylist to a 2d boolean array for more performant checking
   * of complete lines.
   *
   * @param staticBlocks array list
   * @return a 2d boolean of dimensions x = number of blocks wide and y = number of blocks high
   */
  public boolean[][] convertBlocksTo2dBooleanArray(ArrayList<Block> staticBlocks) {
    boolean[][] board = new boolean[BLOCKS_PER_ROW][BLOCKS_PER_COLUMN];

    for (Block staticBlock : staticBlocks) {
      int row_number = (staticBlock.getBlockY() - TOP_BOUNDARY) / SIZE;
      int col_number = (staticBlock.getBlockX() - LEFT_BOUNDARY) / SIZE;

      board[col_number][row_number] = true;
    }

    return board;
  }


  /**
   * Handles checking of completed lines, deleting of completed rows, and returns an
   * int representing the number of completed rows that were deleted which the caller
   * can use to handle scoring.
   *
   * @param staticBlocks
   * @return the number of completed lines that have been deleted (to handle scoring)
   */
  public int checkDelete(ArrayList<Block> staticBlocks) {
    boolean[][] board = convertBlocksTo2dBooleanArray(staticBlocks);
    int linesCompleted = 0;

    for (int row = 0; row < (PLAY_AREA_HEIGHT / SIZE); row++) {
      if (isRowComplete(board, row)) {
        deleteRow(staticBlocks, row);
        shiftStaticBlocksDown(staticBlocks, row);
        linesCompleted++;
      }
    }

    return linesCompleted;
  }


  /**
   * Shifts all static blocks that are above the provided row number down one. For use
   * after a row has been deleted.
   *
   * @param staticBlocks
   * @param row(s) to shift down
   */
  private void shiftStaticBlocksDown(ArrayList<Block> staticBlocks, int row) {
    // Shift all static blocks above the deleted row down
    int rowYThatWasDeleted = TOP_BOUNDARY + row * SIZE;

    for (Block staticBlock: staticBlocks) {
      if (staticBlock.getBlockY() < rowYThatWasDeleted) {
        staticBlock.setY(staticBlock.getBlockY() + SIZE);
      }
    }
  }


  /**
   * Delete all static blocks that are in the provided row number.
   *
   * @param staticBlocks currently in the game
   * @param row to be deleted
   */
  private void deleteRow(ArrayList<Block> staticBlocks, int row) {
    int rowToDelete = (TOP_BOUNDARY + row * SIZE);
    ArrayList<Block> blocksToDelete = new ArrayList<>();

    for (Block staticBlock: staticBlocks) {
      if (staticBlock.y == rowToDelete) {
        blocksToDelete.add(staticBlock);
      }
    }

    staticBlocks.removeAll(blocksToDelete);
  }


  /**
   * Check if the provided row is complete.
   *
   * @param board 2d boolean array indicating whether a given position contains a static block or not
   * @param row to check if completed
   *
   * @return whether the row is completed or not.
   */
  private boolean isRowComplete(boolean[][] board, int row) {
    boolean isLineComplete = true;

    for (int col = 0; col < (PLAY_AREA_WIDTH / SIZE); col++) {
      if (!board[col][row]) {
        isLineComplete = false;
        break;
      }
    }
    return isLineComplete;
  }
}