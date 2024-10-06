
package com.mycompany.millionaire.component.builder;

import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.media.ImageManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public class LabelBuilderImpl implements LabelBuilder {
    
    private final JLabel label;
    private final ComponentServiceImpl service;
    
    public LabelBuilderImpl() {
        this.label = new JLabel();
        this.service = new ComponentServiceImpl();
    }
    
     public LabelBuilderImpl(JLabel label) {
        this.label = label;
        this.service = new ComponentServiceImpl();
    }
    
    @Override
    public LabelBuilderImpl image(ImageIcon image) {
        this.label.setIcon(image);
        return this;
    }
    
    @Override
    public LabelBuilderImpl text(String text) {
        this.label.setText(text);
        return this;
    }
    
    @Override
    public LabelBuilderImpl background(Color color) {
        this.label.setBackground(color);
        return this;
    }
    
    @Override
    public LabelBuilderImpl foreground(Color color) {
        this.label.setForeground(color);
        return this;
    }
    
    @Override
    public LabelBuilderImpl bounds(int x, int y) {
        this.label.setBounds(x,y,service.getWidth(label),service.getHeight(label));
        return this;
    }
    
    @Override
    public LabelBuilderImpl setY(int y) {
        this.label.setBounds(
                this.label.getX(), 
                y, 
                this.label.getWidth(), 
                this.label.getHeight()
        );
        return this;
    }
    
    @Override
    public LabelBuilderImpl font(Font font) {
        this.label.setFont(font);
        return this;
    }
    
    @Override
    public JLabel get() {
        return this.label;
    }

    @Override
    public LabelBuilderImpl size(Dimension d) {
        this.label.setPreferredSize(d);
        return this;
    }

    @Override
    public LabelBuilderImpl size(int width, int height) {
        this.label.setSize(width,height);
        return this;
    }

    @Override
    public LabelBuilderImpl border(Border border) {
        this.label.setBorder(border);
        return this;
    }
    
    // Change image on hover, using image's name from parameter
    @Override
    public LabelBuilderImpl hoverImage(String path) {
        Icon currentIcon = this.label.getIcon();
        int x = label.getX();
        int y = label.getY();
        int width = label.getWidth();
        int height = label.getHeight();
        size(new Dimension(width, height));
        this.label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                image(ImageManager.getImageIcon(path));
                bounds(x, y - 2);
                try {
                    // Play sound on hover
                    AudioManager.handleAudioEvent("hover");
                } catch (UnsupportedAudioFileException | 
                         IOException | 
                         LineUnavailableException ex) {
                    System.out.print("Error running hoverImage() on label: " + ex);
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Set back previous image
                label.setIcon(currentIcon);
                bounds(x, y);
            }
        });
        return this;
    }
    
    // Remove all mouse listeners from JLabel
    @Override
    public LabelBuilderImpl removeMouseListeners() {
        MouseListener[] listeners = this.label.getMouseListeners();
                for(var listener : listeners) {
                    this.label.removeMouseListener(listener); 
                }
        return this;
    }
    
}
