
package com.mycompany.millionaire.data;

/**
 *
 * @author pavel
 */
public class Question {
    
    private String question;
    private String answer;
    private String subject;
    private String[] options;
    private String difficulty;
    
    

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question(String question, String answer, String subject, String[] options, String difficulty) {
        this.question = question;
        this.answer = answer;
        this.subject = subject;
        this.options = options;
        this.difficulty = difficulty;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
    
    
}
