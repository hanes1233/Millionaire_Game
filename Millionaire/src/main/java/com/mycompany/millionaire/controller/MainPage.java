
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
 *
 * @author pavel
 */
 public class MainPage {
    
    private final ComponentServiceImpl SERVICE;
    private final JPanel PANEL;
    
    private JButton startGame;
    private JButton scores;
    private JButton exit;
    private JButton aboutAuthor;

    public MainPage() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.PANEL = GameView.getPanel();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    /**
     * Initializing buttons, event listeners, hover handlers 
     * and add it on panel
     * @throws IOException because we work with AudioManager class, which
     * works with files and audio player
     */
    public void initComponents() throws IOException {
        this.SERVICE.setDefaultLook();
        PANEL.removeAll();
        
        startGame = new ButtonBuilderImpl()
                .defaultConfig()
                .setText("New Game")
                .setBounds(30, 150)
                .get();
        
        startGame.addActionListener((ActionEvent e) -> {
            try {
                new NewGame().drawNewGameComponents();
            } catch (IOException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        scores = new ButtonBuilderImpl()
                .defaultConfig()
                .setText("Scores")
                .setBounds(30, 200)
                .get();
        
        scores.addActionListener((ActionEvent e) -> {
           new Score().drawScoreComponents();
           
        });
        
        
        exit = new ButtonBuilderImpl()
                .defaultConfig()
                .setText("Exit")
                .setBounds(30, 300)
                .get();
        
        exit.addActionListener((ActionEvent e) -> {
            this.SERVICE.dispose();
        });
        
        
        aboutAuthor = new ButtonBuilderImpl()
                .defaultConfig()
                .setText("About")
                .setBounds(30, 250)
                .get();
        
        aboutAuthor.addActionListener((ActionEvent e) -> {
            try {
                new AboutAuthor().drawComponents();
            } catch (IOException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        JLabel introLabel = new LabelBuilderImpl()
                .setText("Welcome to Millionaire Game")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif", Font.BOLD, 22))
                .setBounds(100, 25)
                .get();
        
        JLabel mainLogo = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("label"))
                .setBounds(250, 100)
                .get();
        
        JLabel mute = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("mute"))
                .setBounds(580, 25)
                .get();
        
        mute.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(AudioManager.isIntroActive()) {
                    AudioManager.muteIntro();
                return;
                }
                try {
                    AudioManager.loopSound("intro");
                } catch (IOException |
                    LineUnavailableException |
                    UnsupportedAudioFileException ex ) {
                        throw new RuntimeException("Error catch trying to play intro(MainComponent.class): " + e);
                }
            }
        });
        
        this.SERVICE.addOnPanel(
                startGame, 
                scores, 
                exit, 
                aboutAuthor,
                introLabel, 
                mainLogo, 
                mute
        );
        
        this.SERVICE.reloadPanel();
    }
}
