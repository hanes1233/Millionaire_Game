
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.media.AudioManager;
import java.awt.Color;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JList;

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
    
    // Get total score when game is over
    public int getTotalScore(JList<String> progressList, int progressIndex, int questionIndex) {
        if(questionIndex < 5) {
            return 0;
        }else if(questionIndex >= 5 && questionIndex < 10) {
            progressList.setSelectedIndex(10);
        }else if(questionIndex >= 10 && questionIndex < 14) {
            progressList.setSelectedIndex(5);
        }else {
            progressList.setSelectedIndex(0);
        }
        return getCurrentScore(progressList, questionIndex);
    }
    
    
    // Get current score during the play mode
    public int getCurrentScore(JList<String> progressList, int questionIndex) {
        String score = progressList.getSelectedValue();
        if(CurrentQuestion.getLanguage().equals("English")) {
            if(questionIndex >= 10)
                return convertToNumberScore(score, 2, score.length());
        }
        return convertToNumberScore(score, 1, score.length());
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
