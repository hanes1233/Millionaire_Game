
package com.mycompany.millionaire.constant;

/**
 *
 * @author pavel
 */
public enum Language {
    
    ENGLISH("English"),
    
    CZECH("Český");
    
    private final String name;
    
    private Language(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
       return this.name;
    }
}
