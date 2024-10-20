
package com.mycompany.millionaire.view;

import com.mycompany.millionaire.controller.MainPage;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.media.ImageManager;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import lombok.Data;
import lombok.Getter;

/**
 * Class provides useful methods to create new form and panel templates
 * @author pavel
 */
@Data
public class GameView {
    
    @Getter
    private static JFrame frame;
    
    @Getter
    private static JPanel panel;
    
    
    public static void run() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        MainPage game = new MainPage();
        game.initComponents();
        try {
                AudioManager.loopSound("intro");
                frame.setVisible(true);
            } catch (IOException |
                    LineUnavailableException |
                    UnsupportedAudioFileException e ) {
                throw new RuntimeException("Error catch at GameView run() method: " + e);
            }
    }
    
    /**
     * Create and set up JForm template
     * @return JForm template
     */
    public static JFrame createForm()  {
        frame = new JFrame();
        frame.setContentPane(panel);
        frame.setSize(640,480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Millionaire");
        frame.setIconImage(ImageManager.getImage());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        return frame;
    }
    
    /**
     * Create and set up JPanel template
     * @return JPanel template
     * @throws IOException 
     */
    public static JPanel createPanel() throws IOException {
        Image backgroundImage = ImageIO.read(new File("/home/pavel/NetBeansProjects/Millionaire/src/main/resources/images/wallpaper.jpg"));
        panel = new JPanel(new BorderLayout()) {         
                    @Override 
                        public void paintComponent(Graphics g) {
                            g.drawImage(backgroundImage, 0, 0, null);
                        }
        };
        panel.setLayout(null);
        return panel;
    }
}
