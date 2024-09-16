
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 * @param <T>
 */
public interface Builder<T> {
    
    T text(String text);
    
    T background(Color color);
    
    T foreground(Color color);
    
    T bounds(int x, int y, int width, int height);
    
    T font(Font font);
    
    T size(Dimension d);
    
    T size(int width, int height);
    
    T border(Border border);
    
    Object get();
    
}
