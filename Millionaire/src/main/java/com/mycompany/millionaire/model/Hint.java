
package com.mycompany.millionaire.model;

import com.mycompany.millionaire.component.PanelConfiguration;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ProgressBarBuilderImpl;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.media.ImageManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pavel
 */
@Getter
@Setter
public class Hint { 
    
    private JLabel fiftyToFiftyHint;
    private JLabel friendCallHint;
    private JLabel audienceHelpHint;
    
    private JButton optionA;
    private JButton optionB;
    private JButton optionC;
    private JButton optionD;
    private JProgressBar progressBarA;
    private JProgressBar progressBarB;
    private JProgressBar progressBarC;
    private JProgressBar progressBarD;
    private JPanel panel;
    private Question currentQuestion;
    
    private boolean fiftyToFiftyAvailible;
    private boolean friendCallAvailible;
    private boolean audienceHelpAvailible;
    
    private final ComponentServiceImpl service;
    private final PanelConfiguration panelConfig;
   
    
    public Hint() throws  IOException {
        fiftyToFiftyHint = new JLabel();
        friendCallHint = new JLabel();
        audienceHelpHint = new JLabel();
        
        this.service = new ComponentServiceImpl();
        this.panelConfig = new PanelConfiguration();
        
        this.fiftyToFiftyAvailible = true;
        this.friendCallAvailible = true;
        this.audienceHelpAvailible = true;
        
        fiftyToFiftyHint = new LabelBuilderImpl(fiftyToFiftyHint)
                .image(ImageManager.getImageIcon("fiftyhint"))
                .bounds(30, 30, service.getWidth(fiftyToFiftyHint), service.getHeight(fiftyToFiftyHint))
                .hoverImage("fiftyhoverhint")
                .get();
        
        fiftyToFiftyHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(fiftyToFiftyAvailible) {
                    fiftyToFiftyAvailible = false;
                    try {
                        AudioManager.handleAudioEvent("50-50");
                        removeWrongOptions();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fiftyToFiftyHint = new LabelBuilderImpl(fiftyToFiftyHint)
                        .setY(fiftyToFiftyHint.getY() + 9)
                        .image(ImageManager.getImageIcon("fiftyexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
        
        
        friendCallHint = new LabelBuilderImpl(friendCallHint)
                .image(ImageManager.getImageIcon("callhint"))
                .bounds(120, 30, service.getWidth(friendCallHint), service.getHeight(friendCallHint))
                .hoverImage("callhoverhint")
                .get();
        
        friendCallHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(friendCallAvailible) {
                    friendCallAvailible = false;
                    try {
                        AudioManager.muteIntro();
                        AudioManager.handleAudioEvent("friendcall");
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    friendCallHint = new LabelBuilderImpl(friendCallHint)
                        .setY(friendCallHint.getY() + 9)
                        .image(ImageManager.getImageIcon("callexpired"))
                        .removeMouseListeners()
                        .get();
                }
            }
        });
        
        audienceHelpHint = new LabelBuilderImpl(audienceHelpHint)
                .image(ImageManager.getImageIcon("audiencehint"))
                .bounds(210, 30, service.getWidth(audienceHelpHint), service.getHeight(audienceHelpHint))
                .hoverImage("audiencehoverhint")
                .get();
        
        audienceHelpHint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(audienceHelpAvailible) {
                    audienceHelpAvailible = false;
                    try {
                        progressBarA = new JProgressBar(JProgressBar.VERTICAL,0,100);
                        defineProgressBars();
                        AudioManager.muteIntro();
                        AudioManager.handleAudioEvent("audience");
                        performAudienceVoting();
                    } catch (UnsupportedAudioFileException | 
                             IOException | 
                             LineUnavailableException ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Hint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    audienceHelpHint = new LabelBuilderImpl(audienceHelpHint)
                        .setY(audienceHelpHint.getY() + 9)
                        .image(ImageManager.getImageIcon("audienceexpired"))
                        .removeMouseListeners()
                        .get();
                }
                
            }
        });
    }
    
    public void addHints(JPanel panel) {
        panel = panelConfig.addOnPanel(panel, friendCallHint, fiftyToFiftyHint, audienceHelpHint);
        panel.repaint();
    }
    
    public void addFiftyToFiftyHint(JPanel panel) {
        panel.add(fiftyToFiftyHint);
        panel.repaint();
    }
    
    private void removeWrongOptions() {
        String[] options = currentQuestion.getOptions();
        String answer = currentQuestion.getAnswer();
        
        List<String> falseOptions = Arrays.stream(options)
            .filter(option -> !option.contains(answer))             
            .collect(Collectors.toList());

        int randomIndex = new Random().nextInt(falseOptions.size()-1);
        falseOptions.remove(randomIndex);
        
        List<JButton> optionButtons = List.of(optionA,optionB,optionC,optionD);
        
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
    
    private void performAudienceVoting() {
        String currentDifficulty = currentQuestion.getDifficulty();
        String answer = currentQuestion.getAnswer();
        List<JButton> optionButtons = List.of(optionA,optionB,optionC,optionD);
        
        int[] percentageValues = switch (currentDifficulty) {
            case "Easy" -> getPercentageValues(30);
            case "Medium" -> getPercentageValues(50);
            case "Hard" -> getPercentageValues(80);
            default -> getPercentageValues(95);
        };
        
        for(JButton currentOption : optionButtons) {
            if(currentOption.getText().contains(answer)) {
                
            }
        }
        
       new Thread(() -> {
           int i = 0;
           try {
               while(i < 100) {
                   loadingProgress(i);
                   i = i+1;
               }
           }catch(InterruptedException e) {
               System.out.println("Error catched in performAudienceVoting method : " + e);
           }
        }).start();
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
    
     private void loadingProgress(int progress) throws InterruptedException {
        this.progressBarA.setValue(progress);
        this.progressBarB.setValue(progress);
        this.progressBarC.setValue(progress);
        this.progressBarD.setValue(progress);
        this.panel = panelConfig.removeElements(
                panel, 
                progressBarA,
                progressBarB,
                progressBarC,
                progressBarD
        );
        this.panel = panelConfig.addOnPanel(
                panel, 
                progressBarA,
                progressBarB,
                progressBarC,
                progressBarD
        );
        Thread.sleep(60);
        this.panel.revalidate();
        this.panel.repaint();
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
                 .bounds(359,155,service.getWidth(labelB),service.getHeight(labelB))
                 .get();
         
         labelC = new LabelBuilderImpl(labelC)
                 .text("C")
                 .foreground(Color.WHITE)
                 .bounds(409,155,service.getWidth(labelC),service.getHeight(labelC))
                 .get();
         
         labelD = new LabelBuilderImpl(labelD)
                 .text("D")
                 .foreground(Color.WHITE)
                 .bounds(459,155,service.getWidth(labelD),service.getHeight(labelD))
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
                 .bounds(350,70,30,80)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarC = new ProgressBarBuilderImpl(this.progressBarC)
                 .bounds(400,70,30,80)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         progressBarD = new ProgressBarBuilderImpl(this.progressBarD)
                 .bounds(450,70,30,80)
                 .background(Color.BLACK)
                 .foreground(new Color(255,204,255))
                 .get();
         
         this.panel = panelConfig.addOnPanel(
                 panel, 
                 labelA,
                 labelB,
                 labelC,
                 labelD,
                 progressBarA,
                 progressBarB,
                 progressBarC,
                 progressBarD
         );
         this.panel.revalidate();
         this.panel.repaint();
     }
}
