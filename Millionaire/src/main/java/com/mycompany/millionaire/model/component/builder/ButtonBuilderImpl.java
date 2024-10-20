
package com.mycompany.millionaire.model.component.builder;

import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
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
 * Button Builder implementation class
 * @author pavel
 */
public class ButtonBuilderImpl implements ButtonBuilder{
    
    private final JButton BUTTON;
    private final ComponentServiceImpl SERVICE;
    
    public ButtonBuilderImpl() {
        this.BUTTON = new JButton();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    @Override
    public ButtonBuilderImpl setImage(ImageIcon image) {
        this.BUTTON.setIcon(image);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl setText(String text) {
        this.BUTTON.setText(text);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl setBackground(Color color) {
        this.BUTTON.setBackground(color);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl setForeground(Color color) {
        this.BUTTON.setForeground(color);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl setBounds(int x, int y) {
        this.BUTTON.setBounds(x,y,SERVICE.getWidth(BUTTON), SERVICE.getHeight(BUTTON));
        return this;
    }
    
    @Override
    public ButtonBuilderImpl setFont(Font font) {
        this.BUTTON.setFont(font);
        return this;
    }
    
    @Override
    public JButton get() {
        return this.BUTTON;
    }

    @Override
    public ButtonBuilderImpl setPreferredSize(Dimension d) {
        this.BUTTON.setPreferredSize(d);
        return this;
    }

    @Override
    public ButtonBuilderImpl setSize(int width, int height) {
        this.BUTTON.setSize(width,height);
        return this;
    }

    @Override
    public ButtonBuilderImpl setBorder(Border border) {
        this.BUTTON.setBorder(border);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl onHover() {
        this.BUTTON.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change color to orange on hover
                BUTTON.setBackground(new Color(255,153,51));

            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Reset colot to default
                BUTTON.setBackground(new Color(0, 38, 75));
            }
        });
        return this;
    }
    
    @Override
    public ButtonBuilderImpl onClick() {
        this.BUTTON.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            try {
                // Stop playing any sound on click
                AudioManager.muteIntro();
                AudioManager.stopAllSounds();
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
    public ButtonBuilderImpl setTextHorizontalAlign(int position) {
        this.BUTTON.setHorizontalAlignment(position);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl gameStyleConfig() {
        this
            .setFont(new Font("Serif", Font.BOLD, 12))
            .setBackground(new Color(8, 0, 77))
            .setForeground(Color.WHITE)
            .setPreferredSize(new Dimension(220, 35))
            .onClick();
        this.BUTTON.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BUTTON.setBackground(new Color(255,153,51));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BUTTON.setBackground(new Color(8, 0, 77));
            }
        });
        return this;
    }
    
    @Override
    public ButtonBuilderImpl defaultConfig() {
        this
            .setBackground(new Color(0, 38, 75))
            .setForeground(Color.WHITE)
            .onHover()
            .setPreferredSize(new Dimension(150, 35))
            .onClick();
        return this;
    }
    
}
