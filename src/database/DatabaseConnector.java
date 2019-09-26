package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection connection;
    public static Connection getConnnection(){
        try {
           connection = DriverManager.getConnection("jdbc:mysql://localhost/Database","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return connection;
    }
}
