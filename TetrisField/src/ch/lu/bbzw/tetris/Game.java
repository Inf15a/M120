package ch.lu.bbzw.tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch.lu.bbzw.tetris.Move.MoveEndListener;

public class Game {

  private TetrisField tetrisField;
  private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
  private List<GameOverListener> gameOverListeners = new ArrayList<>();

  public Game(TetrisField tetrisField) {
    this.tetrisField = tetrisField;
  }

  public void addGameOverListener(GameOverListener gameOverListener) {
    gameOverListeners.add(gameOverListener);
  }

  private void fireGameOverListeners() {
    for (GameOverListener gameOverListener : gameOverListeners) {
      gameOverListener.onGameOver();
    }
  }

  public void start() {
    System.out.println("start game");
    Move move = new Move(tetrisField);
    move.addMoveEndListener(new MoveEndListener() {

      @Override
      public void onMoveEnd(boolean gameOver) {
        if (gameOver) {
          fireGameOverListeners();
        } else {
          scheduler.schedule(move, 0, TimeUnit.SECONDS);
        }
      }
    });
    scheduler.schedule(move, 0, TimeUnit.SECONDS);
  }

  @FunctionalInterface
  public interface GameOverListener {
    void onGameOver();
  }
}
