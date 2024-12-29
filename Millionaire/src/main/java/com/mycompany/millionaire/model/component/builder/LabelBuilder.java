package com.mycompany.millionaire.model.component.builder;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.stream.Stream;

/**
 * Class for simplifying JLabel objects creation
 * using method chaining
 * @author Pavel H.
 */

@Data
@Accessors(chain = true)
public class LabelBuilder {

    private String text;
    private ImageIcon image;
    private Color background;
    private Color foreground;
    private Font font;
    private Dimension preferredSize;
    private Dimension minimumSize;
    private Dimension maximumSize;
    private Border border;
    @Setter(AccessLevel.NONE)
    protected Integer x, y;

    private JLabel label;

    public LabelBuilder() {
        this.label = new JLabel();
    }

    public LabelBuilder(JLabel label) {
        this.label = label;
    }

    public LabelBuilder setBounds(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    // Remove all mouse listeners from JLabel
    public LabelBuilder removeMouseListeners() {
        Stream.of(this.label.getMouseListeners())
                .forEach(listener -> this.label.removeMouseListener(listener));
        return this;
    }

    public JLabel build() {
        if (text != null) this.label.setText(text);
        if (image != null) this.label.setIcon(image);
        if (background != null) this.label.setBackground(background);
        if (foreground != null) this.label.setForeground(foreground);
        if (font != null) this.label.setFont(font);
        if (preferredSize != null) this.label.setPreferredSize(preferredSize);
        if (minimumSize != null) this.label.setMinimumSize(minimumSize);
        if (maximumSize != null) this.label.setMaximumSize(maximumSize);
        if (border != null) this.label.setBorder(border);
        if (x != null && y != null) this.label.setBounds(x, y, label.getPreferredSize().width, label.getPreferredSize().height);
        return this.label;
    }

}
