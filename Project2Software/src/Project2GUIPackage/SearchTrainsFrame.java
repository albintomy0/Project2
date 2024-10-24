package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchTrainsFrame extends JFrame {

    private TrainTablePanel trainTablePanel;  // Separate class for the train table
    private SortPanel sortPanel;  // Separate class for sorting options
    private ButtonPanel buttonPanel;  // Separate class for buttons
    private JLabel statusLabel;
    private TrainDataModel trainDataModel;  // Model for database operations

    public SearchTrainsFrame() {
        trainDataModel = new TrainDataModel();  // Initialize the model

        // Initialize components
        initComponents();
        initListeners();

        // Load initial train data (sorted by city)
        loadTrainData("CITY");

        setVisible(true);
    }

    // Initialize UI components
    private void initComponents() {
        trainTablePanel = new TrainTablePanel();
        sortPanel = new SortPanel();
        buttonPanel = new ButtonPanel();

        // Status label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(new Color(0, 153, 76));

        // Frame properties
        setTitle("AUCKLAND TRAIN BOOKING SYSTEM");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame
        getContentPane().setBackground(new Color(192, 210, 238));

        // Organize the layout with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the sort panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(sortPanel, gbc);

        // Add the train table
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 3.0;
        add(trainTablePanel, gbc);

        // Add the button panel
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        add(buttonPanel, gbc);

        // Add status label
        gbc.gridy = 3;
        gbc.weighty = 0;
        add(statusLabel, gbc);
    }

    // Initialize listeners for buttons and sorting
    private void initListeners() {
        buttonPanel.getBackButton().addActionListener(e -> {
            new GUIProject2();
            dispose();
        });

        buttonPanel.getSelectTrainButton().addActionListener(e -> {
            int selectedRow = trainTablePanel.getSelectedRow();
            if (selectedRow != -1) {
                // Get selected train details
                String selectedTicketID = trainTablePanel.getValueAt(selectedRow, 0);
                String selectedCity = trainTablePanel.getValueAt(selectedRow, 1);

                // Open booking window
                new BookTicketFrame(selectedCity, selectedTicketID);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a train.");
            }
        });

        buttonPanel.getQuitButton().addActionListener(e -> System.exit(0));

        sortPanel.addSortListener(e -> {
            String selectedSortOption = sortPanel.getSelectedSortOption();
            loadTrainData(selectedSortOption);
        });
    }

    // Load train data based on sorting selection
    private void loadTrainData(String sortBy) {
        try {
            Object[][] trainData = trainDataModel.getSortedTrainData(sortBy);
            trainTablePanel.clearTable();  // Clear table before adding new rows

            for (Object[] row : trainData) {
                trainTablePanel.addRow(row);  // Add new data to the table
            }

            statusLabel.setText("Trains sorted by " + sortBy + " successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading train data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchTrainsFrame();
    }
}
