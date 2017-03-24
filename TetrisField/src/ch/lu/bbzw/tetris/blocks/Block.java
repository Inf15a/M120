package ch.lu.bbzw.tetris.blocks;

public abstract class Block {
  public static final int MAX_BLOCK_WIDTH = 3;
  public static final int MAX_BLOCK_HEIGHT = 3;
  protected boolean[][] pattern = new boolean[MAX_BLOCK_WIDTH][MAX_BLOCK_WIDTH];
  protected String cssBackground = "red";

  public Block() {
    for (int i = 0; i < MAX_BLOCK_WIDTH; i++) {
      for (int j = 0; j < MAX_BLOCK_HEIGHT; j++) {
        pattern[i][j] = false;
      }
    }
  }

  public boolean[][] getPattern() {
    return pattern;
  }

  public boolean isTrueAt(int x, int y) {
    return pattern[x][y];
  }

  public String getCssFont() {
    return cssBackground;
  }
}
