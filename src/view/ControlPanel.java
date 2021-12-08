package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	private JButton restartButton, loadGameButton, saveGameButton, exitButton;
	private CheatingButtonPanel cheatingButtonPanel;

	public ControlPanel(GameFrame frame, int width, int height) {
		this.setLayout(new GridLayout(2, 3));
		this.setSize(width, height);

		restartButton = new JButton("Restart");
		// restartBtn.setSize(120, 50);
		// restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
		add(restartButton);
		restartButton.addActionListener(e -> {
			System.out.println("click restart Btn");
			frame.restart();
		});
	
		loadGameButton = new JButton("Load");
		// loadGameBtn.setSize(120, 50);
		// loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
		add(loadGameButton);
		loadGameButton.addActionListener(e -> {
		    System.out.println("clicked Load Btn");
		    String filePath = JOptionPane.showInputDialog(this, "input the path here");
		//     controller.readFileData(filePath);
		});
	
		saveGameButton = new JButton("Save");
		// saveGameBtn.setSize(120, 50);
		// saveGameBtn.setLocation(loadGameBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
		add(saveGameButton);
		saveGameButton.addActionListener(e -> {
		    System.out.println("clicked Save Btn");
		    String filePath = JOptionPane.showInputDialog(this, "input the path here");
		//     controller.writeDataToFile(filePath);
		});

		exitButton = new JButton("Exit");
		add(exitButton);
		exitButton.addActionListener(e -> {
			System.out.println("clicked Exit Btn");
			frame.dispose();
			GameFrame.controller.getStartFrame().setVisible(true);
		});
		

		cheatingButtonPanel = new CheatingButtonPanel();
        	// cheatingButtonPanel.setLocation(saveGameBtn.getX()+saveGameBtn.getWidth()+30, saveGameBtn.getY());
        	add(cheatingButtonPanel);

	}
}
