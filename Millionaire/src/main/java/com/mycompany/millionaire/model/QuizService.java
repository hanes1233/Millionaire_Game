
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.controller.Game;
import com.mycompany.millionaire.controller.GameOver;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.data.GameConfiguration;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.view.GameView;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import lombok.Data;

/**
 * Class provides service methods to quiz
 * @author pavel
 */
@Data
public class QuizService {
    
    private Question pollQuestion;
    private final ComponentServiceImpl COMPONENT_SERVICE;
    private final JPanel PANEL;
    
    // Constructor
    public QuizService() {
        this.PANEL = GameView.getPanel();
        this.COMPONENT_SERVICE = new ComponentServiceImpl();
    }
    
    /**
     * Method checks is userChoice equals to answer
     * @param userChoice
     * @param answer
     * @return true or false
     */
    public boolean isAnswerCorrect(String userChoice, String answer) {
        return userChoice.equals(answer);
    } 
    
    /**
     * Method to arrange thinking effect
     * @param difficulty parameter specified 'thinking' duration
     * @throws InterruptedException
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException 
     */
    private void thinkingEffect(String difficulty) throws 
            InterruptedException, 
            UnsupportedAudioFileException, 
            IOException, 
            LineUnavailableException {
        // Set duration of 'thinking' based on input's 'difficulty' parameter
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
    
    /**
     * Change correct answer button's color to green
     * @param currentQuestion - to get needed information, such as options and answer
     */
    private void changeColorToGreen(Question currentQuestion) {
        String answer = currentQuestion.getAnswer();
        if(currentQuestion.getOptionA().getText().contains(answer)) {
            currentQuestion.getOptionA().setBackground(new Color(51,255,51));
        }else if(currentQuestion.getOptionB().getText().contains(answer)) {
            currentQuestion.getOptionB().setBackground(new Color(51,255,51));
        }else if(currentQuestion.getOptionC().getText().contains(answer)) {
            currentQuestion.getOptionC().setBackground(new Color(51,255,51));
        }else if(currentQuestion.getOptionD().getText().contains(answer)) {
            currentQuestion.getOptionD().setBackground(new Color(51,255,51));
        }
        
        // Revalidate and repaint panel
        this.COMPONENT_SERVICE.reloadPanel();
    } 
    
    /**
     * Get current score based on provided question index
     * @param questionIndex
     * @return score we want to add to total score
     */
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
    
    /**
     * Helper method to perform multiple functions
     * @param difficulty - needed to run thinkingEffect() method
     * @param correctAnswer - needed to perform 'soundReaction()'
     * @param currentQuestion  - needed to 'changeColorToGreen()'
     */
    public void handleUserChoice(String difficulty, boolean correctAnswer, Question currentQuestion) {
        try {
             this.thinkingEffect(difficulty);
             AudioManager.soundReaction(correctAnswer);
             this.changeColorToGreen(currentQuestion);
        } catch (InterruptedException | 
                 UnsupportedAudioFileException | 
                 IOException | 
                 LineUnavailableException ex) {
                     Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }
    
    /**
     * Finish game method creates GameOver instance with provided parameters
     * @param score - needed to save user
     * @param winner - needed to save user
     */
    public void finishGame(int score, boolean winner) {
        try {
             new GameOver(score, winner).run();
        } catch (IOException |
                 LineUnavailableException |
                 UnsupportedAudioFileException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                 }
    }
    
    /**
     * Method to handle current score
     * @param gameConfig - needed to get 'score'
     * @param questionIndex  - needed to calculate current score
     */
    public void handleScore(GameConfiguration gameConfig, int questionIndex) {
        PANEL.removeAll();
        COMPONENT_SERVICE.reloadPanel();
        int score = gameConfig.getScore();
        score += this.getCurrentScore(questionIndex);
        gameConfig.setScore(score);
    }
}
