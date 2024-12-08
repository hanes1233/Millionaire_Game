
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
    
    private final JButton button;
    private final ComponentServiceImpl service;

    public ButtonBuilderImpl() {
        this.button = new JButton();
        this.service = new ComponentServiceImpl();
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
    public ButtonBuilderImpl bounds(int x, int y) {
        this.button.setBounds(x,y, service.getWidth(button), service.getHeight(button));
        return this;
    }
    
    @Override
    public ButtonBuilderImpl font(Font font) {
        this.button.setFont(font);
        return this;
    }
    
    @Override
    public JButton build() {
        return this.button;
    }

    @Override
    public ButtonBuilderImpl preferredSize(Dimension d) {
        this.button.setPreferredSize(d);
        return this;
    }

    @Override
    public ButtonBuilderImpl minSize(int width, int height) {
        this.button.setSize(width,height);
        return this;
    }

    @Override
    public ButtonBuilderImpl border(Border border) {
        this.button.setBorder(border);
        return this;
    }
    
    @Override
    public ButtonBuilderImpl onHover() {
        this.button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change color to orange on hover
                button.setBackground(new Color(255,153,51));

            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Reset colot to default
                button.setBackground(new Color(0, 38, 75));
            }
        });
        return this;
    }
    
    @Override
    public ButtonBuilderImpl onClick() {
        this.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Stop playing any sound on click
                AudioManager.muteIntro();
                AudioManager.stopAllSounds();
                // Play click sound
                AudioManager.handleAudioEvent("click");
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
            .font(new Font(Font.SERIF, Font.BOLD, 12))
            .background(new Color(8, 0, 77))
            .foreground(Color.WHITE)
            .preferredSize(new Dimension(220, 35))
            .onClick();
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
            .onHover()
            .preferredSize(new Dimension(150, 35))
            .onClick();
        return this;
    }
    
}
