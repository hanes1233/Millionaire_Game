
package com.mycompany.millionaire.data.constant;


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
