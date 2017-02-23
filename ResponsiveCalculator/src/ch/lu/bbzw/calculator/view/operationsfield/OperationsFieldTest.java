package ch.lu.bbzw.calculator.view.operationsfield;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OperationsFieldTest extends Application {

  @Override
  public void start(Stage primaryStage) {
    CalculatorOperationsField calc = new CalculatorOperationsField();
    primaryStage.setScene(new Scene(calc));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
