
package com.mycompany.millionaire.model.component.builder;

import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 * Progress Bar implementation class
 * @author pavel
 */
public class ProgressBarBuilderImpl implements ProgressBarBuilder {
    
    private final JProgressBar PROGRESS_BAR;
    private final ComponentServiceImpl SERVICE;
    
    public ProgressBarBuilderImpl(JProgressBar progressBar) {
        this.PROGRESS_BAR = progressBar;
        this.SERVICE = new ComponentServiceImpl();
    }

    @Override
    public ProgressBarBuilderImpl setText(String text) {
        this.PROGRESS_BAR.setString(text);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl setBackground(Color color) {
        this.PROGRESS_BAR.setBackground(color);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl setForeground(Color color) {
        this.PROGRESS_BAR.setForeground(color);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl setBounds(int x, int y) {
        this.PROGRESS_BAR.setBounds(x, y, SERVICE.getWidth(PROGRESS_BAR), SERVICE.getHeight(PROGRESS_BAR));
        return this;
    }

    @Override
    public ProgressBarBuilderImpl setFont(Font font) {
        this.PROGRESS_BAR.setFont(font);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl setPreferredSize(Dimension d) {
        this.PROGRESS_BAR.setPreferredSize(d);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl setSize(int width, int height) {
        this.PROGRESS_BAR.setSize(width,height);
        return this;
    }

    @Override
    public ProgressBarBuilderImpl setBorder(Border border) {
        this.PROGRESS_BAR.setBorder(border);
        return this;
    }
    
    @Override
    public ProgressBarBuilderImpl setValue(int value) {
        this.PROGRESS_BAR.setValue(value);
        return this;
    }

    @Override
    public JProgressBar get() {
        return this.PROGRESS_BAR;
    }
    
}
