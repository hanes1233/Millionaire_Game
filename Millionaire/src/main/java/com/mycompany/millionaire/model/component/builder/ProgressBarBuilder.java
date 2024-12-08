
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
    ProgressBarBuilder text(String text);
    
    /**
     * Set background color on JProgressBar
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder background(Color color);
    
    /**
     * Set foreground color on JProgressBar
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder foreground(Color color);
    
    /**
     * Set bounds of JProgressBar
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder bounds(int x, int y);
    
    /**
     * Set font on text of JProgressBar
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder font(Font font);
    
    /**
     * Set size of JProgressBar
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder preferredSize(Dimension d);
    
    /**
     * Set size of JProgressBar
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder minSize(int width, int height);
    
    /**
     * Set borders of JProgressBar
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ProgressBarBuilder border(Border border);
    
    /**
     * Set value of JProgressBar
     * @param value - get value from parameter
     * @return this object, allowing method chaining
     */
    ProgressBarBuilder value(int value);
    
    /**
     * Get JProgressBar after being set up
     * @return JProgressBar
     */
    @Override
    JProgressBar build();
    
}
