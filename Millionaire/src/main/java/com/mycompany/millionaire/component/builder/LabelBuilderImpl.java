
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
    
    private final JLabel LABEL;
    private final ComponentServiceImpl SERVICE;
    
    public LabelBuilderImpl() {
        this.LABEL = new JLabel();
        this.SERVICE = new ComponentServiceImpl();
    }
    
     public LabelBuilderImpl(JLabel label) {
        this.LABEL = label;
        this.SERVICE = new ComponentServiceImpl();
    }
    
    @Override
    public LabelBuilderImpl image(ImageIcon image) {
        this.LABEL.setIcon(image);
        return this;
    }
    
    @Override
    public LabelBuilderImpl text(String text) {
        this.LABEL.setText(text);
        return this;
    }
    
    @Override
    public LabelBuilderImpl background(Color color) {
        this.LABEL.setBackground(color);
        return this;
    }
    
    @Override
    public LabelBuilderImpl foreground(Color color) {
        this.LABEL.setForeground(color);
        return this;
    }
    
    @Override
    public LabelBuilderImpl bounds(int x, int y) {
        this.LABEL.setBounds(x,y,SERVICE.getWidth(LABEL),SERVICE.getHeight(LABEL));
        return this;
    }
    
    @Override
    public LabelBuilderImpl setY(int y) {
        this.LABEL.setBounds(this.LABEL.getX(), 
                y, 
                this.LABEL.getWidth(), 
                this.LABEL.getHeight()
        );
        return this;
    }
    
    @Override
    public LabelBuilderImpl font(Font font) {
        this.LABEL.setFont(font);
        return this;
    }
    
    @Override
    public JLabel get() {
        return this.LABEL;
    }

    @Override
    public LabelBuilderImpl size(Dimension d) {
        this.LABEL.setPreferredSize(d);
        return this;
    }

    @Override
    public LabelBuilderImpl size(int width, int height) {
        this.LABEL.setSize(width,height);
        return this;
    }

    @Override
    public LabelBuilderImpl border(Border border) {
        this.LABEL.setBorder(border);
        return this;
    }
    
    // Change image on hover, using image's name from parameter
    @Override
    public LabelBuilderImpl hoverImage(String path) {
        Icon currentIcon = this.LABEL.getIcon();
        int x = LABEL.getX();
        int y = LABEL.getY();
        int width = LABEL.getWidth();
        int height = LABEL.getHeight();
        size(new Dimension(width, height));
        this.LABEL.addMouseListener(new java.awt.event.MouseAdapter() {
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
                LABEL.setIcon(currentIcon);
                bounds(x, y);
            }
        });
        return this;
    }
    
    // Remove all mouse listeners from JLabel
    @Override
    public LabelBuilderImpl removeMouseListeners() {
        MouseListener[] listeners = this.LABEL.getMouseListeners();
                for(var listener : listeners) {
                    this.LABEL.removeMouseListener(listener); 
                }
        return this;
    }
    
}
