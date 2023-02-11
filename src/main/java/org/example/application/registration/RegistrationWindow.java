package org.example.application.registration;

import org.example.database.IDataBaseController;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class RegistrationWindow extends JFrame {

    private final IDataBaseController dataBaseController;

    private Container container;

    private JTextField loginTextField;
    private JPasswordField passwordField;
    private JTextField emailTextField;

    private final String FAILED_REGISTRATION = "Ошибка регистрации";
    private final String FAILED_REGISTRATION_DESCRIPTION = "Не удалось создать нового пользователя";
    private final String SUCCESS_REGISTRATION = "Успешная регистрация";
    private final String SUCCESS_REGISTRATION_DESCRIPTION = "Новый пользователь успешно зарегистрирован";

    public RegistrationWindow(IDataBaseController dataBaseController) {

        this.dataBaseController = dataBaseController;
        initializeContainer();
        initializePreferences();
        initializeRegistrationPanel();
        initializeWindow();

    }

    private void initializeContainer() {
        container = getContentPane(); // клиентская область окна
        container.setLayout(new BorderLayout()); // выбираем компоновщик
    }

    private void initializePreferences() {

        setTitle("Окно регистрации");

        setPreferredSize(new Dimension(400, 150));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setResizable(false);

    }

    private void initializeRegistrationPanel() {

        JPanel fieldsPanel = createFieldsPanel();
        JPanel buttonsPanel = createButtonsPanel();

        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new BorderLayout());
        registrationPanel.add(fieldsPanel, BorderLayout.PAGE_START);
        registrationPanel.add(buttonsPanel, BorderLayout.CENTER);

        container.add(registrationPanel);
    }


    private void initializeWindow() {
        pack();
        setVisible(true);
    }

    private JPanel createButtonsPanel() {

        JButton buttonRegistration = new JButton("Регистрация");
        createListenerForRegistration(buttonRegistration);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 1, 5, 5));
        buttonsPanel.add(buttonRegistration);

        return buttonsPanel;
    }

    private void createListenerForRegistration(JButton buttonRegistration) {
        buttonRegistration.addActionListener((ActionEvent event) -> {
            //регистрируем нового пользователя
            String login = loginTextField.getText();
            String password = Arrays.toString(passwordField.getPassword());
            String email = emailTextField.getText();
            User user = new User(login, password, email);
            try {
                dataBaseController.connectToDataBase();
                boolean isRegistered = dataBaseController.registerUser(user);
                if (isRegistered) {
                    JOptionPane.showMessageDialog(this,
                            SUCCESS_REGISTRATION_DESCRIPTION,
                            SUCCESS_REGISTRATION,
                            JOptionPane.YES_NO_OPTION);
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            FAILED_REGISTRATION_DESCRIPTION,
                            FAILED_REGISTRATION,
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
        emailTextField = new JTextField(15);

        JLabel loginLabel = new JLabel("Логин");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);

        fieldPanel.setLayout(new GridLayout(3, 1, 5, 5));
        fieldPanel.add(loginLabel);
        fieldPanel.add(loginTextField);
        fieldPanel.add(passwordLabel);
        fieldPanel.add(passwordField);
        fieldPanel.add(emailLabel);
        fieldPanel.add(emailTextField);

        return fieldPanel;
    }






}
