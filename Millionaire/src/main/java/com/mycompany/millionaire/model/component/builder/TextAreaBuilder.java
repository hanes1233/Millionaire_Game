
package com.mycompany.millionaire.model.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * Builder for JTextArea component, inherits methods from Builder Interface
 * @author pavel
 */
public interface TextAreaBuilder extends Builder{
    
    /**
     * Set text on JTextArea
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setText(String text);
    
    /**
     * Set background color on JTextArea
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setBackground(Color color);
    
    /**
     * Set foreground color on JTextArea
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setForeground(Color color);
    
    /**
     * Set bounds of JTextArea
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setBounds(int x, int y);
    
    /**
     * Set font on text of JTextArea
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setFont(Font font);
    
    /**
     * Set size of JTextArea
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setPreferredSize(Dimension d);
    
    /**
     * Set size of JTextArea
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setSize(int width, int height);
    
    /**
     * Set borders of JTextArea
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder setBorder(Border border);
    
    /**
     * Get JTextArea after being set up
     * @return JTextArea
     */
    @Override
    JTextArea get();
    
    /**
     * Format text of JTextArea
     * @return this object, allowing method chaining
     */
    TextAreaBuilder formatText();
    
    /**
     * Make JTextArea read-only
     * @return this object, allowing method chaining
     */
    TextAreaBuilder setReadOnly();
}
