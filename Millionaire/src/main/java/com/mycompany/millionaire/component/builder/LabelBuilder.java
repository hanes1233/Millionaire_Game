
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public interface LabelBuilder extends Builder {
    
    LabelBuilder image(ImageIcon image);
    
    @Override
    LabelBuilder text(String text);
    
    @Override
    LabelBuilder background(Color color);
    
    @Override
    LabelBuilder foreground(Color color);
    
    @Override
    LabelBuilder bounds(int x, int y, int width, int height);
    
    @Override
    LabelBuilder font(Font font);
    
    @Override
    LabelBuilder size(Dimension d);
    
    @Override
    LabelBuilder size(int width, int height);
    
    @Override
    LabelBuilder border(Border border);
    
    @Override
    JLabel get();
    
    LabelBuilder hoverImage(String path);
    
    LabelBuilder removeMouseListeners();
    
    LabelBuilder setY(int y);
    
}
