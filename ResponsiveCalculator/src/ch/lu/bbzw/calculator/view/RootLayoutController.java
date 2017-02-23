package ch.lu.bbzw.calculator.view;

import ch.lu.bbzw.calculator.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {
  private MainApp mainApp = null;

  @FXML
  private void handleDefaultCalculator() {
    mainApp.showDefaultCalc();
  }

  @FXML
  private void handleBinaryCalculator() {
    mainApp.showBinaryCalc();
  }

  public void setMainApp(MainApp mainApp) {
    this.mainApp = mainApp;
  }
}
