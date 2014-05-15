package br.com.ufpb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    private String url;
    private String userName;
    private String password;
    private static ConnectionHelper instance;

    private ConnectionHelper() {
        String driver = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            url = "jdbc:mysql://localhost/directory?user=root";
            userName = "root";
            password = "ch4v31r0";
            url = "jdbc:mysql://localhost:3306/arrow";        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = new ConnectionHelper();
        }
        try {
            return DriverManager.getConnection(instance.url,instance.userName, instance.password);
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
