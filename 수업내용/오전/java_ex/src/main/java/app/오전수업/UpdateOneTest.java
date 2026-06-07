package app.오전수업;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class UpdateOneTest {
    public static void main(String[] args) {

        MongoCollection<Document> collection = Database.getCollection("users");
        String id = "6a2250d7b64e3e11d75bc0f4";

        Bson query = eq("_id", new ObjectId(id));

        Bson updates = Updates.combine(
                Updates.set("name", "modify name"),
                Updates.currentTimestamp("lastUpdated"));

        UpdateResult result = collection.updateOne(query, updates);
        System.out.println("==> UpdateResult : " + result.getModifiedCount());
        Database.close();
    }
}