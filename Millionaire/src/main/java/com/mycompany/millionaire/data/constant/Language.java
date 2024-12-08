
package com.mycompany.millionaire.data.constant;

import lombok.Getter;

@Getter
public enum Language {
    
    ENGLISH("English"),
    
    CZECH("Český");
    
    private final String name;
    
    Language(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
