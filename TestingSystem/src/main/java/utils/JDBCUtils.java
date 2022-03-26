package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static JDBCUtils instance;
    private static Connection connection;

    private JDBCUtils() {
        try {
            getConnection();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static JDBCUtils getInstance() {
        if(instance == null) {
            instance = new JDBCUtils();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, IOException {
        if(connection == null) {
            Properties database = new Properties();
            database.load(new FileInputStream("src/main/resources/database.properties"));

            String url = database.getProperty("url");
            String user = database.getProperty("user");
            String pass = database.getProperty("pass");

            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Successful !");
        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if(connection != null && connection.isClosed()) {
            connection.close();
        }
    }
}
