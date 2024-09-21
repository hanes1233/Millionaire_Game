
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.data.FriendAnswer;
import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author pavel
 */
public class FriendCall {
    
    private JPanel panel;
    private final ComponentServiceImpl service;
    
    private final FriendAnswer friendAnswer;
    
    private JLabel timer;
    private JTextArea calling;
    private JList friendList;
    
    private final String language;
    private String randomAnswer;
    
    public FriendCall() {
        this.panel = CurrentQuestion.getPanel();
        this.language = CurrentQuestion.getLanguage();
        this.service = new ComponentServiceImpl();
        this.friendAnswer = new FriendAnswer();
    }
    
     private void defineFriendList() {
         this.timer = new JLabel();
         timer = new LabelBuilderImpl(timer)
                 .text("30")
                 .font(new Font("Serif", Font.BOLD, 22))
                 .foreground(Color.ORANGE)
                 .bounds(300, 45, service.getWidth(timer), service.getHeight(timer))
                 .get();
         
         this.panel.add(timer);
         
         new Thread(() -> {
             int i = 30;
             try {
                 while(i >= 0) {
                     Thread.sleep(1000);
                     timer.setText(String.valueOf(i));
                     this.panel.repaint();
                     i--;
                 }
                 this.panel.remove(timer);
                 this.panel.repaint();
             }catch(InterruptedException e) {
                 System.out.println("Error catched in loadingProgress method : " + e);
             }
         }).start();
         
         DefaultListModel<String> friends = 
                 (this.language.equals("English")) ? this.friendAnswer.getEngFriends() : this.friendAnswer.getCzFriends();
         
         this.friendList = new JList();
         this.friendList = new ListBuilderImpl(friendList)
                 .background(new Color(51,0,102))
                 .foreground(Color.WHITE)
                 .bounds(30, 90, 250, 85)
                 .model(friends)
                 .get();
         this.panel = service.addOnPanel(
                 panel, 
                 friendList
         );
    }
     
     private void callFriend() {
        this.friendList.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    if(language.equals("English")) {
                        randomAnswer = friendAnswer.getRandomEngAnswer();
                    }else {
                        randomAnswer = friendAnswer.getRandomEngAnswer();
                    }
                    
                    if(CurrentQuestion.getDifficulty().equals("Easy") || CurrentQuestion.getDifficulty().equals("Medium")) {
                        String currentAnswer = CurrentQuestion.getAnswer();
                        if(CurrentQuestion.getOptionA().getText().contains(currentAnswer)) {
                            randomAnswer += 'A';
                        }else if(CurrentQuestion.getOptionB().getText().contains(currentAnswer)) {
                            randomAnswer += 'B';
                        }else if(CurrentQuestion.getOptionC().getText().contains(currentAnswer)) {
                            randomAnswer += 'C';
                        }else if(CurrentQuestion.getOptionD().getText().contains(currentAnswer)) {
                            randomAnswer += 'D';
                        }
                    }else {
                        char[] letters = {'A','B','C','D'};
                        int randomLetter = new Random().nextInt(letters.length - 0);
                        randomAnswer += letters[randomLetter];
                    }

                    calling = new JTextArea();
                    calling = new TextAreaBuilderImpl(calling)
                        .formatText()
                        .text("Calling...")
                        .font(new Font("Serif", Font.ITALIC, 20))
                        .background(new Color(0, 38, 75))
                        .foreground(Color.WHITE)
                        .bounds(30, 90, 250, 45)
                        .readOnly()
                        .get();
                    
                    panel.remove(friendList);
                    panel.add(calling);
                    panel.repaint();
                    try {
                        AudioManager.handleAudioEvent("calling");
                        new Thread(() -> {
                           
                           int randomCallingTime = new Random().nextInt((25 - 0) + 3) + 3;
                            try {       
                                Thread.sleep(randomCallingTime * 1000);
                                AudioManager.muteMouseEvent();
                                if(timer.getText().equals("0")) {
                                    panel.remove(calling);
                                    panel.repaint();
                                }else {
                                    calling = new TextAreaBuilderImpl(calling)
                                        .text(randomAnswer)
                                        .font(new Font("Serif", Font.ITALIC, 16))
                                        .bounds(30, 90, 250, 75)
                                        .foreground(Color.ORANGE)
                                        .get();
                                panel.repaint();
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
         this.panel.revalidate();
         this.panel.repaint();
    }
    
    public void performFriendCall() {
         defineFriendList();
         callFriend();
     }
    
    
    
   
}
