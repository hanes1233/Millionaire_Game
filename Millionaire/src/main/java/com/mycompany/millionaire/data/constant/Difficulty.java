
package com.mycompany.millionaire.data.constant;

import lombok.Getter;

@Getter
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
