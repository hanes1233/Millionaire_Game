
package com.mycompany.millionaire.data;

import javax.swing.*;

/**
 * CLass provides game with right-based progress panel,
 * which indicates current question and price for every question
 * @author pavel
 */
public class ProgressList {
    
    private final DefaultListModel<String> progressBar;
    
    // Constructor
    public ProgressList() {
        this.progressBar = new DefaultListModel<>();
    }
    
    /**
     * Get progress list for game
     * @param language - depending on language fill PROGRESS_BAR list for english 
     * or czech game version
     * @return d
     */
    public ListModel<String> getModel(String language) {
        switch(language) {
            case "English" -> this.fulfillEngList();
            case "Český" -> this.fulfillCzList();
            default -> throw new UnsupportedOperationException("Unexptected error catched getting List model");
        }
        return this.progressBar;
    }
    
    // Fulfill PROGRESS_BAR with dollar amounts
    private void fulfillEngList() {
        this.progressBar.addElement("15     $1,000,000");
        this.progressBar.addElement("14        $500,000");
        this.progressBar.addElement("13        $250,000");
        this.progressBar.addElement("12        $100,000");
        this.progressBar.addElement("11          $50,000");
        this.progressBar.addElement("10          $25,000");
        this.progressBar.addElement("9            $15,000");
        this.progressBar.addElement("8            $12,500");
        this.progressBar.addElement("7            $10,000");
        this.progressBar.addElement("6              $7,500");
        this.progressBar.addElement("5              $5,000");
        this.progressBar.addElement("4              $3,000");
        this.progressBar.addElement("3              $2,000");
        this.progressBar.addElement("2              $1,000");
        this.progressBar.addElement("1                 $500");
    }
    
    // Fulfill PROGRESS_BAR with czech koruna amounts
    private void fulfillCzList() {
        this.progressBar.addElement("•   10,000.000Kč");
        this.progressBar.addElement("•     5,000.000Kč");
        this.progressBar.addElement("•     2,500.000Kč");
        this.progressBar.addElement("•     1,250.000Kč");
        this.progressBar.addElement("•        640.000Kč");
        this.progressBar.addElement("•        320.000Kč");
        this.progressBar.addElement("•        160.000Kč");
        this.progressBar.addElement("•          80.000Kč");
        this.progressBar.addElement("•          40.000Kč");
        this.progressBar.addElement("•          20.000Kč");
        this.progressBar.addElement("•          10.000Kč");
        this.progressBar.addElement("•            5.000Kč");
        this.progressBar.addElement("•            3.000Kč");
        this.progressBar.addElement("•            2.000Kč");
        this.progressBar.addElement("•            1.000Kč");
    }
    
}
