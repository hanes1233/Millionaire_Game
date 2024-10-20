
package com.mycompany.millionaire.model.hint;

import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.view.GameView;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.JButton;
import lombok.NoArgsConstructor;

/**
 *
 * @author pavel
 */
@NoArgsConstructor
public class FiftyToFiftyService {
    
    private CurrentQuestion currentQuestion;
    
    public FiftyToFiftyService(CurrentQuestion currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
   
    public void removeWrongOptions() {
        
        List<String> falseOptions = Arrays.stream(currentQuestion.getOptions())
            .filter(option -> !option.contains(currentQuestion.getAnswer()))             
            .collect(Collectors.toList());

        int randomIndex = new Random().nextInt(falseOptions.size()-1);
        falseOptions.remove(randomIndex);
        
        List<JButton> optionButtons = Arrays.asList(
                currentQuestion.getOptionA(),
                currentQuestion.getOptionB(),
                currentQuestion.getOptionC(),
                currentQuestion.getOptionD()
        );
        for(int i = 0; i < currentQuestion.getOptions().length; i++) {
            String currentOption = optionButtons.get(i).getText();
            if(currentOption.contains(falseOptions.get(0)) || currentOption.contains(falseOptions.get(1)) ) {
                optionButtons.get(i).setText("");
                MouseListener[] listeners = optionButtons.get(i).getMouseListeners();
                for(MouseListener listener : listeners) {
                    optionButtons.get(i).removeMouseListener(listener);
                }
                GameView.getPanel().revalidate();
                GameView.getPanel().repaint();
            }
        }
    }
    
}
