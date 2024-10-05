
package com.mycompany.millionaire.data;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import org.bson.Document;

/**
 *
 * @author pavel
 */
public class Database {
    
    private ArrayList<Question> easyQuestions;
    private ArrayList<Question> mediumQuestions;
    private ArrayList<Question> hardQuestions;
    private ArrayList<Question> extremelyQuestions;
    private final Queue<Question> questionList;
    
    public Database(String language, String subject, String difficulty) {
        easyQuestions = new ArrayList<>();
        mediumQuestions = new ArrayList<>();
        hardQuestions = new ArrayList<>();
        extremelyQuestions = new ArrayList<>();
        questionList = new LinkedList<>();
        getQuestions(language, subject, difficulty);
    }
    
    public Queue<Question> getQuestionList() {
        return this.questionList;
    }
    
    private void getQuestions(String language, String subject, String difficulty) {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("millionaire");
        MongoCollection<Document> collection = (language.equals("English")) ? 
                database.getCollection("engquestions") : 
                database.getCollection("czquestions");
        FindIterable<Document> iterableCollection = collection.find();
        for(Document doc: iterableCollection) {
            if(doc.get("subject").equals(subject)) {
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
        }   
            switch(difficulty) {
                case "Easy" -> {
                    easyQuestions = getRandomList(easyQuestions,5);
                    mediumQuestions = getRandomList(mediumQuestions,5);
                    hardQuestions = getRandomList(hardQuestions,3);
                    extremelyQuestions = getRandomList(extremelyQuestions,2);
                    break;
            }
                case "Medium" -> {
                    easyQuestions = getRandomList(easyQuestions,3);
                    mediumQuestions = getRandomList(mediumQuestions,5);
                    hardQuestions = getRandomList(hardQuestions,5);
                    extremelyQuestions = getRandomList(extremelyQuestions,2);
                    break;
            }
                case "Hard" -> {
                    hardQuestions = getRandomList(hardQuestions,10);
                    extremelyQuestions = getRandomList(extremelyQuestions,5);
                    break;
            }
            }
            
            if(!difficulty.equals("Hard")) {
                for(Question easyQuestion : easyQuestions) {
                    questionList.add(easyQuestion);
                }
                for(Question mediumQuestion : mediumQuestions) {
                    questionList.add(mediumQuestion);
                }
            }
            
            for(Question hardQuestion : hardQuestions) {
                questionList.add(hardQuestion);
            }
            for(Question extremelyQuestion : extremelyQuestions) {
                questionList.add(extremelyQuestion);
            }
            
    }
    
    private ArrayList<Question> getRandomList(ArrayList<Question> list, int size) {
        Set<Question> setList = new HashSet<>();
        while(setList.size() != size) {
           int randomIndex = new Random().nextInt(list.size()-1);
           setList.add(list.get(randomIndex));
        }
        ArrayList<Question> randomList = new ArrayList<>();
        randomList.addAll(setList);
        return randomList;
    }
}
    
