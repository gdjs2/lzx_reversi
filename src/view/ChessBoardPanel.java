package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;

import components.ChessGridComponent;
import constant.FilePath;
import model.ChessPiece;
import model.GameMode;
import model.Position;
import model.Step;
import utility.SoundUtility;

public class ChessBoardPanel extends JPanel {
    public static final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;
    private Stack<Step> stepStack;

    public ChessGridComponent[][] getChessGrids() {
        return chessGrids;
    }

    public boolean equal(ChessPiece[][] board) {
        for (int i = 0; i < CHESS_COUNT; ++i)
            for (int j = 0; j < CHESS_COUNT; ++j)
                if (board[i][j] != chessGrids[i][j].getChessPiece())
                    return false;
        return true;
    }

    public Step[] getSteps() {
        Step[] steps = new Step[stepStack.size()];
        for (int i = 0; i < stepStack.size(); ++i)
            steps[i] = stepStack.get(i);
        return steps;
    }

    public ChessPiece[][] getBoard() {
        ChessPiece[][] board = new ChessPiece[CHESS_COUNT][CHESS_COUNT];
        for (int i = 0; i < CHESS_COUNT; ++i)
            for (int j = 0; j < CHESS_COUNT; ++j)
                board[i][j] = chessGrids[i][j].getChessPiece();
        return board;
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
            while (isLocationInRange(nextX, nextY)) {
                ChessPiece nextChessPiece = chessGrids[nextX][nextY].getChessPiece();
                if (nextChessPiece == currentPlayer.op()) flag = true;
                else break;
                nextX += dir[i][0]; nextY += dir[i][1];
            }
            if (flag && isLocationInRange(nextX, nextY) && chessGrids[nextX][nextY].getChessPiece() != null)
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
        stepStack = new Stack<>();
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
        && (GameFrame.controller.getMode() != GameMode.NON_CHEAT || getAvailableDir(row, col, currentPlayer).size() != 0);
    }

    public void reverseBoard(int row, int col) {
        Step step = new Step(new Position(row, col), GameFrame.controller.getMode());

        ChessPiece currentPlayer = chessGrids[row][col].getChessPiece();
        List<Integer> availableDir = getAvailableDir(row, col, currentPlayer);

        for (int d : availableDir) {
            int nextX = row+dir[d][0], nextY = col+dir[d][1];
            while (isLocationInRange(nextX, nextY) 
                && chessGrids[nextX][nextY].getChessPiece()==currentPlayer.op()) {
                    
                    SoundUtility.playSE(FilePath.reverseSE2);

                    // chessGrids[nextX][nextY].setChessPiece(currentPlayer);
                    chessGrids[nextX][nextY].reversePiece();
                    step.addFlip(new Position(nextX, nextY));

                    nextX += dir[d][0];
                    nextY += dir[d][1];
            }
        }

        stepStack.push(step);
        repaint();
    }

    public void recountAvailableGrids() {
        boolean flag = false;
        for (int i = 0; i < CHESS_COUNT; ++i)
            for (int j = 0; j < CHESS_COUNT; ++j) {
                if (this.canClickGrid(i, j, GameFrame.controller.getCurrentPlayer())) {
                    chessGrids[i][j].setCanClick(true);
                    flag = true;
                } else chessGrids[i][j].setCanClick(false);
            }
        if (!flag) {
            GameFrame.controller.swapPlayer();
        }
        return ;
    }

    public void undo() {
        if (stepStack.empty()) return ;

        Step step = stepStack.pop();
        Position p = step.getPutPosition();
        chessGrids[p.x][p.y].setChessPiece(null);
        paintImmediately(0, 0, this.getWidth(), this.getHeight());
        Iterator<Position> flipP = step.getFlipPosition().iterator();

        while (flipP.hasNext()) {
            p = flipP.next();
            chessGrids[p.x][p.y].reversePiece();
        }
        if (step.getMode() != GameMode.CHEAT) {
            GameFrame.controller.swapPlayer();
        }
        GameFrame.controller.getGamePanel().recountAvailableGrids();

        repaint();
    }
}
