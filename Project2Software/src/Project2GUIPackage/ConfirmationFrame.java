package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConfirmationFrame extends JFrame {

    private JLabel toLabel, departureDateLabel, departureTimeLabel, totalLabel;
    private JLabel toValueLabel, dateValueLabel, timeValueLabel, totalCostLabel;
    private JButton confirmButton, cancelButton, backButton;

    private JTable ticketTable;
    private DefaultTableModel tableModel;

    private String selectedCity;
    private String selectedTicketID;  // Store the TICKET_ID
    private String departureDate;
    private int childTickets, adultTickets;

    public ConfirmationFrame(String destination, String ticketID, String departureDate, int childTickets, int adultTickets) {
        // Store values passed from BookTicketFrame
        this.selectedCity = destination;
        this.selectedTicketID = ticketID;  // Store the TICKET_ID
        this.departureDate = departureDate;
        this.childTickets = childTickets;
        this.adultTickets = adultTickets;

        // Initialize components and set up frame
        initComponents();
        initPanels();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Confirm Ticket");
        setSize(700, 400);  // Adjusted size to prevent extra blank space
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame
        getContentPane().setBackground(new Color(192, 210, 238));  // Set background color

        // Labels for values
        toLabel = new JLabel("Auckland to:");
        departureDateLabel = new JLabel("Departure Date:");
        departureTimeLabel = new JLabel("Departure Time:");

        // Display the passed values with correct date format (dd,mm,yyyy)
        toValueLabel = new JLabel(selectedCity);
        dateValueLabel = new JLabel(formatDate(departureDate));  // Format date as dd,mm,yyyy
        timeValueLabel = new JLabel(fetchDepartureTime(selectedTicketID));  // Fetch time from DB based on TICKET_ID

        // Table setup for displaying adult and child ticket information
        String[] columnNames = {"Type", "Train ID", "Number of Tickets", "Ticket Price", "Total Cost"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);

        // Add adult and child ticket data
        addTicketRow("Adult", selectedTicketID, adultTickets, false);  // Adults come first
        addTicketRow("Child", selectedTicketID, childTickets, true);   // Children come second, with free tickets

        // Total cost
        totalLabel = new JLabel("TOTAL: $ ");
        double totalCost = calculateTotalCost(adultTickets, childTickets, fetchPriceValue(selectedTicketID));
        totalCostLabel = new JLabel(String.valueOf(totalCost));

        // Buttons for confirmation, back, and cancellation
        confirmButton = new JButton("CONFIRM BOOKING");
        confirmButton.setBackground(Color.GREEN);
        backButton = new JButton("BACK");
        backButton.setBackground(Color.YELLOW);
        cancelButton = new JButton("CANCEL BOOKING");
        cancelButton.setBackground(Color.RED);
    }

    private void initPanels() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Row 1 - Destination
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(toLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(toValueLabel, gbc);

        // Row 2 - Departure Date
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(departureDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(dateValueLabel, gbc);

        // Row 3 - Departure Time
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(departureTimeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(timeValueLabel, gbc);

        // Row 4 - Ticket Table with Scroll Pane
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(ticketTable);
        add(scrollPane, gbc);

        // Row 5 - Total
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(totalLabel, gbc);

        gbc.gridx = 1;
        add(totalCostLabel, gbc);

        // Row 6 - Buttons
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);

        // Set action listeners for buttons
        setActionListeners();
    }

    private void setActionListeners() {
        // Action for "Back" button to return to the BookTicketFrame
        backButton.addActionListener(e -> {
            new BookTicketFrame(selectedCity, selectedTicketID);  // Go back to the booking screen
            setVisible(false);  // Hide current frame
        });

        // Action for "Cancel" button to return to the home screen (GUIProject2)
        cancelButton.addActionListener(e -> {
            new GUIProject2();  // Go back to the home screen
            setVisible(false);  // Hide current frame
        });

        // Add functionality to confirm button as needed, e.g., save data or confirmation
        confirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Booking confirmed!");
        });
    }

    // Add a row to the table for child or adult ticket information
    private void addTicketRow(String type, String ticketID, int numTickets, boolean isChild) {
        double price = isChild ? 0.0 : fetchPriceValue(ticketID);  // Set price to 0 for children
        double totalCost = calculateTotalCost(numTickets, childTickets, price);
        Object[] rowData = {type, ticketID, numTickets, price, totalCost};
        tableModel.addRow(rowData);
    }

    // Format the date from yyyy-mm-dd to dd,mm,yyyy
    private String formatDate(String departureDate) {
        String[] parts = departureDate.split("-");
        return parts[2] + "," + parts[1] + "," + parts[0];  // dd,mm,yyyy format
    }

    // Fetch the departure time from the database based on the TICKET_ID
    private String fetchDepartureTime(String ticketID) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT TIME FROM TRAINDATA WHERE TICKET_ID = '" + ticketID + "'");
            if (rs.next()) {
                return rs.getString("TIME");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Fetch the price from the database based on the TICKET_ID
    private double fetchPriceValue(String ticketID) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT PRICE FROM TRAINDATA WHERE TICKET_ID = '" + ticketID + "'");
            if (rs.next()) {
                return rs.getDouble("PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Calculate total cost for a single group (children or adults)
    private double calculateTotalCost(int tickets, int childTickets1, double unitPrice) {
        return tickets * unitPrice;
    }

    public static void main(String[] args) {
        // Example: destination Wellington, ticketID T003, departureDate "2024-12-01", 3 children, 2 adults
        new ConfirmationFrame("Wellington", "T003", "2024-12-01", 3, 2);
    }
}
