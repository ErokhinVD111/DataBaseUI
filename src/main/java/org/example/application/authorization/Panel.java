package org.example.application.authorization;

import javax.swing.*;
import java.awt.*;

public abstract class Panel extends JFrame{

    protected String label;
    protected int fieldSize;


    public Panel(String label, int fieldSize) {
        this.label = label;
        this.fieldSize = fieldSize;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    protected void initializePanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JTextField textField = new JTextField(fieldSize);
        JLabel labelLogin = new JLabel(label);
        panel.add(labelLogin);
        panel.add(textField);
        add(panel);
//        JLabel labelPassword = new JLabel("Пароль");
    }


}
