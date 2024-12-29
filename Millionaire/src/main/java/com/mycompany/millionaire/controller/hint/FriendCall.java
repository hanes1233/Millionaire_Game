
package com.mycompany.millionaire.controller.hint;

import com.mycompany.millionaire.data.FriendAnswer;
import com.mycompany.millionaire.data.entity.Question;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.model.component.builder.LabelBuilder;
import com.mycompany.millionaire.model.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mycompany.millionaire.data.constant.CustomColor.INDIGO;
import static com.mycompany.millionaire.data.constant.Language.ENGLISH;
import static java.awt.Color.WHITE;

/**
 * FriendCall controller provides design architecture
 * for 'friend call' simulation
 * @author pavel
 */
public class FriendCall {
    
    // Declare service instances
    protected final Question currentQuestion;
    protected final ComponentServiceImpl service;
    
    // Declare friend's answer instance 
    protected final FriendAnswer friendAnswer;
    
    // Declare components 
    protected JLabel timer;
    protected JTextArea calling;
    protected JList friendList;
    
    protected final String language;
    protected String randomAnswer;
    
    /**
     * Constructor
     * @param currentQuestion to set up current language
     */
    public FriendCall(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
        this.language = currentQuestion.getLanguage();
        this.service = new ComponentServiceImpl();
        this.friendAnswer = new FriendAnswer();
        defineFriendList();
    }
    
     /**
      * Set up components and add on the panel
      */
     private void defineFriendList() {
         this.timer = new JLabel();
         timer = new LabelBuilder()
                 .setText("30")
                 .setFont(new Font(Font.SERIF, Font.BOLD, 22))
                 .setForeground(Color.ORANGE)
                 .setPreferredSize(new Dimension(100, 30))
                 .setBounds(300, 45)
                 .build();
         
         GameView.getPanel().add(timer);
         
         // Thread to simulate 30 seconds timer
         new Thread(() -> {
             int i = 30;
             try {
                 while(i >= 0) {
                     Thread.sleep(1000);
                     timer.setText(String.valueOf(i));
                     GameView.getPanel().repaint();
                     i--;
                 }
                 // Remove timer after time is gone
                 GameView.getPanel().remove(timer);
                 
                 // Revalidate and repaint panel
                 this.service.reloadPanel();
             }catch (InterruptedException e) {
                 Logger.getLogger(FriendCall.class.getName()).log(Level.SEVERE, "Error catched in timer Thread", e);
                 Thread.currentThread().interrupt();
             }
         }).start();
         
         // Define friends list based on provided language
         DefaultListModel<String> friends = 
                 (this.language.equals(ENGLISH.getName())) ? this.friendAnswer.getENG_FRIENDS() : this.friendAnswer.getCZ_FRIENDS();

         this.friendList = new ListBuilderImpl()
                 .background(INDIGO)
                 .foreground(WHITE)
                 .preferredSize(new Dimension(250, 85))
                 .bounds(30, 90)
                 .model(friends)
                 .build();
         this.service.addOnPanel(friendList);
    }
}
