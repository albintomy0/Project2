package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;

public class GUIProject2 extends JFrame {

    private JButton viewTrainsButton, quitButton;

    public GUIProject2() {
        // Initialize components
        initComponents();
        initListeners();
        setVisible(true);
    }

    private void initComponents() {
        setTitle("Auckland Train Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  // Center the frame
        getContentPane().setBackground(new Color(255, 255, 255));  // Set background color to white

        // Title label
        JLabel welcomeLabel = new JLabel("WELCOME to Auckland Train Booking System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Image of the train
        ImageIcon trainImage = new ImageIcon("src/Resources/train_1.png");  // Path to your image
        JLabel imageLabel = new JLabel(trainImage);

        // Buttons
        viewTrainsButton = new JButton("1. View Trains");
        quitButton = new JButton("Quit");

        // Create a panel to hold the buttons, with FlowLayout centered
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(255, 255, 255));  // Set the same background color as the frame
        buttonPanel.add(viewTrainsButton);
        buttonPanel.add(quitButton);

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add train image at the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        // Add welcome message under the image
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(welcomeLabel, gbc);

        // "What would you like to do?" label
        JLabel actionLabel = new JLabel("What Would you like to do today?");
        actionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        add(actionLabel, gbc);

        // Add the button panel (centered buttons) under the label
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);
    }

    private void initListeners() {
        // Action for "View Trains" button to navigate to SearchTrainsFrame
        viewTrainsButton.addActionListener(e -> {
            new SearchTrainsFrame();  // Open SearchTrainsFrame
            dispose();  // Close the welcome screen
        });

        // Action for "Quit" button to close the application
        quitButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        new GUIProject2();  // Launch the welcome screen
    }
}
