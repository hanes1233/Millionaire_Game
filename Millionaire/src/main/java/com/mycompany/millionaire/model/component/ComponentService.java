
package com.mycompany.millionaire.model.component;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author pavel
 */
public interface ComponentService {
    
    public int getWidth(JComponent component);
    
    public int getHeight(JComponent component);
    
    public JPanel removeFromPanel(JComponent ... components);
    
    public void addOnPanel(JComponent... component);
    
    public void removeListeners(JComponent component);
    
    public void removeMotionListeners();
    
    public void runMainPage();
    
    public void dispose();
    
    public void reloadPanel();
    
    public void setAluminiumLook();
    
    public void setDefaultLook();
}
