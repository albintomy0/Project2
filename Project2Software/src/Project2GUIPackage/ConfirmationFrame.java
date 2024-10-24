package Project2GUIPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ConfirmationFrame extends JFrame {

    private JLabel toLabel, departureDateLabel, departureTimeLabel, totalLabel;
    private JLabel toValueLabel, dateValueLabel, timeValueLabel, totalCostLabel;
    private JButton confirmButton, cancelButton, backButton;

    private JTable ticketTable;
    private DefaultTableModel tableModel;

    private String selectedCity;
    private String selectedTicketID;
    private String departureDate;
    private int childTickets, adultTickets;

    public ConfirmationFrame(String destination, String ticketID, String departureDate, int childTickets, int adultTickets) {
        // Store values passed from BookTicketFrame
        this.selectedCity = destination;
        this.selectedTicketID = ticketID;
        this.departureDate = departureDate;
        this.childTickets = childTickets;
        this.adultTickets = adultTickets;

        // Initialize components and set up frame
        initComponents();
        initPanels();
        setVisible(true);
    }

    // Initialize all components and setup GUI properties
    private void initComponents() {
        setTitle("Confirm Ticket");
        setSize(700, 450);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  
        getContentPane().setBackground(new Color(192, 210, 238)); // Light blue background

        // Labels for values
        toLabel = new JLabel("Auckland to:");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        departureDateLabel = new JLabel("Departure Date:");
        departureDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        departureTimeLabel = new JLabel("Departure Time:");
        departureTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Display the passed values
        toValueLabel = new JLabel(selectedCity);
        dateValueLabel = new JLabel(formatDate(departureDate));
        timeValueLabel = new JLabel(fetchDepartureTime(selectedTicketID));

        // Table setup for displaying adult and child ticket information
        String[] columnNames = {"Type", "Train ID", "Number of Tickets", "Ticket Price", "Total Cost"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ticketTable = new JTable(tableModel);
        ticketTable.setRowHeight(25);
        ticketTable.setFillsViewportHeight(true);
        
        // Alternating row colors for better readability
        ticketTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    comp.setBackground(new Color(230, 240, 255));  // Light blue for even rows
                } else {
                    comp.setBackground(Color.WHITE);  // White for odd rows
                }
                return comp;
            }
        });

        // Add ticket data to the table
        addTicketRow("Adult", selectedTicketID, adultTickets, false);
        addTicketRow("Child", selectedTicketID, childTickets, true);

        // Total cost label
        totalLabel = new JLabel("TOTAL: $ ");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        double totalCost = calculateTotalCost(adultTickets, childTickets, fetchPriceValue(selectedTicketID));
        totalCostLabel = new JLabel(String.valueOf(totalCost));
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalCostLabel.setForeground(Color.RED);

        // Buttons
        confirmButton = new JButton("CONFIRM BOOKING");
        confirmButton.setBackground(new Color(0, 128, 0)); // Dark green
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setToolTipText("Click to confirm your booking.");

        cancelButton = new JButton("CANCEL BOOKING");
        cancelButton.setBackground(new Color(255, 0, 0)); // Red
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setToolTipText("Click to cancel the booking.");

        backButton = new JButton("BACK");
        backButton.setBackground(new Color(192, 210, 238)); // Same as background
        backButton.setToolTipText("Go back to the previous screen.");
    }

    // Setup the layout and organize components into panels
    private void initPanels() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Row 0 - Back Button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(backButton, gbc);

        // Row 1 - Destination
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(toLabel, gbc);

        gbc.gridx = 2;
        add(toValueLabel, gbc);

        // Row 2 - Departure Date
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(departureDateLabel, gbc);

        gbc.gridx = 2;
        add(dateValueLabel, gbc);

        // Row 3 - Departure Time
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(departureTimeLabel, gbc);

        gbc.gridx = 2;
        add(timeValueLabel, gbc);

        // Row 4 - Ticket Table with Scroll Pane
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(ticketTable);
        add(scrollPane, gbc);

        // Row 5 - Total
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(totalLabel, gbc);

        gbc.gridx = 2;
        add(totalCostLabel, gbc);

        // Row 6 - Buttons
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(192, 210, 238));
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);

        // Set action listeners for buttons
        setActionListeners();
    }

    // Set action listeners for the buttons
    private void setActionListeners() {
        // Back button
        backButton.addActionListener(e -> {
            new BookTicketFrame(selectedCity, selectedTicketID);
            setVisible(false);
        });

        // Cancel button
        cancelButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel the booking?", "Cancel Booking", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                new GUIProject2();  
                setVisible(false);
            }
        });

        // Confirm button
        confirmButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Do you want to confirm the booking?", "Confirm Booking", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Booking confirmed!");
            }
        });
    }

    // Add rows to the ticket table based on the type of ticket (Adult/Child)
    private void addTicketRow(String type, String ticketID, int numTickets, boolean isChild) {
        double price = isChild ? 0.0 : fetchPriceValue(ticketID);
        double totalCost = calculateTotalCost(numTickets, childTickets, price);
        Object[] rowData = {type, ticketID, numTickets, price, totalCost};
        tableModel.addRow(rowData);
    }

    // Format date from yyyy-mm-dd to dd-mm-yyyy
    private String formatDate(String departureDate) {
        String[] parts = departureDate.split("-");
        return parts[2] + "-" + parts[1] + "-" + parts[0];
    }

    // Fetch departure time from the database based on the ticket ID
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

    // Fetch the price of the ticket from the database based on the ticket ID
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

    // Calculate total cost for a ticket type (either adult or child)
    private double calculateTotalCost(int tickets, int childTickets1, double unitPrice) {
        return tickets * unitPrice;
    }

    public static void main(String[] args) {
        new ConfirmationFrame("Wellington", "T003", "2024-12-01", 3, 2);
    }
}
