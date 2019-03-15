package org.java.learn.util;

import java.io.*;

public class FileUtils {

    public static void openFile(String filePath, int lineLimit) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));

        String line = "";
        while(lineLimit >0 || (line = br.readLine()) !=null){
            lineLimit--;
            System.out.println( line);
        }
    }

    public static void main(String[] args){
        try {
            openFile("D:\\j2ee\\workspace\\jhqc\\DB\\database.sql", 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer.toBinaryString(10);
    }
}
