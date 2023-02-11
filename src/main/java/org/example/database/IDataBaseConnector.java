package org.example.database;

import java.sql.Connection;

public interface IDataBaseConnector {
    IDataBaseConnector setDataBaseUrl(String url);
    IDataBaseConnector setDataBaseUser(String user);
    IDataBaseConnector setDataBasePassword(String password);
    Connection createConnectionToDataBase() throws Exception;
}
