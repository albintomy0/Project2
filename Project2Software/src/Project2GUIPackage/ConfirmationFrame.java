/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Albin Tomy
 */


public class ConfirmationFrame extends JFrame {

    private JLabel toLabel, departureDateLabel, departureTimeLabel, totalLabel;
    private JLabel toValueLabel, dateValueLabel, timeValueLabel, totalCostLabel;
    private JButton confirmButton, cancelButton, backButton;

    private JTable ticketTable;
    private DefaultTableModel tableModel;

    private String selectedCity;
    private String departureDate;
    private int childTickets, adultTickets;

    public ConfirmationFrame(String destination, String departureDate, int childTickets, int adultTickets) {
        // Store values passed from BookTicketFrame
        this.selectedCity = destination;
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
        timeValueLabel = new JLabel(fetchDepartureTime(selectedCity));  // Fetch time from DB based on destination

        // Table setup for displaying child and adult ticket information
        String[] columnNames = {"Type", "Ticket ID", "Number of Tickets", "Ticket Price", "Total Cost"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);

        // Add child and adult ticket data
        addTicketRow("Child", selectedCity, childTickets);
        addTicketRow("Adult", selectedCity, adultTickets);

        // Total cost
        totalLabel = new JLabel("TOTAL:");
        double totalCost = calculateTotalCost(childTickets, adultTickets, fetchPriceValue(selectedCity));
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
            new BookTicketFrame(selectedCity);  // Go back to the booking screen
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
    private void addTicketRow(String type, String destination, int numTickets) {
        String trainId = fetchTrainId(destination);
        double price = fetchPriceValue(destination);
        double totalCost = calculateTotalCost(numTickets, adultTickets, price);
        Object[] rowData = {type, trainId, numTickets, price, totalCost};
        tableModel.addRow(rowData);
    }

    // Format the date from yyyy-mm-dd to dd,mm,yyyy
    private String formatDate(String departureDate) {
        String[] parts = departureDate.split("-");
        return parts[2] + "," + parts[1] + "," + parts[0];  // dd,mm,yyyy format
    }

    // Fetch the departure time from the database based on the destination
    private String fetchDepartureTime(String destination) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT TIME FROM TRAINDATA WHERE CITY = '" + destination + "'");
            if (rs.next()) {
                return rs.getString("TIME");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Fetch the train ID from the database based on the destination
    private String fetchTrainId(String destination) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT TICKET_ID FROM TRAINDATA WHERE CITY = '" + destination + "'");
            if (rs.next()) {
                return rs.getString("TICKET_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    // Fetch the price from the database based on the destination
    private double fetchPriceValue(String destination) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT PRICE FROM TRAINDATA WHERE CITY = '" + destination + "'");
            if (rs.next()) {
                return rs.getDouble("PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Calculate total cost for a single group (children or adults)
    private double calculateTotalCost(int tickets, int adultTickets1, double unitPrice) {
        return tickets * unitPrice;
    }

    public static void main(String[] args) {
        // Example: destination Wellington, departureDate "2024-10-24", 2 children, 2 adults
        new ConfirmationFrame("Wellington", "2024-10-24", 2, 2);
    }
}