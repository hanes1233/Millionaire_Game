
package com.mycompany.millionaire.model;

import java.awt.Dimension;
import javax.swing.JComponent;

/**
 *
 * @author pavel
 */
public class ComponentServiceImpl implements ComponentService {

    @Override
    public int getWidth(JComponent component) {
        Dimension size = component.getPreferredSize();
        return size.width;
    }

    @Override
    public int getHeight(JComponent component) {
        Dimension size = component.getPreferredSize();
        return size.height;
    }
    
}
