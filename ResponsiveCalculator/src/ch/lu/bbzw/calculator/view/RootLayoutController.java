package ch.lu.bbzw.calculator.view;

import ch.lu.bbzw.calculator.Calculator;
import ch.lu.bbzw.calculator.utils.StyleType;
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

	@FXML
	private void handleSetMaterialDesign() {
		calculator.changeStylesheet(StyleType.MATERIAL);
	}

	@FXML
	private void handleSetFXClassic() {
		calculator.changeStylesheet(StyleType.FXCLASSIC);
	}
}
