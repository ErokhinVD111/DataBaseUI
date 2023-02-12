package org.example.database;

import org.example.user.User;

import java.sql.SQLException;

public interface IDataBaseController {
    void connectToDataBase() throws Exception;
    void disconnectFromDataBase();
    DataBaseStatesReturn authorizeUser(User user) throws SQLException;
    DataBaseStatesReturn registerUser(User user) throws SQLException;

}
