package utility;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundUtility {
	public static void playSE(String filePath) {
		try {
			AudioInputStream ipt = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(ipt);
			clip.start();
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
	    	}
	}
	
}
