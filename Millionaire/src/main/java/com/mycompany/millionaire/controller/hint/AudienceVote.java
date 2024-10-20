
package com.mycompany.millionaire.controller.hint;

import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ProgressBarBuilderImpl;
import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Audience vote controller
 * @author pavel
 */
public class AudienceVote {
    
    // Service instances
    protected final CurrentQuestion CURRENT_QUESTION;
    protected final ComponentServiceImpl service;
    
    // region Components instances
    protected JProgressBar progressBarA;
    protected JProgressBar progressBarB;
    protected JProgressBar progressBarC;
    protected JProgressBar progressBarD;
    
    protected JLabel percentageA;
    protected JLabel percentageB;
    protected JLabel percentageC;
    protected JLabel percentageD;
    
    protected JButton optionA;
    protected JButton optionB;
    protected JButton optionC;
    protected JButton optionD;
    // endregion
    
    
    /**
     * Constructor
     * @param currentQuestion initialize current question to get options values
     */
    public AudienceVote(CurrentQuestion currentQuestion) {
        this.CURRENT_QUESTION = currentQuestion;
        this.service = new ComponentServiceImpl();
        this.optionA = CURRENT_QUESTION.getOptionA();
        this.optionB = CURRENT_QUESTION.getOptionB();
        this.optionC = CURRENT_QUESTION.getOptionC();
        this.optionD = CURRENT_QUESTION.getOptionD();
        this.defineProgressBars();
    }
    
    /**
     * Set up components and add to the panel
     */
    private void defineProgressBars() {
         
         JLabel labelA = new LabelBuilderImpl()
                 .setText("A")
                 .setForeground(Color.WHITE)
                 .setBounds(309, 155)
                 .get();
         
         JLabel labelB = new LabelBuilderImpl()
                 .setText("B")
                 .setForeground(Color.WHITE)
                 .setBounds(349, 155)
                 .get();
         
         JLabel labelC = new LabelBuilderImpl()
                 .setText("C")
                 .setForeground(Color.WHITE)
                 .setBounds(389, 155)
                 .get();
         
         JLabel labelD = new LabelBuilderImpl()
                 .setText("D")
                 .setForeground(Color.WHITE)
                 .setBounds(429, 155)
                 .get();
         
         percentageA = new LabelBuilderImpl()
                 .setText("0 %")
                 .setPreferredSize(new Dimension(30,15))
                 .setFont(new Font("Serif", Font.BOLD, 11))
                 .setForeground(Color.WHITE)
                 .setBounds(305, 55)
                 .get();
         
         percentageB = new LabelBuilderImpl()
                 .setText("0 %")
                 .setPreferredSize(new Dimension(30,15))
                 .setFont(new Font("Serif", Font.BOLD, 11))
                 .setForeground(Color.WHITE)
                 .setBounds(345, 55)
                 .get();
         
         percentageC = new LabelBuilderImpl()
                 .setText("0 %")
                 .setPreferredSize(new Dimension(30,15))
                 .setFont(new Font("Serif", Font.BOLD, 11))
                 .setForeground(Color.WHITE)
                 .setBounds(385, 55)
                 .get();
         
         percentageD = new LabelBuilderImpl()
                 .setText("0 %")
                 .setPreferredSize(new Dimension(30,15))
                 .setFont(new Font("Serif", Font.BOLD, 11))
                 .setForeground(Color.WHITE)
                 .setBounds(425, 55)
                 .get();
         
         
         progressBarA = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarB = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarC = new JProgressBar(JProgressBar.VERTICAL,0,100);
         progressBarD = new JProgressBar(JProgressBar.VERTICAL,0,100);
         
         progressBarA = new ProgressBarBuilderImpl(this.progressBarA)
                 .setPreferredSize(new Dimension(30, 80))
                 .setBounds(300, 70)
                 .setBackground(Color.BLACK)
                 .setForeground(new Color(255,204,255))
                 .get();
         
         progressBarB = new ProgressBarBuilderImpl(this.progressBarB)
                 .setPreferredSize(new Dimension(30, 80))
                 .setBounds(340, 70)
                 .setBackground(Color.BLACK)
                 .setForeground(new Color(255,204,255))
                 .get();
         
         progressBarC = new ProgressBarBuilderImpl(this.progressBarC)
                 .setPreferredSize(new Dimension(30, 80))
                 .setBounds(380, 70)
                 .setBackground(Color.BLACK)
                 .setForeground(new Color(255,204,255))
                 .get();
         
         progressBarD = new ProgressBarBuilderImpl(this.progressBarD)
                 .setPreferredSize(new Dimension(30, 80))
                 .setBounds(420, 70)
                 .setBackground(Color.BLACK)
                 .setForeground(new Color(255,204,255))
                 .get();
         
         this.service.addOnPanel(
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
         
         // Revalidate and repaint panel
         this.service.reloadPanel();
     }
}
