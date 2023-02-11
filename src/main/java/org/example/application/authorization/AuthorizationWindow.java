package org.example.application.authorization;

import java.awt.*;
import javax.swing.*;

public class AuthorizationWindow extends JFrame {

    private Container container;

    private JTextField loginTextField;
    private JPasswordField passwordField;
    private JPanel authorizationPanel;

    public AuthorizationWindow() {
        initializeContainer();
        initializePreferences();
        initializeWindow();
    }

    private void initializeContainer() {
        container = getContentPane(); // клиентская область окна
        container.setLayout(new BorderLayout()); // выбираем компоновщик
    }

    private void initializePreferences() {

        setTitle("Окно авторизации");
        // желательные размеры окна
        setPreferredSize(new Dimension(400, 500));

        // завершить приложение при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createAuthorizationPanel();

    }

    private void initializeWindow() {
        pack(); // устанавливаем желательные размеры
        setVisible(true);
    }

    private void createAuthorizationPanel() {
        authorizationPanel = new JPanel(new GridBagLayout());

        loginTextField = new JTextField(15);
        JLabel loginLabel = new JLabel("Логин");
        passwordField = new JPasswordField(15);
        JLabel passwordLabel = new JLabel("Пароль");


        authorizationPanel.add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        authorizationPanel.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));

        authorizationPanel.add(loginTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));
        authorizationPanel.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(2,2,2,2), 0,0));

        container.add(authorizationPanel);
    }



}
