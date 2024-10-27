
package com.mycompany.millionaire.model.hint;

import com.mycompany.millionaire.controller.hint.AudienceVote;
import com.mycompany.millionaire.data.Question;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Service class for AudienceVote controller
 * @author pavel
 */
public class AudienceVoteService extends AudienceVote {

    // Constructor creates new AudioVote controller instance
    public AudienceVoteService(Question currentQuestion) {
        super(currentQuestion);
    }

    /**
     * Method displays voting process
     */
    public void performAudienceVoting() {
        String currentDifficulty = CURRENT_QUESTION.getQuestionDifficulty();
        String answer = CURRENT_QUESTION.getAnswer();
        
        // Depending on difficulty create sorted array of 4 random values 
        int[] percentageValues = switch (currentDifficulty) {
            case "Easy" -> getSortedArray(30);
            case "Medium" -> getSortedArray(50);
            case "Hard" -> getSortedArray(80);
            default -> getSortedArray(95);
        };
        
        // Queue will contain just 3 false option's numbers(percents) 
        Queue<Integer> otherPercentages = new PriorityQueue<>();
        
        /* Whereas percentageValues array is sorted, we want to push just 
           first 3 values to queue, we're achieving it by subtracting 2 of array length
        */
        for(int i = percentageValues.length-2; i >= 0; i--) {
            otherPercentages.add(percentageValues[i]);
        }
        
        // Run loadingProgress with 'answer' value (percentageValues[3]) on corresponding place
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
    
    /**
     * Method creates array of integers with length of 4
     * Depending on 'range' parameter, generate 3 random integers and assign it 
     * to values in array.
     * We are looking on this array as on array of percentages, which means 
     * sum of all four values must be 100%. The main idea of method to generate 
     * three random values in range of input's 'range' parameter, sum all of them, 
     * and subtract it of 100. The rest takes for last fourth element. 
     * Thus, as parameter 'range' is smaller, we are getting bigger value for last 
     * element, which is supposed to be the value for correct option, and 
     * vice versa for bigger value in 'range' parameter.
     * @param range - specifies in what range we should generate random values
     * @return sorted array on integers
     */
    private int[] getSortedArray(int range) {
       // Variable depicts 100%
       int hundredPercents = 100;
       int[] percentageValues = new int[4];
       for(int i = 0; i < percentageValues.length; i++) {
           /* If i == 3 means our loop finished 3 iterations and now percentageValues
              has 3 assigned values, so we are going to work with last value, which 
              supposed to be indicator of correct option(answer). 
              Also we are checking if our hundredPercents value are bigger then 0
              so we can assign it to our last elemnt
           */
           if(i == 3 && hundredPercents > 0) {
             percentageValues[i] = hundredPercents;
             break;
           }
           
           // Generate random integer in parameter's 'range'
           int randomValue = new Random().nextInt((range - 0) + 1) + 1;
           
           // Divide random integer by 2 if this value equal to/bigger then value in hundredPercents
           while(randomValue >= hundredPercents) {
               randomValue /= 2;
           }
           
           // Subtract random value of 100%
           hundredPercents -= randomValue; 
           
           // Assign random value to array's value
           percentageValues[i] = randomValue;
        }
       
        // Sort array
        Arrays.sort(percentageValues);
        
        return percentageValues;
    }
    
    /**
     * Method counts until reach specified values in parameters, but max 100.
     * @param targetA - specifies value in percentages for optionA
     * @param targetB - specifies value in percentages for optionB
     * @param targetC - specifies value in percentages for optionC
     * @param targetD - specifies value in percentages for optionD
     */
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
    
    /**
     * Changes progressBar component's value and text
     * @param progress - current progress
     * @param targetA - max value for optionA
     * @param targetB - max value for optionB
     * @param targetC - max value for optionC
     * @param targetD - max value for optionD
     * @throws InterruptedException 
     */
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
        
        // Revalidate and repaint panel
        service.reloadPanel();
    }
    
}
