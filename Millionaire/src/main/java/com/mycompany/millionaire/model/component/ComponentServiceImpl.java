
package com.mycompany.millionaire.model.component;

import com.mycompany.millionaire.controller.MainPage;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.view.GameView;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lombok.NoArgsConstructor;

/**
 * Component Service implementation class
 * @author pavel
 */
@NoArgsConstructor
public class ComponentServiceImpl implements ComponentService {
    
    // Get current JPanel and assign to local PANEL variable to work with
    private final JPanel PANEL = GameView.getPanel();

    @Override
    public int getWidth(JComponent component) {
        Dimension size = component.getPreferredSize();
        return size.width;
    }

    @Override
    public int getHeight(JComponent component) {
        Dimension size = component.getPreferredSize();
        return size.height;
    }
    
    @Override
    public JPanel removeFromPanel(JComponent ... components) {
        for(var item : components) {
            PANEL.remove(item);
        }
        return PANEL;
    }
    
    @Override
    public void addOnPanel(JComponent... component) {
        for (JComponent item : component) {
            PANEL.add(item);
        }
    }
    
    @Override
    public void removeMotionListeners() {
        MouseMotionListener[] list = PANEL.getMouseMotionListeners();
        for(var listener : list) {
            PANEL.removeMouseMotionListener(listener);
        }
    }

    @Override
    public void runMainPage() {
        try {
                AudioManager.stopAllSounds();
                AudioManager.muteIntro();
                new MainPage().initComponents();
                AudioManager.loopSound("intro");
            } catch (IOException |
                    NullPointerException |
                    LineUnavailableException |
                    UnsupportedAudioFileException ex ) {
                throw new RuntimeException("Error catch trying to play intro(MainComponent.class): " + ex);
            }
    }

    @Override
    public void dispose() {
        AudioManager.stopAllSounds();
        AudioManager.muteIntro();
        GameView.getFrame().dispose();
    }
    
    @Override
    public void setAluminiumLook() {
        try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }catch (ClassNotFoundException |
                InstantiationException |
                NullPointerException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
                    throw new RuntimeException("Error catch at GameFrame run() method while setting up theme: " + e);
                }
    }
    
    @Override
    public void setDefaultLook() {
        try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException |
                    UnsupportedLookAndFeelException e ) {
                throw new RuntimeException("Error catch at main method: " + e);
            }
    }
    
    @Override
    public void reloadPanel() {
        this.PANEL.revalidate();
        this.PANEL.repaint();
    }
}