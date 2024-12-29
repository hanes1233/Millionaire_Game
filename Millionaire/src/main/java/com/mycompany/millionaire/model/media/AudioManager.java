
package com.mycompany.millionaire.model.media;

//region imports

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
//endregion

/**
 *
 * @author pavel
 * Class for handling audio
 */
public class AudioManager {

    private AudioManager() {}
    
    private static AudioInputStream introStream;
    private static Clip soundToLoop;
    private static Clip mouseEvent; 
    private static final LinkedList<Clip> SOUND_EVENTS = new LinkedList<>();
    private static final String ERROR_MSG = "Exception was caught playing audio";
    
    /**
     * Loop sound
     * @param sound - name of sound to insert into path
     */
    public static void loopSound(String sound) {
            try {
                introStream =
                        AudioSystem.getAudioInputStream(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/audio/" + sound + ".wav")
                                .getAbsoluteFile());
                soundToLoop = AudioSystem.getClip();
                soundToLoop.open(introStream);
                soundToLoop.loop(Clip.LOOP_CONTINUOUSLY);
                soundToLoop.start();
            } catch (LineUnavailableException |
                    UnsupportedAudioFileException |
                    NullPointerException |
                    IOException e) {
                Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, ERROR_MSG, e);
            }

    }
    
    /**
     * Play sound
     * @param sound - name of sound to insert into path
     */
    public static void handleAudioEvent(String sound) {
            try {
                introStream =
                        AudioSystem.getAudioInputStream(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/audio/"+ sound +".wav")
                                .getAbsoluteFile());
                mouseEvent =  AudioSystem.getClip();
                mouseEvent.open(introStream);
                mouseEvent.start();
                SOUND_EVENTS.add(mouseEvent);
            }catch (UnsupportedOperationException |
                    IOException |
                    UnsupportedAudioFileException |
                    LineUnavailableException e) {
                Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, ERROR_MSG, e);
            }
    }
    
    /**
     * Stop playing all sound streams
     */
    public static void stopAllSounds() {
        for (Clip soundEvent : SOUND_EVENTS) {
            if (soundEvent.isActive()) {
                soundEvent.stop();
                soundToLoop.addLineListener(e -> {
                    if (e.getType().equals(LineEvent.Type.STOP)) {
                        soundToLoop.close();
                    }
                });

                mouseEvent.addLineListener(e -> {
                    if (e.getType().equals(LineEvent.Type.STOP)) {
                        mouseEvent.close();
                    }
                });
            }
            try {
                soundToLoop.close();
                mouseEvent.close();
                introStream.close();
            } catch (IOException ex) {
                Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, ERROR_MSG, ex);
            }
        }
    }
    
    /**
     * Stop currently playing mouseEvent sound
     */
    public static void muteMouseEvent() {
        mouseEvent.stop();
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
     * Check if sound event currently playing
     * @return true or false
     */
    public static boolean isMouseEventActive() {
        if(mouseEvent != null) {
            return mouseEvent.isActive();
        }
        return false;
    }
    
    /**
     * Play main theme sounds depending on provided index
     * @param index displays on what question player is now
     */
    public static void handleMainTheme(int index) {
        try {
            if(index >= 0 && index < 5) {
                loopSound("gameplay");
            }else if(index == 5) {
                handleAudioEvent("fifthquestion");
            }else if(index > 5 && index < 10) {
                handleAudioEvent("mediumquestion");
            }else if(index == 10) {
                handleAudioEvent("tenthquestion");
            }else if(index == 11) {
                handleAudioEvent("eleventhquestion");
            }else if(index == 12) {
                handleAudioEvent("twelfthquestion");
            }else if(index == 13) {
                handleAudioEvent("thirteenthquestion");
            }else if(index == 14) {
                handleAudioEvent("fourteenthquestion");
            }else if(index == 15) {
                handleAudioEvent("fifteenthquestion");
            }
        } catch (Exception e) {
            Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, ERROR_MSG, e);
        }
    }
    
    /**
     * Provides following audio effects, reacting on user's choose
     * @param correct - displays is user's choice is true
     */
    public static void soundReaction(boolean correct) {
        if(correct) {
            stopAllSounds();
            handleAudioEvent("correct");
        }else {
            stopAllSounds();
            handleAudioEvent("wrong");
        }
    }
    
}
