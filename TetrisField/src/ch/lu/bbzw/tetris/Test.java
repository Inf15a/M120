package ch.lu.bbzw.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

  @Override
  public void start(Stage primaryStage) {
    TetrisField tf = new TetrisField(8, 10);
    primaryStage.setScene(new Scene(tf));
    primaryStage.show();
    tf.start();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
