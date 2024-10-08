
package com.mycompany.millionaire.form;

import com.mycompany.millionaire.component.builder.LabelBuilderImpl;
import com.mycompany.millionaire.component.PanelTemplate;
import com.mycompany.millionaire.component.builder.TableBuilderImpl;
import com.mycompany.millionaire.model.ComponentServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author pavel
 */
public class Score extends JFrame {
    
    private JFrame scoreForm;
    private JPanel scorePanel;
    private final ComponentServiceImpl SERVICE;
    private JTable table;
    private final FormFactory FACTORY;
    private final PanelTemplate PANEL_CONFIG;
    
    
    /**
     * Constructor initializes factory class to get method createForm and getPanel(),
     * to initialize new form and panel. Also in constructor we initialize service 
     * for get useful methods for our components
     * @throws IOException because factory method 'getPanel' also works with image(icon) 
     */
    public Score() throws IOException {
        this.PANEL_CONFIG = new PanelTemplate();
        this.FACTORY = new FormFactory();
        this.scorePanel = PanelTemplate.getPanel();
        this.SERVICE = new ComponentServiceImpl();
    }
    
    /**
     * Create new form with factory method 'createForm', set panel and initialize 
     * components to this panel
     */
    public void run() {
        scoreForm = FACTORY.createForm();
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
                .bounds(150, 25)
                .get();
        
        String[] columnNames = { "Name", "Score", "Wins" };
        String[][] data = {
            { "Kundan Kumar Jha", "4031", "CSE" },
            { "Anand Jha", "6014", "IT" }
        };
        
        JLabel name = new LabelBuilderImpl()
                .text("Name")
                .foreground(Color.ORANGE)
                .font(new Font("Serif", Font.BOLD, 14))
                .bounds(130, 60)
                .get();
        
        JLabel score = new LabelBuilderImpl()
                .text("Score")
                .foreground(Color.ORANGE)
                .font(new Font("Serif", Font.BOLD, 14))
                .bounds(250, 60)
                .get();
        
        JLabel wins = new LabelBuilderImpl()
                .text("Wins")
                .foreground(Color.ORANGE)
                .font(new Font("Serif", Font.BOLD, 14))
                .bounds(370, 60)
                .get();
        
        table = new JTable(data, columnNames);
        table = new TableBuilderImpl(table)
                .background(new Color(173, 194, 215))
                .foreground(Color.BLACK)
                .size(new Dimension(350, 380))
                .bounds(130,80)
                .get();
        
        
        this.PANEL_CONFIG.addBackButton(scoreForm);
        this.scorePanel = SERVICE
                .addOnPanel(
                scorePanel,
                name,
                score,
                wins,
                table,
                listDescription);
    }
    
}
