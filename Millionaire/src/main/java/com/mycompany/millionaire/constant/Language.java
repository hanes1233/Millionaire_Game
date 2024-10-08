
package com.mycompany.millionaire.constant;

/**
 *
 * @author pavel
 */
public enum Language {
    
    ENGLISH("English"),
    
    CZECH("Český");
    
    private final String NAME;
    
    private Language(String name) {
        this.NAME = name;
    }
    
    @Override
    public String toString() {
       return this.NAME;
    }
}
