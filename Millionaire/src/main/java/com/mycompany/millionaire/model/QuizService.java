
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.media.AudioManager;
import java.awt.Color;
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
                AudioManager.stopAllSounds();
                AudioManager.handleAudioEvent("longclick");
                Thread.sleep(7000);
            }
            default -> {
                AudioManager.stopAllSounds();
                AudioManager.handleAudioEvent("longclick");
                Thread.sleep(10000);
            }
        }
    }
    
    public void changeColorToGreen() {
        String answer = CurrentQuestion.getAnswer();
        if(CurrentQuestion.getOptionA().getText().contains(answer)) {
            CurrentQuestion.getOptionA().setBackground(new Color(51,255,51));
        }else if(CurrentQuestion.getOptionB().getText().contains(answer)) {
            CurrentQuestion.getOptionB().setBackground(new Color(51,255,51));
        }else if(CurrentQuestion.getOptionC().getText().contains(answer)) {
            CurrentQuestion.getOptionC().setBackground(new Color(51,255,51));
        }else if(CurrentQuestion.getOptionD().getText().contains(answer)) {
            CurrentQuestion.getOptionD().setBackground(new Color(51,255,51));
        }
        CurrentQuestion.getPanel().repaint();
    } 
    
    // Get current score during the play mode
    public int getCurrentScore(int questionIndex) {
        if(questionIndex > 0 && questionIndex <= 5) {
            return 3;
        }else if(questionIndex > 5 && questionIndex <= 10) {
            return 5;
        }else if(questionIndex > 10 && questionIndex <= 13) {
            return 7;
        }else if(questionIndex > 13) {
            return 10;
        }
        return 0;
    }
    
    // Convert string score to integer
    public int convertToNumberScore(String textToConvert, int substring, int length) {
        String score = textToConvert
                    .substring(substring,length)
                    .replaceAll("[\\$s+Kč,•.]","")
                    .trim();
        return Integer.parseInt(score);
    }
}
