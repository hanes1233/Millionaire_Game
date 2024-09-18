
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.component.PanelConfiguration;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.data.FriendAnswer;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author pavel
 */
public class FriendCall {
    
    private JPanel panel;
    private final ComponentServiceImpl service;
    private final PanelConfiguration panelConfig;
    
    private final FriendAnswer friendAnswer;
    
    private JLabel timer;
    private JList friendList;
    
    private final String language;
    
    public FriendCall(JPanel panel, String language) {
        this.panel = panel;
        this.language = language;
        this.service = new ComponentServiceImpl();
        this.panelConfig = new PanelConfiguration();
        this.friendAnswer = new FriendAnswer();
    }
    
    public void defineFriendList() {
         
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
        // this.panel.add(friendList);
         this.panel = panelConfig.addOnPanel(
                 panel, 
                 friendList
         );
         
         this.friendList.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent evt) {
               // JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    if(language.equals("English")) {
                        System.out.println(friendAnswer.getRandomEngAnswer());
                    }
                    //presudocode
                    //remove JList
                    //add JTextArea with calling .... sound and icon for 5 seconds
                    //clear JTextArea
                    //set answer from answers array depending on difficulty
                    //after 30 seconds remove JTextArea
                } 
            }
         });
         this.panel.revalidate();
         this.panel.repaint();
     }
}
