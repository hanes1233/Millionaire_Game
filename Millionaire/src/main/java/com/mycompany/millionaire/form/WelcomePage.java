
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.component.PanelTemplate;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ButtonBuilderImpl;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.media.ImageManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author pavel
 */
public final class WelcomePage {
    
    private JFrame frame;
    private JPanel panel;
    private final ComponentServiceImpl service;
    
    
    private JButton startGame;
    private JButton scores;
    private JButton exit;
    private JButton aboutAuthor;
    
    public JPanel getPanel() {
        return this.panel;
    }
    
    public void initializeFrameCopy(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Constructor initializes factory to get useful 'createForm' and 'getPanel'
     * methods, also initializing service to work with configuration of components
     * @throws IOException - could be thrown because 'createForm' 
     * method works with image
     * @throws javax.sound.sampled.LineUnavailableException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     */
    public WelcomePage() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.panel = PanelTemplate.getPanel();
        service = new ComponentServiceImpl();
        initComponents();
    }
    
    /**
     * Initializing buttons, event listeners, hover handlers 
     * and add it on panel
     * @throws IOException because we work with AudioManager class, which
     * works with files and audio player
     */
    private void initComponents() throws IOException {
        
        startGame = new JButton();
        startGame = new ButtonBuilderImpl(startGame)
                .defaultConfig()
                .text("New Game")
                .bounds(30, 150, 150, 35)
                .get();
        
        startGame.addActionListener((ActionEvent e) -> {
            try {
                new NewGame().run();
                this.frame.dispose();
            } catch (IOException ex) {
                Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        scores = new JButton();
        scores = new ButtonBuilderImpl(scores)
                .defaultConfig()
                .text("Scores")
                .bounds(30, 200, 135, 35)
                .get();
        
        scores.addActionListener((ActionEvent e) -> {
            try {
                new Score().run();
                this.frame.dispose();
            } catch (IOException ex) {
                Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
            }     
        });
        
        
        exit = new JButton();
        exit = new ButtonBuilderImpl(exit)
                .defaultConfig()
                .text("Exit")
                .bounds(30,300,105,35)
                .get();
        
        exit.addActionListener((ActionEvent e) -> {
            AudioManager.muteIntro();
            frame.dispose();
        });
        
        
        aboutAuthor = new JButton("About");
        aboutAuthor = new ButtonBuilderImpl(aboutAuthor)
                .defaultConfig()
                .text("About")
                .bounds(30, 250, 120, 35)
                .get();
        
        aboutAuthor.addActionListener((ActionEvent e) -> {
            try {
                new AboutAuthor().run();
                this.frame.dispose();
            }catch (IOException ex) {
                Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        JLabel mute = new JLabel();
        JLabel introLabel = new JLabel();
        JLabel mainLogo = new JLabel();
        
        introLabel = new LabelBuilderImpl(introLabel)
                .text("Welcome to Millionaire Game")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 22))
                .bounds(100, 25, service.getWidth(introLabel), service.getHeight(introLabel))
                .get();
        
        mainLogo = new LabelBuilderImpl(mainLogo)
                .image(ImageManager.getImageIcon("label"))
                .bounds(250, 100, service.getWidth(mainLogo), service.getHeight(mainLogo))
                .get();
        
        mute = new LabelBuilderImpl(mute)
                .bounds(580, 25, 32, 32)
                .image(ImageManager.getImageIcon("mute"))
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
        
        this.panel = service.addOnPanel(
                panel, 
                startGame, 
                scores, 
                exit, 
                aboutAuthor,
                introLabel, 
                mainLogo, 
                mute);
    }
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                BasicConfigurator.configure();
                FormFactory form = new FormFactory();
                SplashScreen loading = new SplashScreen(null,true);
                loading.setLocationRelativeTo(null);
                loading.setVisible(true);
                form.createForm(true);
            } catch (IOException |
                    LineUnavailableException |
                    ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException |
                    UnsupportedLookAndFeelException |
                    UnsupportedAudioFileException e ) {
                throw new RuntimeException("Error catch at main method: " + e);
            }
        });
    }
    
}
