
package com.mycompany.millionaire.controller;

import com.mycompany.millionaire.controller.hint.Hint;
import com.mycompany.millionaire.data.GameConfiguration;
import com.mycompany.millionaire.data.ProgressList;
import com.mycompany.millionaire.data.entity.Question;
import com.mycompany.millionaire.model.QuizService;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.model.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.model.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.media.ImageManager;
import com.mycompany.millionaire.view.GameView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static com.mycompany.millionaire.data.constant.CustomColor.*;
import static com.mycompany.millionaire.data.constant.Difficulty.*;
import static com.mycompany.millionaire.data.constant.FontType.SERIF;
import static com.mycompany.millionaire.data.constant.Tag.*;


/**
 * Game controller
 * @author pavel
 */
@Getter
@Setter
public class Game {
    
    // region Components
    private JTextArea questionText;
    private JButton optionA;
    private JButton optionB;
    private JButton optionC;
    private JButton optionD;
    private JList gameProgress;
    private JLabel exit;
    // endregion
    
    // Model variables
    private final ComponentServiceImpl componentService;
    private final QuizService quizService;
    private Question currentQuestion;
    private GameConfiguration gameConfig;
    private final JPanel panel;
    private ProgressList progressList;
    private ListModel<String> progressBar;
    private Hint hints;
    
    // region game settings
    private String language;
    private String subject;
    private String difficulty;
    private String currentAnswer;
    private String[] options;
    private boolean gameOver;
    private boolean correctAnswer;
    // endregion

    /**
     * Constructor
     * @param config - game configuration instance
     */
    public Game(GameConfiguration config) {
        
        // Init panel and service variables
        this.panel = GameView.getPanel();
        this.componentService = new ComponentServiceImpl();
        this.quizService = new QuizService();
        
        // Set look
        this.componentService.setAluminiumLook();
        this.hints = new Hint();
        this.hints.initHints();
        this.progressList = new ProgressList();
        this.gameConfig = config;
        this.difficulty = config.getGameDifficulty();
        this.language = config.getLanguage();
        this.subject = config.getSubject();
        
        // Get progress list based on language
        this.progressBar = this.progressList.getModel(language);
        this.startQuiz();
    }
    
    /**
     * Create components for JPanel
     */
    public void initComponents() {
        
        // Clear panel from components if exist
        this.panel.removeAll();
        
        // Play main theme
        AudioManager.handleMainTheme(gameConfig.getQuestionIndex());
        
        // Define current options
        this.options = currentQuestion.getOptions();
        
        // Shuffle options
        Collections.shuffle(Arrays.asList(this.options));
        
        questionText = new TextAreaBuilderImpl()
                .formatText()
                .text(currentQuestion.getQuestion())
                .font(new Font(SERIF, Font.ITALIC, 16))
                .background(PRUSSIAN_BLUE)
                .foreground(Color.WHITE)
                .preferredSize(new Dimension(450, 90))
                .bounds(30, 180)
                .readOnly()
                .build();
        
        
        optionA = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text(HTML_OPEN + FONT_OPEN + "A:" + FONT_CLOSE + options[0] + HTML_CLOSE)
                .textHorizontalAlign(SwingConstants.LEFT)
                .minSize(220, 35)
                .bounds(30, 300)
                .build();
        
        optionA.addActionListener((ActionEvent e) -> {  
                correctAnswer = quizService.isAnswerCorrect(options[0], currentQuestion.getAnswer());
                startQuiz();
        });
        
        optionB = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text(HTML_OPEN + FONT_OPEN + "B:" + FONT_CLOSE + options[1] + HTML_CLOSE)
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(260, 300)
                .build();
        
        optionB.addActionListener((ActionEvent e) -> {
                correctAnswer = quizService.isAnswerCorrect(options[1], currentQuestion.getAnswer());
                startQuiz();
        });
        
        optionC = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text(HTML_OPEN + FONT_OPEN + "C:" + FONT_CLOSE + options[2] + HTML_CLOSE)
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(30, 350)
                .build();
        
        optionC.addActionListener((ActionEvent e) -> {
                correctAnswer = quizService.isAnswerCorrect(options[2], currentQuestion.getAnswer());
                startQuiz();
        }); 
        
        optionD = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text(HTML_OPEN + FONT_OPEN + "D:" + FONT_CLOSE + options[3] + HTML_CLOSE)
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(260, 350)
                .build();
        
        optionD.addActionListener((ActionEvent e) -> {
                correctAnswer = quizService.isAnswerCorrect(options[3], currentQuestion.getAnswer());
                startQuiz();
          
        });
        
        // Setting up CurrentQuestion's options for using through the rest of programm
        currentQuestion.setOptionA(this.optionA);
        currentQuestion.setOptionB(this.optionB);
        currentQuestion.setOptionC(this.optionC);
        currentQuestion.setOptionD(this.optionD);
          
        hints.setCurrentQuestion(currentQuestion);
        
        // For "Hard" difficulty we are not going to add hints at all
        if (!this.difficulty.equals(HARD.getName())) {

            // For "Easy" difficulty, we are going to add all hints
            if (this.difficulty.equals(EASY.getName())) {
                for(JLabel hint : hints.getHints())
                    this.panel.add(hint);
            
            // For "Medium" difficulty we are going to add one hint only
            }else if(this.difficulty.equals(MEDIUM.getName())) {
                this.panel.add(hints.getFiftyToFiftyHint());
            }
        }
        
        exit = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("exit"))
                .bounds(580, 20)
                .build();
        
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Kill game
                componentService.dispose();
            }
        });

        gameProgress = new ListBuilderImpl()
                .model(progressBar)
                .preferredSize(new Dimension(200, 280))
                .bounds(500, 80)
                .background(DEEP_VIOLET)
                .foreground(AMBER)
                .selectedIndex(gameConfig.getProgressIndex())
                .build();
        
        // Add components on JPanel
        this.componentService.addOnPanel(
                exit, 
                questionText, 
                optionA, 
                optionB, 
                optionC, 
                optionD, 
                gameProgress
        );
        
       // Revalidate and repaint panel
       this.componentService.reloadPanel();
    }
    
    /**
     * Start game
     * @throws IOException 
     */
    private void startQuiz() {
        // Check is question queue in GameConfiguration is not empty
        if (!gameConfig.getQuestions().isEmpty()) {
            
            // Check does panel contains any components
            if (panel.getComponents().length > 0) {
                
                // Handle user's choice view, sound and thinking simulation
                this.quizService.handleUserChoice(currentQuestion.getQuestionDifficulty(), correctAnswer, currentQuestion);
                
                // Add new motion listener to panel
                panel.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                       // Remove all motion listeners from panel
                       componentService.removeMotionListeners();
                       
                       // Check is answer correct
                       if (correctAnswer) {
                           // Update progress bar and question indexes
                           int progressIndex = gameConfig.getProgressIndex() - 1;
                           int questionIndex = gameConfig.getQuestionIndex() + 1;
                           
                           // Set new indexes to GameConfiguration
                           gameConfig.setProgressIndex(progressIndex);
                           gameConfig.setQuestionIndex(questionIndex);
                           
                           // Handle user's score
                           quizService.handleScore(gameConfig, questionIndex);
                           
                           // Poll next question from GameConfiguration's queue of questions and assign to 'currentQuestion' variable
                           currentQuestion = gameConfig.pollQuestion();
                           
                           // Re-draw components with new question
                           initComponents();
                       }else {
                           // Finish game
                           quizService.finishGame(gameConfig.getScore(), false);
                       }
                    }
                });
            }else {
                // Poll next question from GameConfiguration's queue of questions and assign to 'currentQuestion' variable
                this.currentQuestion = gameConfig.pollQuestion();
                
                // Re-draw components with new question
                initComponents();
            }
        }else {
            // Handle user's choice view, sound and thinking simulation
            this.quizService.handleUserChoice(currentQuestion.getQuestionDifficulty(), correctAnswer, currentQuestion);
            
            // Add new motion listener to panel
            panel.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    // Remove all motion listeners from panel
                    componentService.removeMotionListeners();
                    
                    // Check is answer correct
                    if (correctAnswer) {
                        
                        // Handle user's score
                        quizService.handleScore(gameConfig, gameConfig.getQuestionIndex());
                        
                        // Finish game
                        quizService.finishGame(gameConfig.getScore(),true);
                    }else {
                        // Finish game
                        quizService.finishGame(gameConfig.getScore(),false);
                    }
                }
            });
        }
    }
}
