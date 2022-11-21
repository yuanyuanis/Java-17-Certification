package com.yuanyuanis.io.file;

import java.io.File;
import java.io.IOException;

public class A_TestFileCreation {

    public static void main(String ...args) throws IOException {
        File zooFile = new File("C:\\SpringCertProSource\\Java-17-Certification\\Nio2AndFiles\\io\\src\\main\\resources\\test.txt");
        File zooFile2 = new File("C:\\SpringCertProSource\\Java-17-Certification\\Nio2AndFiles\\io\\src\\main\\resources", "\\test2.txt");
        File padre = new File("C:\\SpringCertProSource\\Java-17-Certification\\Nio2AndFiles\\io\\src\\main\\resources");
        File hijo = new File(padre,"\\test3.txt");

        System.out.printf("zooFile exist %s hijo exist: %s%n", zooFile.exists(), hijo.exists());

        try {

            zooFile.createNewFile();
            zooFile2.createNewFile();
            System.out.println(zooFile.getAbsolutePath());
            System.out.printf("zooFile exist %s hijo exist: %s%n", zooFile.exists(), hijo.exists());

            Thread.sleep(2500);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            zooFile.delete();
            zooFile2.delete();
        }
    }
}
