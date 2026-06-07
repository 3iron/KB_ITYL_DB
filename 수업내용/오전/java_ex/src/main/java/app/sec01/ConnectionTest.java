package app.sec01;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionTest {
    public static void main(String[] args) {
        // 연결 정보
        String uri = "mongodb://127.0.0.1:27017";
        String db = "practice_db";

        try(MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase(db);

        } catch (Exception e) {
            System.out.println("몽고DB연결시 에러 발생 " + e.getMessage());
        }

    }
}
