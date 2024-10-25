package Project2GUIPackage;

public class ReceiptModel {

    private String destination;
    private String departureDate;
    private String departureTime;
    private String trainID;
    private int adultTickets;
    private int childTickets;
    private double totalCost;

    // Constructor to initialize the data
    public ReceiptModel(String destination, String departureDate, String departureTime, String trainID, int adultTickets, int childTickets, double totalCost) {
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.trainID = trainID;
        this.adultTickets = adultTickets;
        this.childTickets = childTickets;
        this.totalCost = totalCost;
    }

    // Getter methods for each field
    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getTrainID() {
        return trainID;
    }

    public int getAdultTickets() {
        return adultTickets;
    }

    public int getChildTickets() {
        return childTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }

}

