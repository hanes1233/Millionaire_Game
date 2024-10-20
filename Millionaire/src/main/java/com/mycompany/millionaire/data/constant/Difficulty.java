
package com.mycompany.millionaire.data.constant;


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
