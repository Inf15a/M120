package ch.lu.bbzw.tetris;

import javafx.scene.layout.Pane;

public class TetrisPane extends Pane {
  private boolean isFinal = false;

  public boolean isFinal() {
    return isFinal;
  }

  public void setFinal(boolean isFinal) {
    this.isFinal = isFinal;
  }
}
