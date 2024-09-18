
package com.mycompany.millionaire.component.builder;

import com.mycompany.millionaire.media.AudioManager;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author pavel
 */
public class ButtonBuilderImpl implements ButtonBuilder{
    
    private final JButton button;
    
    
    public ButtonBuilderImpl(JButton button) {
        this.button = button;
    }
    
    public ButtonBuilderImpl() {
        this.button = new JButton();
    }
    
    @Override
    public ButtonBuilderImpl image(ImageIcon image) {
        this.button.setIcon(image);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl text(String text) {
        this.button.setText(text);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl background(Color color) {
        this.button.setBackground(color);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl foreground(Color color) {
        this.button.setForeground(color);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl bounds(int x, int y, int width, int height) {
        this.button.setBounds(x,y,width,height);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl font(Font font) {
        this.button.setFont(font);
        return this;
    }
    
    @Override
    public JButton get() {
        return this.button;
    }

    @Override
    public ButtonBuilderImpl size(Dimension d) {
        this.button.setSize(d);
        return this;
    }

    @Override
    public ButtonBuilderImpl size(int width, int height) {
        this.button.setSize(width,height);
        return this;
    }

    @Override
    public ButtonBuilderImpl border(Border border) {
        this.button.setBorder(border);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl selectedItemHover() {
        this.button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change color to orange on hover
                button.setBackground(new Color(255,153,51));
                try {
                    // Set up hover sound
                    AudioManager.handleAudioEvent("hover");
                } catch (UnsupportedAudioFileException | 
                         IOException | 
                         LineUnavailableException ex) {
                    System.out.print("Error running selectedItemHover() on button: " + ex);
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Changle color back
                button.setBackground(new Color(0, 38, 75));
            }
        });
        return this;
    }
    
    @Override
    public ButtonBuilderImpl clickSound() {
        this.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            try {
                // Stop playing sound on click
                AudioManager.muteIntro();
                // Play click sound
                AudioManager.handleAudioEvent("click");
            }catch (UnsupportedAudioFileException |
                    IOException |
                    LineUnavailableException ex) {
                System.out.print("Error running click() on button: " + ex);
            }
           }
        });
        return this;
    }
    
    @Override
    public ButtonBuilderImpl textHorizontalAlign(int position) {
        this.button.setHorizontalAlignment(position);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl gameStyleConfig() {
        this
            .font(new Font("Serif", Font.BOLD, 12))
            .background(new Color(8, 0, 77))
            .foreground(Color.WHITE)
            .clickSound();
        this.button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255,153,51));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(8, 0, 77));
            }
        });
        return this;
    }
    
    @Override
    public ButtonBuilderImpl defaultConfig() {
        this
            .background(new Color(0, 38, 75))
            .foreground(Color.WHITE)
            .selectedItemHover()
            .clickSound();
        return this;
    }
    
}
