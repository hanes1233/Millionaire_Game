
package com.mycompany.millionaire.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.NoArgsConstructor;
import org.bson.Document;

/**
 *
 * @author pavel
 */
@NoArgsConstructor
public class Database {
    
    private MongoDatabase getDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        return mongoClient.getDatabase("millionaire");
    }
    
    public MongoCollection<Document> getCollection(String collectionName) {
        
        switch (collectionName) {
            case "English" -> {
                return getDatabase().getCollection("engquestions");
            }
            case "Český" -> {
                return getDatabase().getCollection("czquestions");
            }
            case "users" -> {
                return getDatabase().getCollection("users");
            }
            default -> throw new IllegalArgumentException("Collection name not valid.");
        }
    }
}
    
