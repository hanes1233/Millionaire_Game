
package com.mycompany.millionaire.data;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.bson.Document;

/**
 *
 * @author pavel
 */
public class Database {
    
    private final ArrayList<Question> easyQuestions;
    private final ArrayList<Question> mediumQuestions;
    private final ArrayList<Question> hardQuestions;
    private final ArrayList<Question> extremelyQuestions;
    private final Queue<Question> questionList;
    
    public Database(String language) {
        easyQuestions = new ArrayList<>();
        mediumQuestions = new ArrayList<>();
        hardQuestions = new ArrayList<>();
        extremelyQuestions = new ArrayList<>();
        questionList = new LinkedList<>();
        getQuestions(language);
    }
    
    public Queue<Question> getQuestionList() {
        return this.questionList;
    }
    
    private void getQuestions(String language) {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("millionaire");
        MongoCollection<Document> collection = (language.equals("English")) ? 
                database.getCollection("engquestions") : 
                database.getCollection("czquestions");
        FindIterable<Document> iterableCollection = collection.find();
        for(Document doc: iterableCollection) {
            String optionsToString = doc.get("options").toString();
            String[] options = optionsToString.split(", ");
            for(int i = 0; i < options.length; i++) {
                options[i] = options[i].replaceAll("\\[", "").replaceAll("\\]","").trim();
            }
            Question question = new Question(
                    doc.get("question").toString(),
                    doc.get("answer").toString(),
                    doc.get("subject").toString(),
                    options,
                    doc.get("level").toString()
            );
            switch(question.getDifficulty()) {
                case "Easy" -> {
                    easyQuestions.add(question);
                }
                case "Medium" -> {
                    mediumQuestions.add(question);
                }
                case "Hard" -> {
                    hardQuestions.add(question);
                }
                case "Extremely" -> {
                    extremelyQuestions.add(question);
                }
                default -> System.out.println("Error in difficulty string - unknown name: " + question.getDifficulty());
            }
        }           
            for(Question easyQuestion : easyQuestions) {
                questionList.add(easyQuestion);
            }
            for(Question mediumQuestion : mediumQuestions) {
                questionList.add(mediumQuestion);
            }
            for(Question hardQuestion : hardQuestions) {
                questionList.add(hardQuestion);
            }
            for(Question extremelyQuestion : extremelyQuestions) {
                questionList.add(extremelyQuestion);
            }
    }
}
    
