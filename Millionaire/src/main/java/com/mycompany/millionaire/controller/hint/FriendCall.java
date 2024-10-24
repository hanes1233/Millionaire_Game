
package com.mycompany.millionaire.controller.hint;

import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.data.FriendAnswer;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.view.GameView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

/**
 *
 * @author pavel
 */
public class FriendCall {
    
    // Declare service instances
    protected final Question CURRENT_QUESTION;
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
        this.CURRENT_QUESTION = currentQuestion;
        this.language = currentQuestion.getLanguage();
        this.service = new ComponentServiceImpl();
        this.friendAnswer = new FriendAnswer();
        defineFriendList();
    }
    
     /**
      * Set up components and add to the panel
      */
     private void defineFriendList() {
         this.timer = new JLabel();
         timer = new LabelBuilderImpl()
                 .setText("30")
                 .setFont(new Font("Serif", Font.BOLD, 22))
                 .setForeground(Color.ORANGE)
                 .setPreferredSize(new Dimension(100, 30))
                 .setBounds(300, 45)
                 .get();
         
         GameView.getPanel().add(timer);
         
         // Thread to simulate 30 second's timer
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
             }catch(InterruptedException e) {
                 System.out.println("Error catched in timer Thread : " + e);
             }
         }).start();
         
         // Define friends list based on provided language
         DefaultListModel<String> friends = 
                 (this.language.equals("English")) ? this.friendAnswer.getENG_FRIENDS() : this.friendAnswer.getCZ_FRIENDS();
         
         this.friendList = new JList();
         this.friendList = new ListBuilderImpl()
                 .setBackground(new Color(51,0,102))
                 .setForeground(Color.WHITE)
                 .setPreferredSize(new Dimension(250, 85))
                 .setBounds(30, 90)
                 .setModel(friends)
                 .get();
         this.service.addOnPanel(friendList);
    }
}
