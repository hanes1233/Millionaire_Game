
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
    
    private JFrame authorForm;
    private JPanel authorPanel;
    private final FormFactory factory;
    private final ComponentServiceImpl service;
    private final PanelTemplate panelConfiguration;
    
    private JLabel flaticon;
    private JLabel stackoverflow;
    private JLabel github;
    private JLabel linkedin;
    private JLabel mail;
    
    
    public AboutAuthor() throws IOException {
        this.panelConfiguration = new PanelTemplate();
        this.factory = new FormFactory();
        this.authorPanel = PanelTemplate.getPanelWithRectangle();
        this.service = new ComponentServiceImpl();
    }
    
    public void run() {
        this.authorForm = this.factory.createForm();
        this.authorForm.setContentPane(this.authorPanel);
        initComponents();
    }
    
    private void initComponents() {
        this.github = new JLabel();
        this.linkedin = new JLabel();
        this.mail = new JLabel();
        this.flaticon = new JLabel();
        this.stackoverflow = new JLabel();
        
        JLabel authorLabel = new JLabel();
        JLabel authorName = new JLabel();
        JLabel contactLabel = new JLabel();
        JLabel technologiesUsed = new JLabel();
        JLabel java = new JLabel();
        JLabel mongodb = new JLabel();
        JLabel thanksTo = new JLabel();
        
        JTextArea gameDescription = new JTextArea();
        
        authorLabel = new LabelBuilderImpl(authorLabel)
                .text("AUTHOR:")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 26))
                .bounds(75, 45, service.getWidth(authorLabel), service.getHeight(authorLabel))
                .get();
        
        authorName = new LabelBuilderImpl(authorName)
                .text("Pavel Herasymov")
                .foreground(Color.yellow)
                .font(new Font("Serif", Font.BOLD, 24))
                .bounds(250,45, service.getWidth(authorName), service.getHeight(authorName))
                .get();
        
        contactLabel = new LabelBuilderImpl(contactLabel)
                .text("CONTACTS")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 26))
                .bounds(220, 110, service.getWidth(contactLabel), service.getHeight(contactLabel))
                .get();
        
        github = new LabelBuilderImpl(github)
                .image(ImageManager.getImageIcon("github"))
                .background(Color.WHITE)
                .bounds(100, 170, service.getWidth(github), service.getHeight(github))
                .get();
        
        github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                github = new LabelBuilderImpl(github)
                        .image(new ImageIcon(ImageManager.scaleImage("github")))
                        .size(80,80)
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
        
        
        linkedin = new LabelBuilderImpl(linkedin)
                .image(ImageManager.getImageIcon("linkedin"))
                .background(Color.WHITE)
                .bounds(300, 170, service.getWidth(linkedin), service.getHeight(linkedin))
                .get(); 
        
        linkedin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                linkedin = new LabelBuilderImpl(linkedin)
                        .image(new ImageIcon(ImageManager.scaleImage("linkedin")))
                        .size(80,80)
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
        
        mail = new LabelBuilderImpl(mail)
                .image(ImageManager.getImageIcon("mail"))
                .background(Color.WHITE)
                .bounds(500, 170, service.getWidth(mail), service.getHeight(mail))
                .get(); 
        
        mail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mail = new LabelBuilderImpl(mail)
                        .image(new ImageIcon(ImageManager.scaleImage("mail")))
                        .size(80,80)
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
        
        technologiesUsed = new LabelBuilderImpl(technologiesUsed)
                .text("TECHNOLOGIES USED: ")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 18))
                .bounds(340,260, service.getWidth(technologiesUsed), service.getHeight(technologiesUsed))
                .get();
        
        java = new LabelBuilderImpl(java)
                .image(ImageManager.getImageIcon("java"))
                .text("Java")
                .foreground(Color.WHITE)
                .bounds(340,300,service.getWidth(java),service.getHeight(java))
                .get();
        
        mongodb = new LabelBuilderImpl(mongodb)
                .image(ImageManager.getImageIcon("mongodb"))
                .text("MongoDB")
                .foreground(Color.WHITE)
                .bounds(440,300,service.getWidth(mongodb),service.getHeight(mongodb))
                .get();
        
        thanksTo = new LabelBuilderImpl(thanksTo)
                .text("THANKS TO:")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 18))
                .bounds(340,360, service.getWidth(thanksTo),service.getHeight(thanksTo))
                .get();
        
        flaticon = new LabelBuilderImpl(flaticon)
                .image(ImageManager.getImageIcon("flaticon"))
                .text("Flaticon")
                .foreground(Color.WHITE)
                .bounds(340, 400, service.getWidth(flaticon), service.getHeight(flaticon))
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
       
        
        stackoverflow = new LabelBuilderImpl(stackoverflow)
                .image(ImageManager.getImageIcon("stackoverflow"))
                .text("StackOverFlow")
                .foreground(Color.WHITE)
                .bounds(460, 400, service.getWidth(stackoverflow), service.getHeight(stackoverflow))
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
        
        gameDescription = new TextAreaBuilderImpl(gameDescription)
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
                .bounds(15,260,300,205)
                .readOnly()
                .get();
        this.panelConfiguration.addBackButton(authorForm);
        this.authorPanel = service
                //.addBackButton(authorForm)
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
