
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.media.AudioManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;

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
}
