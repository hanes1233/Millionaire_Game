
package com.mycompany.millionaire.model.Hints;

import com.mycompany.millionaire.component.PanelConfiguration;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ProgressBarBuilderImpl;
import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
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
    private final PanelConfiguration panelConfig;
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
        this.panelConfig = new PanelConfiguration();
        this.service = new ComponentServiceImpl();
        this.optionA = CurrentQuestion.getOptionA();
        this.optionB = CurrentQuestion.getOptionB();
        this.optionC = CurrentQuestion.getOptionC();
        this.optionD = CurrentQuestion.getOptionD();
        this.defineProgressBars();
    }
    
    
    private void defineProgressBars() {
         
         JLabel labelA = new JLabel();
         JLabel labelB = new JLabel();
         JLabel labelC = new JLabel();
         JLabel labelD = new JLabel();
         
         labelA = new LabelBuilderImpl(labelA)
                 .text("A")
                 .foreground(Color.WHITE)
                 .bounds(309,155,service.getWidth(labelA),service.getHeight(labelA))
                 .get();
         
         labelB = new LabelBuilderImpl(labelB)
                 .text("B")
                 .foreground(Color.WHITE)
                 .bounds(349,155,service.getWidth(labelB),service.getHeight(labelB))
                 .get();
         
         labelC = new LabelBuilderImpl(labelC)
                 .text("C")
                 .foreground(Color.WHITE)
                 .bounds(389,155,service.getWidth(labelC),service.getHeight(labelC))
                 .get();
         
         labelD = new LabelBuilderImpl(labelD)
                 .text("D")
                 .foreground(Color.WHITE)
                 .bounds(429,155,service.getWidth(labelD),service.getHeight(labelD))
                 .get();
         
         percentageA = new JLabel();
         percentageB = new JLabel();
         percentageC = new JLabel();
         percentageD = new JLabel();
         
         percentageA = new LabelBuilderImpl(percentageA)
                 .text("0 %")
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(305,55,service.getWidth(percentageA)*2,service.getHeight(percentageA))
                 .get();
         
         percentageB = new LabelBuilderImpl(percentageB)
                 .text("0 %")
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(345,55,service.getWidth(percentageB)*2,service.getHeight(percentageB))
                 .get();
         
         percentageC = new LabelBuilderImpl(percentageC)
                 .text("0 %")
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(385,55,service.getWidth(percentageC)*2,service.getHeight(percentageC))
                 .get();
         
         percentageD = new LabelBuilderImpl(percentageD)
                 .text("0 %")
                 .font(new Font("Serif", Font.BOLD, 11))
                 .foreground(Color.WHITE)
                 .bounds(425,55,service.getWidth(percentageD)*2,service.getHeight(percentageD))
                 .get();
         
         
         progressBarA = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarB = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarC = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarD = new JProgressBar(JProgressBar.VERTICAL,0,100);
         
         progressBarA = new ProgressBarBuilderImpl(this.progressBarA)
                 .bounds(300,70,30,80)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarB = new ProgressBarBuilderImpl(this.progressBarB)
                 .bounds(340,70,30,80)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarC = new ProgressBarBuilderImpl(this.progressBarC)
                 .bounds(380,70,30,80)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarD = new ProgressBarBuilderImpl(this.progressBarD)
                 .bounds(420,70,30,80)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         this.panel = panelConfig.addOnPanel(
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
            case "Easy" -> getPercentageValues(30);
            case "Medium" -> getPercentageValues(50);
            case "Hard" -> getPercentageValues(80);
            default -> getPercentageValues(95);
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
    
    private int[] getPercentageValues(int range) {
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
