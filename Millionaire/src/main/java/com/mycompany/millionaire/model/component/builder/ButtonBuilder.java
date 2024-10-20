
package com.mycompany.millionaire.model.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * Builder for JButton component, inherits methods from Builder Interface
 * @author pavel
 */
public interface ButtonBuilder extends Builder {
    
    /**
     * Set image
     * @param image - get image from parameter
     * @return this object, allowing method chaining
     */
    ButtonBuilder setImage(ImageIcon image);
    
    /**
     * Set text on JButton
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setText(String text);
    
    /**
     * Set background color on JButton
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setBackground(Color color);
    
    /**
     * Set foreground color on JButton
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setForeground(Color color);
    
    /**
     * Set bounds of JButton
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setBounds(int x, int y);
    
    /**
     * Set font on text of JButton
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setFont(Font font);
    
    /**
     * Set preferred size of JButton
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setPreferredSize(Dimension d);
    
    /**
     * Set size of JButton
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setSize(int width, int height);
    
    /**
     * Set borders of JButton
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ButtonBuilder setBorder(Border border);
    
    /**
     * Configure JButton behavior on hover
     * @return this object, allowing method chaining
     */
    ButtonBuilder onHover();
    
    /**
     * Configure JButton behavior on click
     * @return this object, allowing method chaining
     */
    ButtonBuilder onClick();
    
    /**
     * Set text align
     * @param position to locate text
     * @return this object, allowing method chaining
     */
    ButtonBuilder setTextHorizontalAlign(int position);
    
    /**
     * Method for complex JButton's configuration to avoid boilerplate code
     * @return this object, allowing method chaining
     */
    ButtonBuilder gameStyleConfig();
    
    /**
     * Another version of complex JButton's configuration to avoid boilerplate
     * code
     * @return this object, allowing method chaining
     */
    ButtonBuilder defaultConfig();
    
    /**
     * Get JButton
     * @return JButton after being set up
     */
    @Override
    JButton get();
    
}
