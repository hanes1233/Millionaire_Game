
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.media.AudioManager;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author pavel
 */
public class QuizService {
    
    public boolean isAnswerCorrect(String userChoice, String answer) {
        return userChoice.equals(answer);
    }
    
    public void thinkingEffect() throws 
            InterruptedException, 
            UnsupportedAudioFileException, 
            IOException, 
            LineUnavailableException {
        String difficulty = CurrentQuestion.getDifficulty();
        switch (difficulty) {
            case "Easy" -> Thread.sleep(1000);
            case "Medium" -> Thread.sleep(3000);
            case "Hard" -> {
                AudioManager.handleAudioEvent("longclick");
                Thread.sleep(7000);
            }
            default -> {
                AudioManager.handleAudioEvent("longclick");
                Thread.sleep(10000);
            }
        }
    }
    
    
}
