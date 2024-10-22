package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchTrainsFrame extends JFrame {

    JComboBox<String> fromDropdown;
    JComboBox<String> toDropdown;
    JTextField dayField, monthField, yearField;
    String[] locations = {"Select", "Auckland", "Wellington", "Hamilton", "Tauranga", "Rotorua"};

    public SearchTrainsFrame() {
        // Set up the frame
        setTitle("Search Trains");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Back button
        JButton backButton = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(backButton, gbc);

        // Back button action to go back to the main welcome screen
        backButton.addActionListener(e -> {
            new GUIProject2();  // Go back to the welcome screen
            dispose();  // Close the current window
        });

        // Search Trains label
        JLabel titleLabel = new JLabel("SEARCH TRAINS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // From label and dropdown
        gbc.anchor = GridBagConstraints.WEST;
        JLabel fromLabel = new JLabel("From");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromLabel, gbc);

        fromDropdown = new JComboBox<>(locations);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fromDropdown, gbc);

        // To label and dropdown
        JLabel toLabel = new JLabel("To");
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(toLabel, gbc);

        toDropdown = new JComboBox<>(locations);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(toDropdown, gbc);

        // Action to remove selected location from the other dropdown
        fromDropdown.addActionListener(e -> updateToDropdown());
        toDropdown.addActionListener(e -> updateFromDropdown());

        // Departure date label
        JLabel departLabel = new JLabel("Depart on");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(departLabel, gbc);

        // Date fields (DD/MM/YYYY)
        JPanel datePanel = new JPanel();
        dayField = new JTextField("DD", 2);
        monthField = new JTextField("MM", 2);
        yearField = new JTextField("YYYY", 4);
        datePanel.add(dayField);
        datePanel.add(monthField);
        datePanel.add(yearField);

        addDateFieldListeners();  // Call the method to clear date fields on focus

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(datePanel, gbc);

        // Number of tickets label and spinner
        JLabel ticketsLabel = new JLabel("No. of tickets:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(ticketsLabel, gbc);

        JSpinner ticketSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(ticketSpinner, gbc);

        // Find Tickets button
        JButton findTicketsButton = new JButton("FIND TICKETS");
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(findTicketsButton, gbc);

        // Display the frame
        setVisible(true);
    }

    // Update the "To" dropdown by removing the selected "From" location
    private void updateToDropdown() {
        String selectedFrom = (String) fromDropdown.getSelectedItem();
        toDropdown.removeAllItems();

        for (String location : locations) {
            if (!location.equals(selectedFrom)) {
                toDropdown.addItem(location);  // Only exclude the "From" location
            }
        }
    }

    // Update the "From" dropdown by removing the selected "To" location
    private void updateFromDropdown() {
        String selectedTo = (String) toDropdown.getSelectedItem();
        fromDropdown.removeAllItems();

        for (String location : locations) {
            if (!location.equals(selectedTo)) {
                fromDropdown.addItem(location);  // Only exclude the "To" location
            }
        }
    }

    // Add listeners to date fields to remove placeholder text when focused
    private void addDateFieldListeners() {
        dayField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dayField.getText().equals("DD")) {
                    dayField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (dayField.getText().isEmpty()) {
                    dayField.setText("DD");
                }
            }
        });

        monthField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (monthField.getText().equals("MM")) {
                    monthField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (monthField.getText().isEmpty()) {
                    monthField.setText("MM");
                }
            }
        });

        yearField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (yearField.getText().equals("YYYY")) {
                    yearField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (yearField.getText().isEmpty()) {
                    yearField.setText("YYYY");
                }
            }
        });
    }

    public static void main(String[] args) {
        new SearchTrainsFrame();  // For standalone testing
    }
}
