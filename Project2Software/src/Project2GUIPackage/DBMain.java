package Project2GUIPackage;

public class DBMain {

    public static void main(String[] args) {
        DBTrainBookingSystem trainBookingSystem = new DBTrainBookingSystem();

        // Connect to the TrainBookingDB, create the table, and insert data
        trainBookingSystem.connectTrainBookingDB();

        // Close the connection
        trainBookingSystem.closeConnection();
    }
}
