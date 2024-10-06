
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.component.PanelTemplate;
import com.mycompany.millionaire.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.data.CurrentQuestion;
import com.mycompany.millionaire.data.Database;
import com.mycompany.millionaire.data.ProgressList;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.media.ImageManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import com.mycompany.millionaire.model.Hints.Hint;
import com.mycompany.millionaire.model.QuizService;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lombok.Data;


/**
 *
 * @author pavel
 */
@Data
public class GameFrame {
    
    private JTextArea questionText;
    private JButton optionA;
    private JButton optionB;
    private JButton optionC;
    private JButton optionD;
    private JList<String> gameProgress;
    private JLabel exit;
    
    private JFrame gameFrame;
    private JPanel gamePanel;
    private CurrentQuestion currentQuestion;
    private final FormFactory formFactory;
    private final ComponentServiceImpl service;
    private final QuizService quizService;
    private ProgressList progressList;
    private Queue<Question> questions;
    private Database database;
    private Hint hints;
    
    private String language;
    private String subject;
    private String difficulty;
    private String currentAnswer;
    private String[] options;
    private int progressIndex = 15;
    private int questionIndex = 0;
    private int score = 0;
    private boolean gameOver;
    private boolean correctAnswer;
    
    public GameFrame() throws  IOException {
        this.formFactory = new FormFactory();
        this.service = new ComponentServiceImpl();
        this.quizService = new QuizService();
        this.hints = new Hint();
    }
    
    public void run(String language, String subject, String difficulty) throws IOException {
        this.language = language;
        this.subject = subject;
        this.difficulty = difficulty;
        try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }catch (ClassNotFoundException |
                InstantiationException |
                NullPointerException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
                throw new RuntimeException("Error catch at GameFrame run() method: " + e);
            }
        this.database = new Database(language, subject, difficulty);
        this.questions = database.getQuestionList();
        this.progressList = new ProgressList();
        this.gamePanel = PanelTemplate.getPanel();
        this.gameFrame = formFactory.createForm();
        this.gameFrame.setContentPane(gamePanel);
        this.startQuiz();
    }
    
    private void initComponents(Question currentQuestion) {
        try {
            AudioManager.handleMainTheme(this.questionIndex);
        } catch (LineUnavailableException |
                UnsupportedAudioFileException | 
                NullPointerException | 
                IOException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        this.options = currentQuestion.getOptions();
        
        // Shuffle options
        Collections.shuffle(Arrays.asList(this.options));
        this.currentAnswer = currentQuestion.getAnswer();
        
        
        questionText = new TextAreaBuilderImpl()
                .formatText()
                .text(currentQuestion.getQuestion())
                .font(new Font("Serif", Font.ITALIC, 16))
                .background(new Color(0, 38, 75))
                .foreground(Color.WHITE)
                .size(new Dimension(450, 90))
                .bounds(30, 180)
                .readOnly()
                .get();
        
        
        optionA = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>A:</font>" + options[0] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .size(220, 35)
                .bounds(30, 300)
                .get();
        
        optionA.addActionListener((ActionEvent e) -> {
                this.correctAnswer = quizService.isAnswerCorrect(options[0], currentAnswer);
                startQuiz();   
        });
        
        optionB = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>B:</font>" + options[1] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(260, 300)
                .get();
        
        optionB.addActionListener((ActionEvent e) -> {
                this.correctAnswer = quizService.isAnswerCorrect(options[1], currentAnswer);
                startQuiz(); 
        });
        
        optionC = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>C:</font>" + options[2] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(30, 350)
                .get();
        
        optionC.addActionListener((ActionEvent e) -> {
                this.correctAnswer = quizService.isAnswerCorrect(options[2], currentAnswer);         
                startQuiz();
        }); 
        
        optionD = new ButtonBuilderImpl()
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>D:</font>" + options[3] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(260, 350)
                .get();
        
        optionD.addActionListener((ActionEvent e) -> {
                this.correctAnswer = quizService.isAnswerCorrect(options[3], currentAnswer);
                startQuiz();
        });
        
        CurrentQuestion.setOptionA(this.optionA);
        CurrentQuestion.setOptionB(this.optionB);
        CurrentQuestion.setOptionC(this.optionC);
        CurrentQuestion.setOptionD(this.optionD);
          
        if(!this.difficulty.equals("Hard")) {
            if(this.difficulty.equals("Easy")) {
                hints.addHints(gamePanel);
            }else if(this.difficulty.equals("Medium")) {
                hints.addFiftyToFiftyHint(gamePanel);
            }
        } 
        
        
        exit = new LabelBuilderImpl()
                .image(ImageManager.getImageIcon("exit"))
                .bounds(580, 20)
                .get();
        
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AudioManager.stopAllSounds();
                AudioManager.muteIntro();
                gameFrame.dispose();
            }
        });
        
        
        gameProgress = new JList();
        gameProgress = new ListBuilderImpl(gameProgress)
                .model(progressList.getModel(this.language))
                .size(new Dimension(200, 280))
                .bounds(500, 80)
                .background(new Color(12, 4, 77))
                .foreground(new Color(242, 177, 0))
                .selectedIndex(this.progressIndex)
                .get();
        
        this.gamePanel = service.addOnPanel(gamePanel, 
            exit,
            questionText, 
            optionA, 
            optionB, 
            optionC, 
            optionD,
            gameProgress
            );
    }
    
    private void startQuiz() {
        if(!questions.isEmpty()) {
            if(this.gamePanel.getComponents().length > 0) {
                try {
                    quizService.thinkingEffect();
                    AudioManager.soundReaction(this.correctAnswer);
                } catch (InterruptedException | 
                UnsupportedAudioFileException | 
                IOException | 
                LineUnavailableException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Question question = this.questions.poll();
            
            this.questionIndex++;
            this.progressIndex--;
            
            if(this.gamePanel.getComponents().length > 0) {
                quizService.changeColorToGreen();
                gamePanel.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                       gamePanel.removeAll();
                       gamePanel.repaint();
                       currentQuestion = new CurrentQuestion(gamePanel, question, language, subject);
                       service.removeMotionListeners(gamePanel);
                       if(correctAnswer) {
                           score = quizService.getCurrentScore(gameProgress, questionIndex);
                           initComponents(question);
                       }else {
                           questionIndex--;
                           progressIndex++;
                           GameOver gameOver = new GameOver(quizService.getTotalScore(gameProgress, progressIndex, questionIndex));
                           try {
                               gameFrame.dispose();
                               try {
                                   gameOver.run();
                               } catch (MalformedURLException |
                                        LineUnavailableException |
                                        UnsupportedAudioFileException ex) {
                                   Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                               }
                           } catch (IOException ex) {
                               Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                           }
                       }
                       
                    }
                });
            }else {
                this.gamePanel.removeAll();
                this.gamePanel.repaint();
                currentQuestion = new CurrentQuestion(gamePanel, question, language, subject);
                initComponents(question);
            }
        }else {
            GameOver gameOver = new GameOver(quizService.getTotalScore(gameProgress, progressIndex, questionIndex));
            try {
                gameFrame.dispose();
                try {
                    gameOver.run();
                } catch (MalformedURLException |
                         LineUnavailableException |
                         UnsupportedAudioFileException ex) {
                         Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                         }
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
