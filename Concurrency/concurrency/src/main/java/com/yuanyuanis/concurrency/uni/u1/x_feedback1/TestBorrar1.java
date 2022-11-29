package com.yuanyuanis.concurrency.uni.u1.x_feedback1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestBorrar1 implements ActionListener {
    JFrame frame = new JFrame();
    JButton button = new JButton("Click Me");//Creating object of JButton class

    TestBorrar1() {
        prepareGUI();
        buttonProperties();//Calling buttonProperties() method
    }

    public void prepareGUI() {
        frame.setTitle("My Window");
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setBounds(200, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void buttonProperties() {
        button.setBounds(130, 200, 100, 40);//Setting location and size of button
        frame.add(button);//adding button to the frame
    }


    public static void main(String... args) {
        new TestBorrar1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
