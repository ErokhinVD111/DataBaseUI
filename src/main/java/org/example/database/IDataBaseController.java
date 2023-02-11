package org.example.database;

import org.example.user.User;

public interface IDataBaseController {
    void connectToDataBase() throws Exception;
    void disconnectFromDataBase();
    boolean authorizeUser(User user);
    boolean registerUser(User user);

}
