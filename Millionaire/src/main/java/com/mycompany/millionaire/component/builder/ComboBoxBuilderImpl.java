
package com.mycompany.millionaire.component.builder;

import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComboBox;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public class ComboBoxBuilderImpl implements ComboBoxBuilder{
    
    private final JComboBox COMBO_BOX;
    private final ComponentServiceImpl SERVICE;
    
    
    public ComboBoxBuilderImpl() {
        this.COMBO_BOX = new JComboBox();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    public ComboBoxBuilderImpl(JComboBox comboBox) {
        this.COMBO_BOX = comboBox;
        this.SERVICE = new ComponentServiceImpl();
    }

    @Override
    public ComboBoxBuilderImpl text(String text) {
        return null;
    }

    @Override
    public ComboBoxBuilderImpl background(Color color) {
        this.COMBO_BOX.setBackground(color);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl foreground(Color color) {
        this.COMBO_BOX.setForeground(color);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl bounds(int x, int y) {
        this.COMBO_BOX.setBounds(x,y,SERVICE.getWidth(COMBO_BOX),SERVICE.getHeight(COMBO_BOX));
        return this;
    }

    @Override
    public ComboBoxBuilderImpl font(Font font) {
        this.COMBO_BOX.setFont(font);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl size(Dimension d) {
        this.COMBO_BOX.setPreferredSize(d);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl size(int width, int height) {
        this.COMBO_BOX.setSize(width,height);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl border(Border border) {
        this.COMBO_BOX.setBorder(border);
        return this;
    }
    
    @Override
    public JComboBox get() {
        return this.COMBO_BOX;
    }
    
    @Override
    public ComboBoxBuilderImpl addItems(Object... type) {
        for(var item : type) {
            this.COMBO_BOX.addItem(item);
        }
        return this;
    }
    
    @Override
    public ComboBoxBuilderImpl addItem(Object item) {
        this.COMBO_BOX.addItem(item);
        return this;
    }
    
    @Override
    public ComboBoxBuilderImpl selectedItemHover() {
        this.COMBO_BOX.addItemListener((ItemEvent event) -> {
            if ((event.getStateChange() == ItemEvent.SELECTED)) {
                try {
                    // Play hover sound
                    AudioManager.handleAudioEvent("hover");
                } catch (UnsupportedAudioFileException |
                        IOException |
                        LineUnavailableException ex) {
                    Logger.getLogger(ComboBoxBuilderImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
        });
       
        return this;
    }
    
}
