import view.StartFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // GameFrame mainFrame = new GameFrame(800);
            // mainFrame.setVisible(true);
            StartFrame startFrame = new StartFrame(300, 600);
            startFrame.setVisible(true);
        });
    }
}
