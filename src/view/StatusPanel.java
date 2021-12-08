package view;

import model.ChessPiece;

import javax.swing.*;

import constant.ColorCollection;

import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel playerLabel;
    private JLabel scoreLabel;
    private JLabel cheatLabel;

    public StatusPanel(int width, int height) {
        this.setSize(width, height);
        this.setLayout(new GridLayout(3, 1));
        this.setVisible(true);

        this.playerLabel = new JLabel();
        // this.playerLabel.setLocation(0, 10);
        // this.playerLabel.setSize((int) (width * 0.4), height);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        this.setPlayerText(ChessPiece.BLACK.name());
        add(playerLabel);

        this.scoreLabel = new JLabel();
        // this.scoreLabel.setLocation((int) (width * 0.4), 10);
        // this.scoreLabel.setSize((int) (width * 0.5), height);
        this.scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
        this.setScoreText(2,2);
        add(scoreLabel);

        this.cheatLabel = new JLabel();
        this.cheatLabel.setFont(new Font("Calibri", Font.BOLD, 25));
        this.cheatLabel.setForeground(ColorCollection.cheatModeNotice);
        this.cheatLabel.setText("Cheating Mode!");
        this.cheatLabel.setVisible(false);
        add(cheatLabel);
    }

    public void setScoreText(int black, int white) {
        this.scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d", black, white));
    }

    public void setPlayerText(String playerText) {
        this.playerLabel.setText(playerText + "'s turn");
    }

    public JLabel getCheatLabel() {
        return cheatLabel;
    }

    // public void setCheatLabel(JLabel cheatLabel) {
    //     this.cheatLabel = cheatLabel;
    // }

}
