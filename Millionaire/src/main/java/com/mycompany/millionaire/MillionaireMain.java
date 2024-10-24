
package com.mycompany.millionaire;

import com.mycompany.millionaire.view.GameView;
import com.mycompany.millionaire.view.SplashScreen;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.apache.log4j.BasicConfigurator;


public class MillionaireMain {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                BasicConfigurator.configure();
                // Set-up and run 'loading screen'
                SplashScreen loading = new SplashScreen(null,true);
                loading.setLocationRelativeTo(null);
                loading.setVisible(true);
                // Set-up and run game view
                GameView.createPanel();
                GameView.createForm();
                GameView.run();
            } catch (IOException |
                    LineUnavailableException |
                    UnsupportedAudioFileException e ) {
                throw new RuntimeException("Error catch at main method: " + e);
            }
        });
    }
}
