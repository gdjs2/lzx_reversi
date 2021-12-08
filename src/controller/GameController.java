package controller;

import javax.swing.JOptionPane;

import components.ChessGridComponent;
import view.ChessBoardPanel;
import model.ChessPiece;
import model.GameMode;
import view.*;

public class GameController {


    private ChessBoardPanel gamePanel;
    private StartFrame startFrame;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;
    private GameMode mode;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel, StartFrame startFrame) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        this.mode = GameMode.NON_CHEAT;
        this.startFrame = startFrame;
        blackScore = 2;
        whiteScore = 2;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    // public void setStatusPanel(StatusPanel statusPanel) {
    //     this.statusPanel = statusPanel;
    // }

    private boolean endCheck() {
        return blackScore == 0 
        || whiteScore == 0 
        || blackScore + whiteScore == ChessBoardPanel.CHESS_COUNT*ChessBoardPanel.CHESS_COUNT;
    }

    public void swapPlayer() {
        countScore();
        currentPlayer = currentPlayer.op();
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
        
        if (endCheck()) {
            //todo
            JOptionPane.showMessageDialog(null, "End");
        }
    }


    public void countScore() {
        blackScore = whiteScore = 0;
        int boardSize = ChessBoardPanel.CHESS_COUNT;
        ChessGridComponent[][] board = gamePanel.getChessGrids();
        for (int i = 0; i < boardSize; ++i)
            for (int j = 0; j < boardSize; ++j) {
                if (board[i][j].getChessPiece() == ChessPiece.BLACK) ++blackScore;
                else if (board[i][j].getChessPiece() == ChessPiece.WHITE) ++whiteScore;
            }
        return ;
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

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public GameMode getMode() {
        return mode;
    }

    public StartFrame getStartFrame() {
        return startFrame;
    }


    public void readFileData(String fileName) {
    }

    public void writeDataToFile(String fileName) {
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }
}
