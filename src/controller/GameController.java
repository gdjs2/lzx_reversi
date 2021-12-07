package controller;

import components.ChessGridComponent;
import view.ChessBoardPanel;
import model.ChessPiece;
import view.*;

public class GameController {


    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;
    }

    public void swapPlayer() {
        countScore();
        currentPlayer = currentPlayer.op();
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
        gamePanel.recountAvailableGrids();
    }


    public void countScore() {
        //todo: modify the countScore method
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                ChessGridComponent chessGridComponent = new ChessGridComponent(i, j);
                if (chessGridComponent.getCol() == 1)
                    blackScore++;
                if (chessGridComponent.getCol() == -1)
                    whiteScore++;
            }
        }

    }


    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void readFileData(String fileName) {
    }

    public void writeDataToFile(String fileName) {
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }
}
