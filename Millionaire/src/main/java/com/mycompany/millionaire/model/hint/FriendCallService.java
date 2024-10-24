
package com.mycompany.millionaire.model.hint;

import com.mycompany.millionaire.controller.hint.FriendCall;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.model.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.view.GameView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JTextArea;

/**
 *
 * @author pavel
 */
public class FriendCallService extends FriendCall {

    public FriendCallService(Question currentQuestion) {
        super(currentQuestion);
    }
    
    public void callFriend() {
        this.friendList.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    if(language.equals("English")) {
                        randomAnswer = friendAnswer.getRandomEngAnswer();
                    }else {
                        randomAnswer = friendAnswer.getRandomEngAnswer();
                    }
                    
                    if(CURRENT_QUESTION.getQuestionDifficulty().equals("Easy") || CURRENT_QUESTION.getQuestionDifficulty().equals("Medium")) {
                        String currentAnswer = CURRENT_QUESTION.getAnswer();
                        if(CURRENT_QUESTION.getOptionA().getText().contains(currentAnswer)) {
                            randomAnswer += 'A';
                        }else if(CURRENT_QUESTION.getOptionB().getText().contains(currentAnswer)) {
                            randomAnswer += 'B';
                        }else if(CURRENT_QUESTION.getOptionC().getText().contains(currentAnswer)) {
                            randomAnswer += 'C';
                        }else if(CURRENT_QUESTION.getOptionD().getText().contains(currentAnswer)) {
                            randomAnswer += 'D';
                        }
                    }else {
                        char[] letters = {'A','B','C','D'};
                        int randomLetter = new Random().nextInt(letters.length - 0);
                        randomAnswer += letters[randomLetter];
                    }

                    calling = new JTextArea();
                    calling = new TextAreaBuilderImpl()
                        .formatText()
                        .setText("Calling...")
                        .setFont(new Font("Serif", Font.ITALIC, 20))
                        .setBackground(new Color(0, 38, 75))
                        .setForeground(Color.WHITE)
                        .setPreferredSize(new Dimension(250, 45))
                        .setBounds(30, 90)
                        .setReadOnly()
                        .get();
                    
                    GameView.getPanel().remove(friendList);
                    GameView.getPanel().add(calling);
                    GameView.getPanel().repaint();
                    try {
                        AudioManager.handleAudioEvent("calling");
                        new Thread(() -> {
                           
                           int randomCallingTime = new Random().nextInt((25 - 0) + 3) + 3;
                            try {       
                                Thread.sleep(randomCallingTime * 1000);
                                AudioManager.muteMouseEvent();
                                if(timer.getText().equals("0")) {
                                    GameView.getPanel().remove(calling);
                                    GameView.getPanel().repaint();
                                }else {
                                    calling = new TextAreaBuilderImpl(calling)
                                        .setText(randomAnswer)
                                        .setFont(new Font("Serif", Font.ITALIC, 16))
                                        .setPreferredSize(new Dimension(250, 75))
                                        .setBounds(30, 90)
                                        .setForeground(Color.ORANGE)
                                        .get();
                                GameView.getPanel().repaint();
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(FriendCall.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }).start();
                    } catch (UnsupportedAudioFileException |
                             IOException |
                             LineUnavailableException ex) {
                        Logger.getLogger(FriendCall.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            }
         });
         GameView.getPanel().revalidate();
         GameView.getPanel().repaint();
    }
}
