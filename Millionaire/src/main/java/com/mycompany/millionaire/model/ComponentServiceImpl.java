
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.form.FormFactory;
import com.mycompany.millionaire.media.AudioManager;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author pavel
 */
public class ComponentServiceImpl implements ComponentService {
    
    private final FormFactory FACTORY;
    
    public ComponentServiceImpl() {
        this.FACTORY = new FormFactory();
    }

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
    public JPanel removeFromPanel(JPanel panel, JComponent ... components) {
        for(var item : components) {
            panel.remove(item);
        }
        return panel;
    }
    
    @Override
    public JPanel addOnPanel(JPanel panel, JComponent... component) {
        for (JComponent item : component) {
            panel.add(item);
        }
        return panel;
    }
    
    @Override
    public void removeListeners(JComponent component) {
        MouseListener[] list = component.getMouseListeners();
        for(var listener : list) {
            component.removeMouseListener(listener);
        }
    }
    
    @Override
    public void removeMotionListeners(JComponent component) {
        MouseMotionListener[] list = CurrentQuestion.getPanel().getMouseMotionListeners();
        for(var listener : list) {
            component.removeMouseMotionListener(listener);
        }
    }

    @Override
    public void runMainPage(JFrame frame) {
        try {
                AudioManager.stopAllSounds();
                AudioManager.muteIntro();
                FACTORY.createForm(true);
                frame.dispose();
            } catch (IOException |
                    NullPointerException |
                    ClassNotFoundException |
                    InstantiationException |
                    LineUnavailableException |
                    IllegalAccessException | 
                    UnsupportedLookAndFeelException |
                    UnsupportedAudioFileException ex ) {
                throw new RuntimeException("Error catch trying to play intro(MainComponent.class): " + ex);
            }
    }

    @Override
    public void dispose(JFrame frame) {
        AudioManager.stopAllSounds();
        AudioManager.muteIntro();
        frame.dispose();
    }
}
