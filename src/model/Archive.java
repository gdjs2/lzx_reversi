package model;

import javax.swing.JOptionPane;

import controller.GameController;
import view.ChessBoardPanel;

public class Archive {
	private boolean isFinished;
	private ChessPiece winnerOrPlayer;
	private Step[] steps;
	private ChessPiece[][] board;

	public Archive(GameController controller) {
		isFinished = controller.endCheck() != GameStatus.NONE;
		if (isFinished) winnerOrPlayer = controller.endCheck().getWinner();
		else winnerOrPlayer = controller.getCurrentPlayer();

		steps = controller.getGamePanel().getSteps();
		board = controller.getGamePanel().getBoard();
	}

	public void load(GameController controller) {
		for (Step step : steps) {
			if (!controller.putPiece(step)) {
				Position p = step.getPutPosition();
				JOptionPane.showMessageDialog(null, String.format("Invalid step: [%d, %d]!", p.x, p.y));
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		if (isFinished != (controller.endCheck() != GameStatus.NONE)) {
			JOptionPane.showMessageDialog(null, "Finish status does not match!");
			return ;
		}
		if (isFinished && winnerOrPlayer != controller.endCheck().getWinner()) {
			JOptionPane.showMessageDialog(null, "Winner does not match!");
			return ;
		}
		if (!isFinished && winnerOrPlayer != controller.getCurrentPlayer()) {
			JOptionPane.showMessageDialog(null, "Player does not match!");
			return ;
		}
		if (!controller.getGamePanel().equal(board)) {
			JOptionPane.showMessageDialog(null, "Final board does not match!");
		}
	}
}
