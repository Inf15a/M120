package ch.lu.bbzw.tetris;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch.lu.bbzw.tetris.Tick.TickEndListener;
import ch.lu.bbzw.tetris.blocks.Block;
import ch.lu.bbzw.tetris.blocks.Block1X1;
import ch.lu.bbzw.tetris.blocks.Block3X3;
import ch.lu.bbzw.tetris.blocks.BlockL;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Move implements Runnable {
	private List<MoveEndListener> moveEndListeners = new ArrayList<>();
	private TetrisField tetrisField;
	private Block[] blocks = new Block[] { new Block1X1(), new Block3X3(), new BlockL() };

	public Move(TetrisField tetrisField) {
		this.tetrisField = tetrisField;
	}

	@Override
	public void run() {
		System.out.println("start move");

		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		Tick tick = new Tick(tetrisField);
		tick.setActualBlock(generateRandomBlock());
		tick.addTickEndListener(new TickEndListener() {

			@Override
			public void onTickEnd(List<Point2D.Double> points, Block block, boolean isLastTick) {
				tetrisField.setActiveBackground(points, block, isLastTick);
				if (isLastTick) {
					tetrisField.handleFullLines();
					fireMoveEndListeners();
					try {
						scheduler.shutdown();
						scheduler.awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException ignore) {
						// do nothing
					}
				}
			}
		});
		tetrisField.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) {
					tick.moveLeft();
				} else if (event.getCode() == KeyCode.RIGHT) {
					tick.moveRight();
				}
			}
		});

		scheduler.scheduleAtFixedRate(tick, 1, 1, TimeUnit.SECONDS);
	}

	public void addMoveEndListener(MoveEndListener endListener) {
		moveEndListeners.add(endListener);
	}

	private void fireMoveEndListeners() {
		for (MoveEndListener moveEndListener : moveEndListeners) {
			moveEndListener.onMoveEnd(tetrisField.checkIfFieldIsFinal(0, (TetrisField.amountOfColumns - 1) / 2));
		}
	}

	private Block generateRandomBlock() {
		Random randy = new Random();
		return blocks[randy.nextInt(blocks.length)];
	}

	@FunctionalInterface
	interface MoveEndListener {
		void onMoveEnd(boolean gameOver);
	}
}
