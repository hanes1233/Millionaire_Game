
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.PanelTemplate;
import com.mycompany.millionaire.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.media.ImageManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import com.mycompany.millionaire.model.Redirect;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 *
 * @author pavel
 */
public class AboutAuthor extends JFrame {

    private final FormFactory FACTORY;
    private final ComponentServiceImpl SERVICE;
    private final PanelTemplate PANEL_CONFIG;
    
    private JFrame authorForm;
    private JPanel authorPanel;
    
    private JLabel flaticon;
    private JLabel stackoverflow;
    private JLabel github;
    private JLabel linkedin;
    private JLabel mail;
    
    
    public AboutAuthor() throws IOException {
        this.PANEL_CONFIG = new PanelTemplate();
        this.FACTORY = new FormFactory();
        this.authorPanel = PanelTemplate.getPanelWithRectangle();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    public void run() {
        this.authorForm = this.FACTORY.createForm();
        this.authorForm.setContentPane(this.authorPanel);
        initComponents();
    }
    
    private void initComponents() {
        
        JLabel authorLabel = new LabelBuilderImpl()
                .text("AUTHOR:")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 26))
                .bounds(75, 45)
                .get();
        
        JLabel authorName = new LabelBuilderImpl()
                .text("Pavel Herasymov")
                .foreground(Color.yellow)
                .font(new Font("Serif", Font.BOLD, 24))
                .bounds(250, 45)
                .get();
        
        JLabel contactLabel = new LabelBuilderImpl()
                .text("CONTACTS")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 26))
                .bounds(220, 110)
                .get();
        
        github = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("github"))
                .background(Color.WHITE)
                .bounds(100, 170)
                .get();
        
        github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                github = new LabelBuilderImpl(github)
                        .image(new ImageIcon(ImageManager.scaleImage("github")))
                        .bounds(100,165)
                        .get();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                github.setIcon(ImageManager.getImageIcon("github"));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Redirect.redirectOnWeb("https://github.com/hanes1233");
            }
        });
        
        
        linkedin = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("linkedin"))
                .background(Color.WHITE)
                .bounds(300, 170)
                .get(); 
        
        linkedin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linkedin = new LabelBuilderImpl(linkedin)
                        .image(new ImageIcon(ImageManager.scaleImage("linkedin")))
                        .bounds(300,165)
                        .get();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linkedin.setIcon(ImageManager.getImageIcon("linkedin"));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Redirect.redirectOnWeb("https://www.linkedin.com/in/pavel-herasymov-8036a627b/");
            }
        });
        
        mail = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("mail"))
                .background(Color.WHITE)
                .bounds(500, 170)
                .get(); 
        
        mail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mail = new LabelBuilderImpl(mail)
                        .image(new ImageIcon(ImageManager.scaleImage("mail")))
                        .bounds(500,165)
                        .get();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mail.setIcon(ImageManager.getImageIcon("mail"));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Redirect.redirectToMail("pavelherasymov@seznam.cz");
            }
        });
        
        JLabel technologiesUsed = new LabelBuilderImpl()
                .text("TECHNOLOGIES USED: ")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 18))
                .bounds(340, 260)
                .get();
        
        JLabel java = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("java"))
                .text("Java")
                .foreground(Color.WHITE)
                .bounds(340, 300)
                .get();
        
        JLabel mongodb = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("mongodb"))
                .text("MongoDB")
                .foreground(Color.WHITE)
                .bounds(440, 300)
                .get();
        
        JLabel thanksTo = new LabelBuilderImpl()
                .text("THANKS TO:")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 18))
                .bounds(340, 360)
                .get();
        
        flaticon = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("flaticon"))
                .text("Flaticon")
                .foreground(Color.WHITE)
                .bounds(340, 400)
                .get();
        
        flaticon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                flaticon.setForeground(new Color(255, 0, 255));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                flaticon.setForeground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Redirect.redirectOnWeb("https://www.flaticon.com/");
            }
        });
       
        
        stackoverflow = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("stackoverflow"))
                .text("StackOverFlow")
                .foreground(Color.WHITE)
                .bounds(460, 400)
                .get();
        
        stackoverflow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stackoverflow.setForeground(new Color(255, 0, 255));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stackoverflow.setForeground(Color.WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Redirect.redirectOnWeb("http://stackoverflow.com");
            }
        });
        
        JTextArea gameDescription = new TextAreaBuilderImpl()
                .text("Millionaire Game ® \n \nA quiz competition with"
                + " player attempting to win \na top prize of $1,000,000"
                + " by answering a series \nof multiple-choice questions. \n"
                + "Game starts in easy mode for first 5 questions,"
                + "\nthen difficulty increases to meadium level until \n10th question,"
                + "after contestant have to answer \n3 HARD questions, and finish "
                + " game with \n2 EXTREMELY HARD questions."
                + "There are 3 hints \navailible during the game : 50/50, \nphone-a-friend and "
                + "ask the audience.")
                .foreground(Color.WHITE)
                .background(Color.BLACK)
                .border(null)
                .size(300, 205)
                .bounds(15, 260)
                .readOnly()
                .get();
        this.PANEL_CONFIG.addBackButton(authorForm);
        this.authorPanel = SERVICE
                .addOnPanel(
                    authorPanel,
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
                    authorLabel
                );
    }
    
    
}
