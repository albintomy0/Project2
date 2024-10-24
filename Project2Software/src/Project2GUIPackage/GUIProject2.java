package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;

public class GUIProject2 extends JFrame {

    private JButton viewTrainsButton, quitButton;

    public GUIProject2() {
        // Initialize components and listeners
        initComponents();
        initListeners();
        setVisible(true);
    }

    // Method to initialize all the UI components
    private void initComponents() {
        // Set up the main frame
        setTitle("Auckland Train Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame on the screen
        getContentPane().setBackground(Color.WHITE);  // Set the background color to white

        // Title label with bold font
        JLabel welcomeLabel = new JLabel("WELCOME to Auckland Train Booking System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Try loading the train image and handle the case where the image is missing
        JLabel imageLabel;
        try {
            ImageIcon trainImage = new ImageIcon("src/Resources/train_1.png");  // Path to your image
            imageLabel = new JLabel(trainImage);
        } catch (Exception e) {
            // If the image is missing, display a placeholder message instead
            imageLabel = new JLabel("Image not available");
            imageLabel.setForeground(Color.RED);
        }

        // Initialize the buttons in a separate method for modularity
        initButtons();

        // Create a panel to hold the buttons, with FlowLayout centered
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);  // Set the same background color as the frame
        buttonPanel.add(viewTrainsButton);
        buttonPanel.add(quitButton);

        // Set up layout with GridBagLayout for positioning components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add the train image at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        // Add the welcome message under the image
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);

        // Label prompting the user for the next action
        JLabel actionLabel = new JLabel("What would you like to do today?");
        actionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        add(actionLabel, gbc);

        // Add the button panel (centered buttons) under the label
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    // Method to initialize the buttons
    private void initButtons() {
        // Initialize the "View Trains" button
        viewTrainsButton = new JButton("1. View Trains");
        viewTrainsButton.setToolTipText("Click to view available trains");

        // Initialize the "Quit" button
        quitButton = new JButton("Quit");
        quitButton.setToolTipText("Click to exit the system");
    }

    // Method to initialize listeners for buttons
    private void initListeners() {
        // Listener for the "View Trains" button
        viewTrainsButton.addActionListener(e -> {
            new SearchTrainsFrame();  // Open SearchTrainsFrame when clicked
            dispose();  // Close the welcome screen
        });

        // Listener for the "Quit" button
        quitButton.addActionListener(e -> System.exit(0));  // Close the application when clicked
    }

    // Entry point to launch the GUI
    public static void main(String[] args) {
        // Launch the main frame
        new GUIProject2();
    }
}
