package com.yuanyuanis.concurrency.uni.u1.ExecutorService;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Prueba de concepto temporizador con Swing y Progress Bar sin SwingWorker
 */
public class CuentaTemporizador {
    // Contador del temporizador
    private int contador = 0;

    // Etiqueta para mostrar el contador del temporizador
    private JLabel etiquetaContador;

    // Timer para aumentar el contador
    private Timer temporizador;

    public CuentaTemporizador() {
        // Crear la ventana
        JFrame ventana = new JFrame("Cuenta Temporizador");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(300, 100);
        ventana.setLayout(new BorderLayout());

        // Crear la etiqueta para mostrar el contador
        etiquetaContador = new JLabel("0");
        ventana.add(etiquetaContador, BorderLayout.CENTER);

        // Crear el botón para iniciar el temporizador
        JButton botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Iniciar el temporizador
                temporizador.start();
            }
        });
        ventana.add(botonIniciar, BorderLayout.SOUTH);

        // Crear el temporizador para aumentar el contador cada segundo
        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aumentar el contador
                contador++;

                // Actualizar la etiqueta con el nuevo valor del contador
                etiquetaContador.setText(String.valueOf(contador));
            }
        });

        // Mostrar la ventana
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        // Crear el cuenta temporizador en el hilo de interfaz de usuario
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CuentaTemporizador();
            }
        });
    }
}

