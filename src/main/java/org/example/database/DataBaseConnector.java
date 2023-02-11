package org.example.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnector implements IDataBaseConnector{

    private String dataBaseUrl;
    private String dataBaseUser;
    private String dataBasePassword;
    private Connection connection;

    @Override
    public DataBaseConnector setDataBaseUrl(String url) {
        this.dataBaseUrl = url;
        return this;
    }

    @Override
    public DataBaseConnector setDataBaseUser(String user) {
        this.dataBaseUser = user;
        return this;
    }

    @Override
    public DataBaseConnector setDataBasePassword(String password) {
        this.dataBasePassword = password;
        return this;
    }

    @Override
    public Connection createConnectionToDataBase() throws Exception {

        try {
            connection = DriverManager
                    .getConnection(dataBaseUrl, dataBaseUser, dataBasePassword);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Connection failed");
        }
        return connection;
    }
}
