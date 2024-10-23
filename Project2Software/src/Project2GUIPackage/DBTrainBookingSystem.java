package Project2GUIPackage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTrainBookingSystem {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public DBTrainBookingSystem() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    // Method to connect to the Train Booking Database
    public void connectTrainBookingDB() {
        try {
            this.statement = conn.createStatement();
            this.checkExistedTable("TrainData");

            // Create the table and insert data in batch
            this.statement.addBatch("CREATE TABLE TrainData ("
                    + "TICKET_ID VARCHAR(10), "
                    + "CITY VARCHAR(50), "
                    + "SEAT_NUMBER INT, "
                    + "PRICE FLOAT, "
                    + "TIME VARCHAR(10))");

            this.statement.addBatch("INSERT INTO TrainData VALUES "
                    + "('T003', 'Wellington', 100, 45.00, '07:45'), "
                    + "('T002', 'Hamilton', 100, 25.00, '09:30'), "
                    + "('T004', 'Tauranga', 80, 40.00, '10:15'), "
                    + "('T006', 'Hamilton', 80, 30.00, '11:00'), "
                    + "('T005', 'Rotorua', 80, 50.00, '12:00'), "
                    + "('T007', 'Wellington', 100, 50.00, '13:00'), "
                    + "('T008', 'Tauranga', 80, 42.00, '14:00'), "
                    + "('T009', 'Rotorua', 100, 55.00, '15:30'), "
                    + "('T010', 'Hamilton', 80, 28.00, '16:00'), "
                    + "('T011', 'Wellington', 70, 48.00, '17:45'), "
                    + "('T012', 'Tauranga', 70, 43.00, '19:15'), "
                    + "('T013', 'Rotorua', 80, 60.00, '20:00')");

            this.statement.executeBatch();
            System.out.println("Table 'TrainData' created and data inserted successfully.");

        } catch (SQLException ex) {
            System.out.println("Error connecting to TrainBookingDB or executing batch: " + ex.getMessage());
        }
    }

    // Method to check if the table exists and drop it if necessary
    public void checkExistedTable(String tableName) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(), null);

            while (rs.next()) {
                String existingTable = rs.getString("TABLE_NAME");
                if (existingTable.equalsIgnoreCase(tableName)) {
                    this.statement.executeUpdate("DROP TABLE " + tableName);
                    System.out.println("Table '" + tableName + "' has been deleted.");
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error checking/dropping table: " + ex.getMessage());
        }
    }

    // Close the database connection
    public void closeConnection() {
        dbManager.closeConnections();
    }
}