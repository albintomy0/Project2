package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

public class BookTicketFrame extends JFrame implements ActionListener {

    private JLabel cityLabel;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
    private JSpinner adultTicketsSpinner, childTicketsSpinner;
    private JButton backButton, bookButton, quitButton, resetButton;
    private JLabel titleLabel;
    private TicketBookingModel bookingModel;  // Model for ticket booking data

    private String selectedCity;
    private String selectedTicketID;

    public BookTicketFrame(String selectedCity, String selectedTicketID) {
        this.selectedCity = selectedCity;
        this.selectedTicketID = selectedTicketID;
        this.bookingModel = new TicketBookingModel();  // Initialize the model

        // Initialize components, panels, and action listeners
        initComponents();
        initPanels();
        initActionListeners();
        setVisible(true);
    }

    // Method to initialize the UI components
    public void initComponents() {
        // Initialize buttons
        backButton = new JButton("BACK");
        bookButton = new JButton("Book Ticket(s)");
        quitButton = new JButton("Quit");
        resetButton = new JButton("Reset");

        // Tooltips to help the user understand the button actions
        backButton.setToolTipText("Go back to the search trains screen.");
        bookButton.setToolTipText("Proceed with booking the ticket.");
        quitButton.setToolTipText("Exit the system.");
        resetButton.setToolTipText("Clear all selections and reset.");

        // Set the Book button color for enhanced feedback
        bookButton.setBackground(new Color(0, 128, 0));
        bookButton.setForeground(Color.WHITE);

        // Title label for the frame
        titleLabel = new JLabel("BOOK TICKET");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Initialize day combo box with days 1-31
        dayComboBox = createDayComboBox();

        // Initialize month combo box with December by default
        monthComboBox = new JComboBox<>(new String[]{"12"});
        monthComboBox.setToolTipText("Select month of departure.");

        // Initialize year combo box with current year and the next 9 years
        yearComboBox = createYearComboBox();
        
        // Initialize ticket spinners for adults and children
        adultTicketsSpinner = createTicketSpinner("Select number of adult tickets.");
        childTicketsSpinner = createTicketSpinner("Select number of child tickets.");

        // Label to display the selected city
        cityLabel = new JLabel(selectedCity);

        // Frame properties
        setTitle("AUCKLAND TRAIN BOOKING SYSTEM");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(192, 210, 238));  // Set background color
    }

    // Method to set up the layout and positioning of UI components
    public void initPanels() {
        // Set up layout and positioning using GridBagLayout
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

        // "From" label and static value for departure
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
        add(cityLabel, gbc);

        // Departure date label and fields
        JLabel dateLabel = new JLabel("Departure Date:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(dateLabel, gbc);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dayComboBox);
        datePanel.add(monthComboBox);
        datePanel.add(yearComboBox);
        datePanel.setBackground(new Color(192, 210, 238));

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
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);  // Ensure visibility of the text
        buttonPanel.add(quitButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(resetButton);
        buttonPanel.setBackground(new Color(192, 210, 238));  // Same background color as frame

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    // Initialize action listeners for buttons and combo boxes
    public void initActionListeners() {
        backButton.addActionListener(e -> {
            new SearchTrainsFrame();
            setVisible(false);
        });

        yearComboBox.addActionListener(e -> handleYearChange());

        bookButton.addActionListener(e -> handleBooking());

        quitButton.addActionListener(e -> handleQuit());

        resetButton.addActionListener(e -> resetForm());
    }

    // Handle year changes for the year combo box
    private void handleYearChange() {
        String selectedYear = yearComboBox.getSelectedItem().toString();
        if (selectedYear.equals("2024")) {
            monthComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"12"}));
        } else {
            monthComboBox.setModel(new DefaultComboBoxModel<>(bookingModel.getAllMonths()));
        }
    }

    // Handle the booking process
    private void handleBooking() {
        String departureDate = yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "-" + dayComboBox.getSelectedItem();
        int childTickets = (int) childTicketsSpinner.getValue();
        int adultTickets = (int) adultTicketsSpinner.getValue();

        if (childTickets == 0 && adultTickets == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one ticket.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        } else {
            new ConfirmationFrame(selectedCity, selectedTicketID, departureDate, childTickets, adultTickets);
            setVisible(false);
        }
    }

    // Handle quit action with confirmation
    private void handleQuit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Reset the form to its default state
    private void resetForm() {
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        adultTicketsSpinner.setValue(0);
        childTicketsSpinner.setValue(0);
    }

    // Helper method to create day combo box
    private JComboBox<String> createDayComboBox() {
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.format("%02d", i);
        }
        JComboBox<String> comboBox = new JComboBox<>(days);
        comboBox.setToolTipText("Select day of departure.");
        return comboBox;
    }

    // Helper method to create year combo box
    private JComboBox<String> createYearComboBox() {
        int currentYear = Year.now().getValue();
        String[] years = new String[10];
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear + i);
        }
        JComboBox<String> comboBox = new JComboBox<>(years);
        comboBox.setToolTipText("Select year of departure.");
        return comboBox;
    }

    // Helper method to create ticket spinners
    private JSpinner createTicketSpinner(String toolTip) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        spinner.setToolTipText(toolTip);
        return spinner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        new BookTicketFrame("Wellington", "T003");
    }
}
