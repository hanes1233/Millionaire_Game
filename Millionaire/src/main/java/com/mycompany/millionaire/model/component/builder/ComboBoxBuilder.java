package com.mycompany.millionaire.model.component.builder;

import com.mycompany.millionaire.model.media.AudioManager;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ItemEvent;

@Data
@Accessors(chain = true)
public class ComboBoxBuilder<T> {

    private Color background;
    private Color foreground;
    private Font font;
    private Dimension preferredSize;
    private Dimension minimumSize;
    private Dimension maximumSize;
    private Border border;
    @Setter(AccessLevel.NONE)
    protected Integer x, y;

    private JComboBox<T> comboBox;

    public ComboBoxBuilder(JComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }

    public ComboBoxBuilder<T> setBounds(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ComboBoxBuilder<T> onHover() {
        this.comboBox.addItemListener((ItemEvent event) -> {
            if ((event.getStateChange() == ItemEvent.SELECTED)) {
                // Play sound on hover
                AudioManager.handleAudioEvent("hover");
            }
        });

        return this;
    }

    public JComboBox<T> build() {
        if (background != null) this.comboBox.setBackground(background);
        if (foreground != null) this.comboBox.setForeground(foreground);
        if (font != null) this.comboBox.setFont(font);
        if (preferredSize != null) this.comboBox.setPreferredSize(preferredSize);
        if (minimumSize != null) this.comboBox.setMinimumSize(minimumSize);
        if (maximumSize != null) this.comboBox.setMaximumSize(maximumSize);
        if (border != null) this.comboBox.setBorder(border);
        if (x != null && y != null) this.comboBox.setBounds(x, y, comboBox.getPreferredSize().width, comboBox.getPreferredSize().height);
        return comboBox;
    }
}
