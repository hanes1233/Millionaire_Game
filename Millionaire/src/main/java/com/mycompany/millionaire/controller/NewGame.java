
package com.mycompany.millionaire.controller;

import com.mycompany.millionaire.model.component.builder.ComboBoxBuilderImpl;
import com.mycompany.millionaire.model.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.model.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.data.GameConfiguration;
import com.mycompany.millionaire.data.constant.Difficulty;
import com.mycompany.millionaire.data.constant.Language;
import com.mycompany.millionaire.data.constant.Subject;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.view.GameView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pavel
 */
public class NewGame {
    
    private final ComponentServiceImpl SERVICE;
    private final JPanel PANEL;
    
    private JButton backButton;
    private JButton start;
    private JComboBox<String> languagesList;
    private JComboBox<String> subjectsList;
    private JComboBox<String> difficultiesList;
    
    public NewGame() throws IOException {
        this.PANEL = GameView.getPanel();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    public void drawNewGameComponents() {
        this.PANEL.removeAll();
        
        languagesList = new JComboBox(Language.values());
        subjectsList = new JComboBox(Subject.values());
        difficultiesList = new JComboBox(Difficulty.values());
        
        JLabel welcomeLabel = new LabelBuilderImpl()
                .setText("Choose your destiny!")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif", Font.BOLD, 26))
                .setBounds(165, 50)
                .get();
        
        JLabel language = new LabelBuilderImpl()
                .setText("Language")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif", Font.BOLD, 18))
                .setBounds(180, 150)
                .get();
        
        JLabel subject = new LabelBuilderImpl()
                .setText("Subject")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif",Font.BOLD, 18))
                .setBounds(180, 200)
                .get();
        
        JLabel difficulty = new LabelBuilderImpl()
                .setText("Difficulty")
                .setForeground(Color.WHITE)
                .setFont(new Font("Serif",Font.BOLD, 18))
                .setBounds(180, 250)
                .get();
        
        languagesList = new ComboBoxBuilderImpl(languagesList)
                .setForeground(Color.WHITE)
                .setPreferredSize(new Dimension(100,30))
                .setBounds(300, 150)
                .onHover()
                .get();
        
        
        subjectsList = new ComboBoxBuilderImpl(subjectsList)
                .setForeground(Color.WHITE)
                .setPreferredSize(new Dimension(120,30))
                .setBounds(300, 200)
                .onHover()
                .get();
        
        difficultiesList = new ComboBoxBuilderImpl(difficultiesList)
                .setForeground(Color.WHITE)
                .setPreferredSize(new Dimension(100,30))
                .setBounds(300, 250)
                .onHover()
                .get();
        
        start = new ButtonBuilderImpl()
                .setText("Start")
                .setForeground(Color.WHITE)
                .setBackground(new Color(0, 38, 75))
                .setPreferredSize(new Dimension(150, 35))
                .setBounds(240, 320)
                .onHover()
                .get();
        
        
        
        start.addActionListener((ActionEvent e) -> {
                String selectedLanguage = languagesList.getSelectedItem().toString();
                String selectedSubject = subjectsList.getSelectedItem().toString();
                String selectedDifficulty = difficultiesList.getSelectedItem().toString();
                this.PANEL.removeAll();
                GameConfiguration config = new GameConfiguration(selectedDifficulty, selectedLanguage, selectedSubject);
            try {
                new Game(config);
            } catch (IOException ex) {
                Logger.getLogger(NewGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.backButton = new ButtonBuilderImpl()
                .setText("<")
                .setFont(new Font("Serif", Font.BOLD, 16))
                .setForeground(Color.WHITE)
                .setBackground(new Color(0, 38, 75))
                .setSize(50,25)
                .setBounds(15,15)
                .onHover()
                .get();
        
        backButton.addActionListener((ActionListener) -> {
            SERVICE.runMainPage();
        });
        
        this.SERVICE.addOnPanel(
                backButton, 
                welcomeLabel, 
                language, 
                subject, 
                difficulty,
                languagesList,
                subjectsList,
                difficultiesList,
                start
        );
        
        this.SERVICE.reloadPanel();
    }
}
