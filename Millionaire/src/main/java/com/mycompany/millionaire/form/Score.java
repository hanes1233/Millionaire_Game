
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.PanelTemplate;
import com.mycompany.millionaire.component.builder.ListBuilderImpl;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author pavel
 */
public class Score extends JFrame {
    
    private JFrame scoreForm;
    private JPanel scorePanel;
    private final ComponentServiceImpl service;
    private JList bestPlayers;
    private final FormFactory factory;
    private final PanelTemplate panelConfig;
    
    
    /**
     * Constructor initializes factory class to get method createForm and getPanel(),
     * to initialize new form and panel. Also in constructor we initialize service 
     * for get useful methods for our components
     * @throws IOException because factory method 'getPanel' also works with image(icon) 
     */
    public Score() throws IOException {
        this.panelConfig = new PanelTemplate();
        this.factory = new FormFactory();
        this.scorePanel = PanelTemplate.getPanel();
        this.service = new ComponentServiceImpl();
    }
    
    /**
     * Create new form with factory method 'createForm', set panel and initialize 
     * components to this panel
     */
    public void run() {
        scoreForm = factory.createForm();
        scoreForm.setContentPane(this.scorePanel);
        initComponents();
    }
    
    /**
     * Initialize our buttons,labels and event listeners and append it to panel
     */
    private void initComponents() {
        
        JLabel listDescription = new LabelBuilderImpl()
                .text("Statistics of best players")
                .foreground(Color.WHITE)
                .font(new Font("Serif", Font.BOLD, 22))
                .bounds(170, 25)
                .get();
        
        
        bestPlayers = new ListBuilderImpl()
                .size(new Dimension(450,380))
                .background(new Color(173, 194, 215))
                .foreground(Color.WHITE)
                .bounds(100, 60)
                .get();
        
        this.panelConfig.addBackButton(scoreForm);
        this.scorePanel = service
                .addOnPanel(
                scorePanel,
                bestPlayers,
                listDescription);
    }
    
}
