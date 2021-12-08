package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.GameMode;

public class CheatingButtonPanel extends JPanel {
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton 	nonCheat = new JRadioButton("No Cheat"), 
			cheat = new JRadioButton("Cheat Mode");
			// whiteCheat = new JRadioButton("Cheat in White"), 
			// blackCheat = new JRadioButton("Cheat in Black");
	
	public CheatingButtonPanel() {
		super();
		this.setLayout(new GridLayout(3, 1));

		buttonGroup.add(nonCheat);
		buttonGroup.add(cheat);
		// buttonGroup.add(whiteCheat);
		// buttonGroup.add(blackCheat);

		ActionListener l = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton button = (JRadioButton)(e.getSource());
				if (button == nonCheat) {
					GameFrame.controller.setMode(GameMode.NON_CHEAT);
					GameFrame.controller.getStatusPanel().getCheatLabel().setVisible(false);
				}
				// else if (button == whiteCheat) GameFrame.controller.setMode(GameMode.WHITE_CHEAT);
				// else if (button == blackCheat) GameFrame.controller.setMode(GameMode.BLACK_CHEAT);
				else if (button == cheat) {
					GameFrame.controller.setMode(GameMode.CHEAT);
					GameFrame.controller.getStatusPanel().getCheatLabel().setVisible(true);
				}

				GameFrame.controller.getGamePanel().recountAvailableGrids();
				GameFrame.controller.getGamePanel().repaint();

				System.out.printf("GameMode changed to %s\n", GameFrame.controller.getMode());
			}
		};

		nonCheat.addActionListener(l);
		cheat.addActionListener(l);
		// whiteCheat.addActionListener(l);
		// blackCheat.addActionListener(l);

		this.add(nonCheat);
		this.add(cheat);
		// this.add(whiteCheat);
		// this.add(blackCheat);

		this.setSize(150, 50);
		this.setVisible(true);

		nonCheat.setSelected(true);
	}
}
