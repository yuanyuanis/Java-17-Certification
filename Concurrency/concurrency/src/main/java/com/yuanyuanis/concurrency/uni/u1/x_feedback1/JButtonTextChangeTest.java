package com.yuanyuanis.concurrency.uni.u1.x_feedback1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JButtonTextChangeTest extends JFrame {

    private JTextField textField;
    private JButton button;
    public JButtonTextChangeTest() {
        setTitle("JButtonTextChange Test");
        setLayout(new FlowLayout());
        JTextField givenName = new JTextField(10);
        JPanel inFieldPane = new JPanel();
        inFieldPane.setLayout(new GridLayout(2,2));
        inFieldPane.add(new JLabel("Given Name"));
        inFieldPane.add(givenName);
        textField = new JTextField(20);
        button = new JButton("Initial Button");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!textField.getText().equals(""))
                    button.setText(textField.getText());
            }
        });
        add(textField);
        add(button);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JButtonTextChangeTest();
    }
}
