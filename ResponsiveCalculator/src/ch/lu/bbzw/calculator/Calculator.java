package ch.lu.bbzw.calculator;

import java.io.IOException;

import ch.lu.bbzw.calculator.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Calculator extends Application {

  private Stage primaryStage;
  private BorderPane rootLayout;

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.getIcons().add(new Image("calculator.png"));
    initRootLayout();
    showDefaultCalc();
  }

  public void showBinaryCalc() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(this.getClass().getResource("view/BinaryCalculator.fxml"));
      SplitPane splitPane = (SplitPane) loader.load();

      rootLayout.setCenter(splitPane);

    } catch (IOException e) {
      e.printStackTrace();
      // while runtime there have to be the right file
    }

  }

  public void showDefaultCalc() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(this.getClass().getResource("view/DefaultCalculator.fxml"));
      SplitPane splitPane = (SplitPane) loader.load();

      rootLayout.setCenter(splitPane);

    } catch (IOException e) {
      e.printStackTrace();
      // while runtime there have to be the right file
    }
  }

  private void initRootLayout() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(this.getClass().getResource("view/RootLayout.fxml"));
      rootLayout = (BorderPane) loader.load();

      RootLayoutController controller = (RootLayoutController) loader.getController();
      controller.setMainApp(this);

      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      // while runtime there have to be the right file
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
