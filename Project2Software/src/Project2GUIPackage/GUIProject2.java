package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;

public class GUIProject2 extends JFrame {

    public GUIProject2() {
        // Set up the frame
        setTitle("Auckland Train Booking System");
        setSize(600, 400);  // Adjust size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  // Using BorderLayout for structured layout

        // Add the train image
        JLabel trainImageLabel = new JLabel(new ImageIcon(getClass().getResource("/Project2GUIPackage/train_1.png")));
        add(trainImageLabel, BorderLayout.NORTH);  // Position image at the top

        // Add the welcome text
        JLabel welcomeLabel = new JLabel("WELCOME to Auckland Train Booking System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18));  // Enhanced font styling
        add(welcomeLabel, BorderLayout.CENTER);  // Position in the center

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("What Would you like to do today?"));

        // Create the buttons
        JButton viewTrainsButton = new JButton("1. View Trains");
        JButton bookTicketsButton = new JButton("2. Book Ticket");  // Updated text

        // Add tooltips for better usability
        viewTrainsButton.setToolTipText("Click to view the list of available trains");
        bookTicketsButton.setToolTipText("Click to book your train ticket");  // Updated tooltip

        // Action Listener for "View Trains" button
        viewTrainsButton.addActionListener(e -> {
            SearchTrainsFrame searchTrainsFrame = new SearchTrainsFrame(); // Open the search tab
            dispose();  // Close the welcome frame
        });

        // Action Listener for "Book Ticket" button
        bookTicketsButton.addActionListener(e -> {
            BookTicketFrame bookTicketFrame = new BookTicketFrame();  // Open the Book Ticket frame
            dispose();  // Close the welcome frame
        });


        // Customize button colors for better aesthetics
        viewTrainsButton.setBackground(new Color(220, 220, 220));  // Light grey
        viewTrainsButton.setForeground(Color.BLACK);  // Black text

        bookTicketsButton.setBackground(new Color(220, 220, 220));  // Light grey
        bookTicketsButton.setForeground(Color.BLACK);  // Black text

        // Add buttons to the panel
        buttonPanel.add(viewTrainsButton);
        buttonPanel.add(bookTicketsButton);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);  // Position button panel at the bottom

        // Set the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Launch the frame
        GUIProject2 guiProject2 = new GUIProject2();
    }
}
