
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public interface ComboBoxBuilder <E> extends Builder{
    
    @Override
    ComboBoxBuilder text(String text);
    
    @Override
    ComboBoxBuilder background(Color color);
    
    @Override
    ComboBoxBuilder foreground(Color color);
    
    @Override
    ComboBoxBuilder bounds(int x, int y, int width, int height);
    
    @Override
    ComboBoxBuilder font(Font font);
    
    @Override
    ComboBoxBuilder size(Dimension d);
    
    @Override
    ComboBoxBuilder size(int width, int height);
    
    @Override
    ComboBoxBuilder border(Border border);
    
    ComboBoxBuilderImpl addItems(E... type);
    
    ComboBoxBuilderImpl addItem(E item);
    
    ComboBoxBuilderImpl selectedItemHover();
    
    @Override
    JComboBox get();
    
}
