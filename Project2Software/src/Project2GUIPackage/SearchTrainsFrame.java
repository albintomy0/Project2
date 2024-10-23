package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class SearchTrainsFrame extends JFrame {

    private JTable trainTable;
    private DefaultTableModel tableModel;
    private JButton backButton, selectTrainButton, quitButton;
    private JLabel titleLabel;

    public SearchTrainsFrame() {
        // Initialize components and set up frame
        initComponents();
        initPanels();
        initListeners();
        loadTrainDataFromDB();  // Load train data from the database
        setVisible(true);
    }

    public void initComponents() {
        // Initialize buttons
        backButton = new JButton("BACK");
        selectTrainButton = new JButton("Select Train");
        quitButton = new JButton("Quit");

        backButton.setToolTipText("Go back to the main menu.");
        selectTrainButton.setToolTipText("Select the train you want.");
        quitButton.setToolTipText("Exit the system.");

        // Title label
        titleLabel = new JLabel("AVAILABLE TRAINS", SwingConstants.CENTER); // Centering the label text
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Table setup
        String[] columnNames = {"Ticket ID", "City", "Seats", "Price", "Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        trainTable = new JTable(tableModel);
        trainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Single selection

        // Frame properties
        setTitle("AUCKLAND TRAIN BOOKING SYSTEM");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame
        getContentPane().setBackground(new Color(192, 210, 238));  // Set background color to light blue
    }

    public void initPanels() {
        // Set up layout and positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Back button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(backButton, gbc);

        // Title label - span across all columns to center
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Train table with scroll pane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;  // Span across 4 columns for the table
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(trainTable);
        add(scrollPane, gbc);

        // Select train button
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(selectTrainButton, gbc);

        // Quit button
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(quitButton, gbc);
    }

    public void initListeners() {
        // Add action listeners for buttons
        backButton.addActionListener(e -> {
            new GUIProject2();
            dispose();  // Close current window
        });

        selectTrainButton.addActionListener(e -> {
            int selectedRow = trainTable.getSelectedRow();
            if (selectedRow != -1) {
                // Get the selected city from the table
                String selectedCity = tableModel.getValueAt(selectedRow, 1).toString();
                new BookTicketFrame(selectedCity);  // Pass the selected city to the BookTicketFrame
                dispose();  // Close the current frame
            } else {
                JOptionPane.showMessageDialog(this, "Please select a train.");
            }
        });

        quitButton.addActionListener(e -> System.exit(0));  // Exit the system
    }

    // Load data from the TRAINDATA table in the Derby database
    private void loadTrainDataFromDB() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");

            // Create a statement
            stmt = conn.createStatement();

            // Execute a query to retrieve all data from the TRAINDATA table
            String sql = "SELECT * FROM TRAINDATA";
            rs = stmt.executeQuery(sql);

            // Loop through the result set and add rows to the table model
            while (rs.next()) {
                String ticketId = rs.getString("TICKET_ID");
                String city = rs.getString("CITY");
                int seatNumber = rs.getInt("SEAT_NUMBER");
                double price = rs.getDouble("PRICE");
                String time = rs.getString("TIME");

                // Add each train's data as a row in the table
                tableModel.addRow(new Object[]{ticketId, city, seatNumber, price, time});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SearchTrainsFrame();
    }
}
