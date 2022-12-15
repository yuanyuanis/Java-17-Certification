package com.yuanyuanis.concurrency.uni.u1.c_swingworker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TareaCargaImagen extends SwingWorker<ArrayList<BufferedImage>, Integer> {

    private ArrayList<File> ficheros;
    private JLabel lbEstado;

    /**
     * Constructor
     *
     * @param ficheros La lista de ficheros (imágenes) que se tiene que cargar
     * @param lbEstado La etiqueta de texto donde notificar el proceso de carga
     */
    public TareaCargaImagen(ArrayList<File> ficheros, JLabel lbEstado) {
        this.ficheros = ficheros;
        this.lbEstado = lbEstado;
    }

    /**
     * Método abstracto heredado de la clase SwingWorker donde se realiza la
     * tarea en segundo plano.
     * En este caso se cargan las imágenes una a una a la vez que se va notificando
     * el proceso de carga para luego mostrarlo en una barra de progreso y en una
     * etiqueta de texto
     */
    @Override
    protected ArrayList<BufferedImage> doInBackground() throws Exception {

        ArrayList<BufferedImage> listaImagenes = new ArrayList<BufferedImage>();
        BufferedImage imagen = null;
        int cantidad = ficheros.size();
        int i = 1;

        for (File fichero : ficheros) {
            try {
                // Carga la imagen de disco a memoria
                imagen = ImageIO.read(fichero);
                listaImagenes.add(imagen);
                // Notifica el estado de la carga (en este caso el número de imagen procesada)
                publish(i++);
                /*
                 *  Notifica el progreso (de 0 a 100). Aprovechamos para pintarlo en una barra de progreso
                 *  desde la GUI
                 */
                setProgress(100 * i / cantidad);

                /*
                 * Si la tarea ha sido cancelada se termina la carga de imágenes
                 * Si se desea que la tarea pueda ser cancelada en cualquier momento
                 * hay que 'dar facilidades' como comprobar periodicamente si se debe
                 * terminar su ejecución
                 */
                if (isCancelled())
                    break;

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        // Al final del proceso se devuelven las imágenes cargadas
        return listaImagenes;
    }

    /**
     * Método heredado de la clase SwingWorker donde se indican las tareas a realizar
     * para actualizar el estado intermedio de la tarea en segundo plano. Los valores
     * que desde el método doInBackground() notifiquemos invocando el método publish()
     * se le pasan a este método en forma de lista puesto que no está asegurado que por
     * cada llamada a publish() haya una llamada a process(). Se pasarán siempre los
     * valores publicados pendientes de procesar
     *
     * @see javax.swing.SwingWorker#process(java.util.List)
     */
    @Override
    protected void process(List<Integer> valores) {

        lbEstado.setText(valores.get(valores.size() - 1) + " de " + ficheros.size());
    }
}