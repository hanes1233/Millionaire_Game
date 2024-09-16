
package com.mycompany.millionaire.component.builder;

import com.mycompany.millionaire.media.AudioManager;
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
    
    private final JComboBox comboBox;
    
    
    public ComboBoxBuilderImpl() {
        this.comboBox = new JComboBox();
    }
    
    public ComboBoxBuilderImpl(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public ComboBoxBuilderImpl text(String text) {
        return null;
    }

    @Override
    public ComboBoxBuilderImpl background(Color color) {
        this.comboBox.setBackground(color);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl foreground(Color color) {
        this.comboBox.setForeground(color);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl bounds(int x, int y, int width, int height) {
        this.comboBox.setBounds(x,y,width,height);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl font(Font font) {
        this.comboBox.setFont(font);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl size(Dimension d) {
        this.comboBox.setPreferredSize(d);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl size(int width, int height) {
        this.comboBox.setSize(width,height);
        return this;
    }

    @Override
    public ComboBoxBuilderImpl border(Border border) {
        this.comboBox.setBorder(border);
        return this;
    }
    
    @Override
    public JComboBox get() {
        return this.comboBox;
    }
    
    @Override
    public ComboBoxBuilderImpl addItems(Object... type) {
        for(var item : type) {
            this.comboBox.addItem(item);
        }
        return this;
    }
    
    @Override
    public ComboBoxBuilderImpl addItem(Object item) {
        this.comboBox.addItem(item);
        return this;
    }
    
    @Override
    public ComboBoxBuilderImpl selectedItemHover() {
        this.comboBox.addItemListener((ItemEvent event) -> {
            if ((event.getStateChange() == ItemEvent.SELECTED)) {
                try {
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
