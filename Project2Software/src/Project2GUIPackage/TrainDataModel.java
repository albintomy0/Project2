package Project2GUIPackage;

import java.sql.*;
import java.util.ArrayList;

public class TrainDataModel {

    // Method to retrieve sorted train data from the database
    public Object[][] getSortedTrainData(String sortBy) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Object[]> trainDataList = new ArrayList<>();

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");
            stmt = conn.createStatement();

            // SQL query to fetch sorted data
            String sql = "SELECT * FROM TRAINDATA ORDER BY " + sortBy;
            rs = stmt.executeQuery(sql);

            // Loop through result set and store train data
            while (rs.next()) {
                Object[] row = {
                    rs.getString("TICKET_ID"),
                    rs.getString("CITY"),
                    rs.getInt("SEAT_NUMBER"),
                    rs.getDouble("PRICE"),
                    rs.getString("TIME")
                };
                trainDataList.add(row);
            }

            return trainDataList.toArray(new Object[0][]);  // Convert ArrayList to 2D array

        } finally {
            // Clean up database resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}

