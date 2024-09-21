
package com.mycompany.millionaire.model;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;

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
    
    @Override
    public JPanel removeFromPanel(JPanel panel, JComponent ... components) {
        for(var item : components) {
            panel.remove(item);
        }
        return panel;
    }
    
    @Override
    public JPanel addOnPanel(JPanel panel, JComponent... component) {
        for (JComponent item : component) {
            panel.add(item);
        }
        return panel;
    }
    
}
