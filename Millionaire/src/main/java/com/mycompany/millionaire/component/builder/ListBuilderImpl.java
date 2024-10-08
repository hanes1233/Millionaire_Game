
package com.mycompany.millionaire.component.builder;

import com.mycompany.millionaire.model.ComponentServiceImpl;
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
public class ListBuilderImpl implements ListBuilder{
    
    private final JList LIST;
    private final ComponentServiceImpl SERVICE;
    
    public ListBuilderImpl() {
        this.LIST = new JList();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    public ListBuilderImpl(JList list) {
        this.LIST = list;
        this.SERVICE = new ComponentServiceImpl();
    }

    @Override
    public ListBuilder bounds(int x, int y) {
        this.LIST.setBounds(x, y, SERVICE.getWidth(LIST), SERVICE.getHeight(LIST));
        return this;
    }

    @Override
    public ListBuilder background(Color color) {
         this.LIST.setBackground(color);
         return this;
    }

    @Override
    public ListBuilder foreground(Color color) {
         this.LIST.setForeground(color);
         return this;
    }

    @Override
    public ListBuilder model(ListModel model) {
        this.LIST.setModel(model);
        return this;
    }

    @Override
    public ListBuilder selectedIndex(int index) {
        this.LIST.setSelectedIndex(index);
        return this;
    }

    @Override
    public ListBuilder font(Font font) {
        this.LIST.setFont(font);
        return this;
    }

    @Override
    public ListBuilder size(Dimension d) {
         this.LIST.setPreferredSize(d);
         return this;
    }

    @Override
    public ListBuilder size(int width, int height) {
         this.LIST.setSize(width, height);
         return this;
    }

    @Override
    public ListBuilder border(Border border) {
        this.LIST.setBorder(border);
        return this;
    }

    @Override
    public JList get() {
        return this.LIST;
    }

    @Override
    public Object text(String text) {
        throw new UnsupportedOperationException("Not supported on list.");
    }
    
}
