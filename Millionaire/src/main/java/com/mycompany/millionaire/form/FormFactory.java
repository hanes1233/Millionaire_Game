
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.media.ImageManager;
import com.mycompany.millionaire.media.AudioManager;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Class provides useful methods to create new form and panel templates
 * @author pavel
 */
public class FormFactory {
    
    private JFrame form;
    
    // Constructor initializes our form
    public FormFactory() {
        this.form = new JFrame();
    }
    
    public JFrame getFrame() {
        return this.form;
    }
    
    /**
     * Setting up our form
     * @return form instance
     */
    public JFrame createForm() {
        this.form.setSize(640,480);
        this.form.setLocationRelativeTo(null);
        this.form.setResizable(false);
        this.form.setTitle("Millionaire");
        this.form.setIconImage(ImageManager.getImage(ImageManager.getImageIcon("icon")));
        this.form.setVisible(true); 
        this.form.setDefaultCloseOperation(EXIT_ON_CLOSE);
        return form;
    }
    
    /**
     * Do the same as previous createForm method, but also set up intro sound
     * @param loopIntro = boolean value to understand if we really want to play 
     * intro sound
     * @return form instance
     * @throws LineUnavailableException all of exceptions comes from AudioManager 
     * class, because there we works with files and media players
     * @throws UnsupportedAudioFileException
     * @throws NullPointerException
     * @throws IOException 
     * @throws java.lang.ClassNotFoundException 
     * @throws java.lang.InstantiationException 
     * @throws java.lang.IllegalAccessException 
     * @throws javax.swing.UnsupportedLookAndFeelException 
     */
    public JFrame createForm(boolean loopIntro) 
            throws LineUnavailableException, 
                   UnsupportedAudioFileException, 
                   NullPointerException, 
                   IOException,
                   ClassNotFoundException,
                   InstantiationException,
                   IllegalAccessException,
                   UnsupportedLookAndFeelException {
        this.form = new JFrame();
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        WelcomePage component = new WelcomePage();
        form.setContentPane(component.getPanel());
        form.setSize(640,480);
        form.setLocationRelativeTo(null);
        form.setResizable(false);
        form.setTitle("Millionaire");
        component.initializeFrameCopy(form);
        form.setIconImage(ImageManager.getImage(ImageManager.getImageIcon("icon")));
        if(loopIntro) {
            AudioManager.loopSound("intro");
        }
        form.setVisible(true); 
        form.setDefaultCloseOperation(EXIT_ON_CLOSE);
        return form;
    }
    
}
