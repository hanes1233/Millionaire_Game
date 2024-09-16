
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public interface ButtonBuilder extends Builder {
    
    ButtonBuilder image(ImageIcon image);
    
    @Override
    ButtonBuilder text(String text);
    
    @Override
    ButtonBuilder background(Color color);
    
    @Override
    ButtonBuilder foreground(Color color);
    
    @Override
    ButtonBuilder bounds(int x, int y, int width, int height);
    
    @Override
    ButtonBuilder font(Font font);
    
    @Override
    ButtonBuilder size(Dimension d);
    
    @Override
    ButtonBuilder size(int width, int height);
    
    @Override
    ButtonBuilder border(Border border);
    
    ButtonBuilder selectedItemHover();
    
    ButtonBuilder clickSound();
    
    ButtonBuilder textHorizontalAlign(int position);
    
    ButtonBuilder gameStyleConfig();
    
    ButtonBuilder defaultConfig();
    
    @Override
    JButton get();
    
}
