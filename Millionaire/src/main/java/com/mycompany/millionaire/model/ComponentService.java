
package com.mycompany.millionaire.model;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author pavel
 */
public interface ComponentService {
    
    public int getWidth(JComponent component);
    
    public int getHeight(JComponent component);
    
    public JPanel removeFromPanel(JPanel panel, JComponent ... components);
    
    public JPanel addOnPanel(JPanel panel, JComponent... component);
    
    public void removeListeners(JComponent component);
    
    public void removeMotionListeners(JComponent component);
    
    
}
