
package com.mycompany.millionaire.constant;

/**
 *
 * @author pavel
 */
public enum Difficulty {
    
    EASY("Easy"),
    
    MEDIUM("Medium"),
    
    HARD("Hard");
    
    private final String name;
    
    private Difficulty(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
