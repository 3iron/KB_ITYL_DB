package app.sec03;

import app.sec01.Database;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Iterator;

public class FindTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = Database.getCollection("study");

        // FindIterable : 조회 결과를 반복자(Iterator) 형태로 반환할 수 있는 객체
        FindIterable<Document> doc = collection.find();

        // Iterator : 컬렉션(Collection)에 들어있는 데이터를 하나씩 꺼내서 순회하는 객체
        Iterator<Document> it = doc.iterator();

        while(it.hasNext()) {
            System.out.println("다음 객체 : " + it.next());
        }

        Database.close();
    }
}
