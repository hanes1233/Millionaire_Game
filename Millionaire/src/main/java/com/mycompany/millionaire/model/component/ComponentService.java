
package com.mycompany.millionaire.model.component;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Interface provides methods for JComponents
 * @author pavel
 */
public interface ComponentService {
    
    /**
     * Get component's preferred width
     * @param component to get width of
     * @return width
     */
    public int getWidth(JComponent component);
    
    /**
     * Get component's preferred height
     * @param component to get height of
     * @return height
     */
    public int getHeight(JComponent component);
    
    /**
     * Remove multiple components from JPanel
     * @param components to remove
     * @return JPanel after components removing
     */
    public JPanel removeFromPanel(JComponent ... components);
    
    /**
     * Add multiple components on JPanel
     * @param component - components to add
     */
    public void addOnPanel(JComponent... component);
    
    /**
     * Remove motion listeners from JPanel
     */
    public void removeMotionListeners();
    
    /**
     * Clear panel and run main(Welcome) page
     */
    public void runMainPage();
    
    /**
     * Close current JFrame
     */
    public void dispose();
    
    /**
     * Revalidate and repaint JPanel
     */
    public void reloadPanel();
    
    /**
     * Set aluminium look
     */
    public void setAluminiumLook();
    
    /**
     * Set nimbus look
     */
    public void setDefaultLook();
}
