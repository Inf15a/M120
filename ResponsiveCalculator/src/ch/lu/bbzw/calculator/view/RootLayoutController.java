package ch.lu.bbzw.calculator.view;

import ch.lu.bbzw.calculator.Calculator;
import javafx.fxml.FXML;

public class RootLayoutController {
  private Calculator calculator = null;

  @FXML
  private void handleDefaultCalculator() {
    calculator.showDefaultCalc();
  }

  @FXML
  private void handleBinaryCalculator() {
    calculator.showBinaryCalc();
  }

  public void setMainApp(Calculator mainApp) {
    this.calculator = mainApp;
  }
}
