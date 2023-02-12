package org.example.database;

import org.example.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController implements IDataBaseController {

    private Connection connectionToDataBase;

    private final DataBaseConnector connectorToDataBase;

    public DataBaseController() {
        connectorToDataBase = new DataBaseConnector()
                .setDataBaseUrl("jdbc:postgresql://localhost/erokhinkzi19")
                .setDataBaseUser("arcane")
                .setDataBasePassword("123456");
    }

    @Override
    public void connectToDataBase() throws Exception {
        connectionToDataBase = connectorToDataBase.createConnectionToDataBase();
    }

    @Override
    public void disconnectFromDataBase() {

    }

    @Override
    public DataBaseStatesReturn authorizeUser(User user) throws SQLException {
        Statement statement = connectionToDataBase.createStatement();
        String selectSqlCommand = String.format("SELECT login FROM users WHERE login = '%s'", user.getLogin());
        ResultSet result = statement.executeQuery(selectSqlCommand);
        int countAccount = 0;
        while (result.next()) {
            countAccount++;
        }
        if (countAccount == 1) {
            return DataBaseStatesReturn.SUCCESS;
        }
        return DataBaseStatesReturn.FAILED;
    }

    @Override
    public DataBaseStatesReturn registerUser(User user) throws SQLException {
        Statement statement = connectionToDataBase.createStatement();
        String selectSqlCommand = String.format("SELECT login FROM users WHERE login = '%s'", user.getLogin());
        ResultSet result = statement.executeQuery(selectSqlCommand);
        int countAccount = 0;
        while (result.next()) {
            countAccount++;
        }
        if (countAccount == 0) {
            String insertSqlCommand = String.format("INSERT INTO users (login, password, email) VALUES ('%s', '%s', '%s')", user.getLogin(), user.getPassword(), user.getEmail());
            statement.executeUpdate(insertSqlCommand);
            return DataBaseStatesReturn.SUCCESS;
        }
        return DataBaseStatesReturn.FAILED;
    }


}
