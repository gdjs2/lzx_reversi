package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import constant.FilePath;
import utility.FrameUtility;

public class StartFrame extends JFrame {

	public StartFrame(int width, int height) {
		this.setSize(width, height);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		FrameUtility.setBackground(this, FilePath.startFrameBG);
		
		JLabel title = new JLabel("LZX_Reversi");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setLocation(0, 0);
		title.setSize(this.getWidth(), this.getHeight()/3);
		this.add(title);

		int singleCellHeight = this.getHeight()*2/3 / 17;
		int widthOffset = this.getWidth()/5;
		int heightBaseline = this.getHeight()/3;

		JButton startButton = new JButton("START");
		startButton.setLocation(widthOffset, heightBaseline+singleCellHeight);
		startButton.setSize(this.getWidth()*3/5, 3*singleCellHeight);
		startButton.addActionListener(e -> {
			SwingUtilities.invokeLater(() -> {
				GameFrame mainFrame = new GameFrame(800, this);
				mainFrame.setVisible(true);
				this.setVisible(false);
			});
		});
		this.add(startButton);

		JButton loadButton = new JButton("LOAD");
		loadButton.setLocation(widthOffset, heightBaseline+5*singleCellHeight);
		loadButton.setSize(this.getWidth()*3/5, 3*singleCellHeight);
		this.add(loadButton);

		JButton exitButton = new JButton("EXIT");
		exitButton.setLocation(widthOffset, heightBaseline+9*singleCellHeight);
		exitButton.setSize(this.getWidth()*3/5, 3*singleCellHeight);
		this.add(exitButton);

		JLabel copyrightLebel = new JLabel("Copyright. zx ZHANG");
		copyrightLebel.setHorizontalAlignment(SwingConstants.CENTER);
		copyrightLebel.setLocation(widthOffset, heightBaseline+13*singleCellHeight);
		copyrightLebel.setSize(this.getWidth()*3/5, 3*singleCellHeight);
		this.add(copyrightLebel);
	}
}
