
package com.mycompany.millionaire.data;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 * CLass provides game with right-based progress panel,
 * which indicates current question and price for every question
 * @author pavel
 */
public class ProgressList {
    
    private final DefaultListModel<String> PROGRESS_BAR;
    
    // Constructor
    public ProgressList() {
        this.PROGRESS_BAR = new DefaultListModel<>();
    }
    
    /**
     * Get progress list for game
     * @param language - depending on language fill PROGRESS_BAR list for english 
     * or czech game version
     * @return d
     */
    public ListModel getModel(String language) {
        switch(language) {
            case "English" -> this.fulfillEngList();
            case "Český" -> this.fulfillCzList();
            default -> throw new Error("Unexptected error catched getting List model");
        }
        return this.PROGRESS_BAR;
    }
    
    // Fulfill PROGRESS_BAR with dollar amounts
    private void fulfillEngList() {
        this.PROGRESS_BAR.addElement("15     $1,000,000");
        this.PROGRESS_BAR.addElement("14        $500,000");
        this.PROGRESS_BAR.addElement("13        $250,000");
        this.PROGRESS_BAR.addElement("12        $100,000");
        this.PROGRESS_BAR.addElement("11          $50,000");
        this.PROGRESS_BAR.addElement("10          $25,000");
        this.PROGRESS_BAR.addElement("9            $15,000");
        this.PROGRESS_BAR.addElement("8            $12,500");
        this.PROGRESS_BAR.addElement("7            $10,000");
        this.PROGRESS_BAR.addElement("6              $7,500");
        this.PROGRESS_BAR.addElement("5              $5,000");
        this.PROGRESS_BAR.addElement("4              $3,000");
        this.PROGRESS_BAR.addElement("3              $2,000");
        this.PROGRESS_BAR.addElement("2              $1,000");
        this.PROGRESS_BAR.addElement("1                 $500"); 
    }
    
    // Fulfill PROGRESS_BAR with czech koruna amounts
    private void fulfillCzList() {
        this.PROGRESS_BAR.addElement("•   10,000.000Kč");
        this.PROGRESS_BAR.addElement("•     5,000.000Kč");
        this.PROGRESS_BAR.addElement("•     2,500.000Kč");
        this.PROGRESS_BAR.addElement("•     1,250.000Kč");
        this.PROGRESS_BAR.addElement("•        640.000Kč");
        this.PROGRESS_BAR.addElement("•        320.000Kč");
        this.PROGRESS_BAR.addElement("•        160.000Kč");
        this.PROGRESS_BAR.addElement("•          80.000Kč");
        this.PROGRESS_BAR.addElement("•          40.000Kč");
        this.PROGRESS_BAR.addElement("•          20.000Kč");
        this.PROGRESS_BAR.addElement("•          10.000Kč");
        this.PROGRESS_BAR.addElement("•            5.000Kč");
        this.PROGRESS_BAR.addElement("•            3.000Kč");
        this.PROGRESS_BAR.addElement("•            2.000Kč");
        this.PROGRESS_BAR.addElement("•            1.000Kč"); 
    }
    
}
