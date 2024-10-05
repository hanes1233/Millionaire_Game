
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ProgressBarBuilderImpl;
import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author pavel
 */
public class AudienceVote {
    
    private JPanel panel;
    private final ComponentServiceImpl service;
    
    private JProgressBar progressBarA;
    private JProgressBar progressBarB;
    private JProgressBar progressBarC;
    private JProgressBar progressBarD;
    
    private JLabel percentageA;
    private JLabel percentageB;
    private JLabel percentageC;
    private JLabel percentageD;
    
    private final JButton optionA;
    private final JButton optionB;
    private final JButton optionC;
    private final JButton optionD;
    
    public AudienceVote() {
        this.panel = CurrentQuestion.getPanel();
        this.service = new ComponentServiceImpl();
        this.optionA = CurrentQuestion.getOptionA();
        this.optionB = CurrentQuestion.getOptionB();
        this.optionC = CurrentQuestion.getOptionC();
        this.optionD = CurrentQuestion.getOptionD();
        this.defineProgressBars();
    }
    
    
    private void defineProgressBars() {
         
         JLabel labelA = new LabelBuilderImpl()
                 .text("A")
                 .foreground(Color.WHITE)
                 .bounds(309, 155)
                 .get();
         
         JLabel labelB = new LabelBuilderImpl()
                 .text("B")
                 .foreground(Color.WHITE)
                 .bounds(349, 155)
                 .get();
         
         JLabel labelC = new LabelBuilderImpl()
                 .text("C")
                 .foreground(Color.WHITE)
                 .bounds(389, 155)
                 .get();
         
         JLabel labelD = new LabelBuilderImpl()
                 .text("D")
                 .foreground(Color.WHITE)
                 .bounds(429, 155)
                 .get();
         
         percentageA = new LabelBuilderImpl()
                 .text("0 %")
                 .size(new Dimension(30,15))
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(305, 55)
                 .get();
         
         percentageB = new LabelBuilderImpl()
                 .text("0 %")
                 .size(new Dimension(30,15))
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(345, 55)
                 .get();
         
         percentageC = new LabelBuilderImpl()
                 .text("0 %")
                 .size(new Dimension(30,15))
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(385, 55)
                 .get();
         
         percentageD = new LabelBuilderImpl()
                 .text("0 %")
                 .size(new Dimension(30,15))
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(425, 55)
                 .get();
         
         
         progressBarA = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarB = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarC = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarD = new JProgressBar(JProgressBar.VERTICAL,0,100);
         
         progressBarA = new ProgressBarBuilderImpl(this.progressBarA)
                 .size(new Dimension(30, 80))
                 .bounds(300, 70)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarB = new ProgressBarBuilderImpl(this.progressBarB)
                 .size(new Dimension(30, 80))
                 .bounds(340, 70)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarC = new ProgressBarBuilderImpl(this.progressBarC)
                 .size(new Dimension(30, 80))
                 .bounds(380, 70)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarD = new ProgressBarBuilderImpl(this.progressBarD)
                 .size(new Dimension(30, 80))
                 .bounds(420, 70)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         this.panel = service.addOnPanel(
                 panel, 
                 labelA,
                 labelB,
                 labelC,
                 labelD,
                 percentageA,
                 percentageB,
                 percentageC,
                 percentageD,
                 progressBarA,
                 progressBarB,
                 progressBarC,
                 progressBarD
         );
         this.panel.revalidate();
         this.panel.repaint();
     }
    
    
    public void performAudienceVoting() {
        String currentDifficulty = CurrentQuestion.getDifficulty();
        String answer = CurrentQuestion.getAnswer();
        
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
        this.panel.repaint();
    }
}
