package com.example;

import com.example.tetromino.Block;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.example.GameModel.*;
import static com.example.tetromino.Block.SIZE;
import static java.awt.Color.RED;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLineClearingService {

  private static final int BOTTOM_ROW = BOTTOM_BOUNDARY - SIZE;
  private static final int SECOND_ROW = BOTTOM_BOUNDARY - SIZE * 2;
  private static final int THIRD_ROW = BOTTOM_BOUNDARY - SIZE * 3;


  LineClearingService lineClearingService = new LineClearingService();

  /**
   * Checks that if there's a single completed row of static blocks, that row is deleted.
   */
  @Test
  public void testCompletedRowGetsDeleted() {
    // GIVEN:
    ArrayList<Block> staticBlocks = new ArrayList<>();
    // two completed rows
    staticBlocks.addAll(createCompletedRowOfBlocks(BOTTOM_ROW));
    staticBlocks.addAll(createCompletedRowOfBlocks(SECOND_ROW));
    // one lone block in the third row (not completed row)
    staticBlocks.add(createBlock(LEFT_BOUNDARY, THIRD_ROW));

    // WHEN && THEN:
    assertEquals(2, lineClearingService.checkDelete(staticBlocks) , "First 2 rows should have been deleted.");
    assertEquals(1, staticBlocks.size(), "The first two rows should have been removed. The one block in the third" +
      "row should still be present");
  }


  private ArrayList<Block> createCompletedRowOfBlocks(int y){
    ArrayList<Block> staticBlocks = new ArrayList<>();

    for (int i = LEFT_BOUNDARY; i < RIGHT_BOUNDARY; i += SIZE) {
      staticBlocks.add(createBlock(i, y));
    }

    return staticBlocks;
  }


  private Block createBlock(int x, int y) {
    Block block = new Block(RED);
    block.setBlockX(x);
    block.setBlockY(y);

    return block;
  }
}