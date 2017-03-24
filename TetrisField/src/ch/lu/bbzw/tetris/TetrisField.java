package ch.lu.bbzw.tetris;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import ch.lu.bbzw.tetris.blocks.Block;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class TetrisField extends GridPane {

  public static int amountOfRows;
  public static int amountOfColumns;
  private final String backgroundColor = "-fx-background-color: rgb(224,224,224);";
  private final String gridLinesVisible = "-fx-grid-lines-visible: true;";

  private TetrisPane[][] panes;

  public TetrisField(int amountOfRows, int amountOfColumns) {
    TetrisField.amountOfRows = amountOfRows;
    TetrisField.amountOfColumns = amountOfColumns;
    panes = new TetrisPane[TetrisField.amountOfRows][TetrisField.amountOfColumns];
    setStyle(backgroundColor + gridLinesVisible);

    initGrid();
    initPanes();
    addPanesToGrid();
  }

  private void initGrid() {
    for (int i = 0; i < amountOfRows; i++) {
      RowConstraints rc = new RowConstraints();
      rc.setPercentHeight(100.0 / amountOfRows);
      getRowConstraints().add(rc);
    }
    for (int i = 0; i < amountOfColumns; i++) {
      ColumnConstraints cc = new ColumnConstraints();
      cc.setPercentWidth(100.0 / amountOfColumns);
      getColumnConstraints().add(cc);
    }
  }

  private void initPanes() {
    for (int row = 0; row < amountOfRows; row++) {
      for (int col = 0; col < amountOfColumns; col++) {
        panes[row][col] = new TetrisPane();
      }
    }
  }

  private void addPanesToGrid() {
    for (int row = 0; row < amountOfRows; row++) {
      for (int col = 0; col < amountOfColumns; col++) {
        add(panes[row][col], col, row);
      }
    }
  }

  public void start() {
    Game game = new Game(this);
    game.start();
  }

  public void setActiveBackground(List<Double> points, Block block, boolean isFinal) {
    resetPanesBackground();
    for (Double double1 : points) {
      for (int i = 0; i < Block.MAX_BLOCK_HEIGHT; i++) {
        for (int j = 0; j < Block.MAX_BLOCK_WIDTH; j++) {
          try {
            if (block.getPattern()[i][j]) {
              int posX = (int) double1.x + getMinusAmountX(i);
              int posY = (int) double1.y + getMinusAmountY(j);
              if (isFinal) {
                panes[posX][posY].setFinal(true);
              }
              panes[posX][posY].setStyle("-fx-background-color: " + block.getCssFont() + ";");
            }
          } catch (Exception e) {
            // do nothing
          }
        }
      }
    }
  }

  private int getMinusAmountY(int j) {
    if (j == 0) {
      return -1;
    } else if (j == 2) {
      return 1;
    }
    return 0;
  }

  private int getMinusAmountX(int i) {
    if (i == 0) {
      return -2;
    } else if (i == 1) {
      return -1;
    }
    return 0;
  }

  private void resetPanesBackground() {
    for (int row = 0; row < amountOfRows; row++) {
      for (int col = 0; col < amountOfColumns; col++) {
        if (!panes[row][col].isFinal()) {
          panes[row][col].setStyle(backgroundColor);
        }
      }
    }
  }

  public boolean checkIfFieldIsFinal(int positionY, int positionX) {
    return panes[positionY][positionX].isFinal();
  }

  public void handleFullLines() {
    while (!getAllFullLines().isEmpty()) {
      for (Integer row : getAllFullLines()) {
        clearRow(row.intValue());
      }
    }
  }

  private void clearRow(int row) {
    for (int col = 0; col < amountOfColumns; col++) {
      panes[row][col].setFinal(false);
      panes[row][col].setStyle("-fx-background-color: white;");
    }
  }

  private List<Integer> getAllFullLines() {
    List<Integer> rowsWithFullLine = new ArrayList<>();
    for (int row = 0; row < amountOfRows; row++) {
      boolean actualRowIsFull = true;
      for (int col = 0; col < amountOfColumns; col++) {
        if (!panes[row][col].isFinal()) {
          actualRowIsFull = false;
        }
      }
      if (actualRowIsFull) {
        rowsWithFullLine.add(row);
      }
    }

    return rowsWithFullLine;
  }
}
