
package com.mycompany.millionaire.component.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public interface ProgressBarBuilder extends Builder {
    
    @Override
    ProgressBarBuilder text(String text);
    
    @Override
    ProgressBarBuilder background(Color color);
    
    @Override
    ProgressBarBuilder foreground(Color color);
    
    @Override
    ProgressBarBuilder bounds(int x, int y, int width, int height);
    
    @Override
    ProgressBarBuilder font(Font font);
    
    @Override
    ProgressBarBuilder size(Dimension d);
    
    @Override
    ProgressBarBuilder size(int width, int height);
    
    @Override
    ProgressBarBuilder border(Border border);
    
    ProgressBarBuilder value(int value);
    
    @Override
    JProgressBar get(); 
    
}
