import java.applet.AudioClip;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.omg.CORBA.PUBLIC_MEMBER;

import sun.audio.*;
public class PlayMusic{
	 File file;
	 AudioInputStream audioInputStream;
	 Clip clip;
	 public void playSound(String filename) {
		    try {
		    	
		        audioInputStream= AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		        
		        Thread.sleep(1);
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	
	}
	 //±≥æ∞“Ù¿÷
	 public void playLoop(String filename) {
		    try {
		    	
		        audioInputStream= AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
		        clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.loop(Clip.LOOP_CONTINUOUSLY);//—≠ª∑≤•∑≈
		        
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	
	}
	 
	 public void stop(){
		 clip.stop();
	 }
	
}
