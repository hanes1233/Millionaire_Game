
package com.mycompany.millionaire.media;

//region imports
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.NoArgsConstructor;
//endregion

/**
 *
 * @author pavel
 * Class for handling audio
 */
@NoArgsConstructor
public class AudioManager {
    
    private static AudioInputStream introStream;
    private static Clip soundToLoop;
    private static Clip mouseEvent; 
    
    /**
     * Loop sound
     * @param sound - name of sound to insert into path
     * @throws MalformedURLException
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws NullPointerException
     * @throws IOException 
     */
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
    
    /**
     * Play sound
     * @param sound - name of sound to insert into path
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException 
     */
    public static void handleAudioEvent(String sound) throws 
            UnsupportedAudioFileException, 
            IOException,  
            LineUnavailableException {
            introStream = 
                    AudioSystem.getAudioInputStream(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/audio/"+ sound +".wav").getAbsoluteFile());
            mouseEvent =  AudioSystem.getClip();
            mouseEvent.open(introStream);
            mouseEvent.start(); 
    }
    
    /**
     * Stop currently playing sound loop
     */
    public static void muteIntro() {
        soundToLoop.stop();
    }
    
    /**
     * Check if sound loop currently playing
     * @return true or false
     */
    public static boolean isIntroActive() {
        return soundToLoop.isActive();
    }
    
    /**
     * Play main theme sounds depending on provided index
     * @param index displays on what question player is now
     * @throws LineUnavailableException
     * @throws UnsupportedAudioFileException
     * @throws NullPointerException
     * @throws IOException
     * @throws MalformedURLException 
     */
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
    
    /**
     * Provides following audio effects, reacting on user's choose
     * @param correct - displays is user's choice is true
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException 
     */
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
