
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.data.Question;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author pavel
 */
public class FiftyToFifty {
    
    private final JPanel panel;
    private final Question currentQuestion;
    
    public FiftyToFifty(Question currentQuestion, JPanel panel) {
        this.currentQuestion = currentQuestion;
        this.panel = panel;
    }
    
    public void removeWrongOptions(JButton... optionParams) {
        String[] options = this.currentQuestion.getOptions();
        String answer = this.currentQuestion.getAnswer();
        
        List<String> falseOptions = Arrays.stream(options)
            .filter(option -> !option.contains(answer))             
            .collect(Collectors.toList());

        int randomIndex = new Random().nextInt(falseOptions.size()-1);
        falseOptions.remove(randomIndex);
        
        List<JButton> optionButtons = Arrays.asList(optionParams);
        
        for(int i = 0; i < options.length; i++) {
            String currentOption = optionButtons.get(i).getText();
            if(currentOption.contains(falseOptions.get(0)) || currentOption.contains(falseOptions.get(1)) ) {
                optionButtons.get(i).setText("");
                MouseListener[] listeners = optionButtons.get(i).getMouseListeners();
                for(MouseListener listener : listeners) {
                    optionButtons.get(i).removeMouseListener(listener);
                }
                this.panel.revalidate();
                this.panel.repaint();
            }
        }
    }
    
}
