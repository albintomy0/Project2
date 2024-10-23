package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                String ticketId = tableModel.getValueAt(selectedRow, 0).toString();
                JOptionPane.showMessageDialog(this, "Train selected: " + ticketId);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a train.");
            }
        });

        quitButton.addActionListener(e -> System.exit(0));  // Exit the system

        loadTrainData();
    }

    private void loadTrainData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Resources/trains.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] trainData = line.split(",");
                tableModel.addRow(trainData);  // Add each train's data as a row in the table
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchTrainsFrame();
    }
}
