
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public interface ListBuilder extends Builder {
    
    @Override
    ListBuilder bounds(int x, int y, int width, int height);
    
    @Override
    ListBuilder background(Color color);
    
    @Override
    ListBuilder foreground(Color color);
    
    @Override
    ListBuilder font(Font font);
    
    @Override
    ListBuilder size(Dimension d);
    
    @Override
    ListBuilder size(int width, int height);
    
    @Override
    ListBuilder border(Border border);
    
    @Override
    Object text(String text);
    
    @Override
    JList get();
    
    ListBuilder model(ListModel model);
    
    ListBuilder selectedIndex(int index);
    
    ListBuilder prefferedSize(Dimension d);
    
}
