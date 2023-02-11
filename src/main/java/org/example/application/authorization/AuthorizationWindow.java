package org.example.application.authorization;

import org.example.application.registration.RegistrationWindow;
import org.example.database.DataBaseController;
import org.example.database.IDataBaseController;
import org.example.user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.*;

public class AuthorizationWindow extends JFrame {

    private Container container;

    private JTextField loginTextField;
    private JPasswordField passwordField;
    private RegistrationWindow registrationWindow = null;

    private final String FAILED_SIGN_IN = "Ошибка входа";
    private final String FAILED_SIGH_IN_DESCRIPTION = "Неверный логин или пароль";


    private IDataBaseController dataBaseController;

    public AuthorizationWindow() {
        dataBaseController = new DataBaseController();
        initializeContainer();
        initializePreferences();
        initializeAuthorizationPanel();
        initializeWindow();
    }

    private void initializeContainer() {
        container = getContentPane(); // клиентская область окна
        container.setLayout(new BorderLayout()); // выбираем компоновщик
    }

    private void initializePreferences() {

        setTitle("Окно авторизации");

        setPreferredSize(new Dimension(400, 150));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);


    }

    private void initializeWindow() {
        pack();
        setVisible(true);
    }

    private void initializeAuthorizationPanel() {

        JPanel fieldsPanel = createFieldsPanel();
        JPanel buttonsPanel = createButtonsPanel();

        JPanel authorizationPanel = new JPanel();
        authorizationPanel.setLayout(new BorderLayout());
        authorizationPanel.add(fieldsPanel, BorderLayout.PAGE_START);
        authorizationPanel.add(buttonsPanel, BorderLayout.CENTER);

        container.add(authorizationPanel);
    }

    private JPanel createButtonsPanel() {

        JButton buttonSignIn = new JButton("Войти");
        createListenerForSignIn(buttonSignIn);

        JButton buttonRegistration = new JButton("Зарегистрироваться");
        createListenerForRegistration(buttonRegistration);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 5, 5));
        buttonsPanel.add(buttonRegistration);
        buttonsPanel.add(buttonSignIn);

        return buttonsPanel;
    }

    private void createListenerForRegistration(JButton buttonRegistration) {
        buttonRegistration.addActionListener((ActionEvent event) -> {
            //запуск нового окна с регистрацией
            if (registrationWindow == null) {
                registrationWindow = new RegistrationWindow(dataBaseController);
            }
            else {
                if (!registrationWindow.isDisplayable()) {
                    registrationWindow = new RegistrationWindow(dataBaseController);
                }
            }

        });
    }

    private void createListenerForSignIn(JButton buttonSignIn) {
        buttonSignIn.addActionListener((ActionEvent event) -> {
            String login = this.loginTextField.getText();
            String password = Arrays.toString(this.passwordField.getPassword());
            User user = new User(login, password);
            try {
                this.dataBaseController.connectToDataBase();
                boolean isAuthorized = this.dataBaseController.authorizeUser(user);
                if (isAuthorized) {
                    //запуск основного окна
                } else {
                    //вывод сообщения, что пользователь не авторизован
                    JOptionPane.showMessageDialog(this,
                            FAILED_SIGH_IN_DESCRIPTION,
                            FAILED_SIGN_IN,
                            JOptionPane.ERROR_MESSAGE);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        });
    }


    private JPanel createFieldsPanel() {

        JPanel fieldPanel = new JPanel(new GridBagLayout());

        loginTextField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JLabel loginLabel = new JLabel("Логин");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        fieldPanel.setLayout(new GridLayout(2, 1, 5, 5));
        fieldPanel.add(loginLabel);
        fieldPanel.add(loginTextField);
        fieldPanel.add(passwordLabel);
        fieldPanel.add(passwordField);

        return fieldPanel;
    }


}
