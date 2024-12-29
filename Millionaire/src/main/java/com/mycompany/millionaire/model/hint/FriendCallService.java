
package com.mycompany.millionaire.model.hint;

import com.mycompany.millionaire.controller.hint.FriendCall;
import com.mycompany.millionaire.data.entity.Question;
import com.mycompany.millionaire.model.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service for Friend Call controller
 * @author pavel
 */
public class FriendCallService extends FriendCall {

    // Constructor
    public FriendCallService(Question currentQuestion) {
        super(currentQuestion);
    }
    
    /**
     * Method imitate call to friend.
     * User given of 30 seconds to perform virtual call to his friend an get answer.
     * Friend answer correct just for easy and medium question level, otherwise
     * it just generate random answer, which can be false.
     * First, it displays list of people user can call and timer of 30 seconds,
     * which starts immediately.
     * After choosing 'friend' and double-click, 
     * displays message 'calling...' with corresponding ringing sound.
     * Duration of 'calling' depends on random value, which will be less then 
     * standard timer time (30 seconds), but as timer runs immediately, and if 
     * user going to choose friend too long, he can just do not get answer from 
     * hist friend, as all call including process of getting number must be performed 
     * in just 30 seconds.
     */
    public void callFriend() {
        this.friendList.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent evt) {
                 
                 // Double click needed to countinue
                if (evt.getClickCount() == 2) {
                    
                    // Get answer for corresponding language
                    if(language.equals("English")) {
                        randomAnswer = friendAnswer.getRandomEngAnswer();
                    }else {
                        randomAnswer = friendAnswer.getRandomEngAnswer();
                    }
                    
                    // Answers do not contain chars A - D, so if difficulty 'easy' or 'medium'
                    // concat corresponding letter to answer
                    if(currentQuestion.getQuestionDifficulty().equals("Easy") || currentQuestion.getQuestionDifficulty().equals("Medium")) {
                        String currentAnswer = currentQuestion.getAnswer();
                        if(currentQuestion.getOptionA().getText().contains(currentAnswer)) {
                            randomAnswer += 'A';
                        }else if(currentQuestion.getOptionB().getText().contains(currentAnswer)) {
                            randomAnswer += 'B';
                        }else if(currentQuestion.getOptionC().getText().contains(currentAnswer)) {
                            randomAnswer += 'C';
                        }else if(currentQuestion.getOptionD().getText().contains(currentAnswer)) {
                            randomAnswer += 'D';
                        }
                        // Otherwise, concat random letter
                    }else {
                        char[] letters = {'A','B','C','D'};
                        int randomLetter = new Random().nextInt(letters.length - 0);
                        randomAnswer += letters[randomLetter];
                    }

                    calling = new JTextArea();
                    calling = new TextAreaBuilderImpl()
                        .formatText()
                        .text("Calling...")
                        .font(new Font("Serif", Font.ITALIC, 20))
                        .background(new Color(0, 38, 75))
                        .foreground(Color.WHITE)
                        .preferredSize(new Dimension(250, 45))
                        .bounds(30, 90)
                        .readOnly()
                        .build();
                    
                    GameView.getPanel().remove(friendList);
                    GameView.getPanel().add(calling);
                    service.reloadPanel();
                    AudioManager.handleAudioEvent("calling");
                    new Thread(() -> {
                           
                        // Define random calling time in seconds
                        int randomCallingTime = new Random().nextInt((25 - 0) + 3) + 3;
                        try {
                                
                            // Sleep for random amount of seconds just displaying
                            // 'calling' and playing 'ringing sound'
                            Thread.sleep(randomCallingTime * 1000);
                                
                            // Turn off all sounds
                            AudioManager.muteMouseEvent();
                                
                            // If timer already has '0' value, just remove
                            // calling label with no displaying answer
                            if(timer.getText().equals("0")) {
                                GameView.getPanel().remove(calling);
                                service.reloadPanel();
                                    
                                // Otherwise, display friend's answer
                            }else {
                                calling = new TextAreaBuilderImpl(calling)
                                        .text(randomAnswer)
                                        .font(new Font("Serif", Font.ITALIC, 16))
                                        .preferredSize(new Dimension(250, 75))
                                        .bounds(30, 90)
                                        .foreground(Color.ORANGE)
                                        .build();
                                GameView.getPanel().repaint();
                                }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FriendCall.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }).start();
                } 
            }
         });
        
        // Revalidate and repaint panel
        service.reloadPanel();
    }
}
