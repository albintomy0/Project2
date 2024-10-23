package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BookTicketFrame extends JFrame implements ActionListener {

    private JComboBox<String> toDropdown;
    private JTextField dayField, monthField, yearField;
    private JSpinner ticketsSpinner;
    private JButton backButton, bookButton, quitButton;
    private JLabel titleLabel;
    private String[] locations = {"Select", "Wellington", "Hamilton", "Tauranga", "Rotorua"};

    public BookTicketFrame() {
        // Initialize components, panels, and action listeners
        initComponents();
        initPanels();
        initActionListeners();
        setVisible(true);
    }

    public void initComponents() {
        // Initialize the components
        backButton = new JButton("BACK");
        bookButton = new JButton("BOOK TICKET(s)");
        quitButton = new JButton("Quit");

        backButton.setToolTipText("Go back to the main menu.");
        bookButton.setToolTipText("Proceed with booking the ticket.");
        quitButton.setToolTipText("Exit the system.");

        // Title label
        titleLabel = new JLabel("BOOK TICKET");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Date fields
        dayField = new JTextField("DD", 2);
        monthField = new JTextField("MM", 2);
        yearField = new JTextField("YYYY", 4);

        // Spinner for tickets
        ticketsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        // Destination dropdown
        toDropdown = new JComboBox<>(locations);

        // Set frame properties
        setTitle("AUCKLAND TRAIN BOOKING SYSTEM");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);  // Center the book ticket frame
        getContentPane().setBackground(new Color(192, 210, 238));  // Set background color
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

        // Title label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // "From" label and static value
        JLabel fromLabel = new JLabel("From:");
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromLabel, gbc);

        JLabel fromValueLabel = new JLabel("Auckland");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fromValueLabel, gbc);

        // "To" label and dropdown
        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(toLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(toDropdown, gbc);

        // Departure date label and fields
        JLabel dateLabel = new JLabel("Departure Date:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(dateLabel, gbc);

        JPanel datePanel = new JPanel();
        datePanel.add(dayField);
        datePanel.add(monthField);
        datePanel.add(yearField);
        datePanel.setBackground(new Color(192, 210, 238));  // Background color for panel
        addDateFieldListeners();

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(datePanel, gbc);

        // Number of tickets label and spinner
        JLabel ticketsLabel = new JLabel("No. of tickets:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(ticketsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(ticketsSpinner, gbc);

        // Buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(quitButton);
        buttonPanel.add(bookButton);
        buttonPanel.setBackground(new Color(192, 210, 238));  // Background color for button panel
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    public void initActionListeners() {
        // Add action listeners for buttons
        backButton.addActionListener(e -> {
            GUIProject2 welcomeFrame = new GUIProject2();
            welcomeFrame.setVisible(true);
            setVisible(false);  // Hide current frame
        });

        bookButton.addActionListener(e -> {
            // Input validation
            if (toDropdown.getSelectedIndex() == 0 || dayField.getText().equals("DD") || monthField.getText().equals("MM") || yearField.getText().equals("YYYY")) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields before booking.", "Incomplete Information", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ticket(s) booked successfully!", "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        quitButton.addActionListener(e -> System.exit(0));  // Exit the system
    }

    private void addDateFieldListeners() {
        addFocusListener(dayField, "DD");
        addFocusListener(monthField, "MM");
        addFocusListener(yearField, "YYYY");
    }

    private void addFocusListener(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                }
            }
        });
    }

    public static void main(String[] args) {
        BookTicketFrame bookTicketFrame = new BookTicketFrame(); // Launch the Book Ticket Frame
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
