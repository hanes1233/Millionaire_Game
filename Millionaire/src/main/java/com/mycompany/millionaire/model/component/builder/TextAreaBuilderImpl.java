
package com.mycompany.millionaire.model.component.builder;

import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * TextArea Builder implementation class
 * @author pavel
 */
public class TextAreaBuilderImpl implements TextAreaBuilder {
    
    private final JTextArea TEXT_AREA;
    private final ComponentServiceImpl SERVICE;
    
    public TextAreaBuilderImpl() {
        this.TEXT_AREA = new JTextArea();
        this.SERVICE = new ComponentServiceImpl();
    }

    public TextAreaBuilderImpl(JTextArea textArea) {
        this.TEXT_AREA = textArea;
        this.SERVICE = new ComponentServiceImpl();
    }
    
    @Override
    public TextAreaBuilderImpl text(String text) {
        this.TEXT_AREA.setText(text);
        return this;
    }

    @Override
    public TextAreaBuilderImpl background(Color color) {
       this.TEXT_AREA.setBackground(color);
       return this;
    }

    @Override
    public TextAreaBuilderImpl foreground(Color color) {
        this.TEXT_AREA.setForeground(color);
        return this;
    }

    @Override
    public TextAreaBuilderImpl bounds(int x, int y) {
         this.TEXT_AREA.setBounds(x, y, SERVICE.getWidth(TEXT_AREA), SERVICE.getHeight(TEXT_AREA));
         return this;
    }

    @Override
    public TextAreaBuilderImpl font(Font font) {
        this.TEXT_AREA.setFont(font);
        return this;
    }

    @Override
    public TextAreaBuilderImpl preferredSize(Dimension d) {
         this.TEXT_AREA.setPreferredSize(d);
         return this;
    }

    @Override
    public TextAreaBuilderImpl minSize(int width, int height) {
         this.TEXT_AREA.setSize(width,height);
         return this;
    }
    
    @Override
    public JTextArea build() {
        return this.TEXT_AREA;
    }

    @Override
    public TextAreaBuilderImpl border(Border border) {
        this.TEXT_AREA.setBorder(border);
        return this;
    }
    
    @Override
    public TextAreaBuilderImpl formatText() {
        this.TEXT_AREA.setWrapStyleWord(true);
        this.TEXT_AREA.setLineWrap(true);
        return this;
    }
    
    @Override
    public TextAreaBuilderImpl readOnly() {
        this.TEXT_AREA.setEditable(false);
        return this;
    }
    
}
