package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class GUIProject2 extends JFrame implements ActionListener {

    private JButton viewTrainsButton, bookTicketsButton, quitButton;
    private JLabel trainImageLabel, welcomeLabel;

    public GUIProject2() {
        // Initialize components, panels, and action listeners
        initComponents();
        initPanels();
        initActionListeners();
        setVisible(true);
    }

    public void initComponents() {
        // Initialize the buttons and labels
        viewTrainsButton = new JButton("1. View Trains");
        bookTicketsButton = new JButton("2. Book Ticket");
        quitButton = new JButton("Quit");
        viewTrainsButton.setToolTipText("View available trains and their schedules.");
        bookTicketsButton.setToolTipText("Book a train ticket.");
        quitButton.setToolTipText("Exit the system.");

        welcomeLabel = new JLabel("WELCOME to Auckland Train Booking System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18));

        // Initialize image label with the train image
        trainImageLabel = new JLabel(new ImageIcon(getClass().getResource("/Project2GUIPackage/train_1.png")));

        // Set frame properties
        setTitle("Auckland Train Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);  // Center the frame
    }

    public void initPanels() {
        // Create the north panel for the image
        JPanel northPanel = new JPanel();
        northPanel.add(trainImageLabel);
        this.add(northPanel, BorderLayout.NORTH);

        // Create the center panel for the welcome label
        JPanel centerPanel = new JPanel();
        centerPanel.add(welcomeLabel);
        this.add(centerPanel, BorderLayout.CENTER);

        // Create a panel for buttons and add them
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("What Would you like to do today?"));
        buttonPanel.add(viewTrainsButton);
        buttonPanel.add(bookTicketsButton);
        buttonPanel.add(quitButton);  // Add quit button to the main screen
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void initActionListeners() {
        // Add action listeners for the buttons
        viewTrainsButton.addActionListener(this);
        bookTicketsButton.addActionListener(this);
        quitButton.addActionListener(e -> System.exit(0));  // Exit the system
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewTrainsButton) {
            new SearchTrainsFrame();
            this.setVisible(false);  // Hide current frame
        } else if (e.getSource() == bookTicketsButton) {
            new BookTicketFrame();
            this.setVisible(false);  // Hide current frame
        }
    }

    public static void main(String[] args) {
        new GUIProject2();  // Launch the main frame
    }
}
