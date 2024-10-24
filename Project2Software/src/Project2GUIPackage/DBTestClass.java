/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2GUIPackage;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Albin Tomy
 */
public class DBTestClass {

    private final DBManager dbManager;
    private final Connection conn;
    private Statement statement;

    public DBTestClass() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    // Method to fetch and print all data from the TrainData table
    public void fetchAndPrintTrainData() {
        try {
            this.statement = conn.createStatement();
            String query = "SELECT * FROM TrainData";  // SQL query to fetch all records

            ResultSet rs = statement.executeQuery(query);

            // Loop through the result set and print each row
            System.out.println("TICKET_ID\tCITY\t\tSEAT_NUMBER\tPRICE\tTIME");
            while (rs.next()) {
                String ticketId = rs.getString("TICKET_ID");
                String city = rs.getString("CITY");
                int seatNumber = rs.getInt("SEAT_NUMBER");
                float price = rs.getFloat("PRICE");
                String time = rs.getString("TIME");

                System.out.printf("%s\t\t%s\t\t%d\t\t%.2f\t%s\n", ticketId, city, seatNumber, price, time);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error fetching train data: " + ex.getMessage());
        } finally {
            // Ensure the connection is always closed after fetching
            closeConnection();
        }
    }

    // Method to close the connection
    public void closeConnection() {
        dbManager.closeConnections();
    }

    public static void main(String[] args) {
        // Create an instance of DBTestClass and fetch the data
        DBTestClass fetcher = new DBTestClass();
        fetcher.fetchAndPrintTrainData();
    }
}