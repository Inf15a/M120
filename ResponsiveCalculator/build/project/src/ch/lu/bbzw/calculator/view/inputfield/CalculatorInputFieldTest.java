package ch.lu.bbzw.calculator.view.inputfield;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorInputFieldTest extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setScene(new Scene(new CalculatorInputField()));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
