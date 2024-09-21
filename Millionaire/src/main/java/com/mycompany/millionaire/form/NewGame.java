
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.component.builder.ComboBoxBuilderImpl;
import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.PanelTemplate;
import com.mycompany.millionaire.component.builder.ButtonBuilderImpl;
import com.mycompany.millionaire.constant.Difficulty;
import com.mycompany.millionaire.constant.Language;
import com.mycompany.millionaire.constant.Subject;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pavel
 */
public class NewGame {
    
    private JFrame configForm;
    private JPanel configPanel;
    private final FormFactory factory;
    private final ComponentServiceImpl service;
    private final PanelTemplate panelConfig;
    
    private JButton start;
    private JComboBox<String> languagesList;
    private JComboBox<String> subjectsList;
    private JComboBox<String> difficultiesList;
    
    public NewGame() throws IOException {
        this.panelConfig = new PanelTemplate();
        this.factory = new FormFactory();
        this.configPanel = PanelTemplate.getPanel();
        this.service = new ComponentServiceImpl();
    }
    
    public void run() {
        configForm = factory.createForm();
        this.configForm.setContentPane(this.configPanel);
        initComponents();
    }
    
    private void initComponents() {
        JLabel welcomeLabel = new JLabel();
        JLabel language = new JLabel();
        JLabel subject = new JLabel();
        JLabel difficulty = new JLabel();
        languagesList = new JComboBox(Language.values());
        subjectsList = new JComboBox(Subject.values());
        difficultiesList = new JComboBox(Difficulty.values());
        start = new JButton();
        
        welcomeLabel = new LabelBuilderImpl(welcomeLabel)
                .text("Choose your destiny!")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 26))
                .bounds(165, 50, service.getWidth(welcomeLabel), service.getHeight(welcomeLabel))
                .get();
        
        language = new LabelBuilderImpl(language)
                .text("Language")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 18))
                .bounds(180, 150, service.getWidth(language), service.getHeight(language))
                .get();
        
        subject = new LabelBuilderImpl(subject)
                .text("Subject")
                .foreground(Color.WHITE)
                .font(new Font("Serif",Font.BOLD, 18))
                .bounds(180, 200, service.getWidth(subject), service.getHeight(subject))
                .get();
        
        difficulty = new LabelBuilderImpl(difficulty)
                .text("Difficulty")
                .foreground(Color.WHITE)
                .font(new Font("Serif",Font.BOLD, 18))
                .bounds(180, 250, service.getWidth(difficulty), service.getHeight(difficulty))
                .get();
        
        languagesList = new ComboBoxBuilderImpl(languagesList)
                .foreground(Color.WHITE)
                .size(new Dimension(100,30))
                .bounds(300,150,service.getWidth(languagesList),service.getHeight(languagesList))
                .selectedItemHover()
                .get();
        
        
        subjectsList = new ComboBoxBuilderImpl(subjectsList)
                .foreground(Color.WHITE)
                .size(new Dimension(120,30))
                .bounds(300,200,service.getWidth(subjectsList),service.getHeight(subjectsList))
                .selectedItemHover()
                .get();
        
        difficultiesList = new ComboBoxBuilderImpl(difficultiesList)
                .foreground(Color.WHITE)
                .size(new Dimension(100,30))
                .bounds(300,250,service.getWidth(difficultiesList),service.getHeight(difficultiesList))
                .selectedItemHover()
                .get();
        
        start = new ButtonBuilderImpl(start)
                .text("Start")
                .foreground(Color.WHITE)
                .background(new Color(0, 38, 75))
                .bounds(240, 320, 150, 35)
                .selectedItemHover()
                .get();
        
        
        
        start.addActionListener((ActionEvent e) -> {
            
            try {
                String selectedLanguage = languagesList.getSelectedItem().toString();
                String selectedSubject = subjectsList.getSelectedItem().toString();
                String selectedDifficulty = difficultiesList.getSelectedItem().toString();
                
                new GameFrame().run(selectedLanguage, selectedSubject, selectedDifficulty);
                this.configForm.dispose();
            }catch (IOException ex) {
                Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
      
        this.panelConfig.addBackButton(configForm);
        this.configPanel = service
                //.addBackButton(configForm)
                .addOnPanel(
                        configPanel, 
                        welcomeLabel,
                        language, 
                        subject, 
                        difficulty, 
                        languagesList,
                        subjectsList,
                        difficultiesList,
                        start);
        
    }
}
