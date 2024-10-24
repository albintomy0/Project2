package Project2GUIPackage;

public class TicketBookingModel {

    // Method to return all months (January to December)
    public String[] getAllMonths() {
        String[] months = new String[12];
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = String.format("%02d", i);  // Format as two-digit month (e.g., "01" for January)
        }
        return months;
    }

    // Method to handle future logic for booking tickets (currently a placeholder)
    public boolean validateTicketSelection(int adultTickets, int childTickets) {
        // Validate that at least one ticket is selected (either adult or child)
        return adultTickets > 0 || childTickets > 0;
    }
}

