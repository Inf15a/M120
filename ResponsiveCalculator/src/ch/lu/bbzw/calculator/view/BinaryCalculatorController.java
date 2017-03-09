package ch.lu.bbzw.calculator.view;

import ch.lu.bbzw.calculator.utils.NumeralSystemConverterForStrings;
import ch.lu.bbzw.calculator.utils.OutputChoice;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BinaryCalculatorController extends DefaultCalculatorController {
	@FXML
	private GridPane binaryButtons;
	@FXML
	private RadioButton decimalChoice;
	@FXML
	private RadioButton binaryChoice;
	private OutputChoice choice = OutputChoice.DECIMAL;

	@Override
	public void init() {
		for (int i = 0; i < binaryButtons.getChildren().size(); i++) {
			GridPane gridPane = (GridPane) binaryButtons.getChildren().get(i);
			for (int j = 0; j < gridPane.getChildren().size(); j++) {
				VBox vBox = (VBox) gridPane.getChildren().get(j);
				((Button) (vBox.getChildren().get(0))).setOnAction(new BinaryButtonAction());
			}
		}

		calculatorInputField.addButtonActionListener(".", () -> {
		});

		txtOutput.setOnAction(event -> createBinaryButtonOutput());
	}

	@Override
	protected void handleEqual() {
		try {
			switch (lastChoice) {
			case PLUS:
				int summand1 = (int) lastInput;
				int summand2 = Integer.parseInt(txtOutput.getText());
				txtOutput.setText(String.valueOf(summand1 + summand2));
				break;
			case MINUS:
				int minuend = (int) lastInput;
				int subtrahend = Integer.parseInt(txtOutput.getText());
				txtOutput.setText(String.valueOf(minuend - subtrahend));
				break;
			case MULTIPLY:
				int factor1 = (int) lastInput;
				int factor2 = Integer.parseInt(txtOutput.getText());
				txtOutput.setText(String.valueOf(factor1 * factor2));
				break;
			case DIVIDE:
				int dividend = (int) lastInput;
				int divisor = Integer.parseInt(txtOutput.getText());
				txtOutput.setText(String.valueOf(dividend / divisor));
				break;
			default:
				break;
			}
		} catch (NumberFormatException | NullPointerException e) {
			// String is empty or no last input exists
		}
		createBinaryButtonOutput();
	}

	@FXML
	private void handleDecimalBinaryChoice() {
		if (decimalChoice.isSelected()) {
			choice = OutputChoice.DECIMAL;
			setDisabledToTextField2To9(false);
			try {
				txtOutput.setText(NumeralSystemConverterForStrings.txtToDecimal(txtOutput.getText()));
			} catch (NumberFormatException e) {
				// nothing has to be done, txtoutput is empty
			}
		} else if (binaryChoice.isSelected()) {
			choice = OutputChoice.BINARY;
			setDisabledToTextField2To9(true);
			try {
				txtOutput.setText(NumeralSystemConverterForStrings.txtToBinary(txtOutput.getText()));
			} catch (NumberFormatException e) {
				// nothing has to be done, txtoutput is empty
			}
		}
	}

	private void setDisabledToTextField2To9(boolean disabled) {
		calculatorInputField.changeFieldDisabled("2", disabled);
		calculatorInputField.changeFieldDisabled("3", disabled);
		calculatorInputField.changeFieldDisabled("4", disabled);
		calculatorInputField.changeFieldDisabled("5", disabled);
		calculatorInputField.changeFieldDisabled("6", disabled);
		calculatorInputField.changeFieldDisabled("7", disabled);
		calculatorInputField.changeFieldDisabled("8", disabled);
		calculatorInputField.changeFieldDisabled("9", disabled);
	}

	private void createBinaryButtonOutput() {
		char[] binaryCharArray;
		if (choice == OutputChoice.DECIMAL) {
			try {
				binaryCharArray = new StringBuilder(Integer.toBinaryString(Integer.parseInt(txtOutput.getText())))
						.reverse().toString().toCharArray();
			} catch (NumberFormatException e) {
				return;
			}
		} else {
			binaryCharArray = new StringBuilder(txtOutput.getText()).reverse().toString().toCharArray();
		}
		int index = 0;
		for (int i = binaryButtons.getChildren().size() - 1; i >= 0; i--) {
			GridPane gridPane = (GridPane) binaryButtons.getChildren().get(i);
			for (int j = gridPane.getChildren().size() - 1; j >= 0; j--) {
				VBox vBox = (VBox) gridPane.getChildren().get(j);
				Button button = (Button) (vBox.getChildren().get(0));
				try {
					button.setText(String.valueOf(binaryCharArray[index]));
				} catch (ArrayIndexOutOfBoundsException e) {
					// if the char array is empty the fields have to be 0
					button.setText("0");
				}
				index++;
			}
		}
	}

	private class BinaryButtonAction implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Button button = (Button) event.getSource();
			if (button.getText() == "1") {
				button.setText("0");
			} else {
				button.setText("1");
			}
			createOutputFromButtons();
		}

		private void createOutputFromButtons() {
			StringBuilder binarySB = new StringBuilder();
			for (int i = 0; i < binaryButtons.getChildren().size(); i++) {
				GridPane gridPane = (GridPane) binaryButtons.getChildren().get(i);
				for (int j = 0; j < gridPane.getChildren().size(); j++) {
					VBox vBox = (VBox) gridPane.getChildren().get(j);
					binarySB.append(((Button) (vBox.getChildren().get(0))).getText());
				}
			}
			if (choice == OutputChoice.BINARY) {
				txtOutput.setText(binarySB.toString());
			} else {
				txtOutput.setText(String.valueOf(Integer.parseInt(binarySB.toString(), 2)));
			}
		}
	}

}
