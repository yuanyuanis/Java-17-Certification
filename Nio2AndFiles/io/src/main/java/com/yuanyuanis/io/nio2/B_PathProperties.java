package com.yuanyuanis.io.nio2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class B_PathProperties {

    public static void main(String ...args) throws IOException {
        var path = Paths.get("C:\\PruebasNio\\uno");
        var path2 = Paths.get("C:\\PruebasNio\\uno\\Test1.txt");

        propsNioFile(path);
        propsNioFile(path2);
    }

    private static void propsNioFile(Path path) throws IOException {

        if(Files.exists(path)){
            System.out.println("************ Attributes from Path *****************");
            System.out.println();
            System.out.println("File Name: "+ path.getFileName());
            System.out.println("Parent: " + path.getParent());
            System.out.println("isAbsolute: " + path.isAbsolute());
            System.out.println("absolutePath: " + path.toAbsolutePath());

            System.out.println("************ Attributes from Files *****************");
            System.out.println();
            if(Files.isRegularFile(path)) {
                System.out.printf("Files.getLastModifiedTime() throws IOException: %s%n", Files.getLastModifiedTime(path));
                System.out.println("Files size " + Files.size(path));
            }else{
                try(Stream<Path> stream = Files.list(path)){
                    stream.forEach(p -> System.out.println(" " + p.getName(0)));
                }
            }
        }
    }
}
