
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Builder for JLabel component, inherits main methods from Builder Interface
 * @author pavel
 */
public interface LabelBuilder extends Builder {
    
    /**
     * Set image on JLabel
     * @param image - get image from parameter
     * @return this object, allowing method chaining
     */
    LabelBuilder image(ImageIcon image);
    
    /**
     * Set text on JLabel
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder text(String text);
    
    /**
     * Set background color on JLabel
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder background(Color color);
    
     /**
     * Set foreground color on JLabel
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder foreground(Color color);
    
    /**
     * Set bounds of JLabel
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder bounds(int x, int y);
    
    /**
     * Set font on text of JLabel
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder font(Font font);
    
    /**
     * Set size of JLabel
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder size(Dimension d);
    
    /**
     * Set size of JLabel
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder size(int width, int height);
    
    /**
     * Set borders of JLabel
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    LabelBuilder border(Border border);
    
    /**
     * Get JLabel
     * @return JLabel after being set up
     */
    @Override
    JLabel get();
    
    /**
     * Change image on hover
     * @param path used to fetch image
     * @return this object, allowing method chaining
     */
    LabelBuilder hoverImage(String path);
    
    /**
     * Remove mouse listeners
     * @return this object, allowing method chaining
     */
    LabelBuilder removeMouseListeners();
    
    /**
     * Set Y position
     * @param y to get position on panel
     * @return this object, allowing method chaining
     */
    LabelBuilder setY(int y);
    
}
