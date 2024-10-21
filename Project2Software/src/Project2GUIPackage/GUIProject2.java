package Project2GUIPackage;

import javax.swing.*;

public class GUIProject2 extends JFrame {

    public GUIProject2() {
        // Set up the frame
        setTitle("Auckland Train Booking System");
        setSize(600, 400);  // Adjust size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // We will position components manually

        // Add the train image
        JLabel trainImageLabel = new JLabel(new ImageIcon(getClass().getResource("/Project2GUIPackage/train_1 (1).png")));
        trainImageLabel.setBounds(10, 10, 250, 100);  // Adjust image bounds as needed
        add(trainImageLabel);

        // Add the welcome text
        JLabel welcomeLabel = new JLabel("WELCOME to Auckland Train Booking System");
        welcomeLabel.setBounds(270, 50, 300, 30);  // Adjust the text positioning
        add(welcomeLabel);

        
                // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("What Would you like to do today?"));
        buttonPanel.setBounds(50, 150, 450, 100);  // Adjust position and size

        // Create the buttons
        JButton viewTrainsButton = new JButton("1. View Trains");
        JButton chooseDestinationButton = new JButton("2. Choose Destination");

        // Add buttons to the panel
        buttonPanel.add(viewTrainsButton);
        buttonPanel.add(chooseDestinationButton);

        // Add the button panel to the frame
        add(buttonPanel);

        
        
        // Set the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Launch the frame
        GUIProject2 guiProject2 = new GUIProject2();
    }
}

