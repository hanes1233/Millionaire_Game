
package com.mycompany.millionaire.component.builder;

import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author pavel
 */
public class ProgressBarBuilderImpl implements ProgressBarBuilder {
    
    private final JProgressBar progressBar;
    private final ComponentServiceImpl service;
    
    public ProgressBarBuilderImpl(JProgressBar progressBar) {
        this.progressBar = progressBar;
        this.service = new ComponentServiceImpl();
    }

    @Override
    public ProgressBarBuilderImpl text(String text) {
        this.progressBar.setString(text);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl background(Color color) {
        this.progressBar.setBackground(color);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl foreground(Color color) {
        this.progressBar.setForeground(color);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl bounds(int x, int y) {
        this.progressBar.setBounds(x, y, service.getWidth(progressBar), service.getHeight(progressBar));
        return this;
    }

    @Override
    public ProgressBarBuilderImpl font(Font font) {
        this.progressBar.setFont(font);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl size(Dimension d) {
        this.progressBar.setPreferredSize(d);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl size(int width, int height) {
        this.progressBar.setSize(width,height);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl border(Border border) {
        this.progressBar.setBorder(border);
        return this;
    }
    
    @Override
    public ProgressBarBuilderImpl value(int value) {
        this.progressBar.setValue(value);
        return this;
    }

    @Override
    public JProgressBar get() {
        return this.progressBar;
    }
    
}
