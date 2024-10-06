
package com.mycompany.millionaire.component.builder;

import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public class TextAreaBuilderImpl implements TextAreaBuilder {
    
    private final JTextArea textArea;
    private final ComponentServiceImpl service;
    
    public TextAreaBuilderImpl() {
        this.textArea = new JTextArea();
        this.service = new ComponentServiceImpl();
    }

    public TextAreaBuilderImpl(JTextArea textArea) {
        this.textArea = textArea;
        this.service = new ComponentServiceImpl();
    }
    
    @Override
    public TextAreaBuilderImpl text(String text) {
        this.textArea.setText(text);
        return this;
    }

    @Override
    public TextAreaBuilderImpl background(Color color) {
       this.textArea.setBackground(color);
       return this;
    }

    @Override
    public TextAreaBuilderImpl foreground(Color color) {
        this.textArea.setForeground(color);
        return this;
    }

    @Override
    public TextAreaBuilderImpl bounds(int x, int y) {
         this.textArea.setBounds(x, y, service.getWidth(textArea), service.getHeight(textArea));
         return this;
    }

    @Override
    public TextAreaBuilderImpl font(Font font) {
        this.textArea.setFont(font);
        return this;
    }

    @Override
    public TextAreaBuilderImpl size(Dimension d) {
         this.textArea.setPreferredSize(d);
         return this;
    }

    @Override
    public TextAreaBuilderImpl size(int width, int height) {
         this.textArea.setSize(width,height);
         return this;
    }
    
    @Override
    public JTextArea get() {
        return this.textArea;
    }

    @Override
    public TextAreaBuilderImpl border(Border border) {
        this.textArea.setBorder(border);
        return this;
    }
    
    @Override
    public TextAreaBuilderImpl formatText() {
        this.textArea.setWrapStyleWord(true);
        this.textArea.setLineWrap(true);
        return this;
    }
    
    @Override
    public TextAreaBuilderImpl readOnly() {
        this.textArea.setEditable(false);
        return this;
    }
    
}
