package app.오전수업;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionTest {
    public static void main(String[] args) {
        // 연결 정보
        String uri = "mongodb://127.0.0.1:27017";
        String db = "todo_db";

        // 몽고 db와 java 연동
        // 몽고 db 연동할 수 있는 자바 라이브러리가 필요
        // 몽고 db 드라이버 필요

        // 1. 서버 연결 -> 외부 자원(네트워크) 연결은 반드시 예외 처리
        // try-catch-resources
        // 2. 서버 db 연결
        // 3. db collection연결
        // 4. document(json) crud
        // 5. close

        try(MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("1. 몽고 db 연결 성공. " + mongoClient);
            MongoDatabase database = mongoClient.getDatabase(db);
            System.out.println("2. 몽고 db 연결(todo_db) 성공." + database);

        } catch (Exception e) {
            System.out.println("몽고DB연결시 에러 발생 " + e.getMessage());
        }

    }
}
