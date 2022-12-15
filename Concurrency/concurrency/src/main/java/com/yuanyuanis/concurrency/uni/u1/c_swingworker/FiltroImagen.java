package com.yuanyuanis.concurrency.uni.u1.c_swingworker;


import java.io.File;
import java.io.FileFilter;

public class FiltroImagen implements FileFilter {

    @Override
    public boolean accept(File fichero) {

        if (fichero.getName().endsWith(".jpg"))
            return true;

        if (fichero.getName().endsWith(".png"))
            return true;

        if (fichero.getName().endsWith(".gif"))
            return true;

        return false;
    }

}
