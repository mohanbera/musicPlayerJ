package sample;

import com.mongodb.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import org.bson.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class database {
    static MongoClient mongoClient1=null;
    static DB db=null;
    static DBCollection dbCollection=null;
    static boolean initOn=false;
    DBCursor cursor=null;
    @SuppressWarnings("deprecation")
    public void init()
    {
        String uri="mongodb://mohan:mohan@cluster2-shard-00-00-ahl4u.mongodb.net:27017,cluster2-shard-00-01-ahl4u.mongodb.net:27017,cluster2-shard-00-02-ahl4u.mongodb.net:27017/test?ssl=true&replicaSet=Cluster2-shard-0&authSource=admin&retryWrites=true";
        MongoClientURI mongoClientURI=new MongoClientURI(uri);
        mongoClient1=new MongoClient(mongoClientURI);
        db=mongoClient1.getDB("sampleMongoDB1");
        dbCollection=db.getCollection("first");
    }
    public ArrayList<String> list()
    {
        if(!initOn)
        {
            init();
            initOn=true;
        }
        ArrayList<String> arrayList=new ArrayList<>();
        cursor = dbCollection.find();
        while(cursor.hasNext())
        {
            BasicDBObject string00=(BasicDBObject)cursor.next();
            String string001=string00.get("name").toString();
            String string002=string00.get("address").toString();
            arrayList.add(string001+"77"+string002);
        }
        return arrayList;
    }
    @SuppressWarnings("deprecation")
    public Image imageFind(String songName) throws IOException {
        Image image1=null;
        BasicDBObject basicDBObject=new BasicDBObject();
        basicDBObject.put("name",songName);
        cursor=dbCollection.find(basicDBObject);
        while(cursor.hasNext())
        {
            BasicDBObject document=(BasicDBObject)cursor.next();
            if(document.containsKey("image"))
            {
                byte[] bytes=(byte[])(document.get("image"));
                FileOutputStream fileOutputStream=new FileOutputStream(new File("D:/musicPlayer/system/data/musicImage.jpg"));
                fileOutputStream.write(bytes);
                fileOutputStream.close();
                image1=new Image(new File("D:/musicPlayer/system/data/musicImage.jpg").toURI().toString());
            }
        }
        return image1;
    }
}
