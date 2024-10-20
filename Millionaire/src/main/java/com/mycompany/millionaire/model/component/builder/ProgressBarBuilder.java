
package com.mycompany.millionaire.model.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 * Builder for JProgressBar component, inherits methods from Builder Interface
 * @author pavel
 */
public interface ProgressBarBuilder extends Builder {
   
    /**
     * Set text on JProgressBar
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setText(String text);
    
    /**
     * Set background color on JProgressBar
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setBackground(Color color);
    
    /**
     * Set foreground color on JProgressBar
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setForeground(Color color);
    
    /**
     * Set bounds of JProgressBar
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setBounds(int x, int y);
    
    /**
     * Set font on text of JProgressBar
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setFont(Font font);
    
    /**
     * Set size of JProgressBar
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setPreferredSize(Dimension d);
    
    /**
     * Set size of JProgressBar
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setSize(int width, int height);
    
    /**
     * Set borders of JProgressBar
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder setBorder(Border border);
    
    /**
     * Set value of JProgressBar
     * @param value - get value from parameter
     * @return this object, allowing method chaining
     */
    ProgressBarBuilder setValue(int value);
    
    /**
     * Get JProgressBar after being set up
     * @return JProgressBar
     */
    @Override
    JProgressBar get(); 
    
}
