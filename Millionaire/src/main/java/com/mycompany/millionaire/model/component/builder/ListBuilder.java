
package com.mycompany.millionaire.model.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.Border;

/**
 * Builder for JList component, inherits methods from Builder Interface 
 * @author pavel
 */
public interface ListBuilder extends Builder {
    
      /**
     * Set bounds of JList
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    @Override
    ListBuilder bounds(int x, int y);
    
    /**
     * Set background color on JList
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ListBuilder background(Color color);
    
    /**
     * Set foreground color on JList
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ListBuilder foreground(Color color);
    
    /**
     * Set font on text of JList
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ListBuilder font(Font font);
    
    /**
     * Set size of JList
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ListBuilder preferredSize(Dimension d);
    
    /**
     * Set size of JList
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ListBuilder minSize(int width, int height);
    
    /**
     * Set borders of JList
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ListBuilder border(Border border);
    
    /**
     * Overriden unsupported method
     * @param text
     * @return 
     */
    @Override
    Object text(String text);
    
    /**
     * Get JList after being set up 
     * @return JList
     */
    @Override
    JList build();
    
    /**
     * Set model of JList
     * @param model - get model from parameter
     * @return this object, allowing method chaining
     */
    ListBuilder model(ListModel model);
    
    /**
     * Set selected index of JList
     * @param index - get selected index from parameter
     * @return this object, allowing method chaining
     */
    ListBuilder selectedIndex(int index);
}
