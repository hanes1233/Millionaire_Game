
package com.mycompany.millionaire.controller;

import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ButtonBuilderImpl;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.media.ImageManager;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.view.GameView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Main page controller
 * @author pavel
 */
 public class MainPage {
    
    private final ComponentServiceImpl componentService;
    private final JPanel panel;

    // Constructor
    public MainPage() {
        this.panel = GameView.getPanel();
        this.componentService = new ComponentServiceImpl();
    }
    
    /**
     * Initializing buttons, event listeners, hover handlers 
     * and add it on panel
     * @throws IOException
     */
    public void initComponents() {
        
        // Set look
        this.componentService.setDefaultLook();
        
        // Clear panel of components if exist
        this.panel.removeAll();

        JButton startGame = new ButtonBuilderImpl()
                .defaultConfig()
                .text("New Game")
                .bounds(30, 150)
                .build();
        
        startGame.addActionListener((ActionEvent e) -> {
            try {
                new NewGame().drawNewGameComponents();
            } catch (IOException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        JButton scores = new ButtonBuilderImpl()
                .defaultConfig()
                .text("Scores")
                .bounds(30, 200)
                .build();
        
        scores.addActionListener((ActionEvent e) -> new Score().drawScoreComponents());


        JButton exit = new ButtonBuilderImpl()
                .defaultConfig()
                .text("Exit")
                .bounds(30, 300)
                .build();
        
        exit.addActionListener((ActionEvent e) -> this.componentService.dispose());


        JButton aboutAuthor = new ButtonBuilderImpl()
                .defaultConfig()
                .text("About")
                .bounds(30, 250)
                .build();
        
        aboutAuthor.addActionListener((ActionEvent e) -> {
            try {
                new AboutAuthor().drawComponents();
            } catch (IOException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        JLabel introLabel = new LabelBuilderImpl()
                .text("Welcome to Millionaire Game")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 22))
                .bounds(100, 25)
                .build();
        
        JLabel mainLogo = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("label"))
                .bounds(250, 100)
                .build();
        
        JLabel mute = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("mute"))
                .bounds(580, 25)
                .build();
        
        mute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (AudioManager.isIntroActive()) {
                    AudioManager.muteIntro();
                return;
                }
                AudioManager.loopSound("intro");
            }
        });
        
        this.componentService.addOnPanel(
                startGame, 
                scores, 
                exit, 
                aboutAuthor,
                introLabel, 
                mainLogo, 
                mute
        );
        
        // Revalidate and repaint panel
        this.componentService.reloadPanel();
    }
}
