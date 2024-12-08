
package com.mycompany.millionaire.controller.hint;

import com.mycompany.millionaire.data.entity.Question;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ProgressBarBuilderImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.mycompany.millionaire.data.constant.CustomColor.LIGHT_PINK;
import static com.mycompany.millionaire.data.entity.FontObject.serifBold;

/**
 * AudienceVote controller provides design architecture
 * for 'audience vote' action
 * @author pavel
 */
public class AudienceVote {

    // Declare instances of service objects (business logic providers)
    protected final Question currentQuestion;
    protected final ComponentServiceImpl service;
    
    // region JComponent instances
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
    public AudienceVote(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
        this.service = new ComponentServiceImpl();
        this.optionA = this.currentQuestion.getOptionA();
        this.optionB = this.currentQuestion.getOptionB();
        this.optionC = this.currentQuestion.getOptionC();
        this.optionD = this.currentQuestion.getOptionD();
        this.defineProgressBars();
    }
    
    /**
     * Set up components and add to the panel
     */
    private void defineProgressBars() {

        // Prepare data for JLabels displaying symbols A, B, C, D
        final Font SERIF = serifBold(11);
        char[] symbols = {'A', 'B', 'C', 'D'};
        AtomicInteger labelSymbolWidth = new AtomicInteger(269);
        final int height = 155;

        // Prepare data for JLabel with percentage text
        final Dimension dimensionSize = new Dimension(30, 15);
        final int percentageHeight = 55;
        AtomicInteger percentageWidth = new AtomicInteger(305);

        // Prepare data for JProgressBar
        final Dimension progressPreferredSize = new Dimension(30, 80);
        final int progressBarHeight = 70;
        AtomicInteger progressBarWidth = new AtomicInteger(300);

        // Prepare lists for JComponents storing
        List<JLabel> percentageLabelList = new ArrayList<>();
        List<JProgressBar> progressBarList = new ArrayList<>();

        IntStream.range(0, symbols.length)
                .forEach(i -> {
                    // Create JLabel with symbol and add on panel
                    labelSymbolWidth.addAndGet(40);
                    JLabel labelSymbol = new LabelBuilderImpl()
                            .text(String.valueOf(symbols[i]))
                            .foreground(Color.WHITE)
                            .bounds(labelSymbolWidth.get(), height)
                            .build();
                    this.service.addOnPanel(labelSymbol);

                    // Create JLabel with percentage text
                    JLabel percentageLabel = new LabelBuilderImpl()
                            .text("0 %")
                            .preferredSize(dimensionSize)
                            .font(SERIF)
                            .foreground(Color.WHITE)
                            .bounds(percentageWidth.get(), percentageHeight)
                            .build();
                    percentageWidth.addAndGet(40);
                    percentageLabelList.add(percentageLabel);

                    // Create JProgress Bars
                    JProgressBar progressBar = new JProgressBar(SwingConstants.VERTICAL,0,100);
                    progressBar = new ProgressBarBuilderImpl(progressBar)
                            .preferredSize(progressPreferredSize)
                            .bounds(progressBarWidth.get(), progressBarHeight)
                            .background(Color.BLACK)
                            .foreground(LIGHT_PINK)
                            .build();
                    progressBarWidth.addAndGet(40);
                    progressBarList.add(progressBar);
                });

        // Assign variables
        this.percentageA = percentageLabelList.get(0);
        this.percentageB = percentageLabelList.get(1);
        this.percentageC = percentageLabelList.get(2);
        this.percentageD = percentageLabelList.get(3);

        this.progressBarA = progressBarList.get(0);
        this.progressBarB = progressBarList.get(1);
        this.progressBarC = progressBarList.get(2);
        this.progressBarD = progressBarList.get(3);

         this.service.addOnPanel(
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
