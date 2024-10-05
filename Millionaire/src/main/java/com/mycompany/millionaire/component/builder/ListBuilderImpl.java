
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
    
    private final JList list;
    private final ComponentServiceImpl service;
    
    public ListBuilderImpl() {
        this.list = new JList();
        this.service = new ComponentServiceImpl();
    }
    
    public ListBuilderImpl(JList list) {
        this.list = list;
        this.service = new ComponentServiceImpl();
    }

    @Override
    public ListBuilder bounds(int x, int y) {
        this.list.setBounds(x, y, service.getWidth(list), service.getHeight(list));
        return this;
    }

    @Override
    public ListBuilder background(Color color) {
         this.list.setBackground(color);
         return this;
    }

    @Override
    public ListBuilder foreground(Color color) {
         this.list.setForeground(color);
         return this;
    }

    @Override
    public ListBuilder model(ListModel model) {
        this.list.setModel(model);
        return this;
    }

    @Override
    public ListBuilder selectedIndex(int index) {
        this.list.setSelectedIndex(index);
        return this;
    }

    @Override
    public ListBuilder prefferedSize(Dimension d) {
        this.list.setPreferredSize(d);
        return this;
    }

    @Override
    public ListBuilder font(Font font) {
        this.list.setFont(font);
        return this;
    }

    @Override
    public ListBuilder size(Dimension d) {
         this.list.setSize(d);
         return this;
    }

    @Override
    public ListBuilder size(int width, int height) {
         this.list.setSize(width, height);
         return this;
    }

    @Override
    public ListBuilder border(Border border) {
        this.list.setBorder(border);
        return this;
    }

    @Override
    public JList get() {
        return this.list;
    }

    @Override
    public Object text(String text) {
        throw new UnsupportedOperationException("Not supported on list.");
    }
    
}
