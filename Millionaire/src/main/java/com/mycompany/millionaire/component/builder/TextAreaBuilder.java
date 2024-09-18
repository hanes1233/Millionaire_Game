
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * Builder for JTextArea component, inherits main methods from Builder Interface
 * @author pavel
 */
public interface TextAreaBuilder extends Builder{
    
    /**
     * Set text on JTextArea
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder text(String text);
    
    /**
     * Set background color on JTextArea
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder background(Color color);
    
    /**
     * Set foreground color on JTextArea
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder foreground(Color color);
    
    /**
     * Set bounds of JTextArea
     * @param x position on panel
     * @param y position on panel
     * @param width of JTextArea
     * @param height of JTextArea
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder bounds(int x, int y, int width, int height);
    
    /**
     * Set font on text of JTextArea
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder font(Font font);
    
    /**
     * Set size of JTextArea
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder size(Dimension d);
    
    /**
     * Set size of JTextArea
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder size(int width, int height);
    
    /**
     * Set borders of JTextArea
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    TextAreaBuilder border(Border border);
    
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
    TextAreaBuilder readOnly();
}
