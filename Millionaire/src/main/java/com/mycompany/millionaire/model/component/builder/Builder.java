
package com.mycompany.millionaire.model.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 * JComponent Builder interface
 * Made to improve readability of code using Method chaining pattern
 * @param <T>
 */
public interface Builder<T> {
    
    /**
     * Set text
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    T text(String text);
    
    /**
     * Set background color
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    T background(Color color);
    
    /**
     * Set foreground color
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    T foreground(Color color);
    
    /**
     * Set bounds of JComponent
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    T bounds(int x, int y);
    
    /**
     * Set font on text
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    T font(Font font);
    
    /**
     * Set size
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    T preferredSize(Dimension d);
    
    /**
     * Set size
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    T minSize(int width, int height);
    
    /**
     * Set borders
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    T border(Border border);
    
    /**
     * Get JComponent after setting up
     * @return JComponent
     */
    Object build();
    
}
