
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.Border;

/**
 * Builder for JComboBox component, inherits main methods from Builder Interface
 * @author pavel
 */
public interface ComboBoxBuilder <E> extends Builder{
    
    /**
     * Set text on JComboBox
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder text(String text);
    
     /**
     * Set background color on JComboBox
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder background(Color color);
    
    /**
     * Set foreground color on JComboBox
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder foreground(Color color);
    
    /**
     * Set bounds of JComboBox
     * @param x position on panel
     * @param y position on panel
     * @param width of JComboBox
     * @param height of JComboBox
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder bounds(int x, int y, int width, int height);
    
    /**
     * Set font on text of JComboBox
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder font(Font font);
    
    /**
     * Set size of JComboBox
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder size(Dimension d);
    
    /**
     * Set size of JComboBox
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder size(int width, int height);
    
    /**
     * Set borders of JComboBox
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder border(Border border);
    
    /**
     * Add multiple items to JComboBox
     * @param type - array of items
     * @return this object, allowing method chaining
     */
    ComboBoxBuilderImpl addItems(E... type);
    
    /**
     * Add one item to JComboBox
     * @param item to add
     * @return this object, allowing method chaining
     */
    ComboBoxBuilderImpl addItem(E item);
    
    /**
     * Configure JComboBox behavior on hover
     * @return this object, allowing method chaining
     */
    ComboBoxBuilderImpl selectedItemHover();
    
    /**
     * Get JComboBox
     * @return JComboBox
     */
    @Override
    JComboBox get();
    
}
