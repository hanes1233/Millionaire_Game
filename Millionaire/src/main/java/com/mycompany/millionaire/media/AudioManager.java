
package com.mycompany.millionaire.media;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author pavel
 */
public class AudioManager {
    
    private static AudioInputStream introStream;
    private static Clip soundToLoop;
    private static Clip mouseEvent;
    
    
    public AudioManager() {}
    
    
    public static void loopSound(String sound) throws 
            MalformedURLException, 
            LineUnavailableException, 
            UnsupportedAudioFileException, 
            NullPointerException,
            IOException {
            introStream = 
                    AudioSystem.getAudioInputStream(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/audio/" + sound + ".wav").getAbsoluteFile()); 
            soundToLoop = AudioSystem.getClip();
            soundToLoop.open(introStream);
            soundToLoop.start();
            soundToLoop.loop(Clip.LOOP_CONTINUOUSLY); 
    }
    
    
    public static void handleAudioEvent(String audioName) throws 
            UnsupportedAudioFileException, 
            IOException, 
            IOException, 
            LineUnavailableException {
            introStream = 
                    AudioSystem.getAudioInputStream(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/audio/"+ audioName +".wav").getAbsoluteFile());
            mouseEvent =  AudioSystem.getClip();
            mouseEvent.open(introStream);
            mouseEvent.start(); 
    }
    
    
    public static void muteIntro() {
        soundToLoop.stop();
    }
    
    
    public static boolean isIntroActive() {
        return soundToLoop.isActive();
    }
    
    public static void handleMainTheme(int index) throws 
            LineUnavailableException, 
            UnsupportedAudioFileException, 
            NullPointerException,
            IOException,
            MalformedURLException {
        if(index >= 0 && index < 5) {
            loopSound("gameplay");
        }else if(index == 5) {
            loopSound("fifthquestion");
        }else if(index > 5 && index < 10) {
            loopSound("mediumquestion");
        }else if(index == 10) {
            loopSound("tenthquestion");
        }else if(index == 11) {
            loopSound("eleventhquestion");
        }else if(index == 12) {
            loopSound("twelfthquestion");
        }else if(index == 13) {
            loopSound("thirteenthquestion");
        }else if(index == 14) {
            loopSound("fourteenthquestion");
        }else if(index == 15) {
            loopSound("fifteenthquestion");
        }else {
        
        }
    }
    
    public static void soundReaction(boolean correct) throws 
            UnsupportedAudioFileException, 
            IOException, 
            LineUnavailableException {
        if(correct) {
            handleAudioEvent("correct");
        }else {
            handleAudioEvent("wrong");
        }
    }
    
}
