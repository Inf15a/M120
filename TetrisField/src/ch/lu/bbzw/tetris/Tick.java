package ch.lu.bbzw.tetris;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import ch.lu.bbzw.tetris.blocks.Block;

public class Tick implements Runnable {
  private List<TickEndListener> tickEndListeners = new ArrayList<>();
  private int positionY = 0;
  private int positionX = (TetrisField.amountOfColumns - 1) / 2;
  private TetrisField tetrisField;
  private Block actualBlock;

  public Tick(TetrisField tetrisField) {
    this.tetrisField = tetrisField;
  }

  @Override
  public void run() {
    System.out.println("start tick");
    ArrayList<Point2D.Double> points = new ArrayList<>();
    points.add(new Point2D.Double(positionY, positionX));
    positionY++;
    fireTickEndListeners(points);
  }

  public void setActualBlock(Block actualBlock) {
    this.actualBlock = actualBlock;
  }

  public void addTickEndListener(TickEndListener endListener) {
    tickEndListeners.add(endListener);
  }

  private void fireTickEndListeners(ArrayList<Point2D.Double> points) {
    for (TickEndListener tickEndListener : tickEndListeners) {
      tickEndListener.onTickEnd(points, actualBlock, checkIfLastTick());
    }
  }

  private boolean checkIfLastTick() {
    if (positionY >= TetrisField.amountOfRows) {
      return true;
    }
    boolean isLastTick = false;
    if (actualBlock.isTrueAt(2, 1)) {
      isLastTick = tetrisField.checkIfFieldIsFinal(positionY, positionX);
    }
    if (actualBlock.isTrueAt(2, 0) && !isLastTick) {
      isLastTick = tetrisField.checkIfFieldIsFinal(positionY, positionX - 1);
    }
    if (actualBlock.isTrueAt(2, 2) && !isLastTick) {
      isLastTick = tetrisField.checkIfFieldIsFinal(positionY, positionX + 1);
    }
    return isLastTick;
  }

  public void moveLeft() {
    // if (actualBlock.isTrueAt(0, 0) && tetrisField.checkIfFieldIsFinal(positionY - 3, positionX -
    // 1)) {
    // return;
    // }
    // if (actualBlock.isTrueAt(1, 0) && tetrisField.checkIfFieldIsFinal(positionY - 2, positionX -
    // 1)) {
    // return;
    // }
    // if (actualBlock.isTrueAt(2, 0) && tetrisField.checkIfFieldIsFinal(positionY - 1, positionX -
    // 1)) {
    // return;
    // }
    for (int j = 0; j < Block.MAX_BLOCK_WIDTH; j++) {
      for (int i = 0; i < Block.MAX_BLOCK_HEIGHT; i++) {
        if (actualBlock.getPattern()[i][j]) {
          if (j == 0 && positionX - 1 < 1) {
            return;
          }
          if (j == 1 && positionX < 1) {
            return;
          }
          if (j == 2 && positionX + 1 < 1) {
            return;
          }

        }
      }
    }
    if (!tetrisField.checkIfFieldIsFinal(positionY, positionX - 1)) {
      System.out.println("hep");
      positionX--;
    }
  }

  public void moveRight() {
    if (!tetrisField.checkIfFieldIsFinal(positionY, positionX + 1)) {
      positionX++;
    }
  }

  @FunctionalInterface
  interface TickEndListener {
    void onTickEnd(List<Point2D.Double> points, Block block, boolean isLastTick);
  }
}
