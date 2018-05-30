package by.it.verishko.jd03_02;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionCreator {
    static private Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ConnectionCreator() {
    }

    static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            synchronized (ConnectionCreator.class) {
                if (connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(CN.URL_DB, CN.USER_DB, CN.PASSWORD);
                }
            }
        }
        return connection;
    }
}