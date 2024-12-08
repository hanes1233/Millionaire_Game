
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
import javax.swing.*;

import static com.mycompany.millionaire.data.constant.FontType.*;

/**
 * New Game controller
 * @author pavel
 */
public class NewGame {
    
    // Declare service and JPanel instances
    private final ComponentServiceImpl componentService;
    private final JPanel panel;
    
    // Declare components
    private JComboBox<String> languagesList;
    private JComboBox<String> subjectsList;
    private JComboBox<String> difficultiesList;
    
    // Constructor
    public NewGame() throws IOException {
        this.panel = GameView.getPanel();
        this.componentService = new ComponentServiceImpl();
    }
    
    // Init components and add on panel
    public void drawNewGameComponents() {
        this.panel.removeAll();

        languagesList = new JComboBox(Language.values());
        subjectsList = new JComboBox(Subject.values());
        difficultiesList = new JComboBox(Difficulty.values());
        
        JLabel welcomeLabel = new LabelBuilderImpl()
                .text("Choose your destiny!")
                .foreground(Color.WHITE)
                .font(new Font(SERIF, Font.BOLD, 26))
                .bounds(165, 50)
                .build();
        
        JLabel language = new LabelBuilderImpl()
                .text("Language")
                .foreground(Color.WHITE)
                .font(new Font(SERIF, Font.BOLD, 18))
                .bounds(180, 150)
                .build();
        
        JLabel subject = new LabelBuilderImpl()
                .text("Subject")
                .foreground(Color.WHITE)
                .font(new Font(SERIF,Font.BOLD, 18))
                .bounds(180, 200)
                .build();
        
        JLabel difficulty = new LabelBuilderImpl()
                .text("Difficulty")
                .foreground(Color.WHITE)
                .font(new Font(SERIF,Font.BOLD, 18))
                .bounds(180, 250)
                .build();
        
        languagesList = new ComboBoxBuilderImpl(languagesList)
                .foreground(Color.WHITE)
                .preferredSize(new Dimension(100,30))
                .bounds(300, 150)
                .onHover()
                .build();
        
        
        subjectsList = new ComboBoxBuilderImpl(subjectsList)
                .foreground(Color.WHITE)
                .preferredSize(new Dimension(120,30))
                .bounds(300, 200)
                .onHover()
                .build();
        
        difficultiesList = new ComboBoxBuilderImpl(difficultiesList)
                .foreground(Color.WHITE)
                .preferredSize(new Dimension(100,30))
                .bounds(300, 250)
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
