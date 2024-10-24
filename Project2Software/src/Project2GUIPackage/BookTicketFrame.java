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

    private String selectedCity;
    private String selectedTicketID;

    public BookTicketFrame(String selectedCity, String selectedTicketID) {
        this.selectedCity = selectedCity;
        this.selectedTicketID = selectedTicketID;

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
        resetButton = new JButton("Reset");

        backButton.setToolTipText("Go back to the search trains screen.");
        bookButton.setToolTipText("Proceed with booking the ticket.");
        quitButton.setToolTipText("Exit the system.");
        resetButton.setToolTipText("Clear all selections and reset.");

        // Highlight bookButton for enhanced feedback
        bookButton.setBackground(new Color(0, 128, 0));  // Dark green color for Book button
        bookButton.setForeground(Color.WHITE);

        // Title label
        titleLabel = new JLabel("BOOK TICKET");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Dropdown for day, month, and year (from 1st December onwards)
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.format("%02d", i);
        }
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setToolTipText("Select day of departure.");

        // Initially set the month dropdown to only show December
        monthComboBox = new JComboBox<>(new String[]{"12"});
        monthComboBox.setToolTipText("Select month of departure.");

        int currentYear = Year.now().getValue();
        String[] years = new String[10];
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear + i);
        }
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setToolTipText("Select year of departure.");

        // Spinner for adult and child tickets
        adultTicketsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        adultTicketsSpinner.setToolTipText("Select number of adult tickets.");

        childTicketsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        childTicketsSpinner.setToolTipText("Select number of child tickets.");

        // City label to show the selected city
        cityLabel = new JLabel(selectedCity);

        // Set frame properties
        setTitle("AUCKLAND TRAIN BOOKING SYSTEM");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(192, 210, 238));
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
        quitButton.setForeground(Color.WHITE);
        buttonPanel.add(quitButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(resetButton);  // Added reset button
        buttonPanel.setBackground(new Color(192, 210, 238));

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gbc);
    }

    public void initActionListeners() {
        backButton.addActionListener(e -> {
            new SearchTrainsFrame();
            setVisible(false);
        });

        yearComboBox.addActionListener(e -> {
            String selectedYear = yearComboBox.getSelectedItem().toString();
            if (selectedYear.equals("2024")) {
                monthComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"12"}));
            } else {
                String[] allMonths = new String[12];
                for (int i = 1; i <= 12; i++) {
                    allMonths[i - 1] = String.format("%02d", i);
                }
                monthComboBox.setModel(new DefaultComboBoxModel<>(allMonths));
            }
        });

        bookButton.addActionListener(e -> {
            String departureDate = yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "-" + dayComboBox.getSelectedItem();
            int childTickets = (int) childTicketsSpinner.getValue();
            int adultTickets = (int) adultTicketsSpinner.getValue();

            if (childTickets == 0 && adultTickets == 0) {
                JOptionPane.showMessageDialog(this, "Please select at least one ticket.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            } else {
                new ConfirmationFrame(selectedCity, selectedTicketID, departureDate, childTickets, adultTickets);
                setVisible(false);
            }
        });

        quitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        resetButton.addActionListener(e -> {
            // Reset all inputs to their default values
            dayComboBox.setSelectedIndex(0);
            monthComboBox.setSelectedIndex(0);
            yearComboBox.setSelectedIndex(0);
            adultTicketsSpinner.setValue(0);
            childTicketsSpinner.setValue(0);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        new BookTicketFrame("Wellington", "T003");
    }
}
