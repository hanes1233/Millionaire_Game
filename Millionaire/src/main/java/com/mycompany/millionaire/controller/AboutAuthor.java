
package com.mycompany.millionaire.controller;

import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.model.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.model.media.ImageManager;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.model.Redirect;
import com.mycompany.millionaire.view.GameView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 * AboutAuthor controller 
 * @author pavel
 */
public class AboutAuthor extends JFrame {

    private final ComponentServiceImpl SERVICE;
    private final JPanel PANEL;
    
    private JLabel flaticon;
    private JLabel stackoverflow;
    private JLabel github;
    private JLabel linkedin;
    private JLabel mail;
    private JButton backButton;
    
    // Constructor
    public AboutAuthor() throws IOException {
        this.PANEL = GameView.getPanel();
        // Draw rectangle
        BorderLayout rectange = new BorderLayout() {
            public void paintComponent(Graphics g) {
                g.fillRect(0,250,320,205);
                g.drawRect(320,250,320,205);
            }
        }; 
        // Add rectangle on panel
        this.PANEL.setLayout(rectange);
        this.SERVICE = new ComponentServiceImpl();
    }
    
    // Create components for JPanel
    public void drawComponents() {
        
        // Clear panel
        PANEL.removeAll();
        
        // Initialize components
        JLabel authorName = new LabelBuilderImpl()
                .setText("AUTHOR : Pavel Herasymov")
                .setForeground(Color.yellow)
                .setFont(new Font("Serif", Font.BOLD, 24))
                .setBounds(120, 45)
                .get();
        
        JLabel contactLabel = new LabelBuilderImpl()
                .setText("CONTACTS")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif", Font.BOLD, 26))
                .setBounds(220, 110)
                .get();
        
        github = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("github"))
                .setBackground(Color.WHITE)
                .setBounds(100, 170)
                .get();
        
        github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Scale image on hover
                github = new LabelBuilderImpl(github)
                        .setImage(new ImageIcon(ImageManager.scaleImage("github")))
                        .setBounds(100,165)
                        .get();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                github.setIcon(ImageManager.getImageIcon("github"));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("https://github.com/hanes1233");
            }
        });
        
        
        linkedin = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("linkedin"))
                .setBackground(Color.WHITE)
                .setBounds(300, 170)
                .get(); 
        
        linkedin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Scale image on hover
                linkedin = new LabelBuilderImpl(linkedin)
                        .setImage(new ImageIcon(ImageManager.scaleImage("linkedin")))
                        .setBounds(300,165)
                        .get();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linkedin.setIcon(ImageManager.getImageIcon("linkedin"));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("https://www.linkedin.com/in/pavel-herasymov-8036a627b/");
            }
        });
        
        mail = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("mail"))
                .setBackground(Color.WHITE)
                .setBounds(500, 170)
                .get(); 
        
        mail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Scale image on hover
                mail = new LabelBuilderImpl(mail)
                        .setImage(new ImageIcon(ImageManager.scaleImage("mail")))
                        .setBounds(500,165)
                        .get();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mail.setIcon(ImageManager.getImageIcon("mail"));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to new email message form
                Redirect.redirectToMail("pavelherasymov@seznam.cz");
            }
        });
        
        JLabel technologiesUsed = new LabelBuilderImpl()
                .setText("TECHNOLOGIES USED: ")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif", Font.BOLD, 18))
                .setBounds(340, 260)
                .get();
        
        JLabel java = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("java"))
                .setText("Java")
                .setForeground(Color.WHITE)
                .setBounds(340, 300)
                .get();
        
        JLabel mongodb = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("mongodb"))
                .setText("MongoDB")
                .setForeground(Color.WHITE)
                .setBounds(440, 300)
                .get();
        
        JLabel thanksTo = new LabelBuilderImpl()
                .setText("THANKS TO:")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif", Font.BOLD, 18))
                .setBounds(340, 360)
                .get();
        
        flaticon = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("flaticon"))
                .setText("Flaticon")
                .setForeground(Color.WHITE)
                .setBounds(340, 400)
                .get();
        
        flaticon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change foreground color on hover
                flaticon.setForeground(new Color(255, 0, 255));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                flaticon.setForeground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("https://www.flaticon.com/");
            }
        });
       
        
        stackoverflow = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("stackoverflow"))
                .setText("StackOverFlow")
                .setForeground(Color.WHITE)
                .setBounds(460, 400)
                .get();
        
        stackoverflow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change foreground color on hover
                stackoverflow.setForeground(new Color(255, 0, 255));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stackoverflow.setForeground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("http://stackoverflow.com");
            }
        });
        
        JTextArea gameDescription = new TextAreaBuilderImpl()
                .setText("Millionaire Game ® \n \nA quiz competition with"
                + " player attempting to win \na top prize of $1,000,000"
                + " by answering a series \nof multiple-choice questions. \n"
                + "Game starts in easy mode for first 5 questions,"
                + "\nthen difficulty increases to meadium level until \n10th question,"
                + "after contestant have to answer \n3 HARD questions, and finish "
                + " game with \n2 EXTREMELY HARD questions."
                + "There are 3 hints \navailible during the game : 50/50, \nphone-a-friend and "
                + "ask the audience.")
                .setForeground(Color.WHITE)
                .setBackground(Color.BLACK)
                .setBorder(null)
                .setSize(300, 205)
                .setBounds(15, 260)
                .setReadOnly()
                .get();
        
        // Setting up 'return' button
        this.backButton = new ButtonBuilderImpl()
                .setText("<")
                .setFont(new Font("Serif", Font.BOLD, 16))
                .setForeground(Color.WHITE)
                .setBackground(new Color(0, 38, 75))
                .setSize(50,25)
                .setBounds(15,15)
                .onHover()
                .get();
        
        JLabel label = new JLabel();
        
        backButton.addActionListener((ActionListener) -> {
            // Remove rectangle from panel
            PANEL.setLayout(null);
            // Return to main page
            SERVICE.runMainPage();
        });
        
        // Add multiple elements on panel
        this.SERVICE.addOnPanel(
                flaticon,
                stackoverflow,
                thanksTo,
                mongodb,
                java,
                technologiesUsed,
                gameDescription,
                mail,
                linkedin,
                github,
                contactLabel,
                authorName,
                backButton,
                label
        );
        
        // Revalidate and repaint panel;
        this.SERVICE.reloadPanel();
    }
    
    
}
