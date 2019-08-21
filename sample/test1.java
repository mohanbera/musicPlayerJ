package sample;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class test1 {

    public static void main(String[] args) throws IOException {
        String filename1 = "F:\\nnn\\song.mp3";
        File file = new File(filename1);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        long filesize = file.length();
        long filesizeActual = 0L;
        int splitval = 3;
        int splitsize = (int) (filesize / splitval) + (int) (filesize % splitval);
        byte[] b = new byte[splitsize];
        System.out.println(filename1 + "            " + filesize + " bytes");
        try {
            fis = new FileInputStream(file);
            String name1 = filename1.replaceAll(".mp3", "");
            for (int j = 1; j <= splitval; j++)
            {
                String filecalled = name1 + "_split_" + j + ".mp3";
                fos = new FileOutputStream(filecalled);
                int i = fis.read(b);
                fos.write(b, 0, i);
                fos.close();
                fos = null;
                System.out.println(filecalled + "    " + i + " bytes");
                filesizeActual += i;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}