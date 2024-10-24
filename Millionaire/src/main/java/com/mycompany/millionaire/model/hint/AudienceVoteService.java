
package com.mycompany.millionaire.model.hint;

import com.mycompany.millionaire.controller.hint.AudienceVote;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.view.GameView;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author pavel
 */
public class AudienceVoteService extends AudienceVote {

    // Constructor creates new AudioVote controller instance
    public AudienceVoteService(Question currentQuestion) {
        super(currentQuestion);
    }
    
    /**
     * 
     */
    public void performAudienceVoting() {
        String currentDifficulty = CURRENT_QUESTION.getQuestionDifficulty();
        String answer = CURRENT_QUESTION.getAnswer();
        
        int[] percentageValues = switch (currentDifficulty) {
            case "Easy" -> getSortedArray(30);
            case "Medium" -> getSortedArray(50);
            case "Hard" -> getSortedArray(80);
            default -> getSortedArray(95);
        };
        
        Queue<Integer> otherPercentages = new PriorityQueue<>();
        
        for(int i = percentageValues.length-2; i >= 0; i--) {
            otherPercentages.add(percentageValues[i]);
        }
        
        if(optionA.getText().contains(answer)) {
            loadingProgress(percentageValues[3], otherPercentages.poll(), otherPercentages.poll(), otherPercentages.poll());
        }else if(optionB.getText().contains(answer)) {
            loadingProgress(otherPercentages.poll(), percentageValues[3], otherPercentages.poll(), otherPercentages.poll());
        }else if(optionC.getText().contains(answer)) {
            loadingProgress(otherPercentages.poll(), otherPercentages.poll(), percentageValues[3], otherPercentages.poll());
        }else if(optionD.getText().contains(answer)) {
            loadingProgress(otherPercentages.poll(), otherPercentages.poll(), otherPercentages.poll(), percentageValues[3]);
        }   
    }
    
    private int[] getSortedArray(int range) {
       int hundredPercents = 100;
       int[] percentageValues = new int[4];
       for(int i = 0; i < percentageValues.length; i++) {
           if(i == 3 && hundredPercents > 0) {
             percentageValues[i] = hundredPercents;
             break;
           }
           int randomValue = new Random().nextInt((range - 0) + 1) + 1;
           while(randomValue >= hundredPercents) {
               randomValue /= 2;
           }
           hundredPercents -= randomValue; 
           percentageValues[i] = randomValue;
        }
        Arrays.sort(percentageValues);
        return percentageValues;
    }
    
    
    private void loadingProgress(int targetA, int targetB, int targetC, int targetD) {
         new Thread(() -> {
            int i = 0;
            try {
               while(i < 100) {
                   changeActualProgress(i, targetA, targetB, targetC, targetD);
                   i++;
               }
            }catch(InterruptedException e) {
               System.out.println("Error catched in loadingProgress method : " + e);
            }
            }).start();
    }
    
    
    private void changeActualProgress(int progress, int targetA, int targetB, int targetC, int targetD) 
             throws InterruptedException {
         if(progress <= targetA) {
             this.progressBarA.setValue(progress);
             this.percentageA.setText(progress + " %");
         }
         if(progress <= targetB) {
             this.progressBarB.setValue(progress);
             this.percentageB.setText(progress + " %");
         }
         if(progress <= targetC) {
             this.progressBarC.setValue(progress);
             this.percentageC.setText(progress + " %");
         }
         if(progress <= targetD) {
            this.progressBarD.setValue(progress);
            this.percentageD.setText(progress + " %");
         }
        Thread.sleep(80);
        GameView.getPanel().revalidate();
        GameView.getPanel().repaint();
    }
    
}
