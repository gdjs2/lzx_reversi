package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import components.ChessGridComponent;
import model.ChessPiece;

public class ChessBoardPanel extends JPanel {
    public static final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;

    public ChessGridComponent[][] getChessGrids() {
        return chessGrids;
    }

    private static final int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    private boolean isLocationInRange(int row, int col) {
        return 0 <= row && row < CHESS_COUNT && 0 <= col && col < CHESS_COUNT;
    }

    private List<Integer> getAvailableDir(int row, int col, ChessPiece currentPlayer) {
        ArrayList<Integer> availableDir = new ArrayList<>();

        for (int i = 0; i < 8; ++i) {
            boolean flag = false;
            int nextX = row + dir[i][0], nextY = col + dir[i][1];
            if (!isLocationInRange(nextX, nextY)) continue;
            ChessPiece nextChessPiece = chessGrids[nextX][nextY].getChessPiece();
            while (isLocationInRange(nextX, nextY)
                    && nextChessPiece != null 
                    && nextChessPiece == currentPlayer.op()) {
                nextX += dir[i][0];
                nextY += dir[i][1];
                nextChessPiece = chessGrids[nextX][nextY].getChessPiece();
                flag = true;
            }
            if (flag && isLocationInRange(nextX, nextY) && nextChessPiece != null)
                availableDir.add(i);
        }
        
        return availableDir;
    }

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        repaint();
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }


    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        return chessGrids[row][col].getChessPiece() == null 
                && getAvailableDir(row, col, currentPlayer).size() != 0;
    }

    public void reverseBoard(int row, int col) {
        ChessPiece currentPlayer = chessGrids[row][col].getChessPiece();
        List<Integer> availableDir = getAvailableDir(row, col, currentPlayer);
        for (int d : availableDir) {
            int nextX = row+dir[d][0], nextY = col+dir[d][1];
            while (isLocationInRange(nextX, nextY) 
                && chessGrids[nextX][nextY].getChessPiece()==currentPlayer.op()) {
                    chessGrids[nextX][nextY].setChessPiece(currentPlayer);
                    nextX += dir[d][0];
                    nextY += dir[d][1];
            }
        }
        repaint();
    }

    public void recountAvailableGrids() {
        for (int i = 0; i < CHESS_COUNT; ++i)
            for (int j = 0; j < CHESS_COUNT; ++j) {
                if (this.canClickGrid(i, j, GameFrame.controller.getCurrentPlayer()))
                    chessGrids[i][j].setCanClick(true);
                else chessGrids[i][j].setCanClick(false);
            }
        return ;
    }
}
