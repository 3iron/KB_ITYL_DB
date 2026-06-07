package app.sec03;

import app.sec01.Database;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class FindOneTest {
    public static void main(String[] args) {

        MongoCollection<Document> collection = Database.getCollection("study");

        String id = "666a6296f4fe57189cd03eea";

        Bson query = eq("_id", new ObjectId(id));

        Document doc = collection.find(query).first();




    }
}
