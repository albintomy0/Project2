package Project2GUIPackage;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class SearchTrainsFrame extends JFrame {

    private JTable trainTable;
    private DefaultTableModel tableModel;
    private JButton backButton, selectTrainButton, quitButton;
    private JComboBox<String> sortComboBox;  // Drop-down for sorting options
    private JLabel titleLabel, sortLabel;

    public SearchTrainsFrame() {
        // Initialize components and set up frame
        initComponents();
        initPanels();
        initListeners();
        loadTrainDataFromDB("CITY");  // Load train data sorted by city by default
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

        // ComboBox for sorting
        sortComboBox = new JComboBox<>(new String[]{"City", "Price", "Time"});  // Sort options
        sortLabel = new JLabel("Sort by:");

        // Table setup
        String[] columnNames = {"Train ID", "City", "Seats", "Price", "Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        trainTable = new JTable(tableModel);
        trainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Single selection

        // Frame properties
        setTitle("AUCKLAND TRAIN BOOKING SYSTEM");
        setSize(600, 450);  // Adjusted to accommodate sort options
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

        // Sort label and combo box
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(sortLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(sortComboBox, gbc);

        // Train table with scroll pane
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;  // Span across 4 columns for the table
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollPane = new JScrollPane(trainTable);
        add(scrollPane, gbc);

        // Select train button
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(selectTrainButton, gbc);

        // Quit button
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(quitButton, gbc);
        quitButton.setBackground(new Color(255, 102, 102));  // Light red for "Quit"
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
                // Get the selected train details (TICKET_ID and City) from the table
                String selectedTicketID = tableModel.getValueAt(selectedRow, 0).toString();  // Get TICKET_ID
                String selectedCity = tableModel.getValueAt(selectedRow, 1).toString();  // Get City

                // Pass the selected TICKET_ID and City to the BookTicketFrame
                new BookTicketFrame(selectedCity, selectedTicketID);
                dispose();  // Close the current frame
            } else {
                JOptionPane.showMessageDialog(this, "Please select a train.");
            }
        });

        quitButton.addActionListener(e -> System.exit(0));  // Exit the system

        // Add action listener for sorting
        sortComboBox.addActionListener(e -> {
            String selectedSortOption = sortComboBox.getSelectedItem().toString().toUpperCase();
            loadTrainDataFromDB(selectedSortOption);  // Reload data based on the selected sort option
        });
    }

    // Load data from the TRAINDATA table in the Derby database, with sorting based on the parameter
    private void loadTrainDataFromDB(String sortBy) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/DBGUI2", "app", "app");

            // Create a statement
            stmt = conn.createStatement();

            // Sort by city, price, or time based on the user's choice
            String sql = "SELECT * FROM TRAINDATA ORDER BY " + sortBy;
            rs = stmt.executeQuery(sql);

            // Clear the current table data
            tableModel.setRowCount(0);

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
