package org.example.database;

import java.sql.Connection;

public class DataBaseController implements IDataBaseController{

    Connection connection;

    private DataBaseConnector connector;

    public DataBaseController() {
        connector = new DataBaseConnector()
                .setDataBaseUrl("test")
                .setDataBaseUser("vladimir")
                .setDataBasePassword("123456789");
    }

    @Override
    public void connectToDataBase() {
        new Thread(() -> {
            try {
                connection = connector.createConnectionToDataBase();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }



}
