package utility;

import javax.swing.JFrame;

import view.ImagePanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FrameUtility {
	public static void setBackground(JFrame frame, String fileName) {
		try {
			BufferedImage myImage = ImageIO.read(new File("./resources/bg/start_frame_bg.png"));
			frame.setContentPane(new ImagePanel(myImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
