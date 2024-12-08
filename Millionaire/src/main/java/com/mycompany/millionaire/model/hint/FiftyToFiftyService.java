
package com.mycompany.millionaire.model.hint;

import com.mycompany.millionaire.data.entity.Question;
import com.mycompany.millionaire.view.GameView;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.JButton;
import lombok.NoArgsConstructor;

/**
 * Fifty to fifty hint service
 * @author pavel
 */
@NoArgsConstructor
public class FiftyToFiftyService {
    
    // Declare current question
    private Question currentQuestion;
    
    // Constructor
    public FiftyToFiftyService(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
   
    // Remove two random false options
    public void removeWrongOptions() {
        
        // Create list of false options
        List<String> falseOptions = Arrays.stream(currentQuestion.getOptions())
            .filter(option -> !option.contains(currentQuestion.getAnswer()))             
            .collect(Collectors.toList());

        // Remove one random element from falseOptions list
        int randomIndex = new Random().nextInt(falseOptions.size()-1);
        falseOptions.remove(randomIndex);
        
        // Create list of JButtons
        List<JButton> optionButtons = Arrays.asList(
                currentQuestion.getOptionA(),
                currentQuestion.getOptionB(),
                currentQuestion.getOptionC(),
                currentQuestion.getOptionD()
        );
        
        // Iterate over options
        for(int i = 0; i < currentQuestion.getOptions().length; i++) {
            String currentOption = optionButtons.get(i).getText();
            
            // Check is current option equals to any wrong option in falseOptions list
            if(currentOption.contains(falseOptions.get(0)) || currentOption.contains(falseOptions.get(1)) ) {
                
                // if so, clear text of this option in JButton
                optionButtons.get(i).setText("");
                
                // Remove listeners of this JButton, so we are achiving just empty button blank
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
