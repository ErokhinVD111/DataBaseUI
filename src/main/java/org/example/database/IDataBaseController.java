package org.example.database;

import org.example.user.User;

public interface IDataBaseController {
    void connectToDataBase() throws Exception;
    boolean authorizationUser(User user);
    boolean registrationUser(User user);

}
