
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.component.PanelConfiguration;
import com.mycompany.millionaire.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.component.builder.TextAreaBuilderImpl;
import com.mycompany.millionaire.data.Database;
import com.mycompany.millionaire.data.ProgressList;
import com.mycompany.millionaire.data.Question;
import com.mycompany.millionaire.media.AudioManager;
import com.mycompany.millionaire.media.ImageManager;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import com.mycompany.millionaire.model.Hint;
import com.mycompany.millionaire.model.QuizService;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    
    private JTextArea question;
    private JButton optionA;
    private JButton optionB;
    private JButton optionC;
    private JButton optionD;
    private JList<String> gameProgress;
    private JLabel exit;
    
    private JFrame gameFrame;
    private JPanel gamePanel;
    private final FormFactory formFactory;
    private final PanelConfiguration panelConfig;
    private final ComponentServiceImpl service;
    private final QuizService quizService;
    private ProgressList progressList;
    private Queue<Question> questions;
    private Database database;
    private Hint hints;
    
    private String language;
    private String subject;
    private String difficulty;
    private String[] options;
    private int progressIndex = 15;
    private int questionIndex = 0;
    private boolean winner;
    private boolean gameOver;
    private boolean correctAnswer;
    private Question currentQuestion;  
    
    public GameFrame() throws  IOException {
        this.panelConfig = new PanelConfiguration();
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
        this.database = new Database(language);
        this.questions = database.getQuestionList();
        this.progressList = new ProgressList();
        this.gamePanel = panelConfig.getPanel();
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
        
        question = new JTextArea();
        optionA = new JButton();
        optionB = new JButton();
        optionC = new JButton();
        optionD = new JButton();
        
        
        this.options = currentQuestion.getOptions();
        String currentAnswer = currentQuestion.getAnswer();
        
        
        question = new TextAreaBuilderImpl(question)
                .formatText()
                .text(currentQuestion.getQuestion())
                .font(new Font("Serif", Font.ITALIC, 16))
                .background(new Color(0, 38, 75))
                .foreground(Color.WHITE)
                .bounds(30, 180, 450, 90)
                .readOnly()
                .get();
        
        
        optionA = new ButtonBuilderImpl(optionA)
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>A:</font>" + options[0] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(30, 300, 220, 35)
                .get();
        
        optionA.addActionListener((ActionEvent e) -> {
                    optionA.setBackground(new Color(51,255,51));
                    this.correctAnswer = quizService.isAnswerCorrect(options[0], currentAnswer);
                    startQuiz();   
        });
        
        optionB = new ButtonBuilderImpl(optionB)
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>B:</font>" + options[1] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(260, 300, 220, 35)
                .get();
        
        optionB.addActionListener((ActionEvent e) -> {
                    optionB.setBackground(new Color(51,255,51));
                    this.correctAnswer = quizService.isAnswerCorrect(options[1], currentAnswer);
                    startQuiz();
               
        });
        
        optionC = new ButtonBuilderImpl(optionC)
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>C:</font>" + options[2] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(30, 350, 220, 35)
                .get();
        
        optionC.addActionListener((ActionEvent e) -> {
               optionC.setBackground(new Color(51,255,51));
               this.correctAnswer = quizService.isAnswerCorrect(options[2], currentAnswer);
             /*  new Thread(() -> {
                    optionC.setBackground(new Color(51,255,51));
                    this.gamePanel = panelConfig.removeElements(gamePanel, optionC);
                    this.gamePanel.add(optionC);
                    this.gamePanel.revalidate();
                    this.gamePanel.repaint();
                }).start(); */
               
               startQuiz();
        }); 
        
        optionD = new ButtonBuilderImpl(optionD)
                .gameStyleConfig()
                .text("<html><font color=rgb(213,156,0)>D:</font>" + options[3] + "</html>")
                .textHorizontalAlign(SwingConstants.LEFT)
                .bounds(260, 350, 220, 35)
                .get();
        
        optionD.addActionListener((ActionEvent e) -> {
                    optionD.setBackground(new Color(51,255,51));
                    this.correctAnswer = quizService.isAnswerCorrect(options[3], currentAnswer);
                    startQuiz();
               
        });
        
        
        
        
        if(!this.difficulty.equals("Hard")) {
            this.hints.setOptionA(this.optionA);
            this.hints.setOptionB(this.optionB);
            this.hints.setOptionC(this.optionC);
            this.hints.setOptionD(this.optionD);
            this.hints.setCurrentQuestion(currentQuestion);
            
            if(this.difficulty.equals("Easy")) {
                hints.addHints(gamePanel);
            }else if(this.difficulty.equals("Medium")) {
                hints.addFiftyToFiftyHint(gamePanel);
            }
        } 
        
        
        exit = new JLabel();
        exit = new LabelBuilderImpl(exit)
                .image(ImageManager.getImageIcon("exit"))
                .bounds(580, 20, service.getWidth(exit), service.getHeight(exit))
                .get();
        
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AudioManager.muteIntro();
                gameFrame.dispose();
            }
        });
        
        
        gameProgress = new JList();
        gameProgress = new ListBuilderImpl(gameProgress)
                .model(progressList.getModel(this.language))
                .bounds(500, 80, 200, 280)
                .background(new Color(12, 4, 77))
                .foreground(new Color(242, 177, 0))
                .selectedIndex(this.progressIndex)
                .get();
        
        this.gamePanel = panelConfig.addOnPanel(
            gamePanel, 
            exit,
            question, 
            optionA, 
            optionB, 
            optionC, 
            optionD,
            gameProgress
            );
        
        this.hints.setPanel(gamePanel);
    }
    
    private void startQuiz() {
        if(!questions.isEmpty()) {
            if(this.gamePanel.getComponents().length > 0) {
                try {
                    quizService.thinkingEffect(currentQuestion.getDifficulty());
                    AudioManager.soundReaction(this.correctAnswer);
                    Thread.sleep(3500);
                } catch (InterruptedException | 
                UnsupportedAudioFileException | 
                IOException | 
                LineUnavailableException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.gamePanel.removeAll();
                this.gamePanel.repaint();
                //this.optionC.setBackground(new Color(51,255,51));
            }
            this.currentQuestion = this.questions.poll();
            this.questionIndex++;
            this.progressIndex--;
            initComponents(currentQuestion);
        }else {
            this.winner = true;
        }
    }

}
