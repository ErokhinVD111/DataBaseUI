package org.example.database;

import org.example.user.User;

import java.sql.Connection;

public class DataBaseController implements IDataBaseController{

    private Connection connectionToDataBase;

    private final DataBaseConnector connectorToDataBase;

    public DataBaseController() {
        connectorToDataBase = new DataBaseConnector()
                .setDataBaseUrl("test")
                .setDataBaseUser("vladimir")
                .setDataBasePassword("123456789");
    }

    @Override
    public void connectToDataBase() {
        new Thread(() -> {
            try {
                connectionToDataBase = connectorToDataBase.createConnectionToDataBase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void disconnectFromDataBase() {

    }

    @Override
    public boolean authorizeUser(User user) {
        return false;
    }

    @Override
    public boolean registerUser(User user) {
        return false;
    }


}
