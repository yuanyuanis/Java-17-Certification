package com.yuanyuanis.io.file;

import java.io.File;

public class B_FileProperties {

    public static void main(String... args) {

        var file = new File("C:\\PruebasNio\\uno");

        if(file.exists()) {
            System.out.println("Absolute path: %s"+ file.getAbsolutePath());
            System.out.println("IsDirectory: "+ file.isDirectory());
            System.out.println("Parent Path: "+ file.getParent());
            if(file.isFile()){
                System.out.println("Size: " + file.length());
                System.out.println("Size: " + file.lastModified());
            } else {
                for (File subFile: file.listFiles()) {
                    System.out.println("---" + subFile.getName());
                }
            }
        }

    }
}
