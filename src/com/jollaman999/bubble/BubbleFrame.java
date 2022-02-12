package com.jollaman999.bubble;

import javax.swing.*;

public class BubbleFrame extends JFrame {
    private JTextField textField;

    public BubbleFrame() {
        setSize(1000, 600);
        getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("New Button");
        btnNewButton.setBounds(96, 96, 85, 21);
        getContentPane().add(btnNewButton);

        textField = new JTextField();
        textField.setText("11111");
        textField.setBounds(376, 251, 96, 19);
        getContentPane().add(textField);
        textField.setColumns(10);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BubbleFrame();
    }
}
