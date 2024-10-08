
package com.mycompany.millionaire.constant;

/**
 *
 * @author pavel
 */
public enum Difficulty {
    
    EASY("Easy"),
    
    MEDIUM("Medium"),
    
    HARD("Hard");
    
    private final String NAME;
    
    private Difficulty(String name) {
        this.NAME = name;
    }
    
    @Override
    public String toString() {
        return this.NAME;
    }
}
