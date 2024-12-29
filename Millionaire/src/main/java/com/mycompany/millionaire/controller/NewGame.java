
package com.mycompany.millionaire.controller;

import com.mycompany.millionaire.data.GameConfiguration;
import com.mycompany.millionaire.data.constant.Difficulty;
import com.mycompany.millionaire.data.constant.Language;
import com.mycompany.millionaire.data.constant.Subject;
import com.mycompany.millionaire.model.component.ComponentServiceImpl;
import com.mycompany.millionaire.model.component.builder.*;
import com.mycompany.millionaire.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static com.mycompany.millionaire.data.constant.FontType.SERIF;

/**
 * New Game controller
 * @author pavel
 */
public class NewGame {
    
    // Declare service and JPanel instances
    private final ComponentServiceImpl componentService;
    private final JPanel panel;
    
    // Declare components
    private JComboBox<Language> languagesList;
    private JComboBox<Subject> subjectsList;
    private JComboBox<Difficulty> difficultiesList;
    
    // Constructor
    public NewGame() throws IOException {
        this.panel = GameView.getPanel();
        this.componentService = new ComponentServiceImpl();
    }
    
    // Init components and add on panel
    public void drawNewGameComponents() {
        this.panel.removeAll();

        languagesList = new JComboBox<>(Language.values());
        subjectsList = new JComboBox<>(Subject.values());
        difficultiesList = new JComboBox<>(Difficulty.values());
        
        JLabel welcomeLabel = new LabelBuilder()
                .setText("Choose your destiny!")
                .setForeground(Color.WHITE)
                .setFont(new Font(SERIF, Font.BOLD, 26))
                .setBounds(165, 50)
                .build();
        
        JLabel language = new LabelBuilder()
                .setText("Language")
                .setForeground(Color.WHITE)
                .setFont(new Font(SERIF, Font.BOLD, 18))
                .setBounds(180, 150)
                .build();
        
        JLabel subject = new LabelBuilder()
                .setText("Subject")
                .setForeground(Color.WHITE)
                .setFont(new Font(SERIF,Font.BOLD, 18))
                .setBounds(180, 200)
                .build();
        
        JLabel difficulty = new LabelBuilder()
                .setText("Difficulty")
                .setForeground(Color.WHITE)
                .setFont(new Font(SERIF,Font.BOLD, 18))
                .setBounds(180, 250)
                .build();

        languagesList = new ComboBoxBuilder<>(languagesList)
                .setForeground(Color.WHITE)
                .setPreferredSize(new Dimension(100,30))
                .setBounds(300,150)
                .onHover()
                .build();
        
        
        subjectsList = new ComboBoxBuilder<>(subjectsList)
                .setForeground(Color.WHITE)
                .setPreferredSize(new Dimension(120,30))
                .setBounds(300, 200)
                .onHover()
                .build();
        
        difficultiesList = new ComboBoxBuilder<>(difficultiesList)
                .setForeground(Color.WHITE)
                .setPreferredSize(new Dimension(100,30))
                .setBounds(300, 250)
                .onHover()
                .build();

        JButton start = new ButtonBuilderImpl()
                .text("Start")
                .foreground(Color.WHITE)
                .background(new Color(0, 38, 75))
                .preferredSize(new Dimension(150, 35))
                .bounds(240, 320)
                .onHover()
                .build();

        start.addActionListener((ActionEvent e) -> {
            
                // Get selected enum values
                String selectedLanguage = languagesList.getSelectedItem().toString();
                String selectedSubject = subjectsList.getSelectedItem().toString();
                String selectedDifficulty = difficultiesList.getSelectedItem().toString();
                
                // Clear panel of components if exist
                this.panel.removeAll();
                
                // Create new game configuration
                GameConfiguration config = new GameConfiguration(selectedDifficulty, selectedLanguage, selectedSubject);

                // Run new game with configuration
                new Game(config);
        });

        JButton backButton = new ButtonBuilderImpl()
                .text("<")
                .font(new Font(SERIF, Font.BOLD, 16))
                .foreground(Color.WHITE)
                .background(new Color(0, 38, 75))
                .minSize(50,25)
                .bounds(15,15)
                .onHover()
                .build();
        
        backButton.addActionListener(e -> componentService.runMainPage());
        
        this.componentService.addOnPanel(
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
        
        // Repaint and revalidate panel
        this.componentService.reloadPanel();
    }
}
