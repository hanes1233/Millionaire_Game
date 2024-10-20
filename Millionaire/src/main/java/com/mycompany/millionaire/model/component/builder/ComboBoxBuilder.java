
package com.mycompany.millionaire.model.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.Border;

/**
 * Builder for JComboBox component, inherits methods from Builder Interface
 * @author pavel
 */
public interface ComboBoxBuilder <E> extends Builder{
    
    /**
     * Set text on JComboBox
     * @param text - get text from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setText(String text);
    
     /**
     * Set background color on JComboBox
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setBackground(Color color);
    
    /**
     * Set foreground color on JComboBox
     * @param color - get color from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setForeground(Color color);
    
    /**
     * Set bounds of JComboBox
     * @param x position on panel
     * @param y position on panel
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setBounds(int x, int y);
    
    /**
     * Set font on text of JComboBox
     * @param font - get font from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setFont(Font font);
    
    /**
     * Set size of JComboBox
     * @param d - get Dimension from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setPreferredSize(Dimension d);
    
    /**
     * Set size of JComboBox
     * @param width - get width from parameter
     * @param height - get height from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setSize(int width, int height);
    
    /**
     * Set borders of JComboBox
     * @param border - get border from parameter
     * @return this object, allowing method chaining
     */
    @Override
    ComboBoxBuilder setBorder(Border border);
    
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
    ComboBoxBuilderImpl onHover();
    
    /**
     * Get JComboBox
     * @return JComboBox
     */
    @Override
    JComboBox get();
    
}
