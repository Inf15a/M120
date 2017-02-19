package ch.lu.bbzw.calculator.view;

import ch.lu.bbzw.calculator.utils.MathChoices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CalculatorController {

	@FXML
	private TextField txtOutput;
	@FXML
	private GridPane numberPane;
	@FXML
	private GridPane mathPane;

	private String lastInput;
	private MathChoices mathChoice;

	@FXML
	private void handle0() {
		if (!txtOutput.getText().isEmpty()) {
			txtOutput.setText(txtOutput.getText() + "0");
		}
	}

	@FXML
	private void handle1() {
		txtOutput.setText(txtOutput.getText() + "1");
	}

	@FXML
	private void handle2() {
		txtOutput.setText(txtOutput.getText() + "2");
	}

	@FXML
	private void handle3() {
		txtOutput.setText(txtOutput.getText() + "3");
	}

	@FXML
	private void handle4() {
		txtOutput.setText(txtOutput.getText() + "4");
	}

	@FXML
	private void handle5() {
		txtOutput.setText(txtOutput.getText() + "5");
	}

	@FXML
	private void handle6() {
		txtOutput.setText(txtOutput.getText() + "6");
	}

	@FXML
	private void handle7() {
		txtOutput.setText(txtOutput.getText() + "7");
	}

	@FXML
	private void handle8() {
		txtOutput.setText(txtOutput.getText() + "8");
	}

	@FXML
	private void handle9() {
		txtOutput.setText(txtOutput.getText() + "9");
	}

	@FXML
	private void handlePoint() {
		if (!txtOutput.getText().contains(".") && !txtOutput.getText().isEmpty()) {
			txtOutput.setText(txtOutput.getText() + ".");
		}
	}

	@FXML
	private void handleC() {
		if (txtOutput.getText().length() > 0) {
			txtOutput.setText(txtOutput.getText().substring(0, txtOutput.getText().length() - 1));
		}
	}

	@FXML
	private void handleCE() {
		lastInput = "";
		txtOutput.setText("");
	}

	@FXML
	private void handlePlus() {
		storeInput();
		mathChoice = MathChoices.PLUS;
		txtOutput.setText("");
	}

	@FXML
	private void handleMinus() {
		storeInput();
		mathChoice = MathChoices.MINUS;
		txtOutput.setText("");
	}

	@FXML
	private void handleDivide() {
		storeInput();
		mathChoice = MathChoices.DIVIDE;
		txtOutput.setText("");
	}

	@FXML
	private void handleMultiply() {
		storeInput();
		mathChoice = MathChoices.MULTIPLY;
		txtOutput.setText("");
	}

	@FXML
	private void handleEqual() {
		try {
			if (!lastInput.isEmpty()) {
				switch (mathChoice) {
				case PLUS:
					summate();
					break;
				case MINUS:
					subtract();
					break;
				case MULTIPLY:
					multiply();
					break;
				case DIVIDE:
					divide();
					break;
				default:
					break;
				}
			}
		} catch (NullPointerException e) {
		}
	}

	private void divide() {
		try {
			String output = String.valueOf(Double.parseDouble(lastInput) / Double.parseDouble(txtOutput.getText()));
			storeInput();
			txtOutput.setText(output);
		} catch (NumberFormatException e) {
		}
	}

	private void multiply() {
		try {
			String output = String.valueOf(Double.parseDouble(lastInput) * Double.parseDouble(txtOutput.getText()));
			storeInput();
			txtOutput.setText(output);
		} catch (NumberFormatException e) {
		}
	}

	private void subtract() {
		try {
			String output = String.valueOf(Double.parseDouble(lastInput) - Double.parseDouble(txtOutput.getText()));
			storeInput();
			txtOutput.setText(output);
		} catch (NumberFormatException e) {
		}
	}

	private void summate() {
		try {
			String output = String.valueOf(Double.parseDouble(lastInput) + Double.parseDouble(txtOutput.getText()));
			storeInput();
			txtOutput.setText(output);
		} catch (NumberFormatException e) {
		}
	}

	private void storeInput() {
		lastInput = txtOutput.getText();
	}

	public void init() {
		setButtonMaxSize(numberPane);
		setButtonMaxSize(mathPane);
	}

	private void setButtonMaxSize(GridPane pane) {
		for (int i = 0; i < pane.getChildren().size(); i++) {
			HBox box = (HBox) pane.getChildren().get(i);
			Button btn = (Button) box.getChildren().get(0);
			btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			btn.setPrefSize(1000, 2000);
		}
	}
}
