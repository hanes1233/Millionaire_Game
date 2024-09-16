
package com.mycompany.millionaire.data;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author pavel
 */
public class ProgressList {
    
    private final DefaultListModel<String> model;
    
    public ProgressList() {
        this.model = new DefaultListModel<>();
    }
    
    public ListModel getModel(String language) {
        switch(language) {
            case "English" -> this.fullfillEngList();
            case "Český" -> this.fullfillCzList();
            default -> throw new Error("Unexptected error catched getting List model");
        }
        return this.model;
    }
    
    private void fullfillEngList() {
        this.model.addElement("15     $1,000,000");
        this.model.addElement("14        $500,000");
        this.model.addElement("13        $250,000");
        this.model.addElement("12        $100,000");
        this.model.addElement("11          $50,000");
        this.model.addElement("10          $25,000");
        this.model.addElement("9            $15,000");
        this.model.addElement("8            $12,500");
        this.model.addElement("7            $10,000");
        this.model.addElement("6              $7,500");
        this.model.addElement("5              $5,000");
        this.model.addElement("4              $3,000");
        this.model.addElement("3              $2,000");
        this.model.addElement("2              $1,000");
        this.model.addElement("1                 $500"); 
    }
    
    private void fullfillCzList() {
        this.model.addElement("•   10,000.000Kč");
        this.model.addElement("•     5,000.000Kč");
        this.model.addElement("•     2,500.000Kč");
        this.model.addElement("•     1,250.000Kč");
        this.model.addElement("•        640.000Kč");
        this.model.addElement("•        320.000Kč");
        this.model.addElement("•        160.000Kč");
        this.model.addElement("•          80.000Kč");
        this.model.addElement("•          40.000Kč");
        this.model.addElement("•          20.000Kč");
        this.model.addElement("•          10.000Kč");
        this.model.addElement("•            5.000Kč");
        this.model.addElement("•            3.000Kč");
        this.model.addElement("•            2.000Kč");
        this.model.addElement("•            1.000Kč"); 
    }
    
}
