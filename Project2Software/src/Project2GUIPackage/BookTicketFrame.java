import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

public class BookTicketFrame extends JFrame implements ActionListener {

    private JLabel cityLabel;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
    private JSpinner adultTicketsSpinner, childTicketsSpinner;
    private JButton backButton, bookButton, quitButton;
    private JLabel titleLabel;

    private String selectedCity;  // Store the selected city
    private String selectedTicketID;  // Store the selected TICKET_ID

    public BookTicketFrame(String selectedCity, String selectedTicketID) {  // Pass the selected city and ticket ID to this frame
        this.selectedCity = selectedCity;  // Store the selected city
        this.selectedTicketID = selectedTicketID;  // Store the selected TICKET_ID
        // Initialize components, panels, and action listeners
        initComponents();
        initPanels();
        initActionListeners();
        setVisible(true);
    }

    public void initComponents() {
        // Initialize the components
        backButton = new JButton("BACK");
        bookButton = new JButton("Book Ticket(s)");
        quitButton = new JButton("Quit");

        backButton.setToolTipText("Go back to the search trains screen.");
        bookButton.setToolTipText("Proceed with booking the ticket.");
        quitButton.setToolTipText("Exit the system.");

        // Title label
        titleLabel = new JLabel("BOOK TICKET");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Dropdown for day, month, and year (from 1st December onwards)
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.format("%02d", i);
        }
        dayComboBox = new JComboBox<>(days);

        // Initially set the month dropdown to only show December
        monthComboBox = new JComboBox<>(new String[]{"12"});

        int currentYear = Year.now().getValue();
        String[] years = new String[10];
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear + i);  // Upcoming 10 years
        }
        yearComboBox = new JComboBox<>(years);

        // Spinner for adult and child tickets (starting from 0)
        adultTicketsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));  // Adult tickets start from 0
        childTicketsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));  // Child tickets start from 0

        // City label to show the selected city (instead of dropdown)
        cityLabel = new JLabel(selectedCity);

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

        // "To" label and the selected city label
        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(toLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(cityLabel, gbc);  // Display the selected city as a label

        // Departure date label and fields (drop-down combo boxes for date)
        JLabel dateLabel = new JLabel("Departure Date:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(dateLabel, gbc);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        datePanel.setBackground(new Color(192, 210, 238));  // Background color for panel

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(datePanel, gbc);

        // Number of adult tickets label and spinner
        JLabel adultTicketsLabel = new JLabel("Adult Tickets: (>13)");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(adultTicketsLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        add(adultTicketsSpinner, gbc);

        // Number of child tickets label and spinner
        JLabel childTicketsLabel = new JLabel("Child Tickets: (<13)  (free)");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(childTicketsLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        add(childTicketsSpinner, gbc);

        // Buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        quitButton.setBackground(Color.RED);  // Red color for quit button
        quitButton.setForeground(Color.WHITE);  // White text for visibility
        buttonPanel.add(quitButton);
        buttonPanel.add(bookButton);
        buttonPanel.setBackground(new Color(192, 210, 238));  // Background color for button panel
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    public void initActionListeners() {
        // Add action listeners for buttons
        backButton.addActionListener(e -> {
            new SearchTrainsFrame();  // Go back to the search trains screen
            setVisible(false);  // Hide current frame
        });

        // Year selection changes the available months
        yearComboBox.addActionListener(e -> {
            String selectedYear = yearComboBox.getSelectedItem().toString();
            if (selectedYear.equals("2024")) {
                // If 2024 is selected, only show December
                monthComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"12"}));
            } else {
                // For any year greater than 2024, show all months
                String[] allMonths = new String[12];
                for (int i = 1; i <= 12; i++) {
                    allMonths[i - 1] = String.format("%02d", i);
                }
                monthComboBox.setModel(new DefaultComboBoxModel<>(allMonths));
            }
        });

        // When "Book Ticket" button is clicked, open ConfirmationFrame
        bookButton.addActionListener(e -> {
            String departureDate = yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "-" + dayComboBox.getSelectedItem();
            int childTickets = (int) childTicketsSpinner.getValue();
            int adultTickets = (int) adultTicketsSpinner.getValue();

            // Validation: Ensure at least one ticket is selected
            if (childTickets == 0 && adultTickets == 0) {
                JOptionPane.showMessageDialog(this, "Please select at least one ticket.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            } else {
                // Pass the TICKET_ID, selected city, and other info to the ConfirmationFrame
                new ConfirmationFrame(selectedCity, selectedTicketID, departureDate, childTickets, adultTickets);
                setVisible(false);  // Hide the current frame
            }
        });

        quitButton.addActionListener(e -> System.exit(0));  // Exit the system
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        new BookTicketFrame("Wellington", "T003");  // Example of passing a city and ticket ID directly
    }
}
