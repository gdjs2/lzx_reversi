package view;


import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    private ControlPanel controlPanel;
    private StartFrame startFrame;

    private void initBasicComponent() {
        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 8, (this.getHeight() - chessBoardPanel.getHeight()) / 2);

        statusPanel = new StatusPanel((int) (this.getWidth() * 0.4 - (this.getWidth() - chessBoardPanel.getWidth()) / 8), (int) (chessBoardPanel.getHeight() * 0.5));
        statusPanel.setLocation((int)(0.6 * this.getWidth()), (this.getHeight() - chessBoardPanel.getHeight()) / 2);

        controller = new GameController(chessBoardPanel, statusPanel, startFrame);
        controller.setGamePanel(chessBoardPanel);

        controlPanel = new ControlPanel(this, (int) (this.getWidth() * 0.4 - (this.getWidth() - chessBoardPanel.getWidth()) / 8), (int) (chessBoardPanel.getHeight() * 0.5));
        controlPanel.setLocation((int)(0.6 * this.getWidth()), (int)(this.getHeight()/2));

        chessBoardPanel.recountAvailableGrids();

        this.add(chessBoardPanel);
        this.add(statusPanel);
        this.add(controlPanel);
    }

    public void restart() {
        this.remove(chessBoardPanel);
		this.remove(statusPanel);
	
		initBasicComponent();
	
		this.repaint();
    }

    public GameFrame(int frameSize, StartFrame startFrame) {

        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize((int)(frameSize*1.5) + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.startFrame = startFrame;
        this.setLocationRelativeTo(null);

        initBasicComponent();

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
