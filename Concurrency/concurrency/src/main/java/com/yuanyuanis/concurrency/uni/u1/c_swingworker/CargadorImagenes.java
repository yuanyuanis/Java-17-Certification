package com.yuanyuanis.concurrency.uni.u1.c_swingworker;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CargadorImagenes extends JFrame {


    private JButton bExaminar;
    private JButton bCargar;
    private JButton bCancelar;
    private JLabel labelEstado;
    private JProgressBar progressBar;
    private JTextField textField;

    private TareaCargaImagen tarea;

    // Listado de ficheros cuyas imágenes deben cargarse
    private ArrayList<File> ficheros;
    
    public CargadorImagenes() {

        initComponents();
    }
    
    private void examinar(ActionEvent evt) {
        /*
         * Se crea y se muestra un diálogo de selección de directorios
         */
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            return;

        /*
         *  Se recoge el directorio seleccionado y se listan todos los ficheros de imágenes
         */

        File directorio = fileChooser.getSelectedFile();
        ficheros = new ArrayList<File>();
        ficheros.addAll(Arrays.asList(directorio.listFiles(new FiltroImagen())));

        if (ficheros.size() == 0)
            JOptionPane.showMessageDialog(null, "El directorio no contiene imágenes", "Examinar", JOptionPane.WARNING_MESSAGE);

        bCargar.setEnabled(true);
    }

    private void cargar(ActionEvent evt) {

        /*
         *  Crea la tarea en segundo plano
         *
         *  Además, se define un listener de cambios sobre la barra de
         *  progreso que será notificada a través de la tarea lanzada
         *
         *  Después, se lanza la tarea
         */
        tarea = new TareaCargaImagen(ficheros, labelEstado);
        tarea.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if (event.getPropertyName().equals("progress")) {
                    progressBar.setValue((Integer) event.getNewValue());
                }
            }
        });
        tarea.execute();

        bCancelar.setEnabled(true);
        bCargar.setEnabled(false);

        /*
         * Con la llamada a tarea.get() se obtienen los resultados devueltos
         * por la tarea, en este caso la lista de imágenes cargadas
         * Hay que tener en cuenta que la llamada a ese método bloquea la ejecución
         * del interfaz si se hace directamente desde el hilo principal de la aplicación
         */
    }

    private void cancelar(ActionEvent evt) {
        bCargar.setEnabled(true);
        bCancelar.setEnabled(false);

        /*
         * Si la tarea se cancela con éxito se vacia la barra de progreso
         * Si la tarea no 'da facilidades' para su cancelación, ésta no se
         * llevara a cabo
         */
        if (tarea.cancel(true))
            progressBar.setValue(0);
    }

    private void textActionPerformed(ActionEvent evt) {
        // TODO add cdfg handling code here:
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        progressBar = new JProgressBar();
        bExaminar = new JButton();
        bCargar = new JButton();
        bCancelar = new JButton();
        textField = new JTextField();
        labelEstado = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        bExaminar.setText("Examinar");
        bExaminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                examinar(evt);
            }
        });

        bCargar.setText("Cargar");
        bCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cargar(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelar(evt);
            }
        });

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                textActionPerformed(evt);
            }
        });

        labelEstado.setText("");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(45, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bExaminar, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                        .addComponent(bCargar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(bCancelar, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(161, 161, 161)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelEstado)
                                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bExaminar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bCargar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bCancelar)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(labelEstado)
                                .addContainerGap())
        );

        pack();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CargadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CargadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CargadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CargadorImagenes.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CargadorImagenes().setVisible(true);
            }
        });
    }
}



