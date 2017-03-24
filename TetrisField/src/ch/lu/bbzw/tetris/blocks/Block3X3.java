package ch.lu.bbzw.tetris.blocks;

public class Block3X3 extends Block {
  public Block3X3() {
    super();
    cssBackground = "rgb(76,175,80)";
    for (int i = 0; i < MAX_BLOCK_WIDTH; i++) {
      for (int j = 0; j < MAX_BLOCK_HEIGHT; j++) {
        pattern[i][j] = true;
      }
    }
  }
}
