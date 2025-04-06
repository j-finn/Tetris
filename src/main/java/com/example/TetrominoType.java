package com.example;

import java.awt.*;

public enum TetrominoType {
  MINO_Z1("Z1", Color.RED),
  MINO_Z2("Z2", Color.GREEN),
  MINO_T("T", Color.MAGENTA),
  MINO_BAR("Bar", Color.CYAN),
  MINO_SQUARE("Square", Color.YELLOW),
  MINO_L1("L1", Color.ORANGE),
  MINO_L2("L2", Color.BLUE);

  private final String displayName;
  private final Color color;


  TetrominoType(String displayName, Color color) {
    this.displayName = displayName;
    this.color = color;
  }


  public Color getColor() {
    return color;
  }


  @Override
  public String toString() {
    return displayName;
  }
}