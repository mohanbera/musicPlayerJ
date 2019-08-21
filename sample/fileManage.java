package sample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;

public class fileManage {
    public File[] createFile() throws IOException {
        File[] rFiles=new File[3];
        new File("D:/musicPlayer/system/data/").mkdirs();
        File file1 = new File("D:/musicPlayer/system/data/playerData.dat");
        File file2 = new File("D:/musicPlayer/system/data/playerFavData.dat");
        File file3 = new File("D:/musicPlayer/system/data/currentMedia.dat");
        try {
            file1.createNewFile(); // if the file exists do nothing
            file2.createNewFile(); // if the file exists do nothing
            file3.createNewFile(); // if the file exists do nothing
        } catch (IOException e) {
            System.out.println("Problem with creating the file");
        }
        long len1=file1.length();
        if(len1==0)
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/musicPlayer/system/data/playerData.dat"));
            System.out.println("YES0");
            File[] files=new File[1000];
            try {
                objectOutputStream.writeObject(files);
            } catch (IOException e) {
                e.printStackTrace();
            }
            objectOutputStream.close();
            file1 = new File("D:/musicPlayer/system/data/playerData.dat");

            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("D:/musicPlayer/system/data/playerFavData.dat"));
            int[] arr1=new int[1000];
            Arrays.fill(arr1,0);
            try {
                objectOutputStream2.writeObject(arr1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            objectOutputStream2.close();
            file2 = new File("D:/musicPlayer/system/data/playerFavData.dat");

            ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(new FileOutputStream("D:/musicPlayer/system/data/currentMedia.dat"));
            HashMap<String,Integer> hashMap=new HashMap<>();
            hashMap.put("total",0);
            hashMap.put("id",0);
            hashMap.put("time",0);
            try {
                objectOutputStream3.writeObject(hashMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            objectOutputStream3.close();
            file3 = new File("D:/musicPlayer/system/data/currentMedia.dat");
        }
        rFiles[0]=file1;
        rFiles[1]=file2;
        rFiles[2]=file3;
        System.out.println("successful");
        return rFiles;
    }
}
