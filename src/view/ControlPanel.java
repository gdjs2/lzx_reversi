package view;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.GameController;
import model.Archive;

public class ControlPanel extends JPanel {
	private JButton restartButton, loadGameButton, saveGameButton, undoButton, exitButton;
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
			String arcJson;
			try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
				StringBuilder builder = new StringBuilder();
				List<String> lines = reader.lines().toList();
				for (String l : lines) builder.append(l+"\n");
				arcJson = builder.toString();
				Gson gson = new Gson();
				Archive arc = gson.fromJson(arcJson, Archive.class);
				frame.restart();
				arc.load(GameFrame.controller);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
	
		saveGameButton = new JButton("Save");
		// saveGameBtn.setSize(120, 50);
		// saveGameBtn.setLocation(loadGameBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
		add(saveGameButton);
		saveGameButton.addActionListener(e -> {
			System.out.println("clicked Save Btn");
			String filePath = JOptionPane.showInputDialog(this, "input the path here");
			
			Archive arc = new Archive(GameFrame.controller);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String arcJson = gson.toJson(arc);
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
				writer.write(arcJson);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});

		undoButton = new JButton("Undo");
		add(undoButton);
		undoButton.addActionListener(e -> {
			System.out.println("Undo!");
			GameFrame.controller.getGamePanel().undo();
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
