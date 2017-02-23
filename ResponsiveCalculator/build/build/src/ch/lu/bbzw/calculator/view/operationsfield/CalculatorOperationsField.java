package ch.lu.bbzw.calculator.view.operationsfield;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CalculatorOperationsField extends HBox {
  private GridPane gridPane;

  public CalculatorOperationsField() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculatorOperationsField.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    setGridPane();
  }

  public void addButtonActionListener(String buttonName, Runnable runnable) {
    for (int i = 0; i < gridPane.getChildren().size(); i++) {
      HBox box;
      try {
        box = (HBox) gridPane.getChildren().get(i);
      } catch (Exception e) {
        // HBox doesnt have a Button child
        continue;
      }
      for (int j = 0; j < box.getChildren().size(); j++) {
        Button button;
        try {
          button = (Button) box.getChildren().get(j);
        } catch (Exception e) {
          // HBox doesnt have a Button child
          continue;
        }
        if (button.getText().equals(buttonName)) {
          button.setOnAction(event -> runnable.run());
        }
      }
    }
  }

  private void setGridPane() {
    for (int i = 0; i < getChildren().size(); i++) {
      try {
        gridPane = (GridPane) getChildren().get(i);
      } catch (Exception e) {
        System.out.println("uiuiui");
        // is not a gridpane
      }
    }
  }
}
