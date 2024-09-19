
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.data.CurrentQuestion;
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
    
    public FiftyToFifty() {
        this.panel = CurrentQuestion.getPanel();
    }
    
    public void removeWrongOptions() {
        
        List<String> falseOptions = Arrays.stream(CurrentQuestion.getOptions())
            .filter(option -> !option.contains(CurrentQuestion.getAnswer()))             
            .collect(Collectors.toList());

        int randomIndex = new Random().nextInt(falseOptions.size()-1);
        falseOptions.remove(randomIndex);
        
        List<JButton> optionButtons = Arrays.asList(
                CurrentQuestion.getOptionA(),
                CurrentQuestion.getOptionB(),
                CurrentQuestion.getOptionC(),
                CurrentQuestion.getOptionD()
        );
        for(int i = 0; i < CurrentQuestion.getOptions().length; i++) {
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
