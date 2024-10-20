
package com.mycompany.millionaire.model.component.builder;

import com.mycompany.millionaire.model.media.ImageManager;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Label Builder implementation class
 * @author pavel
 */
public class LabelBuilderImpl implements LabelBuilder {
    
    private final JLabel LABEL;
    private final ComponentServiceImpl SERVICE;
    
    public LabelBuilderImpl() {
        this.LABEL = new JLabel();
        this.SERVICE = new ComponentServiceImpl();
    }
    
     public LabelBuilderImpl(JLabel label) {
        this.LABEL = label;
        this.SERVICE = new ComponentServiceImpl();
    }
    
    @Override
    public LabelBuilderImpl setImage(ImageIcon image) {
        this.LABEL.setIcon(image);
        return this;
    }
    
    @Override
    public LabelBuilderImpl setText(String text) {
        this.LABEL.setText(text);
        return this;
    }
    
    @Override
    public LabelBuilderImpl setBackground(Color color) {
        this.LABEL.setBackground(color);
        return this;
    }
    
    @Override
    public LabelBuilderImpl setForeground(Color color) {
        this.LABEL.setForeground(color);
        return this;
    }
    
    @Override
    public LabelBuilderImpl setBounds(int x, int y) {
        this.LABEL.setBounds(x,y,SERVICE.getWidth(LABEL),SERVICE.getHeight(LABEL));
        return this;
    }
    
    @Override
    public LabelBuilderImpl setY(int y) {
        this.LABEL.setBounds(this.LABEL.getX(), 
                y, 
                this.LABEL.getWidth(), 
                this.LABEL.getHeight()
        );
        return this;
    }
    
    @Override
    public LabelBuilderImpl setFont(Font font) {
        this.LABEL.setFont(font);
        return this;
    }
    
    @Override
    public JLabel get() {
        return this.LABEL;
    }

    @Override
    public LabelBuilderImpl setPreferredSize(Dimension d) {
        this.LABEL.setPreferredSize(d);
        return this;
    }

    @Override
    public LabelBuilderImpl setSize(int width, int height) {
        this.LABEL.setSize(width,height);
        return this;
    }

    @Override
    public LabelBuilderImpl setBorder(Border border) {
        this.LABEL.setBorder(border);
        return this;
    }
    
    // Change image on hover, using image's name from parameter
    @Override
    public LabelBuilderImpl onHover(String path) {
        Icon currentIcon = this.LABEL.getIcon();
        int x = LABEL.getX();
        int y = LABEL.getY();
        int width = LABEL.getWidth();
        int height = LABEL.getHeight();
        setPreferredSize(new Dimension(width, height));
        this.LABEL.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setImage(ImageManager.getImageIcon(path));
                setBounds(x, y - 2);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Set back previous image
                LABEL.setIcon(currentIcon);
                setBounds(x, y);
            }
        });
        return this;
    }
    
    // Remove all mouse listeners from JLabel
    @Override
    public LabelBuilderImpl removeMouseListeners() {
        MouseListener[] listeners = this.LABEL.getMouseListeners();
                for(var listener : listeners) {
                    this.LABEL.removeMouseListener(listener); 
                }
        return this;
    }
    
}
