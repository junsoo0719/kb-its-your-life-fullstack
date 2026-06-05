package org.scoula.sec05;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.scoula.app.Database;

import static com.mongodb.client.model.Filters.gt;

public class DeleteManyTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = Database.getCollection("users");
        int age = 15;
        Bson query = gt("age", age);
        DeleteResult result = collection.deleteMany(query);
        System.out.println("==> DeleteManyResult : " + result.getDeletedCount());
        Database.close();
    }
}
