package com.yuanyuanis.io.nio2;

import java.io.File;
import java.nio.file.*;

public class A_TestPathCreation {

    public static void main (String[] args){

        Path rutaExist = Path.of("C:\\PruebasNio","uno");
        System.out.println("isAbsolute: "+rutaExist.isAbsolute());
        System.out.println("rutaExist.toAbsolutePath"+rutaExist.toAbsolutePath());

        Path rutaNotExist = Path.of("C:\\NoExiste","tampoco");
        System.out.println(rutaNotExist.isAbsolute());

        Path rutaExist2 = Paths.get("C:\\PruebasNio\\uno");
        Path rutaNotExist2 = Paths.get(rutaNotExist.toUri());

        System.out.println("rutaExist :"+ Files.exists(rutaExist));
        System.out.println("rutaExist2 :"+ Files.exists(rutaExist2));
        System.out.println("rutaNotExist :"+ Files.exists(rutaNotExist));
        System.out.println("rutaNotExist2 :"+ Files.exists(rutaNotExist2));

        File file = new File("C:\\PruebasNio\\uno");

        Path pathFromFile = file.toPath();

        System.out.println("pathFromFile should exist:"+ Files.exists(pathFromFile));

        var fileFromPath  = pathFromFile.toFile();
        System.out.println("fileFromPath should be true: "+fileFromPath.exists());

        var pathFromFileSystems = FileSystems.getDefault().getPath("Not", "Not2");

        System.out.println("FileSystems.getDefault().getPath(\"Not\", \"Not2\") should print false:" + Files.exists(pathFromFileSystems));
    }
}
