package com.yuanyuanis.concurrency.uni.u1.x_feedback1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaExample {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Java Example");

        JLabel jLabel = new JLabel();
        jLabel.setBounds(50, 150, 350, 40);

        final JTextField jTextField = new JTextField();
        jTextField.setBounds(50, 50, 150, 20);

        JButton jButton = new JButton("Submit");
        jButton.setBounds(50, 100, 100, 30);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTextField.getText().equals(""))
                    jLabel.setText(jTextField.getText());
                else
                    jLabel.setText("Please write something in the edit box");
            }
        });
        jFrame.add(jLabel);
        jFrame.add(jButton);
        jFrame.add(jTextField);
        jFrame.setSize(400, 400);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }
}