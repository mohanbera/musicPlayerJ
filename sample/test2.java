// basic connection with mongoDB and insert new values
package sample;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import org.bson.Document;
import org.bson.types.Binary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.function.BinaryOperator;

public class test2
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException
    {
        String uri="mongodb://mohan:mohan@cluster2-shard-00-00-ahl4u.mongodb.net:27017,cluster2-shard-00-01-ahl4u.mongodb.net:27017,cluster2-shard-00-02-ahl4u.mongodb.net:27017/test?ssl=true&replicaSet=Cluster2-shard-0&authSource=admin&retryWrites=true";
        MongoClientURI mongoClientURI1=new MongoClientURI(uri);
        MongoClient mongoClient1=new MongoClient(mongoClientURI1);

        DB db=mongoClient1.getDB("sampleMongoDB1");
        DBCollection dbCollection=db.getCollection("first");

        BasicDBObject object=new BasicDBObject();
        FileInputStream fileInputStream=new FileInputStream(new File("C:\\Users\\Mohan Bera\\Downloads\\songImage.jpg"));
        byte[] arr1=new byte[fileInputStream.available()];
        fileInputStream.read(arr1);
        object.append("name","Epic Music-When you will save us");
        object.append("address","https://ytww.xyz/@download/192-5c87628805f07-10368000-432/mp3/K1oLThzQ9zo/WHEN%2BWILL%2BYOU%2BSAVE%2BME%2B%257C%2Bby%2BEnd%2BOf%2BSilence%2B%2528Ft.%2BJulie%2BElven%2529.mp3");
        object.append("image",arr1);
        dbCollection.insert(object);
        /*
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", "Tum Hi Ho");
        DBCursor cursor = dbCollection.find(whereQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        */
    }
}