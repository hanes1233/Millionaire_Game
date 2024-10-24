
package com.mycompany.millionaire.controller;

import com.mycompany.millionaire.model.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.model.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.data.ProgressList;
import com.mycompany.millionaire.model.media.AudioManager;
import com.mycompany.millionaire.model.media.ImageManager;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.controller.hint.Hint;
import com.mycompany.millionaire.data.GameConfiguration;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.model.QuizService;
import com.mycompany.millionaire.view.GameView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import lombok.Getter; 
import lombok.Setter;


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
    private JList<String> gameProgress;
    private JLabel exit;
    // endregion
    
    // Model variables
    private final ComponentServiceImpl COMPONENT_SERVICE;
    private final QuizService QUIZ_SERVICE;
    private Question currentQuestion;
    private GameConfiguration gameConfig;
    private final JPanel PANEL;
    private ProgressList progressList;
    private ListModel progressBar;
    private Hint hints;
    //private Question question;
    
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
     * @throws IOException
     */
    public Game(GameConfiguration config) throws  IOException {
        
        // Init panel and service variables
        this.PANEL = GameView.getPanel();
        this.COMPONENT_SERVICE = new ComponentServiceImpl();
        this.QUIZ_SERVICE = new QuizService();
        
        // Set look
        this.COMPONENT_SERVICE.setAluminiumLook();
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
        this.PANEL.removeAll();
        
        // Handle sound theme
        try {
            AudioManager.handleMainTheme(gameConfig.getQuestionIndex());
        } catch (LineUnavailableException |
                UnsupportedAudioFileException | 
                NullPointerException | 
                IOException ex) {
            System.out.println("Exception catched trying play handleMainTheme() in initComponents : " + ex);
        } 
        
        // Define current options
        this.options = currentQuestion.getOptions();
        
        // Shuffle options
        Collections.shuffle(Arrays.asList(this.options));
        
        questionText = new TextAreaBuilderImpl()
                .formatText()
                .setText(currentQuestion.getQuestion())
                .setFont(new Font("Serif", Font.ITALIC, 16))
                .setBackground(new Color(0, 38, 75))
                .setForeground(Color.WHITE)
                .setPreferredSize(new Dimension(450, 90))
                .setBounds(30, 180)
                .setReadOnly()
                .get();
        
        
        optionA = new ButtonBuilderImpl()
                .gameStyleConfig()
                .setText("<html><font color=rgb(213,156,0)>A:</font>" + options[0] + "</html>")
                .setTextHorizontalAlign(SwingConstants.LEFT)
                .setSize(220, 35)
                .setBounds(30, 300)
                .get();
        
        optionA.addActionListener((ActionEvent e) -> {  
                correctAnswer = QUIZ_SERVICE.isAnswerCorrect(options[0], currentQuestion.getAnswer());
                startQuiz();
        });
        
        optionB = new ButtonBuilderImpl()
                .gameStyleConfig()
                .setText("<html><font color=rgb(213,156,0)>B:</font>" + options[1] + "</html>")
                .setTextHorizontalAlign(SwingConstants.LEFT)
                .setBounds(260, 300)
                .get();
        
        optionB.addActionListener((ActionEvent e) -> {
                correctAnswer = QUIZ_SERVICE.isAnswerCorrect(options[1], currentQuestion.getAnswer());
                startQuiz();
        });
        
        optionC = new ButtonBuilderImpl()
                .gameStyleConfig()
                .setText("<html><font color=rgb(213,156,0)>C:</font>" + options[2] + "</html>")
                .setTextHorizontalAlign(SwingConstants.LEFT)
                .setBounds(30, 350)
                .get();
        
        optionC.addActionListener((ActionEvent e) -> {
                correctAnswer = QUIZ_SERVICE.isAnswerCorrect(options[2], currentQuestion.getAnswer());
                startQuiz();
        }); 
        
        optionD = new ButtonBuilderImpl()
                .gameStyleConfig()
                .setText("<html><font color=rgb(213,156,0)>D:</font>" + options[3] + "</html>")
                .setTextHorizontalAlign(SwingConstants.LEFT)
                .setBounds(260, 350)
                .get();
        
        optionD.addActionListener((ActionEvent e) -> {
                correctAnswer = QUIZ_SERVICE.isAnswerCorrect(options[3], currentQuestion.getAnswer());
                startQuiz();
          
        });
        
        // Setting up CurrentQuestion's options for using through the rest of programm
        currentQuestion.setOptionA(this.optionA);
        currentQuestion.setOptionB(this.optionB);
        currentQuestion.setOptionC(this.optionC);
        currentQuestion.setOptionD(this.optionD);
          
        hints.setCurrentQuestion(currentQuestion);
        
        // For "Hard" difficulty we are not gonna add hints at all
        if(!this.difficulty.equals("Hard")) {
            
            // For "Easy" difficulty, we are going to add all hints
            if(this.difficulty.equals("Easy")) {
                for(JLabel hint : hints.getHints())
                    this.PANEL.add(hint);
            
            // For "Medium" difficulty we are going to add one hint only
            }else if(this.difficulty.equals("Medium")) {
                this.PANEL.add(hints.getFiftyToFiftyHint());
            }
        } 
        
        
        exit = new LabelBuilderImpl()
                .setImage(ImageManager.getImageIcon("exit"))
                .setBounds(580, 20)
                .get();
        
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Kill game
                COMPONENT_SERVICE.dispose();
            }
        });
        
        gameProgress = new JList();
        gameProgress = new ListBuilderImpl(gameProgress)
                .setModel(progressBar)
                .setPreferredSize(new Dimension(200, 280))
                .setBounds(500, 80)
                .setBackground(new Color(12, 4, 77))
                .setForeground(new Color(242, 177, 0))
                .setSelectedIndex(gameConfig.getProgressIndex())
                .get();
        
        // Add components on JPanel
        this.COMPONENT_SERVICE.addOnPanel(
                exit, 
                questionText, 
                optionA, 
                optionB, 
                optionC, 
                optionD, 
                gameProgress
        );
        
       // Revalidate and repaint panel
       this.COMPONENT_SERVICE.reloadPanel();
    }
    
    /**
     * Start game
     * @throws IOException 
     */
    private void startQuiz() {
        // Check is question queue in GameConfiguration is not empty
        if(!gameConfig.getQuestions().isEmpty()) {
            
            // Check does panel contains any components
            if(PANEL.getComponents().length > 0) {
                
                // Handle user's choice view, sound and thinking simulation
                this.QUIZ_SERVICE.handleUserChoice(currentQuestion.getQuestionDifficulty(), correctAnswer, currentQuestion);
                
                // Add new motion listener to panel
                PANEL.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                       // Remove all motion listeners from panel
                       COMPONENT_SERVICE.removeMotionListeners();
                       
                       // Check is answer correct
                       if(correctAnswer) {
                           // Update progress bar and question indexes
                           int progressIndex = gameConfig.getProgressIndex() - 1;
                           int questionIndex = gameConfig.getQuestionIndex() + 1;
                           
                           // Set new indexes to GameConfiguration
                           gameConfig.setProgressIndex(progressIndex);
                           gameConfig.setQuestionIndex(questionIndex);
                           
                           // Handle user's score
                           QUIZ_SERVICE.handleScore(gameConfig, questionIndex);
                           
                           // Poll next question from GameConfiguration's queue of questions and assign to 'currentQuestion' variable
                           currentQuestion = gameConfig.pollQuestion();
                           
                           // Re-draw components with new question
                           initComponents();
                       }else {
                           // Finish game
                           QUIZ_SERVICE.finishGame(gameConfig.getScore(), false, language);
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
            this.QUIZ_SERVICE.handleUserChoice(currentQuestion.getQuestionDifficulty(), correctAnswer, currentQuestion);
            
            // Add new motion listener to panel
            PANEL.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    // Remove all motion listeners from panel
                    COMPONENT_SERVICE.removeMotionListeners();
                    
                    // Check is answer correct
                    if(correctAnswer) {
                        
                        // Handle user's score
                        QUIZ_SERVICE.handleScore(gameConfig, gameConfig.getQuestionIndex());
                        
                        // Finish game
                        QUIZ_SERVICE.finishGame(gameConfig.getScore(),true, language);
                    }else {
                        // Finish game
                        QUIZ_SERVICE.finishGame(gameConfig.getScore(),false, language);
                    }
                }
            });
        }
    }
}
