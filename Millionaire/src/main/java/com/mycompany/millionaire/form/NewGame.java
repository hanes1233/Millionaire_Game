
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
    private final FormFactory FACTORY;
    private final ComponentServiceImpl SERVICE;
    private final PanelTemplate PANEL_CONFIG;
    
    private JButton start;
    private JComboBox<String> languagesList;
    private JComboBox<String> subjectsList;
    private JComboBox<String> difficultiesList;
    
    public NewGame() throws IOException {
        this.PANEL_CONFIG = new PanelTemplate();
        this.FACTORY = new FormFactory();
        this.configPanel = PanelTemplate.getPanel();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    public void run() {
        configForm = FACTORY.createForm();
        this.configForm.setContentPane(this.configPanel);
        initComponents();
    }
    
    private void initComponents() {
        languagesList = new JComboBox(Language.values());
        subjectsList = new JComboBox(Subject.values());
        difficultiesList = new JComboBox(Difficulty.values());
        
        JLabel welcomeLabel = new LabelBuilderImpl()
                .text("Choose your destiny!")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 26))
                .bounds(165, 50)
                .get();
        
        JLabel language = new LabelBuilderImpl()
                .text("Language")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 18))
                .bounds(180, 150)
                .get();
        
        JLabel subject = new LabelBuilderImpl()
                .text("Subject")
                .foreground(Color.WHITE)
                .font(new Font("Serif",Font.BOLD, 18))
                .bounds(180, 200)
                .get();
        
        JLabel difficulty = new LabelBuilderImpl()
                .text("Difficulty")
                .foreground(Color.WHITE)
                .font(new Font("Serif",Font.BOLD, 18))
                .bounds(180, 250)
                .get();
        
        languagesList = new ComboBoxBuilderImpl(languagesList)
                .foreground(Color.WHITE)
                .size(new Dimension(100,30))
                .bounds(300, 150)
                .selectedItemHover()
                .get();
        
        
        subjectsList = new ComboBoxBuilderImpl(subjectsList)
                .foreground(Color.WHITE)
                .size(new Dimension(120,30))
                .bounds(300, 200)
                .selectedItemHover()
                .get();
        
        difficultiesList = new ComboBoxBuilderImpl(difficultiesList)
                .foreground(Color.WHITE)
                .size(new Dimension(100,30))
                .bounds(300, 250)
                .selectedItemHover()
                .get();
        
        start = new ButtonBuilderImpl()
                .text("Start")
                .foreground(Color.WHITE)
                .background(new Color(0, 38, 75))
                .size(new Dimension(150, 35))
                .bounds(240, 320)
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
        
      
        this.PANEL_CONFIG.addBackButton(configForm);
        this.configPanel = SERVICE
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
