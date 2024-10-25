package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;

public class ReceiptFrame extends JFrame {

    private JLabel destinationLabel, dateLabel, timeLabel, trainIDLabel;
    private JLabel adultTicketsLabel, childTicketsLabel, totalCostLabel;
    private JButton closeButton;

    private ReceiptModel receiptModel;  // This is the abstraction to handle the receipt data

    public ReceiptFrame(ReceiptModel receiptModel) {
        this.receiptModel = receiptModel;

        // Initialize components, panels, and action listeners
        initComponents();
        initPanels();
        initListeners();

        setVisible(true);
    }

    private void initComponents() {
        setTitle("Ticket Receipt");
        setSize(300, 430);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame
        getContentPane().setBackground(new Color(192, 210, 238));

        // Labels for displaying receipt information
        destinationLabel = new JLabel("Destination: " + receiptModel.getDestination());
        dateLabel = new JLabel("Departure Date: " + receiptModel.getDepartureDate());
        timeLabel = new JLabel("Departure Time: " + receiptModel.getDepartureTime());
        trainIDLabel = new JLabel("Train/Ticket ID: " + receiptModel.getTrainID());

        adultTicketsLabel = new JLabel("Adult Tickets: " + receiptModel.getAdultTickets());
        childTicketsLabel = new JLabel("Child Tickets: " + receiptModel.getChildTickets());

        totalCostLabel = new JLabel("Total Cost: $" + receiptModel.getTotalCost());
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalCostLabel.setForeground(Color.RED);

        // Close button
        closeButton = new JButton("CLOSE");
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.setBackground(new Color(0, 128, 0));
        closeButton.setForeground(Color.WHITE);
    }

    private void initPanels() {
        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);  // Increase insets for more spacing
        gbc.anchor = GridBagConstraints.CENTER;

        // White panel for the receipt details (with a border)
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new GridLayout(8, 1, 30, 20));  // Increase spacing between components
        receiptPanel.setBackground(Color.WHITE);
        receiptPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

        // Add the labels to the receipt panel
        receiptPanel.add(destinationLabel);
        receiptPanel.add(dateLabel);
        receiptPanel.add(timeLabel);
        receiptPanel.add(trainIDLabel);
        receiptPanel.add(adultTicketsLabel);
        receiptPanel.add(childTicketsLabel);
        receiptPanel.add(totalCostLabel);

        // Adding receipt panel to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(receiptPanel, gbc);

        // Close button
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(closeButton, gbc);
    }

    private void initListeners() {
        closeButton.addActionListener(e -> {
            System.exit(0);  // Close the application
        });
    }

    public static void main(String[] args) {
        // Example usage of ReceiptFrame
        ReceiptModel receiptModel = new ReceiptModel("Rotorua", "01-12-2024", "15:30", "T009", 2, 4, 110.0);
        new ReceiptFrame(receiptModel);
    }
}
