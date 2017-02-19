package ch.lu.bbzw.calculator;

import java.io.IOException;

import ch.lu.bbzw.calculator.view.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private SplitPane calculator;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.getIcons().add(new Image("calculator.png"));
		initCalculator();
	}

	private void initCalculator() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("view/Calculator.fxml"));
			calculator = (SplitPane) loader.load();

			CalculatorController controller = (CalculatorController) loader.getController();
			controller.init();

			Scene scene = new Scene(calculator);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
