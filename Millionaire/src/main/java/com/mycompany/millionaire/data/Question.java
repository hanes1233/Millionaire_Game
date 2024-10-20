
package com.mycompany.millionaire.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Question entity class
 * @author pavel
 */

@Data
@AllArgsConstructor
public class Question {
    
    private String question;
    private String answer;
    private String subject;
    private String[] options;
    private String difficulty;
}
