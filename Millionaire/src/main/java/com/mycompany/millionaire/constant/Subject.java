
package com.mycompany.millionaire.constant;

/**
 *
 * @author pavel
 */
public enum Subject {
    
    GEOGRAPHY("Geography"),
    
    HISTORY("History"),
    
    GENERAL("General");
    
    private final String name;
    
    private Subject(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
