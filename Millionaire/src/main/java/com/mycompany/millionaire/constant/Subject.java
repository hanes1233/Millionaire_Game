
package com.mycompany.millionaire.constant;

/**
 *
 * @author pavel
 */
public enum Subject {
    
    GEOGRAPHY("Geography"),
    
    HISTORY("History"),
    
    GENERAL("General");
    
    private final String NAME;
    
    private Subject(String name) {
        this.NAME = name;
    }
    
    @Override
    public String toString() {
        return this.NAME;
    }
}
