
package com.mycompany.millionaire.controller;

import com.mycompany.millionaire.model.Redirect;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.model.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.model.component.builder.LabelBuilder;
import com.mycompany.millionaire.model.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.model.media.ImageManager;
import com.mycompany.millionaire.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.mycompany.millionaire.data.constant.CustomColor.NAVY_BLUE;
import static com.mycompany.millionaire.data.constant.FontType.SERIF;
import static java.awt.Color.MAGENTA;
import static java.awt.Color.WHITE;

/**
 * AboutAuthor controller provides design architecture for 'About' section
 * @author pavel
 */
public class AboutAuthor {

    private final ComponentServiceImpl componentService;
    private final JPanel panel;
    
    private JLabel flaticon;
    private JLabel stackoverflow;
    private JLabel githubLabel;
    private JLabel linkedinLabel;
    private JLabel mailLabel;

    private static final String GITHUB = "github";
    private static final String LINKEDIN = "linkedin";
    private static final String MAIL = "mail";

    // Constructor
    public AboutAuthor() throws IOException {
        this.panel = GameView.getPanel();
        // Draw rectangle
        BorderLayout rectangle = new BorderLayout() {
            public void paintComponent(Graphics g) {
                g.fillRect(0,250,320,205);
                g.drawRect(320,250,320,205);
            }
        };
        // Add rectangle on panel
        this.panel.setLayout(rectangle);
        this.componentService = new ComponentServiceImpl();
    }
    
    // Create components for JPanel
    public void drawComponents() {
        // Clear panel
        panel.removeAll();

        // Initialize components

        JLabel authorName = new LabelBuilder()
                .setText("AUTHOR : Pavel Herasymov")
                .setForeground(Color.yellow)
                .setFont(new Font(SERIF, Font.BOLD, 24))
                .setBounds(120, 45)
                .build();
        
        JLabel contactLabel = new LabelBuilder()
                .setText("CONTACTS")
                .setForeground(WHITE)
                .setFont(new Font(SERIF, Font.BOLD, 26))
                .setBounds(220, 110)
                .build();
        
        githubLabel = new LabelBuilder()
                .setImage(ImageManager.getImageIcon(GITHUB))
                .setBackground(WHITE)
                .setBounds(100, 170)
                .build();
        
        githubLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Scale image on hover
                githubLabel = new LabelBuilder(githubLabel)
                        .setImage(new ImageIcon(ImageManager.scaleImage(GITHUB)))
                        .setBounds(100,165)
                        .build();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                githubLabel.setIcon(ImageManager.getImageIcon(GITHUB));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("https://github.com/hanes1233");
            }
        });
        
        linkedinLabel = new LabelBuilder()
                .setImage(ImageManager.getImageIcon(LINKEDIN))
                .setBackground(WHITE)
                .setBounds(300, 170)
                .build();
        
        linkedinLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Scale image on hover
                linkedinLabel = new LabelBuilder(linkedinLabel)
                        .setImage(new ImageIcon(ImageManager.scaleImage(LINKEDIN)))
                        .setBounds(300,165)
                        .build();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                linkedinLabel.setIcon(ImageManager.getImageIcon(LINKEDIN));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("https://www.linkedin.com/in/pavel-herasymov-8036a627b/");
            }
        });
        
        mailLabel = new LabelBuilder()
                .setImage(ImageManager.getImageIcon("mail"))
                .setBackground(WHITE)
                .setBounds(500, 170)
                .build();
        
        mailLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Scale image on hover
                mailLabel = new LabelBuilder(mailLabel)
                        .setImage(new ImageIcon(ImageManager.scaleImage(MAIL)))
                        .setBounds(500,165)
                        .build();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mailLabel.setIcon(ImageManager.getImageIcon(MAIL));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to new email message form
                Redirect.redirectToMail("pavelherasymov@seznam.cz");
            }
        });
        
        JLabel technologiesUsed = new LabelBuilder()
                .setText("TECHNOLOGIES USED: ")
                .setForeground(WHITE)
                .setFont(new Font(SERIF, Font.BOLD, 18))
                .setBounds(340, 260)
                .build();

        // Prepare data for 'Java' and 'MongoDB' JLabels
        String[] technologies = { "Java", "MongoDB" };
        AtomicInteger technologieWidth = new AtomicInteger(340);
        final int technologieHeight = 300;

        // Create JLabels and add on panel
        IntStream.range(0, 2).forEach(i -> {
            var label = new LabelBuilder()
                    .setImage(ImageManager.getImageIcon(technologies[i].toLowerCase()))
                    .setText(technologies[i])
                    .setForeground(WHITE)
                    .setBounds(technologieWidth.get(), technologieHeight)
                    .build();
            this.componentService.addOnPanel(label);
            technologieWidth.addAndGet(100);
        });

        JLabel thanksTo = new LabelBuilder()
                .setText("THANKS TO:")
                .setForeground(WHITE)
                .setFont(new Font(SERIF, Font.BOLD, 18))
                .setBounds(340, 360)
                .build();
        
        flaticon = new LabelBuilder()
                .setImage(ImageManager.getImageIcon("flaticon"))
                .setText("Flaticon")
                .setForeground(WHITE)
                .setBounds(340, 400)
                .build();
        
        flaticon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change foreground color on hover
                flaticon.setForeground(MAGENTA);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                flaticon.setForeground(WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("https://www.flaticon.com/");
            }
        });
        
        stackoverflow = new LabelBuilder()
                .setImage(ImageManager.getImageIcon("stackoverflow"))
                .setText("StackOverFlow")
                .setForeground(WHITE)
                .setBounds(460, 400)
                .build();
        
        stackoverflow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change foreground color on hover
                stackoverflow.setForeground(MAGENTA);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stackoverflow.setForeground(WHITE);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to following web page
                Redirect.redirectOnWeb("http://stackoverflow.com");
            }
        });
        
        JTextArea gameDescription = new TextAreaBuilderImpl()
                .text("""
                        Millionaire Game ®
                 A quiz competition with player attempting to win
                 a top prize of $1,000,000 by answering a series
                 of multiple-choice questions.
                 Game starts in easy mode for first 5 questions,
                 then difficulty increases to meadium level until
                 10th question,after contestant have to answer
                 3 HARD questions, and finish
                 game with 2 EXTREMELY HARD questions.
                 There are 3 hints availible during the game :
                 50/50, phone-a-friend and ask the audience.
                """)
                .foreground(WHITE)
                .background(Color.BLACK)
                .border(null)
                .minSize(300, 205)
                .bounds(15, 260)
                .readOnly()
                .build();
        
        // Setting up 'return' button
        JButton backButton = new ButtonBuilderImpl()
                .text("<")
                .font(new Font(SERIF, Font.BOLD, 16))
                .foreground(WHITE)
                .background(NAVY_BLUE)
                .minSize(50,25)
                .bounds(15,15)
                .onHover()
                .build();
        
        JLabel label = new JLabel();
        
        backButton.addActionListener(e -> {
            // Remove rectangle from panel
            panel.setLayout(null);
            // Return to main page
            componentService.runMainPage();
        });
        
        // Add multiple elements on panel
        this.componentService.addOnPanel(
                flaticon,
                stackoverflow,
                thanksTo,
                technologiesUsed,
                gameDescription,
                mailLabel,
                linkedinLabel,
                githubLabel,
                contactLabel,
                authorName,
                backButton,
                label
        );
        
        // Revalidate and repaint panel
        this.componentService.reloadPanel();
    }
    
    
}
