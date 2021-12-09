package components;

import model.*;
import view.GameFrame;

import java.awt.*;

import constant.ColorCollection;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;

    private ChessPiece chessPiece;
    private int row;
    private int col;
    private boolean canClick;
    private boolean swaped;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;
    }

    @Override
    public void onMouseClicked() {
        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
        //todo: complete mouse click method
        if (GameFrame.controller.canClick(row, col)) {
            if (this.chessPiece == null) {                
                this.chessPiece = GameFrame.controller.getCurrentPlayer();
                GameFrame.controller.getGamePanel().reverseBoard(row, col);
                if (GameFrame.controller.getMode() == GameMode.NON_CHEAT)
                    GameFrame.controller.swapPlayer();
                GameFrame.controller.getGamePanel().recountAvailableGrids();
            }
            repaint();
        }
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public boolean isSwaped() {
        return swaped;
    }

    public void setSwaped(boolean swaped) {
        this.swaped = swaped;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public void drawPiece(Graphics g) {
        if (this.canClick) g.setColor(ColorCollection.gridColorAct);
        else g.setColor(ColorCollection.gridColorInact);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
            // g.fillArc((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize, 45, 135);
        }
        
    }

    public void reversePiece() {
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.setColor(chessPiece.getColor());
        int baseLine = (int)(Math.random()*270);
        for (int i = 180; i < 361; ++i) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.fillArc((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize, baseLine, i);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
    }


}
