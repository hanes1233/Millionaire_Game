
package com.mycompany.millionaire.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.NoArgsConstructor;
import org.bson.Document;

/**
 * Database class holds database connection settings
 * @author pavel
 */
@NoArgsConstructor
public class Database {
    
    /**
     * Connect to MongoDB and get database
     * @return fetched database
     */
    private MongoDatabase getDatabase() {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        return mongoClient.getDatabase("millionaire");
    }
    
    /**
     * Fetch collection from database
     * @param collectionName - provided parameter to fetch collection
     * @return collection
     */
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
            default -> throw new IllegalArgumentException("Collection name is not valid.");
        }
    }
}
    
