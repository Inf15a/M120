package ch.lu.bbzw.calculator.view;

import ch.lu.bbzw.calculator.utils.MathChoices;
import ch.lu.bbzw.calculator.view.inputfield.CalculatorInputField;
import ch.lu.bbzw.calculator.view.operationsfield.CalculatorOperationsField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class DefaultCalculatorController {
	@FXML
	protected TextField txtOutput;
	@FXML
	protected CalculatorInputField calculatorInputField;
	@FXML
	protected CalculatorOperationsField calculatorOperationsField;
	protected double lastInput = 0;
	protected MathChoices lastChoice;

	@FXML
	public void initialize() {
		txtOutput.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				KeyCombination keyCombinationPlus = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.SHIFT_DOWN);
				KeyCombination keyCombinationStar = new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.SHIFT_DOWN);
				KeyCombination keyCombinationSlash = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.SHIFT_DOWN);

				if (event.getCode() == KeyCode.PLUS || keyCombinationPlus.match(event)) {
					handleAddition();
				} else if (event.getCode() == KeyCode.SLASH || keyCombinationSlash.match(event)) {
					handleDivide();
				} else if (event.getCode() == KeyCode.STAR || keyCombinationStar.match(event)) {
					handleMultiplication();
				} else if (event.getCode() == KeyCode.MINUS) {
					handleSubtraction();
				} else if (event.getCode() == KeyCode.ENTER) {
					handleEqual();
				}
			}
		});

		txtOutputTextPropertyChangeListener();
		calculatorInputField.addButtonActionListener("0", this::handle0);
		calculatorInputField.addButtonActionListener("1", () -> txtOutput.setText(txtOutput.getText() + "1"));
		calculatorInputField.addButtonActionListener("2", () -> txtOutput.setText(txtOutput.getText() + "2"));
		calculatorInputField.addButtonActionListener("3", () -> txtOutput.setText(txtOutput.getText() + "3"));
		calculatorInputField.addButtonActionListener("4", () -> txtOutput.setText(txtOutput.getText() + "4"));
		calculatorInputField.addButtonActionListener("5", () -> txtOutput.setText(txtOutput.getText() + "5"));
		calculatorInputField.addButtonActionListener("6", () -> txtOutput.setText(txtOutput.getText() + "6"));
		calculatorInputField.addButtonActionListener("7", () -> txtOutput.setText(txtOutput.getText() + "7"));
		calculatorInputField.addButtonActionListener("8", () -> txtOutput.setText(txtOutput.getText() + "8"));
		calculatorInputField.addButtonActionListener("9", () -> txtOutput.setText(txtOutput.getText() + "9"));
		calculatorInputField.addButtonActionListener(".", this::handlePoint);
		calculatorInputField.addButtonActionListener("C", this::handleDeleteLastToken);

		calculatorOperationsField.addButtonActionListener("+", this::handleAddition);
		calculatorOperationsField.addButtonActionListener("-", this::handleSubtraction);
		calculatorOperationsField.addButtonActionListener("X", this::handleMultiplication);
		calculatorOperationsField.addButtonActionListener("/", this::handleDivide);
		calculatorOperationsField.addButtonActionListener("CE", this::handleClearAll);
		calculatorOperationsField.addButtonActionListener("=", this::handleEqual);

		init();
	}

		
	protected void txtOutputTextPropertyChangeListener() {
		txtOutput.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("[0-9]{0,}\\.{0,1}[0-9]{0,}")) {
					txtOutput.setText(oldValue);
				}
			}
		});
	}

	protected void init() {
		// can be overwritten by subclasses always gets done as constructor
	}

	protected void handle0() {
		if (txtOutput.getText().length() > 0) {
			txtOutput.setText(txtOutput.getText() + "0");
		}
	}

	protected void handlePoint() {
		if (!txtOutput.getText().contains(".")) {
			txtOutput.setText(txtOutput.getText() + ".");
		}
	}

	protected void handleDeleteLastToken() {
		try {
			txtOutput.setText(txtOutput.getText().substring(0, txtOutput.getText().length() - 1));
		} catch (StringIndexOutOfBoundsException e) {
			// txtOutput is empty and c gets pressed, nothing has to be done
		}
	}

	protected void handleAddition() {
		lastChoice = MathChoices.PLUS;
		storeLastInputAndEmptyTxtOutput();

	}

	protected void handleSubtraction() {
		lastChoice = MathChoices.MINUS;
		storeLastInputAndEmptyTxtOutput();
	}

	protected void handleMultiplication() {
		lastChoice = MathChoices.MULTIPLY;
		storeLastInputAndEmptyTxtOutput();
	}

	protected void handleDivide() {
		lastChoice = MathChoices.DIVIDE;
		storeLastInputAndEmptyTxtOutput();
	}

	protected void handleClearAll() {
		txtOutput.setText("");
		lastInput = 0;
		lastChoice = null;
	}

	protected void handleEqual() {
		try {
			switch (lastChoice) {
			case PLUS:
				double summand1 = lastInput;
				double summand2 = Double.parseDouble(txtOutput.getText());
				txtOutput.setText(String.valueOf(summand1 + summand2));
				break;
			case MINUS:
				double minuend = lastInput;
				double subtrahend = Double.parseDouble(txtOutput.getText());
				txtOutput.setText(String.valueOf(minuend - subtrahend));
				break;
			case MULTIPLY:
				double factor1 = lastInput;
				double factor2 = Double.parseDouble(txtOutput.getText());
				txtOutput.setText(String.valueOf(factor1 * factor2));
				break;
			case DIVIDE:
				double dividend = lastInput;
				double divisor = Double.parseDouble(txtOutput.getText());
				txtOutput.setText(String.valueOf(dividend / divisor));
				break;
			default:
				break;
			}
		} catch (NumberFormatException | NullPointerException e) {
		}
		txtOutput.requestFocus();
	}

	protected void storeLastInputAndEmptyTxtOutput() {
		try {
			lastInput = Double.parseDouble(txtOutput.getText());
		} catch (NumberFormatException e) {
			// string is already empty
		}
		txtOutput.setText("");
		txtOutput.requestFocus();
	}
}
