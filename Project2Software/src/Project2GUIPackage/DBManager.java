package Project2GUIPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBManager {
    // Embedded mode URL, creates the TrainDB if it doesn't exist
    private static final String URL = "jdbc:derby:testy1;create=true";

    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    // Establish a connection to the embedded database (no username/password)
    public void establishConnection() {
        if (this.conn == null) {
            try {
                // Only provide the URL in embedded mode
                conn = DriverManager.getConnection(URL);
                System.out.println("Connected to the database: " + URL);
            } catch (SQLException ex) {
                System.out.println("Error connecting to the database: " + ex.getMessage());
            }
        }
    }

    // Close the database connection
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException ex) {
                System.out.println("Error closing the connection: " + ex.getMessage());
            }
        }
    }
}